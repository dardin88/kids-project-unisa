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
    $("#send").button();
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
    $("#insertTraineeActivity").dialog({
        autoOpen:false,
        modal:true,
        resizable:false,
        width:600
    });
    $("#insertTraineeActivityButton").button();
    $("#removeActivity").button();
}

function createTable(){
    $('#table').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTraineeTableActivity",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "Nome", 
                "value" : $('#Nome').val()
            },

            {
                "name" : "Cognome", 
                "value" : $('#Cognome').val()
            }
            );
     
        },
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Tirocinanti",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Tirocinanti",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },


        
        "aoColumns": [
        {
            "sWidth": "1%"
        },
        {
            "sWidth": "5%"
        },
        {
            "sWidth": "5%"
        },
        {
            "sWidth": "5%"
        }
        ]
    });
}

function search(){
    var oTable = $("#table").dataTable();
    oTable.fnDraw();
        
}

function qualifyFields(){
    document.getElementById("date").disabled=false;
    document.getElementById("name").readOnly=false;
    document.getElementById("description").readOnly=false;
    document.getElementById("startTime").disabled=false;
    document.getElementById("endTime").disabled=false;
    document.getElementById("opinion").readOnly=false;


}

$(document).ready(function(){
    $("#information").validate({
        rules:
        {
            
            Data:{
                required:true,
                date:true
            },
            Nome:"required",
            OraInizio:"required",
            OraFine:"required"
            
        },
        messages:{
            
            Data:" Inserisci il giorno in cui il tirocinante deve scolgere l'attivit&agrave",
            Nome:" Inserisci il nome dell'attivit&agrave",
            OraInizio:" Inserisci l'ora di inzio dell'attivit&agrave",
            OraFine:" Inserisci l'ora di fine dell'attivit&agrave"
        },
        submitHandler:function(form){
            form.submit();
        }
    });
});

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

function openDialogInsertTraineeActivity(){
    $("#insertTraineeActivity").dialog("open");
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
            document.getElementById("id").value=calEvent.id;
        }
    })
}