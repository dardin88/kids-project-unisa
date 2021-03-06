
function CalendarNotEditable() {
    $("#meetingCalendar").fullCalendar({ 
        header: { 
            left: 'prev,next today',
            center: 'title', 	
            right: 'month,agendaWeek,agendaDay'
        },
        
        editable: false, 
       
        allDayDefault: false,
        allDaySlot:false,

        events: {
            url:"LoadCalendar"
        },
        timeFormat: 'H(:mm)',
        
        eventDrop: function(event, dayDelta, minuteDelta){
            // alert(dayDelta+"min="+minuteDelta),
            modifyDate(event.id, dayDelta, minuteDelta)
        },
        
        eventClick: function(event){
            $("#showMeetingWindow").dialog("open");
            $.post("GetMeeting",{
                Id: event.id
            },function(data){
                var result = data.split(",")
                $("#showIdMeeting").val(result[0]);
                $("#showTitleMeeting").html(result[1]);
                $("#showDescriptionMeeting").html(result[2]);
                $("#showDataMeeting").html(result[3]);
                $("#showFirstTimeMeeting").html(result[4]);
                $("#showSecondTimeMeeting").html(result[5]);
                $("#showTypeMeeting").html(result[6]);
                
            })
        }
        
    }); 	
};


function CalendarEditable() {
    $("#meetingCalendar").fullCalendar({ 
        header: { 
            left: 'prev,next today',
            center: 'title', 	
            right: 'month,agendaWeek,agendaDay'
        },
        
        editable: true, 
       
        allDayDefault: false,
        allDaySlot:false,
               
        events: {
            url:"LoadCalendar"
        },
        timeFormat: 'H(:mm)',
        
        eventDrop: function(event, dayDelta, minuteDelta){
            // alert(dayDelta+"min="+minuteDelta),
            modifyDate(event.id, dayDelta, minuteDelta)
        },
        
        eventClick: function(event){
            $("#showMeetingWindow").dialog("open");
            $.post("GetMeeting",{
                Id: event.id
            },function(data){
                var result = data.split(",")
                $("#showIdMeeting").val(result[0]);
                $("#showTitleMeeting").html(result[1]);
                $("#showDescriptionMeeting").html(result[2]);
                $("#showDataMeeting").html(result[3]);
                $("#showFirstTimeMeeting").html(result[4]);
                $("#showSecondTimeMeeting").html(result[5]);
                $("#showTypeMeeting").html(result[6]);
                $("#showStateMeeting").html(result[7]);
                
                var firstHour = result[4].split(":");
                var secondHour = result[5].split(":");
                $("#modifyIdMeeting").val(event.id);
                $("#modifyTitleMeeting").val(result[1]);
                $("#modifyDescriptionMeeting").html(result[2]);
                $("#modifyDataMeeting").val(result[3]);
                $("#modifyStartTime").val(firstHour[0]+":"+firstHour[1]);
                $("#modifyEndTime").val(secondHour[0]+":"+secondHour[1]);
                setModifyValue(result[6]);
                setModifyState(result[7]);
            },"text")
        }
        
    }); 	
};


function initializeMeetingManager(){
    $.ajaxSetup({
        cache: false
    });
    
    $("#newMeetingWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 700
    });
    
    $.validator.setDefaults({
        highlight: function(input){
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input){
            $(input).removeClass("ui-state-highlight");
        }
    });
    
    $("#newMeetingButton").button();
    $("#newMeetingButton").click(function() {
        $("#newMeetingWindow").dialog("open");
     
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
        width: 700
    });
    
    $("#acceptModifyMeetingWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
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

    addMeetingManager(); 
    modifyMeetingManager();
    deleteMeetingManager();
}

function addMeetingManager(){ 
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
        $.post("AddMeeting", {
            meetingTitolo: $("#titleMeeting").val(),
            meetingDescrizione: $("#descriptionMeeting").val(),
            meetingData: $("#dataMeeting").val(),
            meetingOraInizio: $("#startTime").val()+":00",
            meetingOraFine: $("#endTime").val()+":00",
            meetingTipo: getTypeValue(),
            meetingStato: $("#state").val()
        });
        $("#addMeetingWindow").dialog("close");
        location.href = "./meetingCalendar.jsp";
    });
    $("#addMeetingButton").button();
    $("#addMeetingButton").click(function(){
        $("#newMeetingForm").validate({
            rules:
            {
                Titolo:{
                    required:true,
                    minlength: 5, 
                    maxlength:50
                },
                Descrizione:{
                    required:true,
                    maxlength: 500
                },
                Data:{
                    required:true
                },
                OraFine:{
                    required:true
                }
            },
            messages:{
                Titolo:{
                    required: "Titolo obbligatorio",
                    minlength: "Titolo di almeno 4 caratteri", 
                    maxlength: "Titolo di massimo 50 caratteri"
                },
                Descrizione:{
                    required: "Descrizione obbligatoria",
                    maxlength:"Descrizione di massimo 500 caratteri"
                },
                Data:{
                    required: "Data obbligatoria"
                },
                OraFine:{
                    required: "Ora non valida"
                }
            },
            submitHandler:function(){
                $("#addMeetingWindow").dialog("open");
            }
        });
    });   
}

function modifyMeetingManager(){ 
    $("#modifyMeetingButton").button();
    $("#modifyMeetingButton").click(function(){
        $("#modifyMeetingWindow").dialog("open"); 
    });
    $("#modifyMeetingButtonNo").button();
    $("#modifyMeetingButtonNo").click(function(){
        $("#acceptModifyMeetingWindow").dialog("close");
    });
    $("#modifyMeetingButtonSi").button();
    $("#modifyMeetingButtonSi").click(function(){ 
        $.post("ModifyMeeting", {
            modifyId: $("#modifyIdMeeting").val(),
            modifyTitolo: $("#modifyTitleMeeting").val(),
            modifyDescrizione: $("#modifyDescriptionMeeting").val(),
            modifyData: $("#modifyDataMeeting").val(),
            modifyOraInizio: $("#modifyStartTime").val()+":00",
            modifyOraFine: $("#modifyEndTime").val()+":00",
            modifyTipo: getModifyTypeValue(),
            modifyStato: $("#modifyState").val()

        });
        $("#modifyMeetingWindow").dialog("close");
        location.href = "./meetingCalendar.jsp";
    });
    $("#notAcceptModifyMeetingButton").button();
    $("#notAcceptModifyMeetingButton").click(function(){
        $("#modifyMeetingWindow").dialog("close"); 
    });
    $("#acceptModifyMeetingButton").button();
    $("#acceptModifyMeetingButton").click(function(){
        $("#modifyMeetingForm").validate({
            rules:
            {
                Titolo:{
                    required:true,
                    minlength: 5, 
                    maxlength:50
                },
                Descrizione:{
                    required:true,
                    maxlength: 500
                },
                Data:{
                    required:true
                },
                OraFine:{
                    required:true
                }
            },
            messages:{
                Titolo:{
                    required: "Titolo obbligatorio",
                    minlength: "Titolo di almeno 4 caratteri", 
                    maxlength: "Titolo di massimo 50 caratteri"
                },
                Descrizione:{
                    required: "Descrizione obbligatoria",
                    maxlength:"Descrizione di massimo 500 caratteri"
                },
                Data:{
                    required: "Data obbligatoria"
                },
                OraFine:{
                    required:"Ora non valida"
                }
            },
            submitHandler:function(){
                $("#acceptModifyMeetingWindow").dialog("open");
            }
        });
    });
}

function deleteMeetingManager(){
    $("#deleteMeetingButton").button();
    $("#deleteMeetingButton").click(function() {
        $.post("DeleteMeeting", {
            deleteId: $("#showIdMeeting").val()
        });
        $("#showMeetingWindow").dialog("close");
        location.href = "./meetingCalendar.jsp";
    });
}

function modifyDate(id, giorni, minuti){
    $.post("ModifyDate", {
        modifyDateId: id,
        modifyDateDay: giorni,
        modifyMinuteDay: minuti
    });
// alert("invio servlet");
// location.href = "./meetingCalendar.jsp";
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


function modifyTpStartOnHourShowCallback(hour) {
    var tpEndHour = $('#modifyEndTime').timepicker('getHour');
    if ($('#modifyEndTime').val() == '') {
        return true;
    }
    if (hour <= tpEndHour) {
        return true;
    }
    return false;
}
function modifyTpStartOnMinuteShowCallback(hour, minute) {
    var tpEndHour = $('#modifyEndTime').timepicker('getHour');
    var tpEndMinute = $('#modifyEndTime').timepicker('getMinute');
    if ($('#modifyEndTime').val() == '') {
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

function modifyTpEndOnHourShowCallback(hour) {
    var tpStartHour = $('#modifyStartTime').timepicker('getHour');
    if ($('#modifyStartTime').val() == '') {
        return true;
    }
    if (hour >= tpStartHour) {
        return true;
    }
    return false;
}
function modifyTpEndOnMinuteShowCallback(hour, minute) {
    var tpStartHour = $('#modifyStartTime').timepicker('getHour');
    var tpStartMinute = $('#modifyStartTime').timepicker('getMinute');
    if ($('#modifyStartTime').val() == '') {
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

function getTypeValue() { 
    var indice = 0; 
    for (i = 0; i < document.forms["insertForm"].Tipo.length; i++) { 
        if (document.forms["insertForm"].Tipo[i].checked) indice = i; 
    } 
    return document.forms["insertForm"].Tipo[indice].value; 
} 
function getModifyTypeValue() { 
    var indice = 0; 
    for (i = 0; i < document.forms["modifyForm"].Tipo.length; i++) { 
        if (document.forms["modifyForm"].Tipo[i].checked) indice = i; 
    } 
    return document.forms["modifyForm"].Tipo[indice].value; 
} 

function setModifyValue(valore) { 
    var indice=0; 
    if (valore.substr(0, 8)=='Comitato') indice = 0;
    if (valore.substr(0, 18)=='Consiglio Gestione') indice = 1;
    if (valore.substr(0, 15)=='Scuola-Famiglia') indice = 2
    document.forms["modifyForm"].Tipo[indice].checked=true; 
} 

function setModifyState(valore) { 
    var indice=0; 
    if (valore.substr(0, 14)=='Non Confermato') 
        document.getElementById('modifyState').options[0].selected=true;
    if (valore.substr(0, 10)=='Confermato')
        document.getElementById('modifyState').options[1].selected=true;
        } 
