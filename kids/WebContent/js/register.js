/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeLinksManager() {
    $("#registerTab").tabs();
    $("#insertActivityWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 800
    });

}
function buildTable(id){
    $('#table'+id).dataTable({
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

function buildInsertButton(id){
    $("#insertActivityButton"+id).button()
;}

function buildTableActivity(){
    $('#tableActivity').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetDailyActivitySection",
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
                "sWidth": "25%"
            },
            {
                "sWidth": "10%"
            }
        ]
    });
}

function openInsertActivity(id){
    $("#insertActivityWindow").dialog("open");
    
    }