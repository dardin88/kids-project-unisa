function initializeRegistrationFields(){
    $.ajaxSetup({
        cache: false
    });
    
    $.validator.setDefaults({
        highlight: function(input){
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input){
            $(input).removeClass("ui-state-highlight");
        }
    });
    
    jQuery.validator.addMethod("codfiscale", function(value) { 
        // espressione migliorabile... ma sufficiente per il nostro esempio
        var regex = /[A-Z]{6}[\d]{2}[A-Z][\d]{2}[A-Z][\d]{3}[A-Z]/;  
        value =value.toUpperCase();
        return value. match(regex);  
    }, "Please insert a valid italian identification number");
    
    $("#registrationChildFormWindowForm").validate({
        rules:
        {
            registrationChildFormWindowCognome:{
                required:true
            },
            registrationChildFormWindowNome:{
                required:true
            },
            registrationChildFormWindowDataNascita:{
                required:true,
                date:true
            },
            registrationChildFormWindowComuneNascita:{
                required:true
            },
            registrationChildFormWindowCodiceFiscale:{
                required:true,
                codfiscale:true
            },
            registrationChildFormWindowCittadinanza:{
                required:true
            }
        },
        messages:{
            registrationChildFormWindowCognome:{
                required: "Cognome obbligatorio"
            },
            registrationChildFormWindowNome:{
                required: "Nome obbligatorio"
            },
            registrationChildFormWindowDataNascita:{
                required: "Data nascita obbligatoria",
                date: "Formato non valido"
            },
            registrationChildFormWindowComuneNascita:{
                required: "Comune nascita obbligatorio"
            },
            registrationChildFormWindowCodiceFiscale:{
                required: "Codice Fiscale obbligatorio",
                codfiscale: "Formato non valido"
            },
            registrationChildFormWindowCittadinanza:{
                required: "Inserisci cittadinanza"
            }
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
    initRegistrationChildFormWindow();
    initRegistrationChildConfirmWindow();
    initRegistrationChildAlertWindow();
}

function createTableRegistrationChild() {
    $('#registrationChildTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableRegistrationChild",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": true,
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "Non sono presenti domande di iscrizione",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Domande d'iscrizione",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Domande d'iscrizione",
            "sInfoFiltered": "(filtrati da _MAX_ domande d'iscrizione totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca domanda d'iscrizione:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "aoColumns": [
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "22%"
        },
        {
            "sWidth": "22%"
        },
        {
            "sWidth": "16%"
        },
        {
            "sWidth":"15%",
            "sClass": "center"
        }
        ],
        "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });  
}

function search(){
//    var oTable = $("#traineesTable").dataTable();
//    oTable.fnDraw();
}

// FUNZIONI UTILIZZATE DAI PULSANTI DELLA FORM-WINDOW


function createNewDraftRegistrationChildAction() {
     
    
    $("#registrationChildFormWindowForm").validate({
        rules:{
        
        },
        messages:{
            
        },
        submitHandler: function() {
            comunicaConServlet(
                // Nome della Servlet
                "CreateDraftRegistrationChild", 
                {
                    // Parametri da inviare alla servlet
                    Cognome : getValue("registrationChildFormWindowCognome"),
                    Nome    : getValue("registrationChildFormWindowNome"),
                    DataNascita : getValue("registrationChildFormWindowDataNascita"),
                    ComuneNascita : getValue("registrationChildFormWindowComuneNascita"),
                    CodiceFiscale : getValue("registrationChildFormWindowCodiceFiscale"),
                    Cittadinanza : getValue("registrationChildFormWindowCittadinanza"),
                    FasciaUtenza : getValue("registrationChildFormWindowFasciaUtenza")
                }, 
                function(jsonObject) {
                    // Funzione eseguita se la richiesta alla servlet è eseguita con successo
                    setWindowVisibility("registrationChildFormWindow", false);
                    if(jsonObject.IsSuccess) {
                        openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La domanda di iscrizione è stata memorizzata");
                    } else {
                        openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                    }
                }, 
                function(errorMsg) {
                    // Funzione eseguita se la richiesta alla servlet ha presentato errori
                    setWindowVisibility("registrationChildFormWindow", false);
                    //alert("error function: ");
                    openRegistrationChildAlertWindow("Errore", errorMsg);
                }
                );
        }
    });
}
function saveEditDraftRegistrationChildAction() {
    $("#registrationChildFormWindowForm").validate({
        rules: {
        },
        messages: {
        },
        submitHandler: function() {
            comunicaConServlet(
                // Nome della Servlet
                "EditRegistrationChild", 
                {
                    // Parametri da inviare alla servlet
                    Id : getValue("registrationChildFormWindowId"),
                    DataIscrizione : getValue("registrationChildFormWindowDataIscrizione"),
                    Cognome : getValue("registrationChildFormWindowCognome"),
                    Nome    : getValue("registrationChildFormWindowNome"),
                    DataNascita : getValue("registrationChildFormWindowDataNascita"),
                    ComuneNascita : getValue("registrationChildFormWindowComuneNascita"),
                    CodiceFiscale : getValue("registrationChildFormWindowCodiceFiscale"),
                    Cittadinanza : getValue("registrationChildFormWindowCittadinanza"),
                    FasciaUtenza : getValue("registrationChildFormWindowFasciaUtenza")
                }, 
                function(jsonObject) {
                    // Funzione eseguita se la richiesta alla servlet è eseguita con successo
                    setWindowVisibility("registrationChildFormWindow", false);
                    if(jsonObject.IsSuccess) {
                        openRegistrationChildAlertWindow("Operazione eseguita correttamente", "Le modifiche apportate alla bozza della domanda di iscrizione sono state salvata");
                    } else {
                        openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                    }
                }, 
                function(errorMsg) {
                    // Funzione eseguita se la richiesta alla servlet ha presentato errori
                    setWindowVisibility("registrationChildFormWindow", false);
                    //alert("error function: ");
                    openRegistrationChildAlertWindow("Errore", errorMsg);
                }
                );
        }
    });
}
function submitDraftRegistrationChildAction() {
    //alert($("#registrationChildFormWindowFasciaUtenza").val());
    setRegistrationChildFormEditable(true);
    $("#registrationChildFormWindowForm").validate({
        rules: {
            registrationChildFormWindowCognome: {
                required: true
            },
            registrationChildFormWindowNome: {
                required: true
            },
            registrationChildFormWindowDataNascita:  {
                required: true
            },
            registrationChildFormWindowComuneNascita:  {
                required: true
            },
            registrationChildFormWindowCodiceFiscale:  {
                required: true
            },
            registrationChildFormWindowCittadinanza:  {
                required: true
            },
            registrationChildFormWindowFasciaUtenza:  {
                required: true
            }
        },
        messages: {
            registrationChildFormWindowCognome: {
                required: ""
            },
            registrationChildFormWindowNome: {
                required: ""
            },
            registrationChildFormWindowDataNascita:  {
                required: ""
            },
            registrationChildFormWindowComuneNascita:  {
                required: ""
            },
            registrationChildFormWindowCodiceFiscale:  {
                required: ""
            },
            registrationChildFormWindowCittadinanza:  {
                required: ""
            },
            registrationChildFormWindowFasciaUtenza:  {
                required: ""
            }
        },
        invalidHandler: function() {
            setRegistrationChildFormEditable(false);
            openRegistrationChildAlertWindow("Impossibile effettuare la sottomissione", "Alcuni campi della bozza sono ancora vuoti!" + newLine() + 
                "Modificare la bozza e riempire tutti i campi");
        },
        submitHandler: function() {
            comunicaConServlet(
                // Nome della Servlet
                "SubmitNewRegistrationChild", 
                {
                    // Parametri da inviare alla servlet
                    Id : getValue("registrationChildFormWindowId"),
                    Cognome : getValue("registrationChildFormWindowCognome"),
                    Nome    : getValue("registrationChildFormWindowNome"),
                    DataNascita : getValue("registrationChildFormWindowDataNascita"),
                    ComuneNascita : getValue("registrationChildFormWindowComuneNascita"),
                    CodiceFiscale : getValue("registrationChildFormWindowCodiceFiscale"),
                    Cittadinanza : getValue("registrationChildFormWindowCittadinanza"),
                    FasciaUtenza : getValue("registrationChildFormWindowFasciaUtenza")
                }, 
                function(jsonObject) {
                    // Funzione eseguita se la richiesta alla servlet è eseguita con successo
                    setWindowVisibility("registrationChildFormWindow", false);
                    if(jsonObject.IsSuccess) {
                        openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La domanda di iscrizione è stata sottomessa");
                        location.href = "PdfRegistrationChild?Id=" + getValue("registrationChildFormWindowId");
                    } else {
                        openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                    }
                }, 
                function(errorMsg) {
                    // Funzione eseguita se la richiesta alla servlet ha presentato errori
                    setWindowVisibility("registrationChildFormWindow", false);
                    //alert("error function: ");
                    openRegistrationChildAlertWindow("Errore", errorMsg);
                }
                );
        }
    });
    
}
function completeDraftRegistrationChildAction() {
    $("#registrationChildFormWindowForm").validate({
        rules: {
            /*    registrationChildFormWindowMalattie: {required: true},
                registrationChildFormWindowVaccinazioni: {required: true},*/
            registrationChildFormWindowDichiarazionePrivacy:  {
                required: true
            }
        },
        messages: {
            /*    registrationChildFormWindowMalattie: {required: "Inserire le malattie (oppure Nessuna)"},
                registrationChildFormWindowVaccinazioni: {required: "Inserire le vaccinazioni (oppure Nessuna)"},*/
            registrationChildFormWindowDichiarazionePrivacy:  {
                required: "Selezionare la risposta"
            }
        },
        submitHandler: function() {
            comunicaConServlet(
                // Nome della Servlet
                "CompleteRegistrationChild", 
                {
                    // Parametri da inviare alla servlet
                    Id : getValue("registrationChildFormWindowId"),
                    Malattie : getValue("registrationChildFormWindowMalattie"),
                    Vaccinazioni    : getValue("registrationChildFormWindowVaccinazioni"),
                    DichiarazioneDellaPrivacy : getValue("registrationChildFormWindowDichiarazionePrivacy")
                }, 
                function(jsonObject) {
                    // Funzione eseguita se la richiesta alla servlet è eseguita con successo
                    setWindowVisibility("registrationChildFormWindow", false);
                    if(jsonObject.IsSuccess) {
                        openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La domanda di iscrizione è stata completata");
                    } else {
                        openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                    }
                }, 
                function(errorMsg) {
                    // Funzione eseguita se la richiesta alla servlet ha presentato errori
                    setWindowVisibility("registrationChildFormWindow", false);
                    //alert("error function: ");
                    openRegistrationChildAlertWindow("Errore", errorMsg);
                }
                );
        }
    });
}
function confirmCompletingDraftRegistrationChildAction() {
    $("#registrationChildFormWindowForm").validate({
        rules: {
            registrationChildFormWindowIsSetMalattie: {
                required: true
            },
            registrationChildFormWindowIsSetVaccinazioni: {
                required: true
            },
            registrationChildFormWindowIsSetDichiarazionePrivacy:  {
                required: true
            }
        },
        messages: {
            registrationChildFormWindowIsSetMalattie: {
                required: "Selezionare la risposta"
            },
            registrationChildFormWindowIsSetVaccinazioni: {
                required: "Selezionare la risposta"
            },
            registrationChildFormWindowIsSetDichiarazionePrivacy: {
                required: "Selezionare la risposta"
            }
        },
        submitHandler: function() {
            comunicaConServlet(
                // Nome della Servlet
                "ConfirmCompletingRegistrationChild", 
                {
                    // Parametri da inviare alla servlet
                    Id : getValue("registrationChildFormWindowId"),
                    IsSetMalattie : getValue("registrationChildFormWindowIsSetMalattie"),
                    IsSetVaccinazioni    : getValue("registrationChildFormWindowIsSetVaccinazioni"),
                    IsSetDichiarazioneDellaPrivacy : getValue("registrationChildFormWindowIsSetDichiarazionePrivacy")
                }, 
                function(jsonObject) {
                    // Funzione eseguita se la richiesta alla servlet è eseguita con successo
                    setWindowVisibility("registrationChildFormWindow", false);
                    if(jsonObject.IsSuccess) {
                        openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La domanda di iscrizione è stata validata");
                    } else {
                        openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                    }
                }, 
                function(errorMsg) {
                    // Funzione eseguita se la richiesta alla servlet ha presentato errori
                    setWindowVisibility("registrationChildFormWindow", false);
                    //alert("error function: ");
                    openRegistrationChildAlertWindow("Errore", errorMsg);
                }
                );
        }
    });
}
// FUNZIONI CHE NECESSITANO DI UNA FORM-WINDOW
function openInsertNewRegistrationChildWindow() {
    setVisibility("registrationChildRegistrationInfo", false);
    setVisibility("registrationChildStandardField", true);
    if(getValue("user") == "Segreteria") {
        setVisibility("registrationChildSegretaryField", false);
    }
    openRegistrationChildFormWindow("modifyDraft", "Visuale di inserimento di una bozza di domanda di iscrizione", null);
}
function openModifyRegistrationChildWindow(id) {
    setVisibility("registrationChildRegistrationInfo", true);
    setVisibility("registrationChildStandardField", true);
    if(getValue("user") == "Segreteria") {
        setVisibility("registrationChildSegretaryField", false);
    }
    openRegistrationChildFormWindow("modifyDraft", "Visuale di modifica di una bozza di domanda di iscrizione", id);
}
function openViewDetailsRegistrationChildWindow(id) {
    setVisibility("registrationChildRegistrationInfo", true);
    setVisibility("registrationChildStandardField", true);
    if(getValue("user") == "Segreteria") {
        setVisibility("registrationChildSegretaryField", false);
    }
    openRegistrationChildFormWindow("viewDraft", "Dettagli della domanda di iscrizione", id, true);
}
function openCompleteRegistrationChildWindow(id) {
    setVisibility("registrationChildRegistrationInfo", true);
    setVisibility("registrationChildStandardField", false);
    if(getValue("user") == "Segreteria") {
        setVisibility("registrationChildSegretaryField", false);
    }
    openRegistrationChildFormWindow("modifyDraft", "Visuale di completamento della domanda di iscrizione", id);
}
function openComfirmReceivingRegistrationChildWindow(id) {
    setVisibility("registrationChildRegistrationInfo", true);
    setVisibility("registrationChildStandardField", true);
    if(getValue("user") == "Segreteria") {
        setVisibility("registrationChildSegretaryField", false);
    }
    openRegistrationChildFormWindow("viewDraft", "Visuale di conferma della ricezione della domanda di iscrizione", id);
}
function openComfirmCompletingRegistrationChildWindow(id) {
    setVisibility("registrationChildRegistrationInfo", true);
    setVisibility("registrationChildStandardField", false);
    if(getValue("user") == "Segreteria") {
        setVisibility("registrationChildSegretaryField", true);
    }
    openRegistrationChildFormWindow("modifyDraft", "Visuale di conferma del completamento della domanda di iscrizione", id);
}
// FUNZIONI CHE NECESSITANO DI UNA CONFIRM-WINDOW
function openDeleteRegistrationChildWindow(id) {
    var title = "Conferma operazione";
    var text = "Sei sicuro di voler eliminare la domanda di iscrizione";
    var confirmAction = function() {
        comunicaConServlet(
            // Nome della Servlet
            "EditPhaseRegistrationChild", 
            {
                // Parametri da inviare alla servlet
                Id: id,
                FaseDellIscrizione: "eliminata"
            }, 
            function(jsonObject) {
                // Funzione eseguita se la richiesta alla servlet è eseguita con successo
                setWindowVisibility("registrationChildFormWindow", false);
                if(jsonObject.IsSuccess) {
                    openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La domanda di iscrizione è stata eliminata");
                } else {
                    openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                }
            }, 
            function(errorMsg) {
                // Funzione eseguita se la richiesta alla servlet ha presentato errori
                setWindowVisibility("registrationChildFormWindow", false);
                openRegistrationChildAlertWindow("Errore", errorMsg);
            }
            );
    }
    openRegistrationChildConfirmWindow(title, text, id, confirmAction, null);
}
function confirmReceivingRegistrationChildWindow(id) {
    var title = "Conferma operazione";
    var text = "Sei sicuro di voler confermare la ricezione della domanda di iscrizione";
    var confirmAction = function() {
        comunicaConServlet(
            // Nome della Servlet
            "EditPhaseRegistrationChild", 
            {
                // Parametri da inviare alla servlet
                Id: id,
                FaseDellIscrizione: "ricevuta"
            }, 
            function(jsonObject) {
                // Funzione eseguita se la richiesta alla servlet è eseguita con successo
                setWindowVisibility("registrationChildFormWindow", false);
                if(jsonObject.IsSuccess) {
                    openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La domanda di iscrizione è stata memorizzata come ricevuta");
                } else {
                    openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                }
            }, 
            function(errorMsg) {
                // Funzione eseguita se la richiesta alla servlet ha presentato errori
                setWindowVisibility("registrationChildFormWindow", false);
                openRegistrationChildAlertWindow("Errore", errorMsg);
            }
            );
    }
    openRegistrationChildConfirmWindow(title, text, id, confirmAction, null);
}
// FUNZIONI PER L'UTILIZZO DELLA FORM-WINDOW
function initRegistrationChildFormWindow() {
    $("#registrationChildFormWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    // imposta il campo come inserimento della data dal calendario
    $("#registrationChildFormWindowDataNascita").datepicker({
        dateFormat:'yy-mm-dd'
    });
    
    getElement("registrationChildFormWindowId").disabled = true;
    getElement("registrationChildFormWindowDataIscrizione").disabled = true;
    getElement("registrationChildFormWindowFaseIscrizione").disabled = true;
    
    $("#registrationChildFormWindowUndoButton").button();
    $("#registrationChildFormWindowUndoButton").click(function(){
        setWindowVisibility("registrationChildFormWindow", false);
        clearFieldRegistrationChildFormWindow();
    // test: alert("sono in annulla");
    //location.href = "./registrationChild.jsp";
    });
}
function openRegistrationChildFormWindow(windowType, title, idToLoad) {
    $("#registrationChildFormWindow").dialog({
        title: title
    });
    
    if(windowType == "modifyDraft") {
        setRegistrationChildFormEditable(true);
    } else if(windowType == "viewDraft") {
        setRegistrationChildFormEditable(false);
    }
    
    if(idToLoad != null) {
        comunicaConServlet(
            // Nome della Servlet
            "GetRegistrationChild", 
            {
                // Parametri da inviare alla servlet
                Id: idToLoad
            }, 
            function(jsonObject) {
                if(jsonObject.IsSuccess) {
                    // Funzione eseguita se la richiesta alla servlet è eseguita con successo
                    fillInFieldRegistrationChildFormWindow(jsonObject);
                    changeButtonVisibility(jsonObject.FaseDellIscrizione, windowType);
                    var fase = jsonObject.FaseDellIscrizione;
                    if((getValue("user") &&((windowType == "viewDraft" && (fase == "validata" || fase == "completata")) || (windowType == "modifyDraft" && fase == "accettata")))) {
                        setVisibility("registrationChildAdvancedField", true);
                    } else {
                        setVisibility("registrationChildAdvancedField", false);
                    }
                    if(getValue("user") == "Segreteria" && fase == "completata") {
                        changeRegistrationChildAdvancedFieldState(true, true);
                    }
                //openRegistrationChildAlertWindow("Caricamento riuscito", "I dati sono stati caricati correttamente");
                } else {
                    openRegistrationChildAlertWindow("Errore", jsonObject.ErrorMsg);
                }
            }, 
            function(errorMsg) {
                // Funzione eseguita se la richiesta alla servlet ha presentato errori
                setWindowVisibility("registrationChildFormWindow", false);
                openRegistrationChildAlertWindow("Errore", errorMsg);
            }
            );
    } else {
        clearFieldRegistrationChildFormWindow();
        changeButtonVisibility("", windowType);
        setVisibility("registrationChildAdvancedField", false);
    }
    
    setWindowVisibility("registrationChildFormWindow", true);
}
function fillInFieldRegistrationChildFormWindow(registrationChildObject) {
    getElement("registrationChildFormWindowId").value = registrationChildObject.Id;

    getElement("registrationChildFormWindowDataIscrizione").value = registrationChildObject.DataIscrizione;
    getElement("registrationChildFormWindowFaseIscrizione").value = registrationChildObject.FaseDellIscrizione;
    // Non utilizzo il valore di parentAccountId

    getElement("registrationChildFormWindowCognome").value = registrationChildObject.Cognome;
    getElement("registrationChildFormWindowNome").value = registrationChildObject.Nome;
    getElement("registrationChildFormWindowDataNascita").value = registrationChildObject.DataNascita;
    getElement("registrationChildFormWindowComuneNascita").value = registrationChildObject.ComuneNascita;
    getElement("registrationChildFormWindowCodiceFiscale").value = registrationChildObject.CodiceFiscale;
    getElement("registrationChildFormWindowCittadinanza").value = registrationChildObject.Cittadinanza;
    if(registrationChildObject.FasciaUtenza == "full_time") {
        getElement("registrationChildSelectUserRangeFullTime").selected = true;
    } else if(registrationChildObject.FasciaUtenza == "part_time_mattutina") {
        getElement("registrationChildSelectUserRangePartTimeAM").selected = true;
    } else if(registrationChildObject.FasciaUtenza == "part_time_pomeridiana") {
        getElement("registrationChildSelectUserRangePartTimePM").selected = true;
    } else {
        getElement("registrationChildSelectUserRangeEmpty").selected = true;
    }
    
    getElement("registrationChildFormWindowMalattie").value = registrationChildObject.Malattie;
    getElement("registrationChildFormWindowVaccinazioni").value = registrationChildObject.Vaccinazioni;
    getElement("registrationChildFormWindowDichiarazionePrivacy").value = registrationChildObject.DichiarazioneDellaPrivacy;
    
    if(registrationChildObject.DichiarazioneDellaPrivacy == "si") {
        getElement("registrationChildSelectPrivacySi").selected = true;
    } else if(registrationChildObject.DichiarazioneDellaPrivacy == "no") {
        getElement("registrationChildselectPrivacyNo").selected = true;
    } else if(registrationChildObject.DichiarazioneDellaPrivacy == "in_parte") {
        getElement("registrationChildselectPrivacyForse").selected = true;
    } else {
        getElement("registrationChildSelectPrivacyEmpty").selected = true;
    }
}
function clearFieldRegistrationChildFormWindow() {
    $("#registrationChildFormWindowId").val("");

    $("#registrationChildFormWindowCognome").val("");
    $("#registrationChildFormWindowNome").val("");
    $("#registrationChildFormWindowDataNascita").val("");
    $("#registrationChildFormWindowComuneNascita").val("");
    $("#registrationChildFormWindowCodiceFiscale").val("");
    $("#registrationChildFormWindowCittadinanza").val("");
    
    getElement("registrationChildSelectUserRangeEmpty").selected = true;
    getElement("registrationChildSelectPrivacyEmpty").selected = true;
    
    $("#registrationChildFormWindowDataIscrizione").val("");
    $("#registrationChildFormWindowFaseIscrizione").val("");
    // Non utilizzo il valore di parentAccountId
    
    $("#registrationChildFormWindowMalattie").val("");
    $("#registrationChildFormWindowVaccinazioni").val("");
    $("#registrationChildFormWindowDichiarazionePrivacy").val("");
     
}
function changeRegistrationChildRegistrationInfoState(isVisible, isDisabled) {
    setVisibility("registrationChildRegistrationInfo", isVisible);
    //* I campi Id, DataIscrizione, FaseDellIscrizione e ParentAccountId non devono mai essere modificati dall'utente
    getElement("registrationChildFormWindowId").disabled = isDisabled;
    getElement("registrationChildFormWindowDataIscrizione").disabled = isDisabled;
    getElement("registrationChildFormWindowFaseIscrizione").disabled = isDisabled;
// Non utilizzo il valore di parentAccountId
}
function changeRegistrationChildStandardFieldState(isVisible, isDisabled) {
    setVisibility("registrationChildStandardField", isVisible);
    
    getElement("registrationChildFormWindowCognome").disabled = isDisabled;
    getElement("registrationChildFormWindowNome").disabled = isDisabled;
    getElement("registrationChildFormWindowDataNascita").disabled = isDisabled;
    getElement("registrationChildFormWindowComuneNascita").disabled = isDisabled;
    getElement("registrationChildFormWindowCodiceFiscale").disabled = isDisabled;
    getElement("registrationChildFormWindowCittadinanza").disabled = isDisabled;
    getElement("registrationChildFormWindowFasciaUtenza").disabled = isDisabled;
    
}
function changeRegistrationChildAdvancedFieldState(isVisible, isDisabled) {
    setVisibility("registrationChildAdvancedField", isVisible);
    
    getElement("registrationChildFormWindowMalattie").disabled = isDisabled;
    getElement("registrationChildFormWindowVaccinazioni").disabled = isDisabled;
    getElement("registrationChildFormWindowDichiarazionePrivacy").disabled = isDisabled;
    
}
function changeRegistrationChildSegretaryFieldState(isVisible, isDisabled) {
    setVisibility("registrationChildSegretaryField", isVisible);
    
    if(getValue("user") == "Segreteria") {
        getElement("registrationChildFormWindowIsSetMalattie").disabled = isDisabled;
        getElement("registrationChildFormWindowIsSetVaccinazioni").disabled = isDisabled;
        getElement("registrationChildFormWindowIsSetDichiarazionePrivacy").disabled = isDisabled;
    }
}
function setRegistrationChildFormEditable(isEditable) {
    
    /* I campi Id, DataIscrizione, FaseDellIscrizione e ParentAccountId non devono mai essere modificati dall'utente
    getElement("registrationChildFormWindowId").disabled = isToDesable;
    getElement("registrationChildFormWindowDataIscrizione").disabled = isToDesable;
    getElement("registrationChildFormWindowFaseIscrizione").disabled = isToDesable;
    // Non utilizzo il valore di parentAccountId
    //*/
    
    setEditable("registrationChildFormWindowCognome", isEditable);
    setEditable("registrationChildFormWindowNome", isEditable);
    setEditable("registrationChildFormWindowDataNascita", isEditable);
    setEditable("registrationChildFormWindowComuneNascita", isEditable);
    setEditable("registrationChildFormWindowCodiceFiscale", isEditable);
    setEditable("registrationChildFormWindowCittadinanza", isEditable);
    setEditable("registrationChildFormWindowFasciaUtenza", isEditable);
    
    setEditable("registrationChildFormWindowMalattie", isEditable);
    setEditable("registrationChildFormWindowVaccinazioni", isEditable);
    setEditable("registrationChildFormWindowDichiarazionePrivacy", isEditable);
    
    if(getValue("user") == "Segreteria") {
        setEditable("registrationChildFormWindowIsSetMalattie", isEditable);
        setEditable("registrationChildFormWindowIsSetVaccinazioni", isEditable);
        setEditable("registrationChildFormWindowIsSetDichiarazionePrivacy", isEditable);
    }
}
// FUNZIONE DI CONTROLLO SULLA VISIBILITA' DEI PULSANTI DELLA FORM-WINDOS
function changeButtonVisibility(registrationPhase, windowType) {
    var userType = getValue("user");
    if(userType == "Genitore") {
        if(windowType == "modifyDraft" && (registrationPhase == "")) {
            setVisibility("createNewDraftRegistrationChildButton", true);
        } else {
            setVisibility("createNewDraftRegistrationChildButton", false);
        }
        if((windowType == "modifyDraft") && (registrationPhase == "bozza")) {
            setVisibility("saveEditDraftRegistrationChildButton", true);
        } else {
            setVisibility("saveEditDraftRegistrationChildButton", false);
        }
        if((registrationPhase == "bozza") &&
            (windowType == "viewDraft")) {

            setVisibility("submitDraftRegistrationChildButton", true);
        } else {
            setVisibility("submitDraftRegistrationChildButton", false);
        }
        if((registrationPhase == "accettata") && (windowType == "modifyDraft")) {

            setVisibility("completeDraftRegistrationChildButton", true);
        } else {
            setVisibility("completeDraftRegistrationChildButton", false);
        }
    }
    if(userType == "Segreteria") {
        if((registrationPhase == "completata") && (windowType == "modifyDraft")) {
            setVisibility("confirmCompletedViewDetailsDraftButton", true);
        } else {
            setVisibility("confirmCompletedViewDetailsDraftButton", false);
        }
    }
}

// FUNZIONI PER UTILIZZARE LA CONFIRM-WINDOW
function initRegistrationChildConfirmWindow() {
    $("#registrationChildConfirmWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,
        stack: true
    });
    $("#registrationChildConfirmWindowConfirmButton").button();
    $("#registrationChildConfirmWindowUndoButton").button();
    $("#registrationChildConfirmWindowUndoButton").click(function(){
        setWindowVisibility("registrationChildConfirmWindow", false);
    });
    
    $("#registrationChildConfirmWindowForm").validate({
        rules: {
        },
        messages: {
        },
        submitHandler: function() {
        }
    });
}
function openRegistrationChildConfirmWindow(newTitle, text, id, confirmAction, undoAction) {
    $("#registrationChildConfirmWindow").dialog({
        title: newTitle
    });
    getElement("registrationChildConfirmWindowTitle").innerHTML = text;
    $("#registrationChildConfirmWindowId").val(id);
    
    $("#registrationChildConfirmWindowConfirmButton").click(function(){
        setWindowVisibility("registrationChildConfirmWindow",false);
        confirmAction();
    });
    
    /*if(undoAction != null) {
        $("#registrationChildConfirmWindowUndoButton").click(function(){
            setWindowVisibility("registrationChildConfirmWindow", false);
            undoAction();
        });
    }*/
    setWindowVisibility("registrationChildConfirmWindow", true);
}
// FUNZIONI PER UTILIZZARE L'ALERT-WINDOW
function initRegistrationChildAlertWindow() {
    $("#registrationChildAlertWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,
        stack: true
    });
    $("#registrationChildAlertWindowOkButton").button();    
}
function openRegistrationChildAlertWindow(newTitle, text, submitAction) {
    $("#registrationChildAlertWindow").dialog({
        title: newTitle
    });
    getElement("registrationChildAlertWindowTitle").innerHTML = text;
    
    setWindowVisibility("registrationChildAlertWindow", true);
    
    $("#registrationChildAlertWindowForm").validate({
        rules: {
        },
        messages: {
        },
        submitHandler: function() {
            setWindowVisibility("registrationChildAlertWindow", false);
            if(submitAction != null) {
                submitAction();
            }
            location.href = "./registrationChild.jsp";
        }
    });
}
// OTHER FUNCTION
function getTypeValue(idRadio) { 
    var indice = -1; 
    var i = 0;
    var radios = document.getElementsByName(idRadio);

    while(i < radios.length && indice == -1) {
        if(radios[i].checked) {
            indice = i;
        //alert(radios[i].value);
        } else {
            i++;
        }
    }
    return radios[indice].value; 
}
function comunicaConServlet(nomeServlet, parametri, executeIfSuccess, executeIfError) {
    /*
     * Supported data types by JSONObject
     * 1) Number (integer, real, or floating point)
     * 2) String (double-quoted Unicode with backslash escapement)
     * 3) Boolean (true and false)
     * 4) Array (an ordered sequence of values, comma-separated and enclosed in square brackets)
     * 5) Object (collection of key/value pairs, comma-separated and enclosed in curly brackets)
     * 6) null
     */
    $.ajax({
        url: nomeServlet,
        dataType: 'json',
        type: 'POST',
        data: parametri,
        success: function(data, textStatus, jqXHR) {
            /*
             * A function to be called if the request succeeds. 
             * The function gets passed three arguments: The data returned from the server, 
             * formatted according to the dataType parameter; a string describing the status; 
             * and the jqXHR (in jQuery 1.4.x, XMLHttpRequest) object
             */
            executeIfSuccess(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            /*
             * A function to be called if the request fails. The function receives three arguments: 
             * The jqXHR (in jQuery 1.4.x, XMLHttpRequest) object, a string describing the type of error 
             * that occurred and an optional exception object, if one occurred. 
             * Possible values for the second argument (besides null) are "timeout", 
             * "error", "abort", and "parsererror". 
             * When an HTTP error occurs, errorThrown receives the textual portion of the HTTP status, 
             * such as "Not Found" or "Internal Server Error"
             */
            var errorMsg = "Errore nella richiesta alla Servlet (" + textStatus + "):" + newLine() 
            + "HTTP error: " + errorThrown;
            executeIfError(errorMsg);
        },
        complete : function(jqXHR, textStatus) {
        /*
             * A function to be called when the request finishes (after success and error callbacks are executed). 
             * The function gets passed two arguments: The jqXHR (in jQuery 1.4.x, XMLHTTPRequest) object and a 
             * string categorizing the status of the request ("success", "notmodified", "error", "timeout", "abort", 
             * or "parsererror")
             */
        }
    });
}
function newLine() {
    return "<br>";
}
function getElement(id) {
    return document.getElementById(id);
}
function getValue(id) {
    return document.getElementById(id).value;
}
function setWindowVisibility(id, isVisible) {
    if(isVisible) {
        $("#" + id).dialog("open");
    } else {
        $("#" + id).dialog("close");
    }
}
function setVisibility(id, isVisible) {
    if(isVisible) {
        document.getElementById(id).style.display = "inline";
    } else {
        document.getElementById(id).style.display = "none";
    }
}
function setEditable(id, isEditable) {
    if(isEditable) {
        //document.getElementById(id).readOnly = "";
        document.getElementById(id).disabled = false;
    } else {
        //document.getElementById(id).readOnly = "false";
        document.getElementById(id).disabled = true;
    }
}