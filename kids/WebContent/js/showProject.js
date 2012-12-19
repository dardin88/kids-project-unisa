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
    
    $("#draftCoord").button();
    $("#submitCoord").button();
    $("#acceptResp").button();
    $("#acceptDeleg").button();
    $("#requestModify").button();
    buildShowTable();

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

