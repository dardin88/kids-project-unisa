// FUNZIONI DI INIZIALIZZAZIONE DEI COMPONENTI DELLA PAGINA RIGUARDANTI
//  LA GESTIONE DEI CRITERI DI VALUTAZIONE
function initCriteriaWindow() {
    
    if(getValue("user") == "Segreteria") {  // il pulsante è da inizializzare solo se l'utente è 'Segreteria'
        $("#classificationOpenCriteriaWindow").button();
    }
    
    $("#classificationAddCriterionWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#classificationAddCriterion").button();
    $("#classificationCloseCriteriaButton").button();
    
    $("#classificationAddCriterionSubmit").button();
    
    $("#classificationAddCriterionUndoSubmit").button();
    $("#classificationAddCriterionUndoSubmit").click(function() {
        setWindowVisibility("classificationAddCriterionWindow", false);
        // svuoto i campi della form "classificationAddWindowForm"
        getElement("classificationNewCriterionDescrizione").value = "";
        getElement("selectCampoEmpty").selected = true;
        getElement("selectOperandoEmpty").selected = true;
        getElement("classificationNewCriterionCondizione").value = "";
        getElement("classificationNewCriterionPeso").value = "";
    });
    
    createTableCriteria();
}
// FINE FUNZIONI DI INIZIALIZZAZIONE
// -----------------------------------------------------------------------------
// FUNZIONI LEGATE ALLA TABELLA DEI CRITERI
function createTableCriteria() {
        $('#classificationCriteriaTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableCriteria",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "Non sono presenti criteri di valutazione.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Criteri",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Criteri",
            "sInfoFiltered": "(filtrati da _MAX_ criteri totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "aoColumns": [
        {
            "sWidth": "20%",
            "sClass": "center"
        },
        {
            "sWidth": "15%",
            "sClass": "center"
        },
        {
            "sWidth": "7%",
            "sClass": "center"
        },
        {
            "sWidth": "20%",
            "sClass": "center"
        },
        {
            "sWidth": "15%",
            "sClass": "center"
        },
        {
            "sWidth": "15%",
            "sClass": "center"
        },
        {
            "sWidth": "8%",
            "sClass": "center"
        }],
         "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });  
}
function updateCriteriaTable() {
    var oTable = $("#classificationCriteriaTable").dataTable();
    oTable.fnDraw();
}
// FINE FUNZIONI LEGATE ALLA TABELLA DEI CRITERI
// -----------------------------------------------------------------------------
// FUNZIONI UTILIZZATE DAI PULSANTI
function openCriteriaWindow() {
    setVisibility("classificationContentPage", false);
    updateCriteriaTable();
    setVisibility("classificationCriteriaWindow", true);
}
function closeCriteriaWindow() {
    setVisibility("classificationCriteriaWindow", false);
    setVisibility("classificationContentPage", true);
}
function openAddCriteriaWindow() {
    setWindowVisibility("classificationAddCriterionWindow", true);
}
function addCriterion() {
    var param = ["classificationNewCriterionDescrizione", "classificationNewCriterionCampo", 
            "classificationNewCriterionOperando", "classificationNewCriterionCondizione", 
            "classificationNewCriterionPeso"];
    if(valida(param)) {
        comunicaConServlet("AddCriterion", {
                    Descrizione: getValue("classificationNewCriterionDescrizione"),
                    CampoDb: getValue("classificationNewCriterionCampo"),
                    Operando: getValue("classificationNewCriterionOperando"),
                    Condizione: getValue("classificationNewCriterionCondizione"),
                    Peso: getValue("classificationNewCriterionPeso")
                }, function(result) {
                    if(result.IsSuccess) {
                        updateCriteriaTable();
                        openClassificationAlertWindow("Operazione completata", "Il criterio è stata inserito");
                    } else {
                        openClassificationAlertWindow("Operazione non riuscita", "Si è verificato il seguente errore:" + newLine() +
                                result.ErrorMsg);
                    }
                    closeClassificationAddWindow();
                }
            );
    } else {
        //openClassificationAlertWindow("Campi mancanti", "Compilare il campo nome!");
    }
}
function deleteCriteriaWindow(id) {
    var actionOnConfirm = function() {
        comunicaConServlet(
        "DeleteCriterion", 
        {
            Id:id
        }, 
        function(jsonObject) {
            if(jsonObject.IsSuccess) {
                updateCriteriaTable();
                openClassificationAlertWindow("Operazione riuscita", "Il criterio è stato eliminato!")
            } else {
                openClassificationAlertWindow("Errore nell'eliminazione", jsonObject.ErrorMsg);
            }
        });
    }
    openClassificationConfirmWindow("Conferma operazione", "Sei sicuro di voler eliminare il criterio?", actionOnConfirm)
}
function changeCriterionActive(id, isActive) {
    comunicaConServlet("ModifyCriterion", {
            Id: id,
            Abilitato: isActive
        }, function(result) {
            /*
            if(result.IsSuccess) {
               openClassificationAlertWindow("Operazione completata", "La modifica è stata memorizzata");
            } else {
                openClassificationAlertWindow("Operazione non riuscita", "Si è verificato il seguente errore:" + newLine() +
                        result.ErrorMsg);
            }
            //*/
        }
    );
}