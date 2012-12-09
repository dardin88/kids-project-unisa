/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function updateSurvey(surveyId, surveyLink) {
    //check tomorrow..it'll be finished!
    
}

function buildShowTable(){
    $('#linkTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetSurvey", //.__.
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
            "sZeroRecords":  "<b>La ricerca non ha portato alcun risultato.</b>",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ ", //.___.
            "sInfoEmpty":    "<b>Vista da 0 a 0 di 0 di </b>",
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
            "sWidth": "20%","sClass": "center"
        },
        {
            "sWidth": "80%","sClass": "center"
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

