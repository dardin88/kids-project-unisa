/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#confirmButton").button();
    $("#requestInformation").dialog({
        autoOpen:false,
        resizable:false,
        width:650
    });
    $("#sendConvocation").button();
    $("#error").dialog();
    $("#removeTraineeConvocationWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
   
}
function searchRequests(){
    var oTable = $("#requestsTable").dataTable();
    oTable.fnDraw();
}


function buildTraineeTable(){
    $('#traineesTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTraineeTableRequest",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "Nome", 
                "value" : $('#Nome').val()
            },

            {
                "name" : "Cognome", 
                "value" : $('#Cognome').val()
            }
            );
     
        },
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Tirocinanti",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Tirocinanti",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },


        
        "aoColumns": [
        {
            "sWidth":"2%"
        },
        {
            "sWidth": "5%"
        },
        {
            "sWidth": "5%"
        },
        {
            "sWidth": "5%"
        }
        
        ]
    });
}

function buildResponseFromTraineesTable(){
    $('#responseTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetConvocationsTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "Nome", 
                "value" : $('#Nome').val()
            },

            {
                "name" : "Cognome", 
                "value" : $('#Cognome').val()
            }
            );
     
        },
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Tirocinanti",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Tirocinanti",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },


        
        "aoColumns": [
        {
            "sWidth": "30%"
        },
        {
            "sWidth": "30%"
        },
        {
            "sWidth": "30%"
        },
        {
            "sWidth": "10%"
        },
        {
            "sWidth": "10%"
        }
        ]
    });
}

function loadInformationRequestPage(id){
    window.open("requestInformation.jsp?id="+id);

}
function search(){
    var oTable = $("#traineesTable").dataTable();
    oTable.fnDraw();
        
}

function tab(){
    $(function() {
        $( "#tabs" ).tabs();
    });
}
function createCalendar(){
    var layer;
    $('#calendar').fullCalendar({
        events:"GetRequestCalendar",

        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        eventClick: function(calEvent, jsEvent, view) {
            $("#requestInformation").dialog("open");
            document.getElementById("TraineeNumber").value=calEvent.traineeNumber;
            document.getElementById("DateRequest").value=calEvent.dateRequest;
            document.getElementById("Activity").value=calEvent.activity;
            document.getElementById("StartTimeRequest").value=calEvent.startTime;
            document.getElementById("EndTimeRequest").value=calEvent.endTime;
        }
    /* eventMouseover: function( event, jsEvent, view ) 
        {
            layer="<div name=\"dialog\" id=\"dialog\" style=\"width:400px;height:100px;background-color:grey;\"> Titolo:"+event.title+"<br>Data Inizio:"+event.start+"<br>Data Fine:"+event.end+"<br>Attivit&agrave:"+event.activity+"</div>";

            $(this).append(layer);
                        
        },
        eventMouseout: function(event,jsEvent,view){
            var box = document.getElementById("dialog");
                        

            box.parentNode.removeChild(box);
        }
        */
    })
    
                    
}
function removeTraineeConvocation(id){
    $("#removeTraineeConvocationWindow").dialog({
        autoOpen:true
    }); 
    $("#confirmRemoveTraineeConvocationButton").button();
    $("#confirmRemoveTraineeConvocationButton").click(function(){
        $.post("RemoveConvocation", {
            traineeConvocationId:""+id
        });
        $("#removeTraineeConvocationWindow").dialog("close"); 
        var oTable = $("#responseTable").dataTable();
        oTable.fnDraw();
    });
    $("#notConfirmRemoveTraineeConvocationButton").button();
    $("#notConfirmRemoveTraineeConvocationButton").click(function(){
        $("#removeTraineeConvocationWindow").dialog("close");
    });
}
