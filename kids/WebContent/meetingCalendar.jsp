<%-- 
    Document   : meetingCalendar.jsp
    Created on : 21-nov-2012
    Author     : Pasquale Caldarese
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user==null}">
    <c:redirect url="index.jsp" />
</c:if>
<!DOCTYPE html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

            <link rel="stylesheet" type="text/css" href="css/template.css" />
            <link rel="stylesheet" type="text/css" href="css/overcast/jquery-ui-1.9.1.custom.min.css" />
            <link rel="stylesheet" type="text/css" href="css/jquery.dataTables_themeroller.css" />
            <link rel='stylesheet' type='text/css' href='calendario/fullcalendar/fullcalendar.css' /> 

            <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
            <script type="text/javascript" src="js/jquery-ui-1.9.1.custom.min.js"></script>
            <script type="text/javascript" src="js/jquery.validate.min.js"></script>
            <script type="text/javascript" src="js/additional-methods.min.js"></script>
            <script type='text/javascript' src='calendario/fullcalendar/fullcalendar.min.js'></script>
            <script type="text/javascript" src="js/functions.js"></script>
            <script type="text/javascript" src="js/meetingManager.js"></script>
            <script type="text/javascript" src="js/jquery.ui.timepicker.js"></script>

            <title>Kids</title>

            <script type="text/javascript">
                $(document).ready(function() {
                    activePage();
                    initializeMeetingManager();
                <c:if test="${sessionScope.user.getAccountType()=='Admin'||sessionScope.user.getAccountType()=='Segreteria'||sessionScope.user.getAccountType()=='Responsabile Asilo'||sessionScope.user.getAccountType()=='Delegato del rettore'}">                               
                        CalendarEditable();
                </c:if>
                <c:if test="${sessionScope.user.getAccountType()!='Admin' && sessionScope.user.getAccountType() != 'Segreteria' && sessionScope.user.getAccountType() != 'Responsabile Asilo' && sessionScope.user.getAccountType()!='Delegato del rettore'}">                               
                        CalendarNotEditable();
                </c:if> 
                        $("#dataMeeting,#modifyDataMeeting").datepicker({dateFormat:'yy-mm-dd'});
                 
                        $('#modifyStartTime').timepicker({
                            showLeadingZero: false,
                            onHourShow: modifyTpStartOnHourShowCallback,
                            onMinuteShow: modifyTpStartOnMinuteShowCallback,
                            hours: {
                                starts: 0,               
                                ends: 23                  
                            },
                            minutes: {
                                starts: 0,                
                                ends: 59,                 
                                interval: 15               
                            },
                            showCloseButton: true       


                        });
                        $('#modifyEndTime').timepicker({
                            showLeadingZero: false,
                            onHourShow: modifyTpEndOnHourShowCallback,
                            onMinuteShow: modifyTpEndOnMinuteShowCallback,
                            hours: {
                                starts: 0,                
                                ends: 23                  
                            },
                            minutes: {
                                starts: 0,                
                                ends: 59,                 
                                interval: 15               
                            },
                            showCloseButton: true       

                        });
                   
                        $('#startTime').timepicker({
                            showLeadingZero: false,
                            onHourShow: tpStartOnHourShowCallback,
                            onMinuteShow: tpStartOnMinuteShowCallback,
                            hours: {
                                starts: 0,               
                                ends: 23                  
                            },
                            minutes: {
                                starts: 0,                
                                ends: 59,                 
                                interval: 15               
                            },
                            showCloseButton: true       


                        });
                        $('#endTime').timepicker({
                            showLeadingZero: false,
                            onHourShow: tpEndOnHourShowCallback,
                            onMinuteShow: tpEndOnMinuteShowCallback,
                            hours: {
                                starts: 0,                
                                ends: 23                  
                            },
                            minutes: {
                                starts: 0,                
                                ends: 59,                 
                                interval: 15               
                            },
                            showCloseButton: true       

                        });
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
                                                    <p style="width: 480px">
                                                        <c:if test="${sessionScope.user.getAccountType()=='Admin'||sessionScope.user.getAccountType()=='Segreteria'||sessionScope.user.getAccountType()=='Responsabile Asilo'||sessionScope.user.getAccountType()=='Delegato del rettore'}">                                                           
                                                            <input type="button" id="modifyMeetingButton" value="Modifica Riunione" />
                                                            <input type="button" id="deleteMeetingButton" value="Elimina Riunione" />
                                                        </c:if>
                                                        <input style="width: 140px" type="button" id="notMeetingButton" value="Annulla" />
                                                    </p>
                                                    </div>
                                                    </fieldset>
                                                    </form>
                                                    </div>



                                                    <div id="newMeetingWindow" title="Inserisci Riunione" style="display: inline">

                                                        <form id="newMeetingForm" name="insertForm" class="cmxform" method="post" action="">
                                                            <fieldset>
                                                                <div id="artefactsManagement">
                                                                    <p class="formp">
                                                                        <label ><h3> Titolo *: </h3></label> <br>
                                                                            <input id="titleMeeting" name="Titolo" type="text" size=50% style="margin-right: 2%"> </input> <br> <br>
                                                                                    </p>
                                                                                    <p class="formp">
                                                                                        <label ><h3> Descrizione *: </h3></label> <br>
                                                                                            <textarea id="descriptionMeeting" name="Descrizione" class="textarea" rows="5" cols=100%> 
                                                                                            </textarea> <br> 
                                                                                                </p>
                                                                                                <p class="formp">
                                                                                                    <label><h3 style="float: left"> Data * </h3></label> 

                                                                                                    <label class="hourLabel" style="float: left"><h3> Ora * </h3></label> <br> <br>
                                                                                                            <input type="text" id="dataMeeting" name="Data" style="margin-right: 2%">   </input>
                                                                                                            <label> Inizio: </label>
                                                                                                            <input type="text" name="OraInizio" id="startTime">
                                                                                                                <label> Fine: </label>
                                                                                                                <input type="text"  name="OraFine"  id="endTime" >
                                                                                                                    </p>
                                                                                                                    <p class="formp"> 
                                                                                                                        <legend> <h3> Tipologia *: </h3></legend> <br>
                                                                                                                            <ul >
                                                                                                                                <input type="radio" id="typeMeetingComitato" name="Tipo" value="Comitato" checked > Riunione Comitato      
                                                                                                                                    <input type="radio" id="typeMeetingGestione" name="Tipo" value="Consiglio Gestione" > Riunione Consiglio Di Gestione  
                                                                                                                                        <input type="radio" id="typeMeetingScuola" name="Tipo" value="Scuola-Famiglia"> Riunione Scuola-Famiglia   
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

                                                                                                                                                    <form id="modifyMeetingForm" name="modifyForm" class="cmxform" method="post" action="addMeetingServlet">
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
                                                                                                                                                                                                        <input type="text" name="OraInizio" id="modifyStartTime">
                                                                                                                                                                                                        <label> Fine: </label>
                                                                                                                                                                                                        <input type="text"  name="OraFine"  id="modifyEndTime" >
                                                                                                                                                                                                        </p>
                                                                                                                                                                                                        <p class="formp"> 
                                                                                                                                                                                                        <legend> <h3> Tipologia: </h3></legend> <br>
                                                                                                                                                                                                        <ul>
                                                                                                                                                                                                        <input type="radio" id="modifyTypeMeetingComitato" name="Tipo" value="Comitato" > Riunione Comitato     
                                                                                                                                                                                                        <input type="radio" id="modifyTypeMeetingGestione" name="Tipo" value="Consiglio Gestione"> Riunione Consiglio Di Gestione  
                                                                                                                                                                                                        <input type="radio" id="modifyTypeMeetingScuola" name="Tipo" value="Scuola-Famiglia"> Riunione Scuola-Famiglia   
                                                                                                                                                                                                        </ul> <br>
                                                                                                                                                                                                        </p>
                                                                                                                                                                                                        <p style="width: 650px">
                                                                                                                                                                                                        <input type="submit" id="acceptModifyMeetingButton" value="Modifica Riunione" />
                                                                                                                                                                                                        <input style="width: 140px" type="button" id="notAcceptModifyMeetingButton" value="Annulla" />
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
                                                                                                                                                                                                        <c:if test="${sessionScope.user.getAccountType()=='Admin'||sessionScope.user.getAccountType()=='Segreteria'||sessionScope.user.getAccountType()=='Responsabile Asilo'||sessionScope.user.getAccountType()=='Delegato del rettore'}"> 
                                                                                                                                                                                                        <input type="button"  id="newMeetingButton" style="position: absolute; left: 15%" value="Inserisci Riunione"/> 
                                                                                                                                                                                                        </c:if>
                                                                                                                                                                                                        <div id="meetingCalendar" style="width: 60%"></div>
                                                                                                                                                                                                        <%@include file="footer.jsp" %>
                                                                                                                                                                                                        </body>
                                                                                                                                                                                                        </html>
