/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var fulltime = "full_time";
var partTimeP = "part_time_pomeridiana";
var partTimeM = "part_time_mattutina";
function initializetimeServicePage() {
    $("#timeserviceTab").tabs();
    $("#insertRequestTime").button();
    $("#sendRequestModifyTimeService").button();
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
    $("#insertRequestModifyTimeService").button();
    $("#insertRequestModifyTimeServiceDialog").dialog({
        width: 700,
        autoOpen: false
    });
}

function buildnotifyTable() {
    $('#notifyTgdfdable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTimeServiceRequest",
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
function buildRequestTimeServiceSecretaryTable() {
    $('#notifyTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTimeServiceRequestSecretaryTable",
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

function openInsertRequestTimeDialog() {
    $("#insertRequestTimeDialog").dialog("open");
}
function buildRequestTimeServiceParentTable() {
    $('#requestTimeServiceParentTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTimeServiceRequestsTableParent",
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

function removeTimeServiceRequest(id) {

    $.post("RemoveTimeServiceRequest", {
        id: id
    });
    var oTable = $("#requestTimeServiceParentTable").dataTable();
    oTable.fnDraw();
    //location.reload(true)
}

function updateTimeServiceRequest(id, check) {
    if (check.checked) {
        $.post("UpdateTimeServiceRequest", {
            id: id,
            checked: "true"
        }
        )
    }
    else {
        $.post("UpdateTimeServiceRequest", {
            id: id,
            checked: "false"
        }
        )
    }


}
function openInsertModifyTimeServiceRequestDialog() {
    $("#insertRequestModifyTimeServiceDialog").dialog("open");
}
function buildTableChild() {
    $('#TableChild').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetRegistrationChildTable",
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
                "sWidth": "10%"
            },
            {
                "sWidth": "25%"
            },
            {
                "sWidth": "25%"
            },
            {
                "sWidth": "25%"
            }
        ]
    });
}
function changeUserRange(userRange) {
    var i = 0;
    var select = document.getElementById("userRange");
    select.options.length = 0;
    if (strcmp(userRange, fulltime) != 0) {
        select.options[select.options.length] = new Option(fulltime, "full_time", false, false);
    }
    if (strcmp(userRange, partTimeM) != 0) {
        select.options[select.options.length] = new Option(partTimeM, "part_time_mattutina", false, false);
    }
    if (strcmp(userRange, partTimeP) != 0) {
        select.options[select.options.length] = new Option(partTimeP, "part_time_pomeridiana", false, false);
    }
    select.disabled = false;
    document.getElementById("motivation").disabled=false;


}
function strcmp(str1, str2) {
    
    return ((str1 == str2) ? 0 : ((str1 > str2) ? 1 : -1));
}
function tableRequestModifyTimeService() {
    $('#TableRequestModifyTimeService').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetRequestModifyTimeService",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
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

function tableRequestModifyTimeServiceSecretary() {
    $('#TableRequestModifyTimeServiceSecretary').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetRequestModifyTimeServiceSecretary",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
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
            }
        ]
    });
}