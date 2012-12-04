<%-- 
    Document   : meetingCalendar.jsp
    Created on : 21-nov-2012
    Author     : Pasquale
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>--%>
<!DOCTYPE html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" type="text/css" href="css/template.css" />
            <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css" />
            <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
            <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
            <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
            <script type="text/javascript" src="js/jquery.validate.min.js"></script>
            <script type="text/javascript" src="js/additional-methods.min.js"></script>
            <script type='text/javascript' src='./calendario/fullcalendar/fullcalendar.min.js'></script>
            <script type="text/javascript" src="js/functions.js"></script>
            <script type="text/javascript" src="js/meetingManager.js"></script>
            <link rel='stylesheet' type='text/css' href='./calendario/fullcalendar/fullcalendar.css' /> 
            <title>Kids</title>
            <script type="text/javascript">
                $(document).ready(function() {
                    activePage();
                    initializeMeetingManager();
                    $("#dataMeeting,#modifyDataMeeting").datepicker({dateFormat:'yy-mm-dd'});
                });
            </script>

    </head>



    <div id="showMeetingWindow" title="Visualizza Riunione" style="display: inline">

        <form id="showMeetingForm" class="cmxform" method="post" action="">
            <fieldset>
                <input type="hidden" name="showIdMeeting" id="showIdMeeting">
                    <div id="artefactsManagement">
                        <p class="formp">
                            <label ><h3> Titolo: </h3></label>
                            <label id="showTitleMeeting" name="showTitleMeeting"></label> <br>
                        </p>
                        <p class="formp">
                            <label ><h3> Descrizione: </h3></label> 
                            <label id="showDescriptionMeeting" name="showDescriptionMeeting" ></label> <br> 
                        </p>
                        <p class="formp">
                            <label ><h3 style="float: left"> Data: </h3></label> 
                            <label style="float: left; margin-left: 2%;" id="showDataMeeting" name="showDataMeeting"></label> <br> <br>
                                    <label style="float: left"><h3> Ora Inizio: </h3></label>
                                    <label style="float: left; margin-left: 2%;" id="showFirstTimeMeeting" name="showFirstTimeMeeting"></label>
                                    <label class="hourLabel" style="float: left"><h3> Ora Fine: </h3></label>
                                    <label style="margin-left: 2%;"id="showSecondTimeMeeting" name="showSecondTimeMeeting"></label> <br>  
                                        </p>
                                        <p class="formp"> 
                                            <label> <h3 style="float: left"> Tipologia: </h3></label>
                                            <label style="margin-left: 2%;" id="showTypeMeeting" name="showTypeMeeting"></label> <br> <br>
                                                    </p>
                                                    <p>
                                                        <input type="button" id="modifyMeetingButton" value="Modifica Riunione" />
                                                        <input type="button" id="deleteMeetingButton" value="Elimina Riunione" />
                                                        <input type="button" id="notMeetingButton" value="Annulla" />
                                                    </p>
                                                    </div>
                                                    </fieldset>
                                                    </form>
                                                    </div>



                                                    <div id="newMeetingWindow" title="Inserisci Riunione" style="display: inline">

                                                        <form id="newMeetingForm" class="cmxform" method="post" action="">
                                                            <fieldset>
                                                                <div id="artefactsManagement">
                                                                    <p class="formp">
                                                                        <label ><h3> Titolo: </h3></label> <br>
                                                                            <input id="titleMeeting" name="Titolo" type="text" size=50% style="margin-right: 2%"> </input> <br> <br>
                                                                                    </p>
                                                                                    <p class="formp">
                                                                                        <label ><h3> Descrizione: </h3></label> <br>
                                                                                            <textarea id="descriptionMeeting" name="Descrizione" class="textarea" rows="5" cols=100%> 
                                                                                            </textarea> <br> 
                                                                                                </p>
                                                                                                <p class="formp">
                                                                                                    <label><h3 style="float: left"> Data </h3></label> 
                                                                                                    <label class="hourLabel" style="float: left"><h3> Ora </h3></label> <br> <br>
                                                                                                            <input type="text" id="dataMeeting" name="Data" style="margin-right: 2%">   </input>
                                                                                                            <label> Inizio: </label>
                                                                                                            <select id="firstHourMeeting" name="OraInizio">
                                                                                                                <%
                                                                                                                    int i;
                                                                                                                    for (i = 0; i < 24; i++) {
                                                                                                                        out.println("<option name='firstHour'> " + i + " </option>");
                                                                                                                    }
                                                                                                                %>
                                                                                                            </select>


                                                                                                            <select id="firstMinuteMeeting" name="MinutiInizio">
                                                                                                                <option name='firstMinute'> 00 </option>
                                                                                                                <option name='firstMinute'> 15 </option>
                                                                                                                <option name='firstMinute'> 30 </option>
                                                                                                                <option name='firstMinute'> 45 </option>
                                                                                                            </select>

                                                                                                            <label> Fine: </label>
                                                                                                            <select id="secondHourMeeting" name="OraFine">
                                                                                                                <%
                                                                                                                    for (i = 0; i < 24; i++) {
                                                                                                                        out.println("<option name='secondHour'> " + i + " </option>");
                                                                                                                    }
                                                                                                                %>
                                                                                                            </select>
                                                                                                            <select id="secondMinuteMeeting" name="MinutiFine">
                                                                                                                <option name='secondMinute'> 00 </option>
                                                                                                                <option name='secondMinute'> 15 </option>
                                                                                                                <option name='secondMinute'> 30 </option>
                                                                                                                <option name='secondMinute'> 45 </option>
                                                                                                            </select>
                                                                                                            </p>
                                                                                                            <p class="formp"> 
                                                                                                                <legend> <h3> Tipologia: </h3></legend> <br>
                                                                                                                    <ul>
                                                                                                                        <input type="radio" id="typeMeeting" name="Tipo" value="Comitato" checked > Riunione Comitato      
                                                                                                                            <input type="radio" id="typeMeeting" name="Tipo" value="Gestione" > Riunione Consiglio Di Gestione  
                                                                                                                                <input type="radio" id="typeMeeting" name="Tipo" value="Scuola-Famiglia"> Riunione Scuola-Famiglia   
                                                                                                                                    </ul> <br>
                                                                                                                                        </p>
                                                                                                                                        <p>
                                                                                                                                            <input type="submit" id="addMeetingButton" value="Inserisci Riunione" > </input>
                                                                                                                                            <input type="button" id="notAddMeetingButton" value="Annulla" > </input>
                                                                                                                                        </p>
                                                                                                                                        </div>
                                                                                                                                        </fieldset>
                                                                                                                                        </form>
                                                                                                                                        </div>



                                                                                                                                        <div id="modifyMeetingWindow" title="Modifica Riunione" style="display: inline">

                                                                                                                                            <form id="modifyMeetingForm" class="cmxform" method="post" action="addMeetingServlet">
                                                                                                                                                <fieldset>
                                                                                                                                                    <div id="artefactsManagement">
                                                                                                                                                        <p class="formp">
                                                                                                                                                            <input id="modifyIdMeeting" name="Id" type="hidden" size=50%>
                                                                                                                                                                <label ><h3> Titolo: </h3></label> <br>
                                                                                                                                                                    <input id="modifyTitleMeeting" name="Titolo" type="text" size=50%> <br> <br>
                                                                                                                                                                                </p>
                                                                                                                                                                                <p class="formp">
                                                                                                                                                                                    <label ><h3> Descrizione: </h3></label> <br>
                                                                                                                                                                                        <textarea id="modifyDescriptionMeeting" name="Descrizione" class="textarea" rows="5" cols=100%> 
                                                                                                                                                                                        </textarea> <br> 
                                                                                                                                                                                            </p>
                                                                                                                                                                                            <p class="formp">
                                                                                                                                                                                                <label><h3 style="float: left"> Data </h3></label> 
                                                                                                                                                                                                <label class="hourLabel" style="float: left"><h3> Ora </h3></label> <br> <br>
                                                                                                                                                                                                        <input type="text" id="modifyDataMeeting" name="Data">  
                                                                                                                                                                                                        <label> Inizio: </label>
                                                                                                                                                                                                        <select id="modifyFirstHourMeeting" name="OraInizio" >
                                                                                                                                                                                                        <%
                                                                                                                                                                                                        for (i = 0; i < 24; i++) {
                                                                                                                                                                                                        out.println("<option name='firstHour'> " + i + " </option>");
                                                                                                                                                                                                        }
                                                                                                                                                                                                        %>
                                                                                                                                                                                                        </select>

                                                                                                                                                                                                        <select id="modifyFirstMinuteMeeting" name="MinutiInizio">
                                                                                                                                                                                                        <option name='firstMinute'> 00 </option>
                                                                                                                                                                                                        <option name='firstMinute'> 15 </option>
                                                                                                                                                                                                        <option name='firstMinute'> 30 </option>
                                                                                                                                                                                                        <option name='firstMinute'> 45 </option>
                                                                                                                                                                                                        </select>

                                                                                                                                                                                                        <label> Fine: </label>
                                                                                                                                                                                                        <select id="modifySecondHourMeeting" name="OraFine">
                                                                                                                                                                                                        <%
                                                                                                                                                                                                        for (i = 0; i < 24; i++) {
                                                                                                                                                                                                        out.println("<option name='secondHour'> " + i + " </option>");
                                                                                                                                                                                                        }
                                                                                                                                                                                                        %>
                                                                                                                                                                                                        </select>
                                                                                                                                                                                                        <select id="modifySecondMinuteMeeting" name="MinutiFine">
                                                                                                                                                                                                        <option name='firstMinute'> 00 </option>
                                                                                                                                                                                                        <option name='firstMinute'> 15 </option>
                                                                                                                                                                                                        <option name='firstMinute'> 30 </option>
                                                                                                                                                                                                        <option name='firstMinute'> 45 </option>
                                                                                                                                                                                                        </select>
                                                                                                                                                                                                        </p>
                                                                                                                                                                                                        <p class="formp"> 
                                                                                                                                                                                                        <legend> <h3> Tipologia: </h3></legend> <br>
                                                                                                                                                                                                        <ul>
                                                                                                                                                                                                        <input type="radio" id="modifyTypeMeeting" name="Tipo" value="Comitato" checked> Riunione Comitato     
                                                                                                                                                                                                        <input type="radio" id="modifyTypeMeeting" name="Tipo" value="Consiglio"> Riunione Consiglio Di Gestione  
                                                                                                                                                                                                        <input type="radio" id="modifyTypeMeeting" name="Tipo" value="Scuola-Famiglia"> Riunione Scuola-Famiglia   
                                                                                                                                                                                                        </ul> <br>
                                                                                                                                                                                                        </p>
                                                                                                                                                                                                        <p>
                                                                                                                                                                                                        <input type="submit" id="acceptModifyMeetingButton" value="Modifica Riunione" />
                                                                                                                                                                                                        <input type="button" id="notAcceptModifyMeetingButton" value="Annula" />
                                                                                                                                                                                                        </p>
                                                                                                                                                                                                        </div>
                                                                                                                                                                                                        </fieldset>
                                                                                                                                                                                                        </form>
                                                                                                                                                                                                        </div>




                                                                                                                                                                                                        <div id="addMeetingWindow" title="Inserisci Riunione" style="display: inline">
                                                                                                                                                                                                        <form id="addMeetingForm" class="cmxform" method="get" action="">
                                                                                                                                                                                                        <fieldset>
                                                                                                                                                                                                        <p class="formp">
                                                                                                                                                                                                        <label class="artefactLabel" for="artefact">Vuoi inserire la riunione?</label>
                                                                                                                                                                                                        </p>
                                                                                                                                                                                                        <p class="formp">
                                                                                                                                                                                                        <input type="button" class="confirmAddButton" id="addMeetingButtonSi" value="Si"/>
                                                                                                                                                                                                        <input type="button" class="notConfirmAddButton" id="addMeetingButtonNo" value="No"/>
                                                                                                                                                                                                        </p>
                                                                                                                                                                                                        </fieldset>
                                                                                                                                                                                                        </form>
                                                                                                                                                                                                        </div>


                                                                                                                                                                                                        <div id="acceptModifyMeetingWindow" title="Modifica Riunione" style="display: inline">
                                                                                                                                                                                                        <form id="acceptModityMeetingForm" class="cmxform" method="get" action="">
                                                                                                                                                                                                        <fieldset>
                                                                                                                                                                                                        <p class="formp">
                                                                                                                                                                                                        <label class="artefactLabel" for="artefact">Vuoi modificare la riunione?</label>
                                                                                                                                                                                                        </p>
                                                                                                                                                                                                        <p class="formp">
                                                                                                                                                                                                        <input type="button" class="confirmAddButton" id="modifyMeetingButtonSi" value="Si"/>
                                                                                                                                                                                                        <input type="button" class="notConfirmAddButton" id="modifyMeetingButtonNo" value="No"/>
                                                                                                                                                                                                        </p>
                                                                                                                                                                                                        </fieldset>
                                                                                                                                                                                                        </form>
                                                                                                                                                                                                        </div>

                                                                                                                                                                                                        <body>
                                                                                                                                                                                                        <%@include file="header.jsp" %>
                                                                                                                                                                                                        <input type="button"  id="newMeetingButton" style="position: absolute; left: 15%" value="Inserisci Riunione"/> 
                                                                                                                                                                                                        <div id='meetingCalendar' style="width: 60%"></div>
                                                                                                                                                                                                        <%@include file="footer.jsp" %>
                                                                                                                                                                                                        </body>
                                                                                                                                                                                                        </html>
