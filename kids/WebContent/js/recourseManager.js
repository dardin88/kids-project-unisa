function initRecoursePage() {
    $.ajaxSetup({
        cache: false
    });
    
    $("#recourseAddWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#recourseAddWindowMotivoSave").button();
    $("#recourseAddWindowMotivoUndo").button();
    $("#recourseAddWindowMotivoUndo").click(function() {
        closeRecourseAddWindow();
    });
    
    $("#recourseViewDetailsWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#recourseViewDetailsWindowUndo").button();
    $("#recourseViewDetailsWindowUndo").click(function() {
        closeRecourseViewDetailsWindow();
    });
    
    // INIZIALIZZAZIONE ALERT WINDOW
    $("#recourseAlertWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,
        stack: true
    });
    $("#recourseAlertWindowOkButton").button();
    $("#recourseAlertWindowOkButton").click(function() {
        setWindowVisibility("recourseAlertWindow", false);
    });
    // FINE INIZIALIZZAZIONE ALERT WINDOW

    // INIZIALIZZAZIONE CONFIRM WINDOW
    $("#recourseConfirmWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#recourseConfirmWindowConfirmButton").button();
    $("#recourseConfirmWindowUndoButton").button();
    $("#recourseConfirmWindowUndoButton").click(function() {
        setWindowVisibility("recourseConfirmWindow", false);
        // Rimuovo l'evento dal pulsante di conferma
        getElement("recourseConfirmWindowConfirmButton").onClick = "";
        // Svuoto il testo
        getElement("recourseConfirmWindowText").innerHTML = "";
    });
    // FINE INIZIALIZZAZIONE CONFIRM WINDOW
    
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
            openRecourseAlertWindow("Errore", errorMsg);
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
function openRecourseAlertWindow(newTitle, text) {
    $("#recourseAlertWindow").dialog({title: newTitle});
    getElement("recourseAlertWindowText").innerHTML = text;
    
    setWindowVisibility("recourseAlertWindow", true);
}
function openRecourseConfirmWindow(newTitle, text, actionOnConfirm) {
    $("#recourseConfirmWindow").dialog({title: newTitle});
    getElement("recourseConfirmWindowText").innerHTML = text;
    
    $("#recourseConfirmWindowConfirmButton").click(function() {
        if(actionOnConfirm != null) {
            actionOnConfirm();
        }
        setWindowVisibility("recourseConfirmWindow", false);
    });
    
    setWindowVisibility("recourseConfirmWindow", true);
}
function valida(identificatori) {
    var areValid = true;
    var i = 0;
    while(identificatori[i] != null) {
        
        if(getValue(identificatori[i]) == "") {
            getElement(identificatori[i] + "Error").innerHTML = "Inserire il campo";
            areValid &= false;
        } else {
            getElement(identificatori[i] + "Error").innerHTML = "";
        }
        i++;
    }
    return areValid;
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
// FUNZIONI ASSOCIATE ALLE OPERAZIONI DELLA TABELLA
/*
 * Apre la finestra di aggiunta di una domanda di rinuncia
 */
function openInsertRecourseWindow(id) {
    getElement("recourseAddWindowId").value = id;
    setWindowVisibility("recourseAddWindow", true);
}
/*
 * Riempie i campi del dettaglio del ricorso
 */
function openViewDetailsRecourseWindow(id) {
    getElement("recourseViewDetailsWindowId").value = id;
    
    comunicaConServlet(
        "GetRecourse", 
        {
            Id: id
        }, 
        function(jsonObject) {
            if(jsonObject.IsSuccess) {
                // riempio il contenuto della visuale dettagli
                getElement("recourseViewDetailsText").innerHTML = jsonObject.HTML;
                setWindowVisibility("recourseViewDetailsWindow", true);
            } else {
                openRecourseAlertWindow("Errore nel caricamento delle informazioni", jsonObject.ErrorMsg);
            }
        }
    );
}
/*
 * Apre la finestra di richiesta di conferma di eliminare un ricorso
 * dato un ID
 * !NON UTILIZZATO QUINDI LA SERVLET NON E' STATA IMPLEMENTATA PER ORA!
 */
function openDeleteRecourseWindow(id) {
    var actionOnConfirm = function() {
        comunicaConServlet(
        "DeleteRecourse", 
        {
            Id:id
        }, 
        function(jsonObject) {
            if(jsonObject.IsSuccess) {
                updateTableSubmittedRecourse();
                updateTablePossibleRecourse();
                openRecourseAlertWindow("Operazione riuscita", "Il ricorso è stata eliminato!")
            } else {
                openRecourseAlertWindow("Errore nell'eliminazione", jsonObject.ErrorMsg);
            }
        });
    }
    openRecourseConfirmWindow("Conferma operazione", "Sei sicuro di voler eliminare il ricorso?", actionOnConfirm)
}
/*
 * Imposta il ricorso come accettato
 */
function openAcceptRecourseWindow(id) {
    var actionOnConfirm = function() {
        comunicaConServlet(
        "ValutaRecourse", 
        {
            Id: id,
            Valutazione: "accetta"
        }, 
        function(jsonObject) {
            if(jsonObject.IsSuccess) {
                updateTableSubmittedRecourse();
                openRecourseAlertWindow("Operazione riuscita", "Il ricorso è stato accettato!")
            } else {
                openRecourseAlertWindow("Errore nell'accettazione", jsonObject.ErrorMsg);
            }
        });
    }
    openRecourseConfirmWindow("Conferma operazione", "Sei sicuro di voler accettare il ricorso?", actionOnConfirm)
}
/*
 * Imposta il ricorso come rifiutato
 */
function openRifiutaRecourseWindow(id) {
    var actionOnConfirm = function() {
        comunicaConServlet(
        "ValutaRecourse", 
        {
            Id: id,
            Valutazione: "rifiuta"
        }, 
        function(jsonObject) {
            if(jsonObject.IsSuccess) {
                updateTableSubmittedRecourse();
                openRecourseAlertWindow("Operazione riuscita", "Il ricorso è stato rifiutato!")
            } else {
                openRecourseAlertWindow("Errore nel rifiutamento", jsonObject.ErrorMsg);
            }
        });
    }
    openRecourseConfirmWindow("Conferma operazione", "Sei sicuro di voler rifiutare il ricorso?", actionOnConfirm)
}
// -----------------------------------------------------------------------------
// FUNZIONI PER LA VISUALE DI CREAZIONE DI UN RICORSO
function closeRecourseAddWindow() {
    setWindowVisibility("recourseAddWindow", false);
    getElement("recourseAddWindowMotivo").value = "";
    getElement("recourseAddWindowMotivoError").innerHTML = "";
}
/*
 * Inserisce il ricorso
 */
function saveNewRecourse() {
    var param = ["recourseAddWindowMotivo"];
    if(valida(param)) {
        comunicaConServlet("InsertRecourse", {
                    Motivazione: getValue("recourseAddWindowMotivo"),
                    IdIscrizione: getValue("recourseAddWindowId")
                }, function(result) {
                    if(result.IsSuccess) {
                        updateTableSubmittedRecourse();
                        updateTablePossibleRecourse();
                        openRecourseAlertWindow("Operazione completata", "Il ricorso è stato inserito!");
                    } else {
                        openRecourseAlertWindow("Operazione non riuscita", "Si è verificato il seguente errore:" + newLine() +
                                result.ErrorMsg);
                    }
                    closeRecourseAddWindow();
                }
            );
    } else {
        //openRecourseAlertWindow("Campi mancanti", "Compilare il campo motivo!");
    }
}
// -----------------------------------------------------------------------------
// FUNZIONI PER LA VISUALE DEI DETTAGLI DI UN RICORSO
function closeRecourseViewDetailsWindow() {
    setWindowVisibility("recourseViewDetailsWindow", false);
    // svuoto i campi della visuale dettagli
    getElement("recourseViewDetailsWindowId").value = "";
    getElement("recourseViewDetailsText").innerHTML = "";
}