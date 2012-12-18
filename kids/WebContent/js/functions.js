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
            
        case 'accountInformationParent.jsp':
            $("#accountInformationParentMenuEL").css("background-color","#444");
            $("#accountInformationParentMenuEL").css("color","#FFFFFF");
            $("#title").html("Gestione Account - Visualizza dati");
            break;
        case 'accountInformationAll.jsp':
            $("#accountInformationAllMenuEL").css("background-color","#444");
            $("#accountInformationAllMenuEL").css("color","#FFFFFF");
            $("#title").html("Gestione Account - Visualizza dati");
            break;
        case 'accountSecretary.jsp':
            $("#accountSecretaryMenuEL").css("background-color","#444");
            $("#accountSecretaryMenuEL").css("color","#FFFFFF");
            $("#title").html("Gestione Account - Visualizza dati");
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
        case 'paymentManagement.jsp':
            $("#paymentManagementMenuEl").css("background-color", "#444");
            $("#paymentManagementMenuEl").css("color", "#FFFFFF");
            $("#title").html("Gestione Pagamenti - Ricerca genitore");
            break;
        case 'paymentParent.jsp':
            $("#paymentManagementMenuEl").css("background-color", "#444");
            $("#paymentManagementMenuEl").css("color", "#FFFFFF");
            $("#title").html("Gestione Pagamenti");
            break;
        case 'timeService.jsp':
            $("#TimeServiceMenuEL").css("background-color", "#444");
            $("#TimeServiceMenuEL").css("color", "#FFFFFF");
            $("#title").html("Gestione orario di servizio");
            break;
        case 'canteenManagement.jsp':
            $("#canteenManagementMenuEl").css("background-color", "#444");
            $("#canteenManagementMenuEl").css("color", "#FFFFFF");
            $("#title").html("Gestione mensa");
            break;
        case 'healthCommunicationTable.jsp':
            $("#healthCommunicationTable").css("background-color", "#444");
            $("#healthCommunicationTable").css("color", "#FFFFFF");
            $("#title").html("Gestione bambini - Comunicazioni salute");
            break;
        case 'needCommunicationTable.jsp':
            $("#needCommunicationTable").css("background-color", "#444");
            $("#needCommunicationTable").css("color", "#FFFFFF");
            $("#title").html("Gestione bambini - Comunicazioni bisogni");
            break;
        case 'showProject.jsp':
            $("#coordinatorePsico").css("background-color", "#444");
            $("#coordinatorePsico").css("color", "#FFFFFF");
            $("#title").html("Gestione Programma Educativo");
            break;
        case 'registrationChild.jsp':
            $("#registrationChildME").css("background-color", "#444");
            $("#registrationChildME").css("color", "#FFFFFF");
            $("#title").html("Gestione Domande di Iscrizione");
            break;
        case 'classification.jsp':
            $("#classificationME").css("background-color", "#444");
            $("#classificationME").css("color", "#FFFFFF");
            $("#title").html("Gestione Graduatorie");
            break;
        case 'secretarySurveyPage.jsp':
            $("#surveyManagementME").css("background-color", "#444");
            $("#surveyManagementME").css("color", "#FFFFFF");
            $("#title").html("Gestione Questionari Valutazione");
            break;
        case 'surveyShowTable.jsp':
            $("#surveyShowTableME").css("background-color", "#444");
            $("#surveyShowTableME").css("color", "#FFFFFF");
            $("#title").html("Questionari Valutazione");
            break;
        case 'renunciation.jsp':
            $("#renunciationManagementME").css("background-color", "#444");
            $("#renunciationManagementME").css("color", "#FFFFFF");
            $("#title").html("Gestione rinunce");
            break;
        case 'canteenParent.jsp':
            $("#canteenManagementMenuEl").css("background-color", "#444");
            $("#canteenManagementMenuEl").css("color", "#FFFFFF");
            $("#title").html("Gestione Mensa");
            break;
        case 'class.jsp':
            $("#classMenuEl").css("background-color", "#444");
            $("#classMenuEl").css("color", "#FFFFFF");
            $("#title").html("Gestione Classi");
            break;
        case 'classInformation.jsp':
            $("#classMenuEl").css("background-color", "#444");
            $("#classMenuEl").css("color", "#FFFFFF");
            $("#title").html("Gestione Classi");
            break;
        case 'classInsert.jsp':
            $("#classMenuEl").css("background-color", "#444");
            $("#classMenuEl").css("color", "#FFFFFF");
            $("#title").html("Gestione Classi");
            break;
        case 'classModify.jsp':
            $("#classMenuEl").css("background-color", "#444");
            $("#classMenuEl").css("color", "#FFFFFF");
            $("#title").html("Gestione Classi");
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
