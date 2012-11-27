function initializeCommunicationManager(){
    $.ajaxSetup({
        cache: false
    });
    
    $("#newCommunicationWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 800
    });
    $("#newCommunicationButton").button();
    $("#newCommunicationButton").click(function() {
        $("#newCommunicationWindow").dialog("open");
        communicationManager();
    });
    
}

function communicationManager(){ 
    
    $("#addCommunicationButton").button();
    $("#addCommunicationButton").click(function() {
        $("#addCommunicationWindow").dialog("open");
    });
    
    $("#addCommunicationWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    
    $("#notAddCommunicationButton").button();
    $("#notAddCommunicationButton").click(function() {
        $("#newCommunicationWindow").dialog("close");
    });
   
}

function removeArtefact(aId){
    $("#removeArtefactWindow").dialog("open"); 
    $("#confirmRemoveArtefactButton").button();
    $("#confirmRemoveArtefactButton").click(function(){
        $.post("RemoveArtefact?"+new Date().getTime(), {
            aId: aId
        });
        $("#removeArtefactWindow").dialog("close"); 
        var oTable = $("#artefactsTable").dataTable();
        oTable.fnDraw();
    });
    $("#notConfirmRemoveArtefactButton").button();
    $("#notConfirmRemoveArtefactButton").click(function(){
        $("#removeArtefactWindow").dialog("close");
    });
}

