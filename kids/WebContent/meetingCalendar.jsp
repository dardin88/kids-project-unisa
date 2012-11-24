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
                            <script type="text/javascript" src="js/jquery.timePicker.min.js"></script>

                            <script type="text/javascript" src="js/meetingManager.js"></script>
                            <link rel='stylesheet' type='text/css' href='./calendario/fullcalendar/fullcalendar.css' /> 
                            <script type='text/javascript' src='./calendario/jquery/jquery.js'></script> 
                            <script type='text/javascript' src='./calendario/jquery/jquery-ui.js'></script> 
                            <script type='text/javascript' src='./calendario/fullcalendar/fullcalendar.min.js'></script>
                            <title>Kids</title>

                            <script type='text/javascript'>
                                $(document).ready(function() {
                                    var date = new Date();
                                    var d = date.getDate();
                                    var m = date.getMonth();
                                    var y = date.getFullYear();
                                    $('#meetingCalendar').fullCalendar({ 
                                        header: { 
                                            left: 'prev,next today',
                                            center: 'title', 	
                                            right: 'month,agendaWeek,agendaDay'
                                        }, 	
                                        editable: true, 
                                        events: [
                                            { 		
                                                title: 'Evento che dura tutto il giorno', 	
                                                start: new Date(y, m, 1) 
                                            }, 		
                                            { 					
                                                title: 'Evento che dura piu giorni', 		
                                                start: new Date(y, m, 20), 	
                                                end: new Date(y, m, 27) 
                                            }, 			
                                            { 			
                                                id: 999, 	
                                                title: 'Evento ripetuto', 		
                                                start: new Date(y, m, d-3, 16, 0), 
                                                allDay: false 	
                                            },
                                            { 			
                                                id: 999, 	
                                                title: 'Evento ripetuto', 
                                                start: new Date(y, m, d+4, 16, 0), 
                                                allDay: false 			
                                            },
                                            { 			
                                                title: 'Incontro', 		
                                                start: new Date(y, m, d, 10, 30), 
                                                allDay: false 			
                                            }, 
                                            { 					
                                                title: 'Pranzo', 			
                                                start: new Date(y, m, d, 12, 0), 	
                                                end: new Date(y, m, d, 14, 0), 
                                                allDay: false 				
                                            },
                                            { 					
                                                title: 'CompleannoFabio', 				
                                                start: new Date(y, m, d+1, 19, 0), 	
                                                end: new Date(y, m, d+1, 22, 30), 
                                                allDay: false 			
                                            },
                                            { 					
                                                title: 'Evento con link esterno', 			
                                                start: new Date(y, m, 28), 		
                                                end: new Date(y, m, 29), 	
                                                url: 'http://www.mrwebmaster.it/' 
                                            }
                                        ] 		
                                    }); 	
                                }); 
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

                           

                            <div id="newMeetingWindow" title="Inserisci Riunione" style="display: inline">

                                <form id="addMeetingForm" class="cmxform" method="get" action="">
                                    <fieldset>
                                        <div id="artefactsManagement">
                                            <p class="formp">
                                                <label ><h3> Titolo: </h3></label> <br>
                                                    <input id="titleMeeting" name="titleMeeting" type="text" size=50%> <br> <br>
                                            </p>
                                            <p class="formp">
                                                 <label ><h3> Descrizione: </h3></label> <br>
                                                     <textarea id="descriptionMeeting" name="descriptionMeeting" class="textarea" rows="5" cols=120%> 
                                                     </textarea> <br> 
                                            </p>
                                            <p class="formp">
                                                  <label><h3 style="float: left"> Data </h3></label> 
                                                  <label class="hourLabel" style="float: left"><h3> Ora </h3></label> <br> <br>
                                                  <input type="text" id="dataMeeting" name="dataMeeting">  
                                                  <input type="text" id="firstTimeMeeting" name="firstTimeMeeting">
                                                  <input type="text" id="secondTimeMeeting" name="secondTimeMeeting">
                                            </p>
                                            <p class="formp"> 
                                                  <legend> <h3> Tipologia: </h3></legend> <br>
                                                  <ul>
                                                  <input type="radio" id="typeMeeting" name="typeMeeting" checked> Riunione tra rappresentanti      
                                                  <input type="radio" id="typeMeeting" name="typeMeeting"> Riunione bimestrale    
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
    
                                <div id="addMeetingWindow" title="Inserisci Riunione" style="display: inline">
                                    <fieldset>
                                        <p class="formp">
                                            <label class="artefactLabel" for="artefact">Vuoi inserire la runione?</label>
                                        </p>
                                        <p class="formp">
                                            <input type="button" class="confirmAddButton" id="addMeetingButtonSi" value="Si"/>
                                            <input type="button" class="notConfirmAddButton" id="addMeetingButtonNo" value="No"/>
                                        </p>
                                    </fieldset>
                                </div>
    
                           <body>
                               <%@include file="header.jsp" %>
                               <input type="button" class="confirmAddButton" id="newMeetingButton" value="Inserisci Riunione"/>
                               <div id='meetingCalendar'></div>
                               <%@include file="footer.jsp" %>
                           </body>
</html>
