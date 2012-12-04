/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializePaymentsPage() {
    $("#paymentTabGroup").tabs();
    $("#generalPaymentSection").hide();
    
    TableTools.DEFAULTS.aButtons = [];
    buildParentsTable();
}

function buildParentsTable(){
    $("#parentsTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetParentsTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "parentName", 
                "value" : $("#parentName").val()
            },

            {
                "name" : "parentSurname", 
                "value" : $("#parentSurname").val()
            },
            {
                "name" : "parentFiscalCode",
                "value" : $("#parentFiscalCode").val()
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
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Genitori",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Genitori",
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
            "fnRowSelected": function (nodes) {
                doParentSelection(nodes[0].id);
                $("#selectedParent").html(nodes[0]);
            }
        }
    });
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
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
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
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
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
        }
    });
}

function search() {
    var oTable = $("#parentsTable").dataTable();
    oTable.fnDraw();
}

function doParentSelection(parentId) {
    buildPaymentsTable(parentId);
    buildRefundsTable(parentId);
    $("#searchParent").hide();
    $("#generalPaymentSection").show();
}