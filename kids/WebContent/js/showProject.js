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
}

function submit(id){
    if(id!=null){
        $.post("SubmitProject",{
            idProgetto:id
        });
        alert("Progetto Sottomesso!");
        buildShowTable();
    }else{
        alert("Effettuare prima il caricamento della bozza");
    }
}

function buildShowTable(){
    $.post("ShowProject", 
        function (data){ 
            var result=data.split(",");
            alert(result[1]);
            if(result[1]=="Sottomesso"){
                $("#submitProjectAnnualButton").attr("disabled", "disabled");
                $("#confirmProjectAnnualButton").attr("disabled","disabled");
            }
            $("#mostraPath").html(result[0]);
            $("#mostraStato").html(result[1]);
        },"text");
}

