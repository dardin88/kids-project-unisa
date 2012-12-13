/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeCanteenPage() {
    $("#canteenTabGroup").tabs();
    $("#childSelection").hide();
    
    $("#insertDiffMenuDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#insertDiffMenuButton").button();
    
    $("#showAssociatedMenusDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#onlyLastAssMenu").change(function() {
        if ($("#onlyLastAssMenu").attr('checked') == 'checked') {
            $("#menuDate").attr('disabled', true);
        }
        else {
            $("#menuDate").attr('disabled', false);
        }
    });
    
    
    TableTools.DEFAULTS.aButtons = [];
    buildClassTable();
    
    $("#menuDate").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    $("#menuDate").datepicker("setDate", new Date());
    buildAssociatedMenusTable();
    
    $("#dailyMenuDate").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    $("#dailyMenuDate").datepicker("setDate", new Date());
    $("#onlyLastDailyMenu").change(function() {
        if ($("#onlyLastDailyMenu").attr('checked') == 'checked') {
            $("#dailyMenuDate").attr('disabled', true);
        }
        else {
            $("#dailyMenuDate").attr('disabled', false);
        }
    });
    buildDailyMenusTable();
    
    $("#modifyDailyMenuButton").button();
    
    buildMealRequestsTable();
}

function buildClassTable(){
    $("#showClassTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetCanteenClassTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
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
        "sAjaxSource": "GetCanteenChildrenTable",
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

function buildAssociatedMenusTable() {
    $("#showAssociatedMenusTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetAssociatedMenuTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "menuDate", 
                "value" : ($("#menuDate").attr('disabled') == 'disabled') ? ('') : ($("#menuDate").val())
            },
            {
                "name" : "onlyLastAssMenu",
                "value" : ($("#onlyLastAssMenu").attr('checked') == 'checked') ? ('ok') : ('')
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

function buildDailyMenusTable() {
    $("#showDailyMenusTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetDailyMenuTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "dailyMenuDate", 
                "value" : ($("#dailyMenuDate").attr('disabled') == 'disabled') ? ('') : ($("#dailyMenuDate").val())
            },
            {
                "name" : "onlyLastDailyMenu",
                "value" : ($("#onlyLastDailyMenu").attr('checked') == 'checked') ? ('ok') : ('')
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
            "sZeroRecords":  "Nessun men&ugrave; giornaliero trovato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ men&ugrave; giornalieri",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 men&ugrave; giornalieri",
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

function buildMealRequestsTable() {
    $("#showMealRequestsTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetMealRequestsTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        /*"fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "dailyMenuDate", 
                "value" : $("#dailyMenuDate").val()
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
            "sZeroRecords":  "Nessuna richiesta pasto trovata.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ richieste pasti",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 richieste pasti",
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

function searchAssociatedMenus() {
    $("#showAssociatedMenusTable").dataTable().fnDraw();
}

function searchDailyMenus() {
    $("#showDailyMenusTable").dataTable().fnDraw();
}

function doClassSelection(classData) {
    $("#selectedClassData").html(classData.cells[0].innerHTML);
    
    buildChildrenTable(classData.id);
    $("#classSelection").hide();
    $("#childSelection").show();
    
    $("#childSelectionTable").dataTable().fnDraw();
}

function doInsertDiffMenu(childData) {
    $("#hiddenChildIdInsDiff").val(childData.id);
    
    $("#insertDiffMenuDialog").dialog("open");
}

function doAssociatedMenuSelection(assMenuData) {    
    // do $.post to get menu data
    $.post("GetAssociatedMenu",
    {
        menuId: assMenuData.id
    },
    function(jsonData, status) {
        $("#associatedDate").val(jsonData.date);
        $("#associatedFirst").val(jsonData.first);
        $("#associatedSecond").val(jsonData.second);
        $("#associatedSideDish").val(jsonData.sideDish);
        $("#associatedFruit").val(jsonData.fruit);
        $("#associatedMenuType").val(jsonData.type);
    });
    
    $("#showAssociatedMenusDialog").dialog("open");
}