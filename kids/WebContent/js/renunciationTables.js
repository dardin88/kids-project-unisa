function createTablePossibleRenunciation() {
        $('#renunciationPossibleTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTablePossibleRenunciation",
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
            "sZeroRecords":  "Non è possibile presentare domande di rinuncia",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ domande di iscrizione",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Risultati",
            "sInfoFiltered": "(filtrati da _MAX_ domande di iscrizione)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca domanda d'iscrizione:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "aoColumns": [
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
            "sWidth": "20%",
            "sClass": "center"
        },
        {
            "sWidth": "20%",
            "sClass": "center"
        }
        ],
         "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });
    var oTable = $("#renunciationPossibleTable").dataTable();
    if (oTable.length > 0) {
        $("#renunciationPossibleTable").css("width", "100%");
    }
}
function updateTablePossibleRinunciation() {
    var oTable = $("#renunciationPossibleTable").dataTable();
    oTable.fnDraw();
}

function createTableSubmittedRenunciation() {
        $('#renunciationSubmittedTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableRenunciation",
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
            "sZeroRecords":  "Non sono presenti domande di rinuncia",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ domande di rinuncia",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 domande di rinuncia",
            "sInfoFiltered": "(filtrati da _MAX_ domande di rinuncia totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca domanda di rinuncia:",
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
            "sWidth": "20%",
            "sClass": "center"
        }
        ],
         "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });
    var oTable = $("#renunciationSubmittedTable").dataTable();
    if (oTable.length > 0) {
        $("#renunciationSubmittedTable").css("width", "100%");
    }
}
function updateTableSubmittedRinunciation() {
    var oTable = $("#renunciationSubmittedTable").dataTable();
    oTable.fnDraw();
}