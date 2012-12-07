/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function initializePaymentsPage() {
    $("#paymentTabGroup").tabs();
    $("#generalPaymentSection").hide();
    $("#goToParentsSearchBtn").button();
    $("#goToParentsSearchBtn").click(function() {
        $("#generalPaymentSection").hide();
        $("#searchParent").show();
        search();
    });
    
    $( "#expDate" ).datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    $("#insertPaymentButton").button();
    
    $("#insertRefundButton").button();
    
    $("#modifyPaymentsDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $( "#modifyExpDate" ).datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    $("#modifyPaymentButton").button();
    
    $("#validatePaymentsDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 450
    });
    $("#validatePaymentButton").button();
    
    TableTools.DEFAULTS.aButtons = [];
    buildParentsTable();
}

function search() {
    $("#parentsTable").dataTable().fnDraw();
}

function doParentSelection(parentData) {
    $("#selectedParentData").html(parentData.cells[0].innerHTML + ' ' + parentData.cells[1].innerHTML + ' ' + parentData.cells[2].innerHTML);
    $("#hiddenParentIdInsPayment").val(parentData.id);
    $("#hiddenParentIdInsRefund").val(parentData.id);
    
    buildPaymentsTable(parentData.id);
    buildRefundsTable(parentData.id);
    buildPaymentsConvTable(parentData.id);
    $("#searchParent").hide();
    $("#generalPaymentSection").show();
    
    $("#showPaymentsTable").dataTable().fnDraw();
    $("#showRefundsTable").dataTable().fnDraw();
}

function doModifyPayment(paymentData) {
    $("#hiddenModPaymentId").val(paymentData.id);
    
    $("#modifyExpDate").val(paymentData.cells[0].innerHTML);
    $("#modifyPaymentDescription").val(paymentData.cells[1].innerHTML);
    $("#modifyAmount").val(paymentData.cells[2].innerHTML);
    $("#modifyDiscount").val(paymentData.cells[3].innerHTML);
    $("#modifyDiscountDescription").val(paymentData.cells[4].innerHTML);
    
    if (paymentData.cells[6].innerHTML == "true")
        $("#modifyCharge").attr('checked', true);
    
    $("#modifyPaymentsDialog").dialog("open");
}

function doValidatePayment(paymentData) {
    if (paymentData.cells[7].innerHTML == "true") {
        alert('Pagamento gia\' convalidato');   // da sostituire con un dialog
        return;
    }
    
    $("#hiddenValPaymentId").val(paymentData.id);
    
    $("#validateExpDate").val(paymentData.cells[0].innerHTML);
    $("#validatePaymentDescription").val(paymentData.cells[1].innerHTML);
    $("#validateAmount").val(paymentData.cells[2].innerHTML);
    $("#validateDiscount").val(paymentData.cells[3].innerHTML);
    $("#validateDiscountDescription").val(paymentData.cells[4].innerHTML);
    
    if (paymentData.cells[6].innerHTML == "true")
        $("#validateCharge").attr('checked', true);
    
    $("#validatePaymentsDialog").dialog("open");
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
            "fnRowSelected": function(nodes) {
                doParentSelection(nodes[0]);
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
                doModifyPayment(nodes[0]);
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
        }
    });
}

function buildPaymentsConvTable(parentId){
    $("#showPaymentsConvTable").dataTable({
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
                doValidatePayment(nodes[0]);
            }
        }
    });
}

