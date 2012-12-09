/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeCanteenPage() {
    $("#canteenTabGroup").tabs();
    
    $("#insertDiffMenuDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#insertDiffMenuButton").button();
    
    $("#showAssociatedMenuDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    
    TableTools.DEFAULTS.aButtons = [];
    buildClassTable();
    buildAssociatedMenusTable();
}

function doClassSelection(classData) {
    $("#selectedClassData").html(classData.cells[0].innerHTML);
    //$("#hiddenParentIdInsPayment").val(classData.id);
    //$("#hiddenParentIdInsRefund").val(classData.id);
    
    buildChildrenTable(classData.id);
    //buildRefundsTable(classData.id);
    //buildPaymentsConvTable(classData.id);
    $("#classSelection").hide();
    $("#childSelection").show();
    
    $("#childSelectionTable").dataTable().fnDraw();
}

function doInsertDiffMenu(childData) {
    $("#hiddenChildIdInsDiff").val(childData.id);
    
    $("#insertDiffMenuDialog").dialog("open");
}

function doAssociatedMenuSelection(assMenuData) {
    var childId = assMenuData.id;   // child Id
    // do $.post to get menu data
    
    $("#showAssociatedMenuDialog").dialog("open");
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

function buildClassTable(){
    $("#showClassTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetClassTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        /*"fnServerParams": function ( aoData ) {
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
     
        },*/
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Classi",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Classi",
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
                doClassSelection(nodes[0]);
            }
        }
    });
}

function buildChildrenTable(classId){
    $("#childSelectionTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetChildrenTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "classId", 
                "value" : classId
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
            "sZeroRecords":  "Nessun bambino disponibile.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Bambini",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Bambini",
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
                doInsertDiffMenu(nodes[0]);
            }
        }
    });
}

function buildAssociatedMenusTable(){
    $("#showRefundsTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetMenuTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        /*"fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "parentId", 
                "value" : parentId
            }
            );
     
        },*/
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "Nessun men&ugrave; disponibile.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Men&ugrave;",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Men&ugrave;",
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
                doAssociatedMenuSelection(nodes[0]);
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
}
