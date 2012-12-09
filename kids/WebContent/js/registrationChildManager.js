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
        "bFilter": false,
        "fnServerParams": function (aoData) {
//           aoData.push(// Parametri di ricerca aggiuntivi
//            {
//                "name" : "FaseDellIscrizione", 
//                "value" : $('#FaseDellIscrizione').val()
//            }
//            );
//     
        },

        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Domande d'iscrizione",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Domande d'iscrizione",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },

        "oTableTools":{
            "aButtons":[
            "Elimina", "Modifica","Visualizza"
            ]
        },
        "aoColumns": [
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth":"10%"
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
                if(jsonObject.IsSuccess == "true") {
                    openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La bozza della domanda di iscrizione è stata salvata");
                } else {
                    openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                }
            }, 
            function(errorMsg) {
                // Funzione eseguita se la richiesta alla servlet ha presentato errori
                setWindowVisibility("registrationChildFormWindow", false);
                alert("error function: ");
                openRegistrationChildAlertWindow("Errore", errorMsg);
            }
        );
}
function saveEditDraftRegistrationChildAction() {
    comunicaConServlet(
            // Nome della Servlet
            "EditRegistrationChild", 
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
                if(jsonObject.IsSuccess == "true") {
                    openRegistrationChildAlertWindow("Operazione eseguita correttamente", "Le modifiche apportate alla bozza della domanda di iscrizione sono state salvata");
                } else {
                    openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                }
            }, 
            function(errorMsg) {
                // Funzione eseguita se la richiesta alla servlet ha presentato errori
                setWindowVisibility("registrationChildFormWindow", false);
                alert("error function: ");
                openRegistrationChildAlertWindow("Errore", errorMsg);
            }
        );
}
function submitDraftRegistrationChildAction() {
    $("#registrationChildFormWindowForm").validate({
            rules: {
                registrationChildFormWindowCognome: {required: true},
                registrationChildFormWindowNome: {required: true},
                registrationChildFormWindowDataNascita:  {required: true},
                registrationChildFormWindowComuneNascita:  {required: true},
                registrationChildFormWindowCodiceFiscale:  {required: true},
                registrationChildFormWindowCittadinanza:  {required: true},
                registrationChildFormWindowFasciaUtenza:  {required: true}
            },
            messages: {
                registrationChildFormWindowCognome: {required: "Inserire il cognome"},
                registrationChildFormWindowNome: {required: "Inserire il nome"},
                registrationChildFormWindowDataNascita:  {required: "Selezionare la data di nascita"},
                registrationChildFormWindowComuneNascita:  {required: "Inserire il comune di nascita"},
                registrationChildFormWindowCodiceFiscale:  {required: "Inserire il codice fiscale"},
                registrationChildFormWindowCittadinanza:  {required: "Inserire la cittadianza"},
                registrationChildFormWindowFasciaUtenza:  {required: "Selezionare la fascia di utilizzo"}
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
                        if(jsonObject.IsSuccess == "true") {
                            openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La domanda di iscrizione è stata sottomessa");
                        } else {
                            openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                        }
                    }, 
                    function(errorMsg) {
                        // Funzione eseguita se la richiesta alla servlet ha presentato errori
                        setWindowVisibility("registrationChildFormWindow", false);
                        alert("error function: ");
                        openRegistrationChildAlertWindow("Errore", errorMsg);
                    }
                );
            }
        });
    
}
function deleteDraftRegistrationChildAction() {
    setWindowVisibility("registrationChildFormWindow", false);
    openDeleteRegistrationChildWindow(getValue("registrationChildFormWindowId"));
}
function completeDraftRegistrationChildAction() {
    $("#registrationChildFormWindowForm").validate({
            rules: {
                registrationChildFormWindowMalattie: {required: true},
                registrationChildFormWindowVaccinazioni: {required: true},
                registrationChildFormWindowDichiarazionePrivacy:  {required: true}
            },
            messages: {
                registrationChildFormWindowMalattie: {required: "Inserire le malattie (oppure Nessuna)"},
                registrationChildFormWindowVaccinazioni: {required: "Inserire le vaccinazioni (oppure Nessuna)"},
                registrationChildFormWindowDichiarazionePrivacy:  {required: "Selezionare la risposta"}
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
                        if(jsonObject.IsSuccess == "true") {
                            openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La domanda di iscrizione è stata completata");
                        } else {
                            openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                        }
                    }, 
                    function(errorMsg) {
                        // Funzione eseguita se la richiesta alla servlet ha presentato errori
                        setWindowVisibility("registrationChildFormWindow", false);
                        alert("error function: ");
                        openRegistrationChildAlertWindow("Errore", errorMsg);
                    }
                );
            }
        });
}
function confirmDraftRegistrationChildAction() {
    setWindowVisibility("registrationChildFormWindow", false);
    confirmReceivingRegistrationChildWindow(getValue("registrationChildFormWindowId"));
}
function confirmCompletingDraftRegistrationChildAction() {
    $("#registrationChildFormWindowForm").validate({
            rules: {
                registrationChildFormWindowIsSetMalattie: {required: true},
                registrationChildFormWindowIsSetVaccinazioni: {required: true},
                registrationChildFormWindowIsSetDichiarazionePrivacy:  {required: true}
            },
            messages: {
                registrationChildFormWindowIsSetMalattie: {required: "Selezionare la risposta"},
                registrationChildFormWindowIsSetVaccinazioni: {required: "Selezionare la risposta"},
                registrationChildFormWindowIsSetDichiarazionePrivacy: {required: "Selezionare la risposta"}
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
                        if(jsonObject.IsSuccess == "true") {
                            openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La domanda di iscrizione è stata confermata");
                        } else {
                            openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + jsonObject.ErrorMsg);
                        }
                    }, 
                    function(errorMsg) {
                        // Funzione eseguita se la richiesta alla servlet ha presentato errori
                        setWindowVisibility("registrationChildFormWindow", false);
                        alert("error function: ");
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
    openRegistrationChildFormWindow("modifyDraft", "Visuale di inserimento di una bozza di domanda di iscrizione", null, false);
}
function openModifyRegistrationChildWindow(id) {
    setVisibility("registrationChildRegistrationInfo", true);
    setVisibility("registrationChildStandardField", true);
    if(getValue("user") == "Segreteria") {
        setVisibility("registrationChildSegretaryField", false);
    }
    openRegistrationChildFormWindow("modifyDraft", "Visuale di modifica di una bozza di domanda di iscrizione", id, false);
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
    openRegistrationChildFormWindow("modifyDraft", "Visuale di completamento della domanda di iscrizione", id, true);
}
function openComfirmReceivingRegistrationChildWindow(id) {
    setVisibility("registrationChildRegistrationInfo", true);
    setVisibility("registrationChildStandardField", true);
    if(getValue("user") == "Segreteria") {
        setVisibility("registrationChildSegretaryField", false);
    }
    openRegistrationChildFormWindow("viewDraft", "Visuale di conferma della ricezione della domanda di iscrizione", id, false);
}
function openComfirmCompletingRegistrationChildWindow(id) {
    setVisibility("registrationChildRegistrationInfo", true);
    setVisibility("registrationChildStandardField", false);
    if(getValue("user") == "Segreteria") {
        setVisibility("registrationChildSegretaryField", true);
    }
    openRegistrationChildFormWindow("modifyDraft", "Visuale di conferma del completamento della domanda di iscrizione", id, true);
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
                    if(jsonObject.IsSuccess == "true") {
                        openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La domanda di iscrizione è stata eliminata");
                    } else {
                        openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + risultato.ErrorMsg);
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
                    if(jsonObject.IsSuccess == "true") {
                        openRegistrationChildAlertWindow("Operazione eseguita correttamente", "La domanda di iscrizione è stata memorizzata come ricevuta");
                    } else {
                        openRegistrationChildAlertWindow("Operazione non eseguita", "Si è verificato il seguente errore:" + newLine() + risultato.ErrorMsg);
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
    $("#registrationChildFormWindowDataNascita").datepicker({dateFormat:'yy-mm-dd'});
    
    getElement("registrationChildFormWindowId").disabled = true;
    getElement("registrationChildFormWindowDataIscrizione").disabled = true;
    getElement("registrationChildFormWindowFaseIscrizione").disabled = true;
    
    $("#registrationChildFormWindowUndoButton").button();
    $("#registrationChildFormWindowUndoButton").click(function(){
        setWindowVisibility("registrationChildFormWindow", false);
        clearFieldRegistrationChildFormWindow();
    });
}
function openRegistrationChildFormWindow(windowType, title, idToLoad, canAdvancedFieldBeVisibily) {
    $("#registrationChildFormWindow").dialog({title: title});
    
    if(windowType == "modifyDraft") {
        setRegistrationChildFormDisabled(false);
    } else if(windowType == "viewDraft") {
        setRegistrationChildFormDisabled(true);
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
                // Funzione eseguita se la richiesta alla servlet è eseguita con successo
                fillInFieldRegistrationChildFormWindow(jsonObject);
                changeButtonVisibility(jsonObject.FaseDellIscrizione, windowType);
                
                if((jsonObject.FaseDellIscrizione == "accettata" || jsonObject.FaseDellIscrizione == "completata") && canAdvancedFieldBeVisibily) {
                    setVisibility("registrationChildAdvancedField", true);
                } else {
                    setVisibility("registrationChildAdvancedField", false);
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
        setVisibility("registrationChildAdvancedField", canAdvancedFieldBeVisibily);
    }
    
    setWindowVisibility("registrationChildFormWindow", true);
}
function fillInFieldRegistrationChildFormWindow(registrationChildObject) {
    $("#registrationChildFormWindowId").val(registrationChildObject.Id);

    $("#registrationChildFormWindowCognome").val(registrationChildObject.Cognome);
    $("#registrationChildFormWindowNome").val(registrationChildObject.Nome);
    $("#registrationChildFormWindowDataNascita").val(registrationChildObject.DataNascita);
    $("#registrationChildFormWindowComuneNascita").val(registrationChildObject.ComuneNascita);
    $("#registrationChildFormWindowCodiceFiscale").val(registrationChildObject.CodiceFiscale);
    $("#registrationChildFormWindowCittadinanza").val(registrationChildObject.Cittadinanza);
    if(registrationChildObject.FasciaUtenza == "full_time") {
        getElement("registrationChildselectFullTime").selected = true;
    } else if(registrationChildObject.FasciaUtenza == "part_time_mattutina") {
        getElement("registrationChildselectPartTimeAM").selected = true;
    } else if(registrationChildObject.FasciaUtenza == "part_time_pomeridiana") {
        getElement("registrationChildselectPartTimePM").selected = true;
    } else {
        getElement("registrationChildselectEmpty").selected = true;
    }
    
    $("#registrationChildFormWindowDataIscrizione").val(registrationChildObject.DataIscrizione);
    $("#registrationChildFormWindowFaseIscrizione").val(registrationChildObject.FaseDellIscrizione);
    // Non utilizzo il valore di parentAccountId

    $("#registrationChildFormWindowMalattie").val(registrationChildObject.Malattie);
    $("#registrationChildFormWindowVaccinazioni").val(registrationChildObject.Vaccinazioni);
    $("#registrationChildFormWindowDichiarazionePrivacy").val(registrationChildObject.DichiarazioneDellaPrivacy);
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
function setRegistrationChildFormDisabled(isToDesable) {
    
    /* I campi Id, DataIscrizione, FaseDellIscrizione e ParentAccountId non devono mai essere modificati dall'utente
    getElement("registrationChildFormWindowId").disabled = isToDesable;
    getElement("registrationChildFormWindowDataIscrizione").disabled = isToDesable;
    getElement("registrationChildFormWindowFaseIscrizione").disabled = isToDesable;
    // Non utilizzo il valore di parentAccountId
    //*/
    
    getElement("registrationChildFormWindowCognome").disabled = isToDesable;
    getElement("registrationChildFormWindowNome").disabled = isToDesable;
    getElement("registrationChildFormWindowDataNascita").disabled = isToDesable;
    getElement("registrationChildFormWindowComuneNascita").disabled = isToDesable;
    getElement("registrationChildFormWindowCodiceFiscale").disabled = isToDesable;
    getElement("registrationChildFormWindowCittadinanza").disabled = isToDesable;
    getElement("registrationChildFormWindowFasciaUtenza").disabled = isToDesable;
    
    getElement("registrationChildFormWindowMalattie").disabled = isToDesable;
    getElement("registrationChildFormWindowVaccinazioni").disabled = isToDesable;
    getElement("registrationChildFormWindowDichiarazionePrivacy").disabled = isToDesable;
    
    if(getValue("user") == "Segreteria") {
        getElement("registrationChildFormWindowIsSetMalattie").disabled = isToDesable;
        getElement("registrationChildFormWindowIsSetVaccinazioni").disabled = isToDesable;
        getElement("registrationChildFormWindowIsSetDichiarazionePrivacy").disabled = isToDesable;
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
        if((registrationPhase == "" || registrationPhase == "bozza") &&
                    (windowType == "createNewDraft" || windowType == "modifyDraft" || windowType == "viewDraft")) {

            setVisibility("submitDraftRegistrationChildButton", true);
        } else {
            setVisibility("submitDraftRegistrationChildButton", false);
        }
        if((registrationPhase == "bozza" || registrationPhase == "sottomessa" || registrationPhase == "ricevuta") && 
                    (windowType == "modifyDraft" || windowType == "viewDraft")) {

            setVisibility("deleteDraftRegistrationChildButton", true);
        } else {
            setVisibility("deleteDraftRegistrationChildButton", false);
        }
        if((registrationPhase == "accettata") && (windowType == "viewDraft")) {

            setVisibility("completeDraftRegistrationChildButton", true);
        } else {
            setVisibility("completeDraftRegistrationChildButton", false);
        }
    }
    if(userType == "Segreteria") {
        if((registrationPhase == "sottomessa") && (windowType == "viewDraft")) {
            setVisibility("confirmReceivingViewDetailsDraftButton", true);
        } else {
            setVisibility("confirmReceivingViewDetailsDraftButton", false);
        }
        if((registrationPhase == "completata") && (windowType == "viewDraft")) {
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
        width: 600
    });
    $("#registrationChildConfirmWindowConfirmButton").button();
    $("#registrationChildConfirmWindowUndoButton").button();
    $("#registrationChildConfirmWindowUndoButton").click(function(){
        setWindowVisibility("registrationChildConfirmWindow", false);
    });
}
function openRegistrationChildConfirmWindow(newTitle, text, id, confirmAction, undoAction) {
    $("#registrationChildConfirmWindow").dialog({title: newTitle});
    getElement("registrationChildConfirmWindowTitle").innerHTML = text;
    $("#registrationChildConfirmWindowId").val(id);
    
    $("#registrationChildConfirmWindowConfirmButton").click(function(){
        confirmAction();
        setWindowVisibility("registrationChildConfirmWindow",false);
    });
    if(undoAction != null) {
        $("#registrationChildConfirmWindowUndoButton").click(function(){
            undoAction();
            setWindowVisibility("registrationChildConfirmWindow", false);
        });
    }
    
    setWindowVisibility("registrationChildConfirmWindow", true);
}
// FUNZIONI PER UTILIZZARE L'ALERT-WINDOW
function initRegistrationChildAlertWindow() {
    $("#registrationChildAlertWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#registrationChildAlertWindowOkButton").button();
    $("#registrationChildAlertWindowOkButton").click(function(){
        setWindowVisibility("registrationChildAlertWindow", false);
    });
}
function openRegistrationChildAlertWindow(newTitle, text) {
    $("#registrationChildAlertWindow").dialog({title: newTitle});
    getElement("registrationChildAlertWindowTitle").innerHtml = text;
    
    setWindowVisibility("registrationChildAlertWindow", true);
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
        success: function(json) {
            // json è un oggetto che ha come variali i campi aggiunti nella servlet facendo json.add("Id", "valore");
            // per richiamare l'id scrivere json.Id
            executeIfSuccess(json);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            var errorMsg = "Errore nella richiesta alla Servlet (" + errorThrown + "):" + newLine() + + textStatus;
            executeIfError(errorMsg);
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