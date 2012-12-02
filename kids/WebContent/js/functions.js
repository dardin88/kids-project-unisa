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
            $("#insertTrainee").css("background-color","#F82025");
            $("#insertTrainee").css("color","#FFFFFF");
            break;
        case 'trainees.jsp':
            $("#trainees").css("background-color","#F82025");
            $("#trainees").css("color","#FFFFFF");
            break;
        case 'formationScienceNotifications.jsp':
            $("#notifications").css("background-color","#F82025");
            $("#notifications").css("color","#FFFFFF");
            break;
        case 'schedulerActivity.jsp':
            $("#scheduler").css("background-color","#F82025");
            $("#scheduler").css("color","#FFFFFF");
            break;
        case 'insertTraineeActivity.jsp':
            $("#insertTraineeActivity").css("background-color","#F82025");
            $("#insertTraineeActivity").css("color","#FFFFFF");
            break;
        case 'newsInsertNews.jsp':
            //   $("#newsShowTable").css("background-color","#F82025");
            //    $("#newsShowTable").css("color","#FFFFFF");
            //rendere bottone sinistra invisibile
            break;
        case 'traineeRegister.jsp':
            $("#traineeRegister").css("background-color","#F82025");
            $("#traineeRegister").css("color","#FFFFFF");
            break;
        case 'traineeConvocation.jsp':
            $("#traineeConvocation").css("background-color","#F82025");
            $("#traineeConvocation").css("color","#FFFFFF");
            break;
        case 'meetingCalendar.jsp':
            $("#meetingCalendarMenuEL").css("background-color","#444");
            $("#meetingCalendarMenuEL").css("color","#FFFFFF");
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