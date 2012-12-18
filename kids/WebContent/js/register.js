/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeLinksManager() {
    $("#registerTab").tabs();

}
function buildTable(id){
    $('#table'+id).dataTable({
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
            "sInfo": "Vista da _START_ a _END_ di _TOTAL_ Tirocinanti",
            "sInfoEmpty": "Vista da 0 a 0 di 0 Tirocinanti",
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
            }
        ]
    });
}