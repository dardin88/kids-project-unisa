function initRenunciationPage() {
    $.ajaxSetup({
        cache: false
    });
    
    $("#renunciationAddWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#renunciationAddWindowMotivoSave").button();
    $("#renunciationAddWindowMotivoUndo").button();
    $("#renunciationAddWindowMotivoUndo").click(function() {
        closeRenunciationAddWindow();
    });
    
    $("#renunciationViewDetailsWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#renunciationViewDetailsWindowUndo").button();
    $("#renunciationViewDetailsWindowUndo").click(function() {
        closeRenunciationViewDetailsWindow();
    });
    
    // INIZIALIZZAZIONE ALERT WINDOW
    $("#renunciationAlertWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,
        stack: true
    });
    $("#renunciationAlertWindowOkButton").button();
    $("#renunciationAlertWindowOkButton").click(function() {
        setWindowVisibility("renunciationAlertWindow", false);
    });
    // FINE INIZIALIZZAZIONE ALERT WINDOW

    // INIZIALIZZAZIONE CONFIRM WINDOW
    $("#renunciationConfirmWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#renunciationConfirmWindowConfirmButton").button();
    $("#renunciationConfirmWindowUndoButton").button();
    $("#renunciationConfirmWindowUndoButton").click(function() {
        setWindowVisibility("renunciationConfirmWindow", false);
        // Rimuovo l'evento dal pulsante di conferma
        getElement("renunciationConfirmWindowConfirmButton").onClick = "";
        // Svuoto il testo
        getElement("renunciationConfirmWindowText").innerHTML = "";
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
            openRenunciationAlertWindow("Errore", errorMsg);
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
function openRenunciationAlertWindow(newTitle, text) {
    $("#renunciationAlertWindow").dialog({title: newTitle});
    getElement("renunciationAlertWindowText").innerHTML = text;
    
    setWindowVisibility("renunciationAlertWindow", true);
}
function openRenunciationConfirmWindow(newTitle, text, actionOnConfirm) {
    $("#renunciationConfirmWindow").dialog({title: newTitle});
    getElement("renunciationConfirmWindowText").innerHTML = text;
    
    $("#renunciationConfirmWindowConfirmButton").click(function() {
        if(actionOnConfirm != null) {
            actionOnConfirm();
        }
        setWindowVisibility("renunciationConfirmWindow", false);
    });
    
    setWindowVisibility("renunciationConfirmWindow", true);
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
function openInsertRenunciationWindow(id) {
    getElement("renunciationAddWindowId").value = id;
    setWindowVisibility("renunciationAddWindow", true);
}
/*
 * Riempie i campi del dettaglio della graduatoria e gli esiti associati
 * alla graduatoria dato un ID
 */
function openViewDetailsRenunciationWindow(id) {
    getElement("renunciationViewDetailsWindowId").value = id;
    
    comunicaConServlet(
        "GetRenunciation", 
        {
            Id: id
        }, 
        function(jsonObject) {
            if(jsonObject.IsSuccess) {
                // riempio il contenuto della visuale dettagli
                getElement("renunciationViewDetailsText").innerHTML = jsonObject.HTML;
                setWindowVisibility("renunciationViewDetailsWindow", true);
            } else {
                openRenunciationAlertWindow("Errore nel caricamento delle informazioni", jsonObject.ErrorMsg);
            }
        }
    );
}
/*
 * Apre la finestra di richiesta di conferma di eliminare una graduatoria
 * dato un ID
 */
function openDeleteRenunciationWindow(id) {
    var actionOnConfirm = function() {
        comunicaConServlet(
        "DeleteRenunciation", 
        {
            Id:id
        }, 
        function(jsonObject) {
            if(jsonObject.IsSuccess) {
                updateTableSubmittedRinunciation();
                updateTablePossibleRinunciation();
                openRenunciationAlertWindow("Operazione riuscita", "La domanda di rinuncia è stata eliminata!")
            } else {
                openRenunciationAlertWindow("Errore nell'eliminazione", jsonObject.ErrorMsg);
            }
        });
    }
    openRenunciationConfirmWindow("Conferma operazione", "Sei sicuro di voler eliminare la domanda di rinuncia?", actionOnConfirm)
}
/*
 * Imposta la domanda di rinuncia come confermata
 */
function openConfirmRenunciationWindow(id, regChildId) {
    var actionOnConfirm = function() {
        comunicaConServlet(
        "ConfirmRenunciation", 
        {
            Id: id,
            IdIscrizione: regChildId
        }, 
        function(jsonObject) {
            if(jsonObject.IsSuccess) {
                updateTableSubmittedRinunciation();
                openRenunciationAlertWindow("Operazione riuscita", "La domanda di rinuncia è stata confermata!")
            } else {
                openRenunciationAlertWindow("Errore nella conferma", jsonObject.ErrorMsg);
            }
        });
    }
    openRenunciationConfirmWindow("Conferma operazione", "Sei sicuro di voler confermare la domanda di rinuncia?", actionOnConfirm)
}
// -----------------------------------------------------------------------------
// FUNZIONI PER LA VISUALE DI CREAZIONE DI UNA DOMANDA DI RINUNCIA
function closeRenunciationAddWindow() {
    setWindowVisibility("renunciationAddWindow", false);
    getElement("renunciationAddWindowMotivo").value = "";
    getElement("renunciationAddWindowMotivoError").innerHTML = "";
}
/*
 * Inserisce la nuova domanda di rinuncia
 */
function saveNewRenunciation() {
    var param = ["renunciationAddWindowMotivo"];
    if(valida(param)) {
        comunicaConServlet("InsertRenunciation", {
                    Motivazione: getValue("renunciationAddWindowMotivo"),
                    IdIscrizione: getValue("renunciationAddWindowId")
                }, function(result) {
                    if(result.IsSuccess) {
                        updateTableSubmittedRinunciation();
                        updateTablePossibleRinunciation();
                        openRenunciationAlertWindow("Operazione completata", "La domanda è stata sottomessa!");
                    } else {
                        openRenunciationAlertWindow("Operazione non riuscita", "Si è verificato il seguente errore:" + newLine() +
                                result.ErrorMsg);
                    }
                    closeRenunciationAddWindow();
                }
            );
    } else {
        //openRenunciationAlertWindow("Campi mancanti", "Compilare il campo nome!");
    }
}
// -----------------------------------------------------------------------------
// FUNZIONI PER LA VISUALE DEI DETTAGLI DI UNA DOMANDA DI RINUNCIA
function closeRenunciationViewDetailsWindow() {
    setWindowVisibility("renunciationViewDetailsWindow", false);
    // svuoto i campi della visuale dettagli
    getElement("renunciationViewDetailsWindowId").value = "";
    getElement("renunciationViewDetailsText").innerHTML = "";
}