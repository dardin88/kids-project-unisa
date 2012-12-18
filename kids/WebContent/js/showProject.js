/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    
    $("#uploadFileWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    
    $("#submitProjectAnnualButton").button();
    $("#confirmProjectAnnualButton").button();

}

function uploadFile(){
    $("#uploadFileWindow").dialog("open");
    $("#sottometti").button();
    $("#scegliFile").button();  
    if($("#sottometti").click(function(){
        
        var valore=$("#scegliFile").val();
        var str=valore.split("\\");
        var nomeFile=str[str.length-1];       
        if (valore!="")
            document.getElementById("uploadFileForm").action="UploadProject?scegliFile"+nomeFile;
        else
            document.getElementById("uploadFileForm").action=""; 
    }));
    
//    $("#uploadFileWindow").dialog("close"); 
//    var oTable = $("#showProjectAnnualTable").dataTable();
//    oTable.fnDraw();
}

function submit(id){
    $.post("SubmitProject",{
        idProgetto:id
    });
    alert("Progetto Sottomesso!");
}

function buildShowTable(){
    $.post("ShowProject", 
        function (data){ 
            var result=data.aaData;
            $("#mostraPath").html(result[0]);
            $("#mostraStato").html(result[0]);
        },"text");
//    $('#showProjectAnnualTable').dataTable({
//        "bJQueryUI": true,
//        "bServerSide": true,
//        "bProcessing": true,
//        "sAjaxSource": "ShowProject",
//        "bPaginate": true,
//        "bLengthChange": false,
//        "bFilter":false,
//        "bSort": false,
//        "bDestroy": true,
//        "bInfo": true,
//        "bAutoWidth": true,
//        "sPaginationType": "full_numbers",
//        "oLanguage": {
//            "sProcessing":   "Caricamento...",
//            "sLengthMenu":   "Visualizza _MENU_ link",
//            "sZeroRecords":  "<b>La ricerca non ha portato alcun risultato.</b>",
//            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ NEWS",
//            "sInfoEmpty":    "<b>Vista da 0 a 0 di 0 di News</b>",
//            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
//            "sInfoPostFix":  "",
//            "sSearch":       "Contenuto News:",
//            "oPaginate": {
//                "sFirst":    "<<",
//                "sPrevious": "<",
//                "sNext":     ">",
//                "sLast":     ">>"
//            }
//        },
//        "aoColumns": [
//        {
//            "sWidth": "30%"
//        },
//        {
//            "sWidth": "25%"
//        },
//        
//        ],
//        "fnServerData": function (sSource, aoData, fnCallback){ 
//            $.post(sSource,aoData,fnCallback,"json");
//        }
//    });
//    var oTable = $("#showProjectAnnualTable").dataTable();
//    if (oTable.length > 0) {
//        $("#linkTable").css("width", "100%");
//    }
}

