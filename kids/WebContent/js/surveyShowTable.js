function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#surveyTabGroup").tabs();
    $("#removeSurveyWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400,
        height: 150
    });
    buildShowTable();
}

function addSurvey(){
    if(document.getElementById("intext").value.length == 0) {
        alert("Non è stato inserito nulla. Prego di riprovare");
    }
    else { 
        alert("Grazie! Il questionario è stato aggiunto alla lista e sarà visibile dai genitori.");
        $.post("InsertSurvey", {
            link: $("#intext").val() 
        });
    }
}

function removeSurvey(id){
    $("#removeSurveyWindow").dialog("open");
    $("#notRemoveSurveyButton").button();
    $("#notRemoveSurveyButton").click(function(){
        $("#removeSurveyWindow").dialog("close");
    });
    $("#removeSurveyButton").button();
    $("#removeSurveyButton").click(function(){
        $.post("RemoveSurvey",{
            id: id
        });
        $("#removeSurveryWindow").dialog("close");
        document.location.reload(true);
        var oTable = $("#linkTable").dataTable();
        oTable.fnDraw();
    }) 
}

function buildShowTable(){
    $('#linkTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetSurvey",
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
            "sWidth": "16%",
            "sClass": "center"
        },
        {
            "sWidth": "80%",
            "sClass": "center"
        },
        {
            "sWidth": "4%",
            "sClass": "center"
        },
        
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

