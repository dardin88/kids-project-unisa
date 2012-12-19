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
        $("#title").html("Gestione Pagamenti - Ricerca genitore");
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
    
    $("#modifyRefundsDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#modifyRefundButton").button();
    
    TableTools.DEFAULTS.aButtons = [];
    buildParentsTable();
}

$(document).ready(function() {
    $("#insertPaymentForm").validate({
        rules:
        {
            paymentDescription:{
                required:true,
                minlength: 5, 
                maxlength:500
            },
            amount:{
                required:true,
                number: true
            },
            discount:{
                required:true,
                number:true
            },
            expDate:{
                required:true,
                date:true
            }
        },
        messages:{
            paymentDescription:{
                required: "Descrizione obbligatoria",
                minlength: "Descrizione di almeno 4 caratteri", 
                maxlength: "Descrizione di massimo 500 caratteri"
            },
            amount:{
                required: "Importo obbligatorio",
                number: "Inserire solo numeri"
            },
            discount:{
                required: "Sconto obbligatorio",
                number: "Inserire solo numeri"
            },
            expDate:{
                required: "Data obbligatoria",
                date: "Formato non valido"
            }
        },
        submitHandler:function(form){
            form.submit();
        }
    });
});

$(document).ready(function() {
    $("#modifyPaymentForm").validate({
        rules:
        {
            modifyPaymentDescription:{
                required:true,
                minlength: 5, 
                maxlength:500
            },
            modifyAmount:{
                required:true,
                number: true
            },
            modifyDiscount:{
                required:true,
                number:true
            },
            modifyExpDate:{
                required:true,
                date:true
            }
        },
        messages:{
            modifyPaymentDescription:{
                required: "Descrizione obbligatoria",
                minlength: "Descrizione di almeno 4 caratteri", 
                maxlength: "Descrizione di massimo 500 caratteri"
            },
            modifyAmount:{
                required: "Importo obbligatorio",
                number: "Inserire solo numeri"
            },
            modifyDiscount:{
                required: "Sconto obbligatorio",
                number: "Inserire solo numeri"
            },
            modifyExpDate:{
                required: "Data obbligatoria",
                date: "Formato non valido"
            }
        },
        submitHandler:function(form){
            form.submit();
        }
    });
});

$(document).ready(function() {
    $("#modifyRefundsForm").validate({
        rules:
        {
            modifyRefundDescription:{
                required:true,
                minlength: 5, 
                maxlength:500
            },
            modifyRefundAmount:{
                required:true,
                number: true
            }
        },
        messages:{
            modifyRefundDescription:{
                required: "Descrizione obbligatoria",
                minlength: "Descrizione di almeno 4 caratteri", 
                maxlength: "Descrizione di massimo 500 caratteri"
            },
            modifyRefundAmount:{
                required: "Importo obbligatorio",
                number: "Inserire solo numeri"
            }
        },
        submitHandler:function(form){
            form.submit();
        }
    });
});

$(document).ready(function() {
    $("#insertRefundForm").validate({
        rules:
        {
            refundDescription:{
                required:true,
                minlength: 5, 
                maxlength:500
            },
            refundAmount:{
                required:true,
                number: true
            }
        },
        messages:{
            refundDescription:{
                required: "Descrizione obbligatoria",
                minlength: "Descrizione di almeno 4 caratteri", 
                maxlength: "Descrizione di massimo 500 caratteri"
            },
            refundAmount:{
                required: "Importo obbligatorio",
                number: "Inserire solo numeri"
            }
        },
        submitHandler:function(form){
            form.submit();
        }
    });
});


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
    $("#title").html("Gestione Pagamenti - Operazioni");
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
    $("#modifyOriginAccount").val(paymentData.cells[6].innerHTML);
    $("#modifyReceiptCode").val(paymentData.cells[8].innerHTML);
    
    if (paymentData.cells[9].innerHTML.indexOf("accept.png") >= 0) {
        $("#modifyOriginAccount").attr('disabled', false);
        $("#modifyReceiptCode").attr('disabled', false);
    } else {
        $("#modifyOriginAccount").attr('disabled', true);
        $("#modifyReceiptCode").attr('disabled', true);
        $("#modifyOriginAccount").val('');
        $("#modifyReceiptCode").val('');
    }
    
    $("#modifyPaymentsDialog").dialog("open");
}

function doValidatePayment(paymentData) {
    $("#hiddenValPaymentId").val(paymentData.id);
    
    $("#validateExpDate").val(paymentData.cells[0].innerHTML);
    $("#validatePaymentDescription").val(paymentData.cells[1].innerHTML);
    $("#validateAmount").val(paymentData.cells[2].innerHTML);
    $("#validateDiscount").val(paymentData.cells[3].innerHTML);
    $("#validateDiscountDescription").val(paymentData.cells[4].innerHTML);
    
    $("#validatePaymentsDialog").dialog("open");
}

function doModifyRefund(refundData) {
    $("#hiddenModRefundId").val(refundData.id);
    
    $("#modifyRefundDescription").val(refundData.cells[0].innerHTML);
    $("#modifyRefundAmount").val(refundData.cells[1].innerHTML);
    
    if (refundData.cells[2].innerHTML.indexOf("accept.png") >= 0) {
        $("#modifyRefundStatus").attr('checked', true);
    } else {
        $("#modifyRefundStatus").attr('checked', false);
    }
    
    $("#modifyRefundsDialog").dialog("open");
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
    var oTable = $("#parentsTable").dataTable();
    if (oTable.length > 0) {
        $("#parentsTable").css("width", "100%");
    }
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
    var oTable = $("#showPaymentsTable").dataTable();
    if (oTable.length > 0) {
        $("#showPaymentsTable").css("width", "100%");
    }
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
                doModifyRefund(nodes[0]);
            }
        }
    });
    var oTable = $("#showRefundsTable").dataTable();
    if (oTable.length > 0) {
        $("#showRefundsTable").css("width", "100%");
    }
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
                "name" : "parentIdConv", 
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
            "sZeroRecords":  "Nessun pagamento da convalidare.",
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
    var oTable = $("#showPaymentsConvTable").dataTable();
    if (oTable.length > 0) {
        $("#showPaymentsConvTable").css("width", "100%");
    }
}