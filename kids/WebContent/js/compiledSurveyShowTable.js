function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    buildShowTable();
}

function addCompiledSurvey(){
    $.post("InsertCompiledSurvey", {
        idQuestionario: $("#idQuestionario").val(),
        userid: $("#userid").val()
    });
}

function buildShowTable(){
    $('#linkTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetCompiledSurvey",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": true,
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": false,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "<b>La ricerca non ha portato alcun risultato.</b>",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ questionari",
            "sInfoEmpty":    "<b>Vista da 0 a 0 di 0 di questionari</b>",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "sSearch":       "",
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
            "sWidth": "80%",
            "sClass": "center"
        }
        ],
       
        "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });
    var oTable = $("#linkTable").dataTable();
    if (oTable.length > 0) {
        $("#linkTable").css("width", "100%");
    }
}

