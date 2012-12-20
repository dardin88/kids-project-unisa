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
    
    $("#removeCommentoWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
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
                idAutore:$("#idAutore").val(),
                tipoAttore:$("#tipoAttore").val(),
                idProgetto:$("#idProgetto").val()

            });
            $("#insertCommentoWindow").dialog("close");
            var oTable = $("#commentEduTable").dataTable();
            oTable.fnDraw();
            document.location.reload(true);
            $("#contenutoCommento").val("");
        }
    });
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
            $("#idProgetto").val(result[2]);
        },"text");
}

function removeComment(id){
    $("#removeCommentoWindow").dialog("open");
    $("#notRemoveCommentoButton").button();
    $("#notRemoveCommentoButton").click(function(){
        $("#removeCommentoWindow").dialog("close");
    });
    $("#removeCommentoButton").button();
    $("#removeCommentoButton").click(function(){
        $.post("RemoveCommento",{
            idCommento:""+id
        });
        $("#removeCommentoWindow").dialog("close");
        document.location.reload(true);
        var oTable = $("#commentEduTable").dataTable();
        oTable.fnDraw();
    }) 
}


function showComments(){
    $('#commentEduTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetCommentTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": true,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "commentType", 
                "value" : "annual_comm"
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
            "sZeroRecords":  "<b>La ricerca non ha portato alcun risultato.</b>",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ COMMENTI",
            "sInfoEmpty":    "<b>Vista da 0 a 0 di 0 di COmmenti</b>",
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
            "sWidth": "15%",
            "sClass": "center"
        },
        {
            "sWidth": "40%",
            "sClass": "center"
        },
        {
            "sWidth": "20%",
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
    var oTable = $("#commentEduTable").dataTable();
    if (oTable.length > 0) {
        $("#linkTable").css("width", "100%");
    }
}

