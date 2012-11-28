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
    
    //    $.validator.setDefaults({
    //        highlight: function(input){
    //            $(input).addClass("ui-state-highlight");
    //        },
    //        unhighlight: function(input){
    //            $(input).removeClass("ui-state-highlight");
    //        }
    //    });
    $("#newMeetingButton").button();
    $("#newMeetingButton").click(function() {
        $("#newMeetingWindow").dialog("open");
        meetingManager(); 
    });
    $("#addMeetingWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    
    
    $("#modifyMeetingWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 800
    });
    
    $("#showMeetingWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 500
    });
    
    $("#notMeetingButton").button();
    $("#notMeetingButton").click(function() {
        $("#showMeetingWindow").dialog("close");
    });
    
    $("#modifyMeetingButton").button();
    $("#modifyMeetingButton").click(function(){
        $("#modifyMeetingWindow").dialog("open"); 
    });
    
    $("#acceptModifyMeetingButton").button();
    $("#notAcceptModifyMeetingButton").button();
    $("#notAcceptModifyMeetingButton").click(function(){
        $("#modifyMeetingWindow").dialog("close"); 
    });
    $("#deleteMeetingButton").button();
}

function meetingManager(){ 
    $("#addMeetingButton").button();
    $("#addMeetingButton").click(function(){
        $("#newMeetingWindow").validate({
            rules:
            {
                titleMeeting:{
                    required:true,
                    minlength: 5, 
                    maxlength:50
                },
                descriptionMeeting:{
                    required:true,
                    maxlength: 500
                }
            //                dataMeeting:"required",
            //                firstTimeMeeting:"required",
            //                secondTimeMeeting:"required",
            //                typeMeeting:"required"
            },
            messages:{
                titleMeeting:{
                    required: "Titolo obbligatorio",
                    minlength: "Titolo di almeno 4 caratteri", 
                    maxlength: "Titolo di massimo 50 caratteri"
                },
                descriptionMeeting:{
                    required: "Descrizione obbligatoria",
                    maxlength:"Descrizione di massimo 500 caratteri"
                }
            //                dataMeeting:{
            //                    required: "Data obbligatoria"
            //                },
            //                firstTimeMeeting:{
            //                    required:"Ora di inizio obbligatoria"
            //                },
            //                secondTimeMeeting:{
            //                    required:"Ora di fine obbligatoria"
            //                },
            //                typeMeeting:{
            //                    required:"Tipo di riunione obbligatoria"
            //                }
            },
            submitHandler:function(){
                alert('bbbbb');
                $("#addMeetingWindow").dialog("open");
            }
        });
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
        //$("#newMeetingForm").submit();
        alert("djeihfio");
        
    });   
}


function loadingCalendar(){
    $("#showMeetingWindow").dialog("open");
    $.post("getMeetingCalendarServlet");
}



$(document).ready(function() {
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    $('#meetingCalendar').fullCalendar({ 
        header: { 
            left: 'prev,next today',
            center: 'title', 	
            right: 'month,agendaWeek,agendaDay'
        }, 	
        editable: true, 
        events: [
        { 		
            title: 'Evento che dura tutto il giorno', 	
            start: new Date(y, m, 1) 
        }, 		
        { 					
            title: 'Evento che dura piu giorni', 		
            start: new Date(y, m, 20), 	
            end: new Date(y, m, 27) 
        }, 			
        { 			
            id: 999, 	
            title: 'Evento ripetuto', 		
            start: new Date(y, m, d-3, 16, 0), 
            allDay: false 	
        },
        { 			
            id: 999, 	
            title: 'Evento ripetuto', 
            start: new Date(y, m, d+4, 16, 0), 
            allDay: false 			
        },
        { 			
            title: 'Incontro', 		
            start: new Date(y, m, d, 10, 30), 
            allDay: false 			
        }, 
        { 					
            title: 'Pranzo', 			
            start: new Date(y, m, d, 12, 0), 	
            end: new Date(y, m, d, 14, 0), 
            allDay: false 				
        },
        { 					
            title: 'CompleannoFabio', 				
            start: new Date(y, m, d+1, 19, 0), 	
            end: new Date(y, m, d+1, 22, 30), 
            allDay: false 			
        },
        { 					
            title: 'Evento con link esterno', 			
            start: new Date(y, m, 28), 		
            end: new Date(y, m, 29), 	
            url: 'http://www.mrwebmaster.it/' 
        }
        ], 
        eventClick: function(){
            loadingCalendar();
        }
    }); 	
});


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

