/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeCanteenPage() {
    $("#canteenTabGroup").tabs();
    
    TableTools.DEFAULTS.aButtons = [];
    getDailyMenu();
    
    buildChildrenSelect("#childSelect");
    buildAssociatedMenusTable();
    
    $("#menuDate").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    $("#menuDate").datepicker("setDate", new Date());
    
    $("#requestDate").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    $("#requestDate").datepicker("setDate", +1);
    
    buildChildrenSelect("#childSelectModSick");
    getSicknessData();
    $("#modifySicknessButton").button();
    $("#requestMealButton").button();
    
    $("#childSelectModSick").change(function() {
        getSicknessData();
    });
    $("#associatedMenuForm").change(function() {
        searchAssociatedMenus();
    });
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

function getSicknessData() {
    $.post("GetSicknessData",
    {
        childId: $("#childSelectModSick").val()
    },
    function(jsonData, status) {
        $("#sicknessArea").val(jsonData.sickness);
        $("#noteArea").val(jsonData.note);
    });
}

function buildChildrenSelect(selector) {
    $.post("GetParentChildren",
    {
        parentId: $("#hiddenParentId").val()
    },
    function(jsonData, status) {
        for (var i = 0; i < jsonData.length; i++) {
            var childArray = jsonData[i];
            $(selector).append('<option value="' + childArray[0] + '">' + childArray[1] + ' ' + childArray[2]);
        }
    });
}

function buildAssociatedMenusTable(){
    $("#showAssociatedMenuTable").dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetParentChildAssociatedMenuTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "childId", 
                "value" : $("#childSelect").val()
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
