function initializeMeetingManager(){
    $.ajaxSetup({
        cache: false
    });
    
    $("#newMeetingWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 800
    });
    $("#newMeetingButton").button();
    $("#newMeetingButton").click(function() {
        $("#newMeetingWindow").dialog("open");
        meetingManager();
    });
    
}

function meetingManager(){ 
    
    
    
    $("#addMeetingButton").button();
    $("#addMeetingButton").click(function() {
        $("#addMeetingWindow").dialog("open");
    });
    
    $("#addMeetingWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    
    $("#notAddMeetingButton").button();
    $("#notAddMeetingButton").click(function() {
        $("#newMeetingWindow").dialog("close");
    });
    
    $("#addMeetingButtonNo").button();
    $("#addMeetingButtonNo").click(function(){
        $("#addMeetingWindow").dialog("close");
    });
    
    $("#addMeetingButtonSi").button();
    $("#addMeetingButtonSi").click(function(){ 
        $("#addMeetingWindow").dialog("close");
        $("#commentForm").validate();
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

