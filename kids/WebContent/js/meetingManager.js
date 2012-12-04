
$(document).ready(function() {
    $('#meetingCalendar').fullCalendar({ 
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
                
                var firstHour = result[4].split(":");
                var secondHour = result[5].split(":");
                $("#modifyIdMeeting").val(event.id);
                $("#modifyTitleMeeting").val(result[1]);
                $("#modifyDescriptionMeeting").html(result[2]);
                $("#modifyDataMeeting").val(result[3]);
                $("#modifyFirstHourMeeting").val(firstHour[0]);
                $("#modifyFirstMinuteMeeting").val(firstHour[1]);
                $("#modifySecondHourMeeting").val(secondHour[0]);
                $("#modifySecondMinuteMeeting").val(secondHour[1]);
                $("#modifyTypeMeeting").val(result[6]);
            })
        }
        
    }); 	
});


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
    
    jQuery.validator.addMethod("differentHours",function(a,b){
         alert(a+b);
        var oraInizio = parseInt(b[0]);
        var oraFine = parseInt(b[1]);
       
        alert("Inizio: "+oraInizio+" Fine: "+ oraFine);
        if (oraInizio>=oraFine){
            alert("false");
            return false;
        }else{
            alert("true");
            return true;
        }     
    },jQuery.validator.format("Orario non valido"))
    
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
        width: 800
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
            meetingOraInizio: $("#firstHourMeeting").val()+":"+$("#firstMinuteMeeting").val()+":00",
            meetingOraFine: $("#secondHourMeeting").val()+":"+$("#secondMinuteMeeting").val()+":00",
            meetingTipo: $("#typeMeeting").val()
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
                OraInizio:{
                    required:true
                },
                OraFine:{
                    required:true
//                    differentHours: [c ,array[$("#firstHourMeeting").val(), $("#secondHourMeeting").val()]]
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
                    required: "Data obbligatoria",
                    differentHours:"Ora non valida"
                },
                OraInizio:{
                    required:"Ora di inizio obbligatoria"
                },
                OraFine:{
                    required:"Ora di fine obbligatoria",
                    remote:"ora fine sbagliata"
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
            modifyOraInizio: $("#modifyFirstHourMeeting").val()+":"+$("#modifyFirstMinuteMeeting").val()+":00",
            modifyOraFine: $("#modifySecondHourMeeting").val()+":"+$("#modifySecondMinuteMeeting").val()+":00",
            modifyTipo: $("#modifyTypeMeeting").val()
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
                OraInizio:{
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
                OraInizio:{
                    required:"Ora di inizio obbligatoria"
                },
                OraFine:{
                    required:"Ora di fine obbligatoria"
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





