<%-- 
    Document   : header
    Created on : 12-nov-2012, 10.02.24
    Author     : dario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header">
    <c:if test="${sessionScope.user.getTypeAccount()=='Delegato scienze della formazione'}">
            <span id="title">Gestione Tirocini-Delegato Scienze della formazione</span>
    </c:if>
    <input id="logo" type="image" src="img/logo.png" onclick="goToIndex()"/>
    <c:if test="${sessionScope.user.getTypeAccount()=='Segreteria'}">
            <span id="title">Gestione News - Kids Project</span>

    </c:if>
    <span id="userMenu">
        <b class="userMenuEl"> Benvenuto ${sessionScope.user.getNameUser()}</b> 
        <u class="userMenuEl" id="logout" onclick="logout()" onmouseover="this.style.cursor='pointer';">Logout</u>
    </span>
</div>

<c:if test="${sessionScope.user.getAccountType()=='Segreteria'}">
    <div id="navigation">
        <ul>
            <div class="div">
                <li id="newsShowTable"><a href="newsShowTable.jsp">Gestione News</a></li>
            </div>
        </ul>
    </div>
</c:if>

<div id="navigation">
    <ul>
        <c:if test="${sessionScope.user.getTypeAccount()=='Delegato scienze della formazione'}">
            <li id="trainees"><a href="trainees.jsp">Gestione Tirocinanti</a></li>
            <li id="notifications"><a href="formationScienceNotifications.jsp">Notifiche</a></li>
            <li id="scheduler"><a href="schedulerActivity.jsp">Pianificazione attività</a></li>
        </c:if>
        <c:if test="${sessionScope.user.getAccountType()=='Genitore'}">  

            <div class="div"><li id="meetingCalendarMenuEL"><a href="meetingCalendar.jsp">Gestione Riunioni</a></li></div>
            <li id="newsShowTable"><a href="newsShowTable.jsp">Gestione News</a></li>
        </c:if>

    </ul>
</div>
