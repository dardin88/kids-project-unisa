/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function initializetimeServicePage() {
    $("#timeserviceTab").tabs();
}

function buildnotifyTable(){
    $('#notifyTable').dataTable({
        "bProcessing": true,
        "oLanguage": {
            "sLengthMenu": "Mostra _MENU_ oggetti",
            "sSearch": "Cerca:",
            "sInfo": "Da _START_ a _END_ di _TOTAL_ oggetti",
            "sZeroRecords": "Nessun oggetto trovato",
            "sEmptyTable": "Nessun oggetto trovato",
            "oPaginate": {
                "sFirst": "Prima",
                "sLast": "Ultima",
                "sNext": "Successiva",
                "sPrevious": "Precedente"
            }
        },
        "bJQueryUI": true,
        "bPaginate": true,
        "bLengthChange": true,
        "bFilter": true,
        "bSort": true,
        "bInfo": true,
        "bAutoWidth": false,
        "sPaginationType": "full_numbers"
    });
}
