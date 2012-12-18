function createTableClassification() {
        $('#classificationTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableClassification",
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
            "sZeroRecords":  "Non sono presenti graduatorie",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Graduatorie",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Graduatorie",
            "sInfoFiltered": "(filtrati da _MAX_ graduatorie totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca graduatoria:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "aoColumns": [
        {
            "sWidth": "25%",
            "sClass": "center"
        },
        {
            "sWidth": "25%",
            "sClass": "center"
        },
        {
            "sWidth": "25%",
            "sClass": "center"
        },
        {
            "sWidth": "25%",
            "sClass": "center"
        },
        ],
         "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });
    var oTable = $("#classificationTable").dataTable();
    if (oTable.length > 0) {
        $("#classificationTable").css("width", "100%");
    }
}
function updateClassificationTable() {
    var oTable = $("#classificationTable").dataTable();
    oTable.fnDraw();
}

function createTableResult(id) {
        $('#classificationResultTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableResult?Id=" + id + "&Stato=" + getValue("classificationDisplayStatus"),
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
            "sZeroRecords":  "La graduatoria non riporta ancora nessun esito",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Risultati",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Risultati",
            "sInfoFiltered": "(filtrati da _MAX_ risultati totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca risultato:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "aoColumns": [
        {
            "sWidth": "10%",
            "sClass": "center"
        },
        {
            "sWidth": "20%"
        },
        {
            "sWidth": "22%"
        },
        {
            "sWidth": "22%"
        },
        {
            "sWidth": "10%",
            "sClass": "center"
        },
        {
            "sWidth":"16%",
            "sClass": "center"
        }
        ],
         "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });
    var oTable = $("#classificationResultTable").dataTable();
    if (oTable.length > 0) {
        $("#classificationResultTable").css("width", "100%");
    }
}
function updateResultTable() {
    var oTable = $("#classificationResultTable").dataTable();
    oTable.fnDraw();
}