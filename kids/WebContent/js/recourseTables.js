function createTablePossibleRecourse() {
        $('#recoursePossibleTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTablePossibleRecourse",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": true,
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "Non è possibile presentare ricorsi",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ ricorsi",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Ricorsi",
            "sInfoFiltered": "(filtrati da _MAX_ ricorsi)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca domanda di iscrizione:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "aoColumns": [
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%",
            "sClass": "center"
        }
        ],
         "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });
    var oTable = $("#recoursePossibleTable").dataTable();
    if (oTable.length > 0) {
        $("#recoursePossibleTable").css("width", "100%");
    }
}
function updateTablePossibleRecourse() {
    var oTable = $("#recoursePossibleTable").dataTable();
    oTable.fnDraw();
}

function createTableSubmittedRecourse() {
        $('#recourseSubmittedTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableRecourse",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": true,
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "Non sono presenti ricorsi",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ ricorsi",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 ricorsi",
            "sInfoFiltered": "(filtrati da _MAX_ ricorsi totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca ricorso:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "aoColumns": [
        {
            "sWidth": "20%",
            "sClass": "center"
        },
        {
            "sWidth": "20%"
        },
        {
            "sWidth": "20%"
        },
        {
            "sWidth": "20%"
        },
        {
            "sWidth": "10%",
            "sClass": "center"
        },
        {
            "sWidth": "10%",
            "sClass": "center"
        }
        ],
         "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });
    var oTable = $("#recourseSubmittedTable").dataTable();
    if (oTable.length > 0) {
        $("#recourseSubmittedTable").css("width", "100%");
    }
}
function updateTableSubmittedRecourse() {
    var oTable = $("#recourseSubmittedTable").dataTable();
    oTable.fnDraw();
}