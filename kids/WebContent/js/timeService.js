/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function initializetimeServicePage() {
    $("#timeserviceTab").tabs();
    $("#insertRequestTime").button();
    $("#insertRequestTimeDialog").dialog({
        autoOpen: false
    });
    //  buildnotifyTable();
    //buildVisualTimeTable();
    $("#InsertTimeServiceButton").button();
    $("#Time").timepicker({
        showLeadingZero: false,
        hours: {
            starts: 0,
            ends: 23
        },
        minutes: {
            starts: 0,
            ends: 59,
            interval: 1
        },
        showCloseButton: true
    });
    $("#Date").datepicker({
        dateFormat: "yy-mm-dd"

    });
    $("#SendRequest").button();
}

function buildnotifyTable() {
    $('#notifyTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetRefundsTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function(aoData) {
            aoData.push(
                    {
                        "name": "parentId",
                        "value": parentId
                    }
            );

        },
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing": "Caricamento...",
            "sLengthMenu": "Visualizza _MENU_ link",
            "sZeroRecords": "Nessun rimborso disponibile.",
            "sInfo": "Vista da _START_ a _END_ di _TOTAL_ Rimborsi",
            "sInfoEmpty": "Vista da 0 a 0 di 0 Rimborsi",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix": "",
            "oPaginate": {
                "sFirst": "<<",
                "sPrevious": "<",
                "sNext": ">",
                "sLast": ">>"
            }
        }
    });
}

function buildVisualTimeTable() {
    $('#visualTimeTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetRefundsTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function(aoData) {
            aoData.push(
                    {
                        "name": "parentId",
                        "value": parentId
                    }
            );

        },
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing": "Caricamento...",
            "sLengthMenu": "Visualizza _MENU_ link",
            "sZeroRecords": "Nessun rimborso disponibile.",
            "sInfo": "Vista da _START_ a _END_ di _TOTAL_ Rimborsi",
            "sInfoEmpty": "Vista da 0 a 0 di 0 Rimborsi",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix": "",
            "oPaginate": {
                "sFirst": "<<",
                "sPrevious": "<",
                "sNext": ">",
                "sLast": ">>"
            }
        }
    });
}

function showTimeService() {
    $.post("GetTimeService", function(data) {
        var split = data.split(",");
        $("#TextAreaTimeService").val(split[0]);
        $("#idNews").val(split[1]);

    }, "text");
}
$(document).ready(function() {
    $("#requestTimeForm").validate({
        rules:
                {
                    ora: {
                        required: true
                    }
                },
        messages: {
            ora: "Inserisci l'ora"
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
});

function openInsertRequestTimeDialog(){
    $("#insertRequestTimeDialog").dialog("open");
}
function buildRequestTimeServiceParentTable() {
    $('#requestTimeServiceParentTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function(aoData) {
            aoData.push(
                    {
                        "name": "Nome",
                        "value": $('#Nome').val()
                    },
            {
                "name": "Cognome",
                "value": $('#Cognome').val()
            }
            );

        },
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing": "Caricamento...",
            "sLengthMenu": "Visualizza _MENU_ link",
            "sZeroRecords": "La ricerca non ha portato alcun risultato.",
            "sInfo": "Vista da _START_ a _END_ di _TOTAL_ Richieste",
            "sInfoEmpty": "Vista da 0 a 0 di 0 Richieste",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix": "",
            "oPaginate": {
                "sFirst": "<<",
                "sPrevious": "<",
                "sNext": ">",
                "sLast": ">>"
            }
        },
        
        "aoColumns": [
            {
                "sWidth": "25%"
            },
            {
                "sWidth": "25%"
            },
            {
                "sWidth": "25%"
            },
            {
                "sWidth": "10%"
            }
        ]
    });
}
