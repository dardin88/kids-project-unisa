<%-- 
    Document   : header
    Created on : 01-dic-2012
    Author     : Pasquale Caldarese
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header">
    <input id="logo" type="image" src="img/logo.png" onclick="goToIndex()"/>
    <span id="title"></span>
    <span id="userMenu">
        <b class="userMenuEl"> Benvenuto ${sessionScope.user.getNameUser()}</b> 
        <u class="userMenuEl" id="logout" onclick="logout()" onmouseover="this.style.cursor = 'pointer';">Logout</u>
    </span>
</div>
<div id="navigation">
    <ul>
        <c:if test="${sessionScope.user.getAccountType()=='Segreteria'}">
            <div class="div">
                <li id="accountInformationAllMenuEL"><a href="accountInformationAll.jsp">Account</a></li>
            </div>
            <div class="div">
                <li id="registrationChildME"><a href="registrationChild.jsp">Domanda d'Iscrizione</a></li>
            </div>
            <div class="div">
                <li id="classificationME"><a href="classification.jsp">Graduatorie</a></li>
            </div>
            <div class="div">
                <li id="renunciationManagementME"><a href="renunciation.jsp">Rinunce</a></li>
            </div>
            <div class="div">
                <li id="recourseManagementME"><a href="recourse.jsp">Ricorsi</a></li>
            </div>
            <div class="div">
                <li id="paymentManagementMenuEl"><a href="paymentManagement.jsp">Pagamenti</a></li>
            </div>
            <div class="div">
                <li id="TimeServiceMenuEL"><a href="timeService.jsp">Orario di Servizio</a></li>
            </div> 
            <div class="div">
                <li id="managementTraineeActivity"><a href="managerTraineeActivity.jsp">Attivit&agrave Tirocinanti</a></li>
            </div>
            <div class="div">
                <li id="managementTraineeRequest"><a href="managerTraineeRequest.jsp">Richieste di Tirocinanti</a></li>
            </div> 
            <div class="div">
                <li id="surveyManagementME"><a href="secretarySurveyPage.jsp">Questionari Valutazione</a></li>
            </div>
            <div class="div">
                <li id="newsShowTable"><a href="newsShowTable.jsp">News</a></li>
            </div>  
            <div class="div">
                <li id="meetingCalendarMenuEL"><a href="meetingCalendar.jsp">Riunioni</a></li>
            </div>
        </c:if>

        <c:if test="${sessionScope.user.getAccountType()=='Tirocinante'}">
            <div class="div">
                <li id="accountInformationAllMenuEL"><a href="accountInformationAll.jsp">Account</a></li>
            </div>
            <div class="div">
                <li id="traineeRegister"><a href="traineeRegister.jsp">Registro attivit&agrave</a></li>
            </div>
            <div class="div">
                <li id="traineeConvocation"><a href="traineeConvocation.jsp">Convocazioni</a></li>
            </div>
            <div class="div">
                <li id="newsShowTable"><a href="newsShowTable.jsp">News</a></li>
            </div>  
        </c:if>

        <c:if test="${sessionScope.user.getAccountType()=='Responsabile Mensa'}">
            <div class="div">
                <li id="accountInformationAllMenuEL"><a href="accountInformationAll.jsp">Account</a></li>
            </div>
            <div class="div">
                <li id="canteenManagementMenuEl"><a href="canteenManagement.jsp">Mensa</a></li>
            </div>
            <div class="div">
                <li id="newsShowTable"><a href="newsShowTable.jsp">News</a></li>
            </div>  
        </c:if>

        <c:if test="${sessionScope.user.getAccountType()=='Delegato scienze della formazione'}">
            <div class="div">
                <li id="accountInformationAllMenuEL"><a href="accountInformationAll.jsp">Account</a></li>
            </div>
            <div class="div">
                <li id="notifications"><a href="formationScienceNotifications.jsp">Notifiche</a></li>
            </div>
            <div class="div">
                <li id="scheduler"><a href="schedulerActivity.jsp">Pianificazione Attivit&agrave</a></li>
            </div>
            <div class="div">
                <li id="trainees"><a href="trainees.jsp">Tirocinanti</a></li>
            </div>
            <div class="div">
                <li id="newsShowTable"><a href="newsShowTable.jsp">News</a></li>
            </div>  
            <div class="div">
                <li id="meetingCalendarMenuEL"><a href="meetingCalendar.jsp">Riunioni</a></li>
            </div>
        </c:if>

        <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">
            <div class="div">
                <li id="accountInformationParentMenuEL"><a href="accountInformationParent.jsp">Account</a></li>
            </div>
            <div class="div">
                <li id="registrationChildME"><a href="registrationChild.jsp">Domanda d'Iscrizione</a></li>
            </div>
            <div class="div">
                <li id="classificationME"><a href="classification.jsp">Graduatorie</a></li>
            </div>
            <div class="div">
                <li id="renunciationManagementME"><a href="renunciation.jsp">Rinunce</a></li>
            </div>
            <div class="div">
                <li id="recourseManagementME"><a href="recourse.jsp">Ricorsi</a></li>
            </div>
            <div class="div">
                <li id="paymentManagementMenuEl"><a href="paymentParent.jsp">Pagamenti</a></li>
            </div>
            <div class="div">
                <li id="canteenManagementMenuEl"><a href="canteenParent.jsp">Mensa</a></li>
            </div>
            <div class="div">
                <li id="TimeServiceMenuEL"><a href="timeService.jsp">Orario di Servizio</a></li>
            </div>
            <div class="div">
                <li id="surveyShowTableME"><a href="surveyShowTable.jsp">Questionari Valutazione</a></li>
            </div>
            <div class="div">
                <li id="newsShowTable"><a href="newsShowTable.jsp">News</a></li>
            </div>
            <div class="div">
                <li id="meetingCalendarMenuEL"><a href="meetingCalendar.jsp">Riunioni</a></li>
            </div>
            <div class="div">
                <li id="healthCommunicationTable"><a href="healthCommunicationTable.jsp">Comunicazioni Salute</a></li>
            </div>
            <div class="div">
                <li id="needCommunicationTable"><a href="needCommunicationTable.jsp">Comunicazioni Bisogni</a></li>
            </div>
        </c:if>

        <c:if test="${sessionScope.user.getAccountType()=='Educatore'}">
            <div class="div">
                <li id="accountInformationAllMenuEL"><a href="accountInformationAll.jsp">Account</a></li>
            </div>
            <div class="div">
                <li id="newsShowTable"><a href="newsShowTable.jsp">News</a></li>
            </div>  
            <div class="div">
                <li id="meetingCalendarMenuEL"><a href="meetingCalendar.jsp">Riunioni</a></li>
            </div>
            <div class="div">
                <li id="healthCommunicationTable"><a href="healthCommunicationTable.jsp">Comunicazioni Salute</a></li>
            </div>
            <div class="div">
                <li id="needCommunicationTable"><a href="needCommunicationTable.jsp">Comunicazioni Bisogni</a></li>         
            </div>
        </c:if>

        <c:if test="${sessionScope.user.getAccountType()=='Admin'}">  
            <div class="div">
                <li id="accountSecretaryMenuEL"><a href="accountSecretary.jsp">Account</a></li>
            </div>
            <div class="div">
                <li id="newsShowTable"><a href="newsShowTable.jsp">News</a></li>
            </div>  
            <div class="div">
                <li id="meetingCalendarMenuEL"><a href="meetingCalendar.jsp">Riunioni</a></li>
            </div> 
        </c:if>

        <c:if test="${sessionScope.user.getAccountType()=='Coordinatore Psicopedagogico'}">
            <div class="div">
                <li id="accountInformationAllMenuEL"><a href="accountInformationAll.jsp">Account</a></li>
            </div>
            <div class="div">
                <li id="annualProject"><a href="showProject.jsp">Programma Educativo</a></li>
            </div> 
        </c:if>

        <c:if test="${sessionScope.user.getAccountType()=='Responsabile Scientifico'}">
            <div class="div">
                <li id="accountInformationAllMenuEL"><a href="accountInformationAll.jsp">Account</a></li>
            </div>
             <div class="div">
                <li id="annualProject"><a href="showProject.jsp">Programma Educativo</a></li>
            </div>
        </c:if>

        <c:if test="${sessionScope.user.getAccountType()=='Delegato del rettore'}">
            <div class="div">
                <li id="accountSecretaryMenuEL"><a href="accountSecretary.jsp">Account</a></li>
            </div>
            <div class="div">
                <li id="classMenuEl"><a href="class.jsp">Classi</a></li>
            </div>
            <div class="div">
                <li id="TimeServiceMenuEL"><a href="timeService.jsp">Richieste modifica orari di servizio</a></li>
            </div> 
            <div class="div">
                <li id="newsShowTable"><a href="newsShowTable.jsp">News</a></li>
            </div>
            <div class="div">
                <li id="meetingCalendarMenuEL"><a href="meetingCalendar.jsp">Riunioni</a></li>
            </div>
             <div class="div">
                <li id="annualProject"><a href="showProject.jsp">Programma Educativo</a></li>
            </div>
        </c:if>

        <c:if test="${sessionScope.user.getAccountType()=='Responsabile Asilo'}">
            <div class="div">
                <li id="accountInformationAllMenuEL"><a href="accountInformationAll.jsp">Account</a></li>
            </div>
            <div class="div">
                <li id="classMenuEl"><a href="class.jsp">Classi</a></li>
            </div>
            <div class="div">
                <li id="TimeServiceMenuEL"><a href="timeService.jsp">Richieste modifica orari di servizio</a></li>
            </div> 
            <div class="div">
                <li id="newsShowTable"><a href="newsShowTable.jsp">News</a></li>
            </div>
        </c:if>

    </ul>
</div>
