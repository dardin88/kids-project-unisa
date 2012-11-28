<%-- 
    Document   : showCalendar.jsp
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
            <link rel="stylesheet" type="text/css" href="css/template.css" >
                <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css">
                    <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
                        <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css">
                            <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
                            <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
                            <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
                            <script type="text/javascript" src="js/jquery.validate.min.js"></script>
                            <script type="text/javascript" src="js/additional-methods.min.js"></script>
                            <script type="text/javascript" src="js/functions.js"></script>
                            <script type="text/javascript" src="./timePicker/jquery.timePicker.min.js"></script>
                            <script type="text/javascript" src="js/meetingManager.js"></script>
                            <link rel='stylesheet' type='text/css' href='./calendario/fullcalendar/fullcalendar.css' /> 
                            <script type='text/javascript' src='./calendario/jquery/jquery.js'></script> 
                            <script type='text/javascript' src='./calendario/jquery/jquery-ui.js'></script> 
                            <script type='text/javascript' src='./calendario/fullcalendar/fullcalendar.min.js'></script>
                            <title>Kids</title>
                            <script type='text/javascript'>
                                
                            </script>
                            <script type="text/javascript">
                                $(document).ready(function() {
                                    activePage();
                                    initializeMeetingManager();
                                    $("#dataMeeting").datepicker({dateFormat:'dd/mm/yy'});
                                    $("#firstTimeMeeting, #secondTimeMeeting").timePicker();
                                   
                                });
                            </script>

                            </head>

                            <div id="showMeetingWindow" title="Visualizza Riunione" style="display: inline">

                                <form id="showMeetingForm" class="cmxform" method="post" action="addMeetingServlet">
                                    <fieldset>
                                        <div id="artefactsManagement">
                                            <p class="formp">
                                                <label ><h3> Titolo: </h3></label>
                                                <label id="showTitleMeeting" name="showTitleMeeting"> title</label> <br>
                                            </p>
                                            <p class="formp">
                                                <label ><h3> Descrizione: </h3></label> 
                                                <label id="showDescriptionMeeting" name="showDescriptionMeeting" > description </label> <br> 
                                            </p>
                                            <p class="formp">
                                                <label ><h3 style="float: left"> Data: </h3></label> 
                                                <label style="float: left" id="showDataMeeting" name="showDataMeeting"> data </label> <br> <br>
                                                        <label style="float: left"><h3> Ora Inizio: </h3></label>
                                                        <label style="float: left" id="showFirstTimeMeeting" name="showFirstTimeMeeting"> oraInizio </label>
                                                        <label class="hourLabel" style="float: left"><h3> Ora Fine: </h3></label>
                                                        <label id="showSecondTimeMeeting" name="showSecondTimeMeeting"> oraFine </label> <br>  
                                                            </p>
                                                            <p class="formp"> 
                                                                <label  > <h3 style="float: left"> Tipologia: </h3></label>
                                                                <label id="showTypeMeeting" name="showTypeMeeting"> tipologia </label> <br> <br>
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

                                                                            <form id="newMeetingForm" class="cmxform" method="post" action="addMeetingServlet">
                                                                                <fieldset>
                                                                                    <div id="artefactsManagement">
                                                                                        <p class="formp">
                                                                                            <label ><h3> Titolo: </h3></label> <br>
                                                                                                <input id="titleMeeting" name="titleMeeting" type="text" size=50%> <br> <br>
                                                                                                            </p>
                                                                                                            <p class="formp">
                                                                                                                <label ><h3> Descrizione: </h3></label> <br>
                                                                                                                    <textarea id="descriptionMeeting" name="descriptionMeeting" class="textarea" rows="5" cols=100%> 
                                                                                                                    </textarea> <br> 
                                                                                                                        </p>
                                                                                                                        <p class="formp">
                                                                                                                            <label><h3 style="float: left"> Data </h3></label> 
                                                                                                                            <label class="hourLabel" style="float: left"><h3> Ora </h3></label> <br> <br>
                                                                                                                                    <input type="text" id="dataMeeting" name="dataMeeting">  
                                                                                                                                        <input type="text" id="firstTimeMeeting" name="firstTimeMeeting" autocomplete="OFF">
                                                                                                                                            <input type="text" id="secondTimeMeeting" name="secondTimeMeeting" autocomplete="OFF">
                                                                                                                                                </p>
                                                                                                                                                <p class="formp"> 
                                                                                                                                                    <legend> <h3> Tipologia: </h3></legend> <br>
                                                                                                                                                        <ul>
                                                                                                                                                            <input type="radio" id="typeMeeting" name="typeMeeting" checked> Riunione Comitato      
                                                                                                                                                                <input type="radio" id="typeMeeting" name="typeMeeting"> Riunione Consiglio Di Gestione   
                                                                                                                                                                    <input type="radio" id="typeMeeting" name="typeMeeting"> Riunione Scuola-Famiglia   
                                                                                                                                                                        </ul> <br>
                                                                                                                                                                            </p>
                                                                                                                                                                            <p>
                                                                                                                                                                                <input type="button" id="addMeetingButton" value="Inserisci Riunione" />
                                                                                                                                                                                <input type="button" id="notAddMeetingButton" value="Annula" />
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
                                                                                            <label ><h3> Titolo: </h3></label> <br>
                                                                                                <input id="modifyTitleMeeting" name="modifyTitleMeeting" type="text" size=50%> <br> <br>
                                                                                                            </p>
                                                                                                            <p class="formp">
                                                                                                                <label ><h3> Descrizione: </h3></label> <br>
                                                                                                                    <textarea id="modifyDescriptionMeeting" name="modifyDescriptionMeeting" class="textarea" rows="5" cols=100%> 
                                                                                                                    </textarea> <br> 
                                                                                                                        </p>
                                                                                                                        <p class="formp">
                                                                                                                            <label><h3 style="float: left"> Data </h3></label> 
                                                                                                                            <label class="hourLabel" style="float: left"><h3> Ora </h3></label> <br> <br>
                                                                                                                                    <input type="text" id="modifyDataMeeting" name="modifyDataMeeting">  
                                                                                                                                        <input type="text" id="modifyFirstTimeMeeting" name="modifyFirstTimeMeeting">
                                                                                                                                            <input type="text" id="modifySecondTimeMeeting" name="modifySecondTimeMeeting">
                                                                                                                                                </p>
                                                                                                                                                <p class="formp"> 
                                                                                                                                                    <legend> <h3> Tipologia: </h3></legend> <br>
                                                                                                                                                        <ul>
                                                                                                                                                            <input type="radio" id="modifyTypeMeeting" name="modifyTypeMeeting" checked> Riunione Comitato     
                                                                                                                                                                <input type="radio" id="modifyTypeMeeting" name="modifyTypeMeeting"> Riunione Consiglio Di Gestione  
                                                                                                                                                                    <input type="radio" id="modifyTypeMeeting" name="modifyTypeMeeting"> Riunione Scuola-Famiglia   
                                                                                                                                                                        </ul> <br>
                                                                                                                                                                            </p>
                                                                                                                                                                            <p>
                                                                                                                                                                                <input type="button" id="acceptModifyMeetingButton" value="Modifica Riunione" />
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
                                                                                                                                                                                            <label class="artefactLabel" for="artefact">Vuoi inserire la runione?</label>
                                                                                                                                                                                        </p>
                                                                                                                                                                                        <p class="formp">
                                                                                                                                                                                            <input type="button" class="confirmAddButton" id="addMeetingButtonSi" value="Si"/>
                                                                                                                                                                                            <input type="button" class="notConfirmAddButton" id="addMeetingButtonNo" value="No"/>
                                                                                                                                                                                        </p>
                                                                                                                                                                                    </fieldset>
                                                                                                                                                                                </form>
                                                                                                                                                                            </div>



                                                                                                                                                                            <body>
                                                                                                                                                                                <%@include file="header.jsp" %>
                                                                                                                                                                                <input type="button" class="confirmAddButton" id="newMeetingButton" value="Inserisci Riunione"/>
                                                                                                                                                                                <div id='meetingCalendar'></div>
                                                                                                                                                                                <%@include file="footer.jsp" %>
                                                                                                                                                                            </body>
                                                                                                                                                                            </html>
