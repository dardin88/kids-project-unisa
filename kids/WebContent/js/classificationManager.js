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
    
    $("#classificationModifyWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#classificationModifyWindowUndo").button();
    $("#classificationModifyWindowUndo").clic(function() {
        setWindowVisibility("classificationModifyWindow", false);
    });
    
    
    $("#classificationAlertWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600,
        stack: true
    });
    $("#classificationAlertWindowOkButton").button();
    $("#classificationAlertWindowOkButton").clic(function() {
        setWindowVisibility("classificationAlertWindow", false);
    });
}
function setWindowVisibility(id, isVisible) {
    if(isVisible) {
        $("#" + id).dialog("open");
    } else {
        $("#" + id).dialog("close");
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
function classificationButtonActionSaveModify() {
    $("#classificationModifyForm").validate({
        rules: {
            Nome : true
        },
        messages: {
            Nome : "Inserire il nome"
        },
        submitHandler: function() {
            comunicaConServlet("ServletModifyClassification", {
                Id: getValue("classificationModifyWindowId"),
                Data: getValue("classificationModifyWindowData"),
                Stato: getValue("classificationModifyWindowStatus"),
                Nome: getValue("classificationModifyWindowNome")
            })
        }
    });
}
function openClassificationAlertWindow(newTitle, text, submitAction) {
    $("#registrationChildAlertWindow").dialog({title: newTitle});
    getElement("registrationChildAlertWindowTitle").innerHTML = text;
    
    setWindowVisibility("registrationChildAlertWindow", true);
    
    $("#registrationChildAlertWindowForm").validate({
        rules: {
        },
        messages: {
        },
        submitHandler: function() {
            if(submitAction != null) {
                submitAction();
            }
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