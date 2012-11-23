function initializeMeetingManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#addMeetingWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#addMeetingButton").button(); 
    
    $("#addMeetingButton").click(function() {
     $("#addMeetingWindow").dialog("open");
    });
   
    $("#addMeetingButtonSi").button();
    
    $("#addMeetingButtonNo").button();
    $("#addMeetingButtonNo").click(function(){
        $("#addMeetingWindow").dialog("close");
    });
   
    $.validator.setDefaults({
        highlight: function(input) {
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input) {
            $(input).removeClass("ui-state-highlight");
        }
    });
    addMeeting();
}

function addMeeting(){  
     $("#addMeetingButtonSi").click(function(){
        $("#addMeetingForm").validate({
            rules: {
                title: {
                    required: true,
                    remote: {
                        url:"VerifyArtefactExistence",
                        type: "post",
                        asyn: false,
                        data: {
                            titleMeeting: function(){
                                var title = $("#titleMeeting").val();
                                return title;
                            },
                            descriptionMeeting: function(){
                                var description = $("#descriptionMeeting").val();
                                return description;
                            },
                            dataMeeting: function(){
                                var data = $("#dataMeeting").val();
                                return data;
                            },
                            timeMeeting: function(){
                                var time = $("#timeMeeting").val();
                                return time;
                            },
                            typeMeeting: function(){
                                var type = $("typeMeeting").val();
                                return type;
                            }
                        }
                    }
                }
            },
            messages: {
                titleMeeting: {
                    required: "Inserisci il titolo."
                },
                descriptionMeeting: {
                    required: "Inserisci la descrizione."
                },
                dataMeeting: {
                    required: "inserisci la data."
                },
                timeMeeting: {
                    required: "Inserisci l'ora."
                },
                typeMeeting: {
                    required: "Inserire la tipologia."
                }
            }
        });
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

