/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#confirm").dialog({
        autoOpen: true,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#confirmButton").button();

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
    $("#saveButton").button();
    $("#modifyButton").button();
    $("#DateRequest").datepicker({
        dateFormat:"yy-mm-dd"

    });
    $('#StartTimeRequest').timepicker({
        showLeadingZero: false,
        onHourShow: tpStartOnHourShowCallbackModify,
        onMinuteShow: tpStartOnMinuteShowCallbackModify,
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
    $('#EndTimeRequest').timepicker({
        showLeadingZero: false,
        onHourShow: tpEndOnHourShowCallbackModify,
        onMinuteShow: tpEndOnMinuteShowCallbackModify,
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
}
$(document).ready(function(){
    $("#information").validate({
        rules:
        {
            NumeroTirocinanti:{
                required:true,
                number:true,
                min:1
                    
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
        editable:true,
        eventClick: function(calEvent, jsEvent, view) {
            $("#requestInformation").dialog("open");
            document.getElementById("TraineeNumber").value=calEvent.traineeNumber;
            document.getElementById("DateRequest").value=calEvent.dateRequest;
            document.getElementById("Activity").value=calEvent.activity;
            document.getElementById("StartTimeRequest").value=calEvent.startTime;
            document.getElementById("EndTimeRequest").value=calEvent.endTime;
            document.getElementById("idRequest").value=calEvent.id;
        },
        eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {

            $.post("DropTraineeRequest",{
                id:event.id,
                start:event.start.getTime()+((dayDelta)*86400000),
                end:event.end.getTime()+((dayDelta)*86400000)

            })
            
        
        }
    })
    
                    
}
function modifyDialog(){
    document.getElementById("saveButton").style.visibility="visible";
    document.getElementById("modifyButton").style.visibility="hidden";
    document.getElementById("removeRequest").style.visibility="hidden";
    document.getElementById("TraineeNumber").disabled=false;
    document.getElementById("DateRequest").disabled=false;
    document.getElementById("Activity").disabled=false;
    document.getElementById("StartTimeRequest").disabled=false;
    document.getElementById("EndTimeRequest").disabled=false;
    document.getElementById("modifyInformation").action="ModifyTraineeRequest"
}

function tpStartOnHourShowCallbackModify(hour) {
    var tpEndHour = $('#EndTimeRequest').timepicker('getHour');
    if ($('#EndTimeRequest').val() == '') {
        return true;
    }
    if (hour <= tpEndHour) {
        return true;
    }
    return false;
}
function tpStartOnMinuteShowCallbackModify(hour, minute) {
    var tpEndHour = $('#EndTimeRequest').timepicker('getHour');
    var tpEndMinute = $('#EndTimeRequest').timepicker('getMinute');
    if ($('#EndTimeRequest').val() == '') {
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

function tpEndOnHourShowCallbackModify(hour) {
    var tpStartHour = $('#StartTimeRequest').timepicker('getHour');
    if ($('#StartTimeRequest').val() == '') {
        return true;
    }
    if (hour >= tpStartHour) {
        return true;
    }
    return false;
}
function tpEndOnMinuteShowCallbackModify(hour, minute) {
    var tpStartHour = $('#StartTimeRequest').timepicker('getHour');
    var tpStartMinute = $('#StartTimeRequest').timepicker('getMinute');
    if ($('#StartTimeRequest').val() == '') {
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

$(document).ready(function(){
    $("#modifyInformation").validate({
        rules:
        {
            NumeroTirocinanti:{
                required:true,
                number:true,
                min:1
                    
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