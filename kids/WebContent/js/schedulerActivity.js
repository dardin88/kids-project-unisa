/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#activityInformation").dialog({
        autoOpen:false,
        resizable:false,
        width:350
    });
    
   
}
function createCalendar(){
    var layer;
    $('#calendar').fullCalendar({
        events:"GetTraineesActivityCalendar",
        
        header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
         eventClick: function(calEvent, jsEvent, view) {
            $("#activityInformation").dialog("open");
            document.getElementById("TraineeRegister").value=calEvent.trainee;
            document.getElementById("TraineeActivity").value=calEvent.activity;
            document.getElementById("DateActivity").value=calEvent.dateActivity;
            document.getElementById("Description").value=calEvent.description;
            document.getElementById("StartTimeActivity").value=calEvent.startTimeActivity;
            document.getElementById("EndTimeActivity").value=calEvent.endTimeActivity;
            document.getElementById("Opinion").value=calEvent.opinion;
        }
    })
}
