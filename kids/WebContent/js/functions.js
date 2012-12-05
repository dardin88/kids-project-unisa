function logout(){
    window.location='Logout';
}

function goToIndex(){
    window.location.href="index.jsp";
}

function activePage(){
    var url=document.URL;
    urlArray=new Array();
    urlArray=url.split('/');
    currentPage=urlArray[urlArray.length-1];
    switch(currentPage){
        case 'insertTrainee.jsp':
            $("#insertTrainee").css("background-color","#444");
            $("#insertTrainee").css("color","#FFFFFF");
            $("#title").html("Gestione Tirocinante");
            break;
        case 'trainees.jsp':
            $("#trainees").css("background-color","#444");
            $("#trainees").css("color","#FFFFFF");
            $("#title").html("Gestione Tirocinante");
            break;
        case 'formationScienceNotifications.jsp':
            $("#notifications").css("background-color","#444");
            $("#notifications").css("color","#FFFFFF");
            $("#title").html("Gestione Tirocinante");
            break;
        case 'schedulerActivity.jsp':
            $("#scheduler").css("background-color","#444");
            $("#scheduler").css("color","#FFFFFF");
            $("#title").html("Gestione Tirocinante");
            break;
        case 'insertTraineeActivity.jsp':
            $("#insertTraineeActivity").css("background-color","#444");
            $("#insertTraineeActivity").css("color","#FFFFFF");
            $("#title").html("Gestione Tirocinante");
            break;
        case 'newsShowTable.jsp':
            $("#newsShowTable").css("background-color","#444");
            $("#newsShowTable").css("color","#FFFFFF");
            $("#title").html("Gestione News");
            break;
        case 'traineeRegister.jsp':
            $("#traineeRegister").css("background-color","#444");
            $("#traineeRegister").css("color","#FFFFFF");
            $("#title").html("Gestione Tirocinante");
            break;
        case 'traineeConvocation.jsp':
            $("#traineeConvocation").css("background-color","#444");
            $("#traineeConvocation").css("color","#FFFFFF");
            $("#title").html("Gestione Tirocinante");
            break;
        case 'meetingCalendar.jsp':
            $("#meetingCalendarMenuEL").css("background-color","#444");
            $("#meetingCalendarMenuEL").css("color","#FFFFFF");
            $("#title").html("Gestione Calendario");
            break;
        case 'managerTraineeActivity.jsp':
            $("#managementTraineeActivity").css("background-color","#444");
            $("#managementTraineeActivity").css("color","#FFFFFF");
            $("#title").html("Gestione Tirocinante");
            break;
        case 'managerTraineeRequest.jsp':
            $("#managementTraineeRequest").css("background-color","#444");
            $("#managementTraineeRequest").css("color","#FFFFFF");
            $("#title").html("Gestione Tirocinante");
            break;
            
    }
}

function messageDialog(){
    $("#confirm").dialog({
        autoOpen: true,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#confirmButton").button();
}