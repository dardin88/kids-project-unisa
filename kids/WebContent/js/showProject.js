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
    $("#insertCommentoWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 500
    });
    
    $("#draftCoord").button();
    $("#submitCoord").button();
    $("#acceptResp").button();
    $("#acceptDeleg").button();
    $("#requestModify").button();
    $("#insertCommento").button();
    buildShowTable();
}

function inserisciCommento(){
    
    
    $("#submitCommento").button();
    $("#insertCommentoWindow").dialog("open");
    $("#insertCommentoForm").validate({
        rules: {              
            contenutoCommento: {
                required: true
            }               
        },
        messages: {              
            contenutoCommento: {
                required: "Inserisci il contenuto."
            }
        },
        submitHandler: function() {                
            $.post("InsertComment", {
                commentType:"annual_comm",
                contenutoCommento:$("#contenutoCommento").val(),
                idAutore:$("#idAutore").val()
            });
                $("#insertCommentoWindow").dialog("close");

        }
    });
          // document.location.reload(true);
    var oTable = $("#commentEduTable").dataTable();
    oTable.fnDraw();
    $("#contenutoCommento").val("");
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
}

function buildShowTable(){
    $.post("ShowProject", 
        function (data){ 
            var result=data.split(",");
            //            if(result[1]=="Sottomesso"){
            //                $("#submitProjectAnnualButton").attr("disabled", "disabled");
            //                $("#confirmProjectAnnualButton").attr("disabled","disabled");
            //            }
            $("#mostraPath").html("<a style=\"color:black;background:none;\" href=\"DownloadProject?nameFile="+result[0]+"\">"+result[0]+"</a>");
            $("#mostraStato").html(result[1]);
        },"text");
}


function showComments(){
    $('#commentEduTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetCommentsTable",
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
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ NEWS",
            "sInfoEmpty":    "<b>Vista da 0 a 0 di 0 di News</b>",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Contenuto Commento:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "aoColumns": [
        {
            "sWidth": "15%",
            "sClass": "center"
        },
        {
            "sWidth": "8%",
            "sClass": "center"
        },
        {
            "sWidth": "8%",
            "sClass": "center"
        },
        {
            "sWidth": "15%",
            "sClass": "center"
        },
        {
            "sWidth": "15%"  ,
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
    var oTable = $("#linkTable").dataTable();
    if (oTable.length > 0) {
        $("#linkTable").css("width", "100%");
    }
}

