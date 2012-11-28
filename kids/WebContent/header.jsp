<%-- 
    Document   : header
    Created on : 12-nov-2012, 10.02.24
    Author     : dario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header">
    <input id="logo" type="image" src="img/logo.png" onclick="goToIndex()"/>
    <span id="title">Gestione News - Kids Project</span>
    <span id="userMenu">
        <b class="userMenuEl"> Benvenuto ${sessionScope.user.getNameUser()}</b> 
        <u class="userMenuEl" id="logout" onclick="logout()" onmouseover="this.style.cursor='pointer';">Logout</u>
    </span>
</div>

<!--    <li id="newsInsertTable"><a href="insertTable.jsp">Inserisci News</a></li>
        <li id="newsModifyTable"><a href="modifyTable.jsp">Modifica News</a></li>
        <li id="newsSearchTable"><a href="newsSearchTable.jsp">Ricerca News</a></li>
        <li id="newsDeleteTable"><a href="deleteLinks.jsp">Elimina News</a></li> -->

</ul>
</div>
<c:if test="${sessionScope.user.getAccountType()=='Genitore'}">  
    <div id="navigation">
        <ul>
            <!--  <li id="newsSearchTable"><a href="newsSearchTable.jsp">Ricerca News</a></li> -->
            <div class="div"><li id="meetingCalendarMenuEL"><a href="meetingCalendar.jsp">Gestione Riunioni</a></li></div>
            <li id="newsShowTable"><a href="newsShowTable.jsp">Gestione News</a></li>
        </ul>
    </div>
</c:if>
