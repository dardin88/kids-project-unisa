/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#send").button();
    $("#insertTraineeRequest").dialog({
        autoOpen:false,
        modal:true,
        resizable:false,
        width:400
    });
    $("#requestInformation").dialog({
        autoOpen:false,
        modal:true,
        resizable:false,
        width:400
    });
    $("#InsertTraineeRequest").button();
    $("#date").datepicker({
        dateFormat:"yy-mm-dd"

    });
    $('#startTime').timepicker({
        showLeadingZero: false,
        onHourShow: tpStartOnHourShowCallback,
        onMinuteShow: tpStartOnMinuteShowCallback,
        hours: {
            starts: 0,               
            ends: 23                  
        },
        minutes: {
            starts: 0,                
            ends: 59,                 
            interval: 1               
        },
        showCloseButton: true       


    });
    $('#endTime').timepicker({
        showLeadingZero: false,
        onHourShow: tpEndOnHourShowCallback,
        onMinuteShow: tpEndOnMinuteShowCallback,
        hours: {
            starts: 0,                
            ends: 23                  
        },
        minutes: {
            starts: 0,                
            ends: 59,                 
            interval: 1               
        },
        showCloseButton: true       

    });
    $("#removeRequest").button();
}
$(document).ready(function(){
    $("#information").validate({
        rules:
        {
            NumeroTirocinanti:{
                required:true,
                number:true
                    
            },
            Data:{
                required:true,
                date:true
            },
            Attivita:"required",
            OraInizio:"required",
            OraFine:"required"
            
        },
        messages:{
            NumeroTirocinanti:"Inserisci il numero di tirocinanti",
            Data:" Inserisci il giorno in cui sono richiesti i tirocinanti",
            Attivita:"Inserisci l'attivit&agrave da svolgere",
            OraInizio:" Inserisci l'ora di inzio dell'attivit&agrave",
            OraFine:" Inserisci l'ora di fine dell'attivit&agrave"
        },
        submitHandler:function(form){
            form.submit();
        }
    });
});

function openInsertTraineeRequestDialog(){
    $("#insertTraineeRequest").dialog("open");
}
function tpStartOnHourShowCallback(hour) {
    var tpEndHour = $('#endTime').timepicker('getHour');
    if ($('#endTime').val() == '') {
        return true;
    }
    if (hour <= tpEndHour) {
        return true;
    }
    return false;
}
function tpStartOnMinuteShowCallback(hour, minute) {
    var tpEndHour = $('#endTime').timepicker('getHour');
    var tpEndMinute = $('#endTime').timepicker('getMinute');
    if ($('#endTime').val() == '') {
        return true;
    }
    if (hour < tpEndHour) {
        return true;
    }
    if ( (hour == tpEndHour) && (minute < tpEndMinute) ) {
        return true;
    }
    return false;
}

function tpEndOnHourShowCallback(hour) {
    var tpStartHour = $('#startTime').timepicker('getHour');
    if ($('#startTime').val() == '') {
        return true;
    }
    if (hour >= tpStartHour) {
        return true;
    }
    return false;
}
function tpEndOnMinuteShowCallback(hour, minute) {
    var tpStartHour = $('#startTime').timepicker('getHour');
    var tpStartMinute = $('#startTime').timepicker('getMinute');
    if ($('#startTime').val() == '') {
        return true;
    }
    if (hour > tpStartHour) {
        return true;
    }
    if ( (hour == tpStartHour) && (minute > tpStartMinute) ) {
        return true;
    }
    return false;
}
function createCalendar(){
    var layer;
    $('#calendar').fullCalendar({
        events:"GetRequestCalendar",

        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        eventClick: function(calEvent, jsEvent, view) {
            $("#requestInformation").dialog("open");
            document.getElementById("TraineeNumber").value=calEvent.traineeNumber;
            document.getElementById("DateRequest").value=calEvent.dateRequest;
            document.getElementById("Activity").value=calEvent.activity;
            document.getElementById("StartTimeRequest").value=calEvent.startTime;
            document.getElementById("EndTimeRequest").value=calEvent.endTime;
        }
    /* eventMouseover: function( event, jsEvent, view ) 
        {
            layer="<div name=\"dialog\" id=\"dialog\" style=\"width:400px;height:100px;background-color:grey;\"> Titolo:"+event.title+"<br>Data Inizio:"+event.start+"<br>Data Fine:"+event.end+"<br>Attivit&agrave:"+event.activity+"</div>";

            $(this).append(layer);
                        
        },
        eventMouseout: function(event,jsEvent,view){
            var box = document.getElementById("dialog");
                        

            box.parentNode.removeChild(box);
        }
        */
    })
    
                    
}