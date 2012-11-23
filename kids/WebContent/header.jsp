<%-- 
    Document   : header
    Created on : 12-nov-2012, 10.02.24
    Author     : dario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header">
    <input id="logo" type="image" src="img/logo.png" onclick="goToIndex()"/>
    <span id="title">Gestione link di tracciabilità - Kids Project</span>
    <span id="userMenu">
        <u class="userMenuEl" id="logout" onclick="logout()" onmouseover="this.style.cursor='pointer';">Logout</u>
    </span>
</div>
<div id="navigation">
    <ul>
        <div class="div"><li id="artefactsTableMenuEL"><a href="artefactsTable.jsp">Gestisci artefatti</a></li></div>
        <div class="div"><li id="linksTableMenuEL"><a href="linksTable.jsp">Gestisci link di tracciabilità</a></li></div>
        <div class="div"><li id="searchLinksMenuEL"><a href="searchLinks.jsp">Cerca link di tracciabilità</a></li></div>
    </ul>
</div>

