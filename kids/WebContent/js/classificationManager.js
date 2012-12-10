function initClassificationPage() {
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
    
    $("#classificationButtonOpenWindowCreateNew").button();
    
    $("#classificationAddWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#classificationAddWindowSave").button();
    $("#classificationAddWindowUndo").button();
    $("#classificationAddWindowUndo").click(function() {
        closeClassificationAddWindow();
    });
    
    $("#classificationModifyWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#classificationModifyWindowSave").button();
    $("#classificationModifyWindowUndo").button();
    $("#classificationModifyWindowUndo").click(function() {
        closeClassificationModifyWindow();
    });
    
    $("#classificationAggiornaResultButton").button();
    $("#classificationCloseDetailsButton").button();
    
    // ALERT WINDOW
    $("#classificationAlertWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,
        stack: true
    });
    $("#classificationAlertWindowOkButton").button();
    $("#classificationAlertWindowOkButton").click(function() {
        setWindowVisibility("classificationAlertWindow", false);
    });
    
    // CONFIRM WINDOW
    $("#classificationConfirmWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,
        stack: true
    });
    $("#classificationConfirmWindowConfirmButton").button();
    $("#classificationConfirmWindowUndoButton").click(function() {
        setWindowVisibility("classificationConfirmWindow", false);
        // Rimuovo l'evento dal pulsante di conferma
        getElement("classificationConfirmWindowConfirmButton").onClick = "";
        // Svuoto il testo
        getElement("classificationConfirmWindowText").HTML = "";
    });
}
// FINE INIZIALIZZAZIONE DELLA PAGINA
// -----------------------------------------------------------------------------
// FUNZIONI DI UTILIZZO GENERALE
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
function comunicaConServlet(nomeServlet, parametri, executeIfSuccess) {
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
            openClassificationAlertWindow("Errore", errorMsg);
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
function openClassificationAlertWindow(newTitle, text) {
    $("#classificationAlertWindow").dialog({title: newTitle});
    getElement("classificationAlertWindowText").innerHTML = text;
    
    setWindowVisibility("registrationChildAlertWindow", true);
}
function openClassificationConfirmWindow(newTitle, text, actionOnConfirm) {
    $("#classificationConfirmWindow").dialog({title: newTitle});
    getElement("classificationConfirmWindowText").innerHTML = text;
    
    $("#classificationAlertWindowOkButton").click(function() {
        if(actionOnConfirm != null) {
            actionOnConfirm();
        }
        setWindowVisibility("classificationConfirmWindow", false);
    });
    
    setWindowVisibility("classificationConfirmWindow", true);
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
// FINE FUNZIONI DI UTILIZZO GENERALE
// -----------------------------------------------------------------------------
// FUNZIONI ASSOCIATE ALLA PAGINA PRINCIPALE
/*
 * Apre la finestra di aggiunta di una graduatoria
 */
function openAddClassificationWindow() {
    setWindowVisibility("classificationAddWindow", true);
}
// FUNZIONI ASSOCIATE ALLE OPERAZIONI DELLA TABELLA
/*
 * Riempie i campi del dettaglio della graduatoria e gli esiti associati
 * alla graduatoria dato un ID
 */
function viewDetailsClassification(id) {
    getElement("classificationDisplayId").value = id;
    
    comunicaConServlet(
        "GetClassification", 
        {
            Id: id
        }, 
        function(jsonObject) {
            if(jsonObject.IsSuccess) {
                // riempio i campi della form "classificationModifyForm"
                getElement("classificationDisplayId").value = jsonObject.Id;
                getElement("classificationDisplayData").value = jsonObject.Data;
                getElement("classificationDisplayStatus").value = jsonObject.Stato;
                getElement("classificationDisplayNome").value = jsonObject.Nome;
                createTableResult(id);
                setVisibility("classificationDisplay", true);
            } else {
                openRegistrationChildAlertWindow("Errore nel caricamento delle informazioni", jsonObject.ErrorMsg);
            }
        }
    );

    
}
/*
 * Apre la finestra di modifica delle informazioni di una graduatoria dato ID
 */
function openWindowModifyClassification(id) {
    comunicaConServlet(
        "GetClassification", 
        {
            Id: id
        }, 
        function(jsonObject) {
            if(jsonObject.IsSuccess) {
                // riempio i campi della form "classificationModifyForm"
                getElement("classificationModifyWindowId").value = jsonObject.Id;
                getElement("classificationModifyWindowData").value = jsonObject.Data;
                getElement("classificationModifyWindowStatus").value = jsonObject.Stato;
                getElement("classificationModifyWindowNome").value = jsonObject.Nome;
                setWindowVisibility("classificationModifyWindow", true);
            } else {
                openRegistrationChildAlertWindow("Errore nel caricamento delle informazioni", jsonObject.ErrorMsg);
            }
        }
    );
}
/*
 * Apre la finestra di richiesta di conferma di eliminare una graduatoria
 * dato un ID
 */
function openWindowDeleteClassification(id) {
    
}
/*
 * Apre la finestra di richiesta di conferma di trasformazione in provvisoria
 */
function openWindowToProvvisoriaClassification(id) {
    
}
/*
 * Apre la finestra di richiesta di conferma di trasformazione in definitiva
 */
function openWindowToDefinitivaClassification(id) {
    
}
// -----------------------------------------------------------------------------
// FUNZIONI PER LA VISUALE DI CREAZIONE DI UNA GRADUATORIA
function closeClassificationAddWindow() {
    setWindowVisibility("classificationAddWindow", false);
    getElement("classificationAddWindowNome").value = "";
}
/*
 * Salva le modifiche apportate alla graduatoria nella ModifyWindow
 */
function saveNewGraduatoria() {
    $("#classificationAddWindowForm").validate({
        rules: {
            classificationAddWindowNome : true
        },
        messages: {
            classificationAddWindowNome : "Inserire il nome"
        },
        submitHandler: function() {
            comunicaConServlet("AddClassification", {
                    Nome: getValue("classificationAddWindowNome")
                }, function(result) {
                    if(result.IsSuccess) {
                        openClassificationAlertWindow("Operazione completata", "La graduatoria è stata inserita");
                    } else {
                        openClassificationAlertWindow("Operazione non riuscita", "Si è verificato il seguente errore:" + newLine() +
                                result.ErrorMsg);
                    }
                    closeClassificationAddWindow();
                }
            );
        }
    });
}
// -----------------------------------------------------------------------------
// FUNZIONI PER LA VISUALE DI MODIFICA DELLA GRADUATORIA
function closeClassificationModifyWindow() {
    setWindowVisibility("classificationModifyWindow", false);
    // svuoto i campi della form "classificationModifyForm"
    getElement("classificationModifyWindowId").value = "";
    getElement("classificationModifyWindowData").value = "";
    getElement("classificationModifyWindowStatus").value = "";
    getElement("classificationModifyWindowNome").value = "";
}
/*
 * Salva le modifiche apportate alla graduatoria nella ModifyWindow
 */
function saveModifyGraduatoria() {
    $("#classificationModifyForm").validate({
        rules: {
            classificationModifyWindowNome : true
        },
        messages: {
            classificationModifyWindowNome : "Inserire il nome"
        },
        invalidHanler: function() {
            openClassificationAlertWindow("Operazione non riuscita", "Si è verificato un errore nella validazione");
        },
        submitHandler: function() {
            comunicaConServlet("ModifyClassification", {
                    Id: getValue("classificationModifyWindowId"),
                    Data: getValue("classificationModifyWindowData"),
                    Stato: getValue("classificationModifyWindowStatus"),
                    Nome: getValue("classificationModifyWindowNome")
                }, function(result) {
                    closeClassificationModifyWindow();
                    if(result.IsSuccess) {
                        openClassificationAlertWindow("Operazione completata", "La graduatoria è stata modificata");
                    } else {
                        openClassificationAlertWindow("Operazione non riuscita", "Si è verificato il seguente errore:" + newLine() +
                                result.ErrorMsg);
                    }
                }
            );
        }
    });
}
// -----------------------------------------------------------------------------
// FUNZIONI ASSOCIATE ALLA VISUALE IN DETTAGLIO DELLA GRADUATORIA
/*
 * Ricontrolla l'elenco delle RegistrationChild che sono nella fase "Ricevuta" 
 * e crea un elemento Result per quella registrazione se non è già esistente
 */
function updateResultClassification() {
    
}
/*
 * Chiude la visuale di dettaglio della graduatoria
 */
function closeDetailsClassification() {
    setVisibility("classificationDisplay", false);
    // svuoto i campi della "classificationDisplayInfo"
    getElement("classificationDisplayId").value = "";
    getElement("classificationDisplayData").value = "";
    getElement("classificationDisplayNome").value = "";
    getElement("classificationDisplayStatus").value = "";
}
// FUNZIONI PER LA GESTIONE DEI RESULT
/*
 * Funzione visibile nella tabella dei risultati, aggiorna il risultato nel
 * database
 */
function aggiornaRisultatoResult(classificationId, registrationChildId) {
    
}