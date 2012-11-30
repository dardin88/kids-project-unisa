<%-- 
    Document   : header
    Created on : 12-nov-2012, 10.02.24
    Author     : dario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header">
    <input id="logo" type="image" src="img/logo.png" onclick="goToIndex()"/>

    <c:if test="${sessionScope.user.getTypeAccount()=='Delegato scienze della formazione'}">
        <span id="title">Gestione Tirocini-Delegato Scienze della formazione</span>
    </c:if>
    <c:if test="${sessionScope.user.getTypeAccount()=='Segreteria'}">
        <span id="title">Segreteria - Kids Project</span>

    </c:if>
    <span id="userMenu">
        <b class="userMenuEl"> Benvenuto ${sessionScope.user.getNameUser()}</b> 
        <u class="userMenuEl" id="logout" onclick="logout()" onmouseover="this.style.cursor='pointer';">Logout</u>
    </span>
</div>


<div id="navigation">
    <ul>
        <div class="div">
        </div>
    </ul>
</div>

<div id="navigation">
    <ul>
        <div class="div">

            <c:if test="${sessionScope.user.getAccountType()=='Segreteria'}">
                <li id="newsShowTable"><a href="newsShowTable.jsp">Gestione News</a></li>
                <li id="managementTraineeActivity"><a href="">Gestione Attivit&agrave Tirocinanti</a></li>
                <li id="traineeRequest"><a href="">Invia Richiesta di Tirocinanti</a></li>
            </c:if>

            <c:if test="${sessionScope.user.getTypeAccount()=='Delegato scienze della formazione'}">
                <li id="trainees"><a href="trainees.jsp">Gestione Tirocinanti</a></li>
                <li id="notifications"><a href="formationScienceNotifications.jsp">Notifiche</a></li>
                <li id="scheduler"><a href="schedulerActivity.jsp">Pianificazione attivit&agrave</a></li>
            </c:if>
            <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">  

                <li id="meetingCalendarMenuEL"><a href="meetingCalendar.jsp">Gestione Riunioni</a></li>
                <li id="newsShowTable"><a href="newsShowTable.jsp">Gestione News</a></li>
                <li id="healthCommunicationTable"><a href="healthCommunicationTable.jsp">Gestione Bambini - Comunicazioni Salute</a></li>
                <li id="needCommunicationTable"><a href="needCommunicationTable.jsp">Gestione Bambini - Comunicazioni Bisogni</a></li>
            </c:if>
            <c:if test="${sessionScope.user.getAccountType()=='Educatrice'}">  
                <li id="healthCommunicationTable"><a href="healthCommunicationTable.jsp">Gestione Bambini - Comunicazioni Salute</a></li>
                <li id="needCommunicationTable"><a href="needCommunicationTable.jsp">Gestione Bambini - Comunicazioni Bisogni</a></li>

            </c:if>
        </div>


    </ul>
</div>
