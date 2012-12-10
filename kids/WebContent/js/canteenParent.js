/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeCanteenPage() {
    $("#canteenTabGroup").tabs();
    
    TableTools.DEFAULTS.aButtons = [];
    getDailyMenu();
    buildAssociatedMenusTable();
    
    $("#menuDate").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    
    $("#requestDate").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    
    $("#modifySicknessButton").button();
    $("#requestMealButton").button();
}

function getDailyMenu() {
    // do $.post to get menu data
    $.post("GetParentDailyMenu",
    function(jsonData, status) {
        $("#dailyMenuDate").val(jsonData.date);
        $("#dailyMenuFirst").val(jsonData.first);
        $("#dailyMenuSecond").val(jsonData.second);
        $("#dailyMenuSideDish").val(jsonData.sideDish);
        $("#dailyMenuFruit").val(jsonData.fruit);
    });
}

function buildAssociatedMenusTable(){
    $("#showAssociatedMenuTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetParentAssociatedMenuTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "parentId", 
                "value" : $("#hiddenParentId").val()
            },
            {
                "name" : "menuDate", 
                "value" : $("#menuDate").val()
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
            "sZeroRecords":  "Nessun men&ugrave; associato disponibile.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Men&ugrave; associati",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Men&ugrave; associati",
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
    $("#showAssociatedMenuTable").dataTable().fnDraw();
}
