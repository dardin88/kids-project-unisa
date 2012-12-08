/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializePaymentsPage() {
    $("#paymentTabGroup").tabs();
    
    //TableTools.DEFAULTS.aButtons = [];
    
    var parentId = $("#hiddenParentId").val();
    buildPaymentsTable(parentId);
    buildRefundsTable(parentId);
    
    //$("#showPaymentsTable").dataTable().fnDraw();
    //$("#showRefundsTable").dataTable().fnDraw();
}

function buildPaymentsTable(parentId){
    $("#showPaymentsTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetPaymentsTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "parentId", 
                "value" : parentId
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
            "sZeroRecords":  "Nessun pagamento disponibile.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Pagamenti",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Pagamenti",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "sDom": '<"H"Tfr>t<"F"ip>',
        "oTableTools": {
            "sRowSelect": "single",
            "fnRowSelected": function(nodes) {
                
            }
        }
    });
}

function buildRefundsTable(parentId){
    $("#showRefundsTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetRefundsTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "parentId", 
                "value" : parentId
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
            "sZeroRecords":  "Nessun rimborso disponibile.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Rimborsi",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Rimborsi",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "sDom": '<"H"Tfr>t<"F"ip>',
        "oTableTools": {
            "sRowSelect": "single",
            "fnRowSelected": function(nodes) {
                
            }
        }
    });
}