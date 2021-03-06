/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var idClass;
var quanti = 0;

function initializeLinksManager() {
    $("#registerTab").tabs();
    $("#insertActivityWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 800
    });
    $("#informationDailyActivityWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });

}
function buildTable(id) {
    $('#table' + id).dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetDailyActivitySection",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function(aoData) {
            aoData.push(
                    {
                        "name": "id",
                        "value": id
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
            "sInfo": "Vista da _START_ a _END_ di _TOTAL_ Attivita",
            "sInfoEmpty": "Vista da 0 a 0 di 0 Attivita",
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

function buildInsertButton(id) {
    $("#insertActivityButton" + id).button()
            ;
}





function openInsertActivity(id) {
    quanti = 0;
    $("#insertActivityWindow" + id).dialog("open");
    idClass = id;
    document.getElementById("idClass" + id).value = id;
    var oTable = $("#tableActivity" + id).dataTable();
    oTable.fnDraw();
}


function selezionati(elemento) {

    if (elemento.checked)
    {
        quanti += 1;
    }
    else
    {
        quanti -= 1;
    }
}

function controlla(elemento, id) {
    if (quanti == 0) {
        alert("Selezionare almeno un attivita");
        return;
    }
    document.getElementById("form" + id).submit();
}

function loadInformationDailyActivity(id) {
    $("#informationDailyActivityWindow").dialog("open");
    $.post("GetDailyActivity", {
        activityId: id
    }, function(data) {
        var result = data.toString().split(",");
        $("#Nome").val(result[0]);
        $("#Data").val(result[1]);
        $("#Educatore").val(result[2]);
        $("#Note").val(result[3]);

    }, "text");

}

function removeDailyActivity(idActivity,idClas) {
    $.post("RemoveDailyActivitySection", {
        id: idActivity
    });
    var oTable = $("#table" + idClas).dataTable();
    oTable.fnDraw();
}
