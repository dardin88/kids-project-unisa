function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#addLinkButton").button();   
   
    
    $("#addLinkWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#removeNewsWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#updateNewsWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $.validator.setDefaults({
        highlight: function(input) {
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input) {
            $(input).removeClass("ui-state-highlight");
        }
    });

    addNews(); 
}

function removeNews(id,attached){
    $("#removeNewsWindow").dialog("open");
    $("#notRemoveNewsButton").button();
    $("#notRemoveNewsButton").click(function(){
        $("#removeNewsWindow").dialog("close");
    });
    $("#removeNewsButton").button();
    $("#removeNewsButton").click(function(){
        $.post("RemoveNews",{
            idNews:""+id,
            allegatoName:attached
        });
        $("#removeNewsWindow").dialog("close");
        document.location.reload(true);
        var oTable = $("#linkTable").dataTable();
        oTable.fnDraw();
    }) 
}



function updateNews(id,title,description,type,data,time,allegato){
    $("#updateNewsWindow").dialog("open");
    $("#confirmUpdateNews").button();
    $("#updateNews").button();
       
    document.forms["updateNewsForm"].elements["nomeNews"].value=title;        
    document.forms["updateNewsForm"].elements["descrizioneNews"].value=description;        
  
    $("#artefactTipo2 option").each(function() {
        if($(this).text()==type){
            $(this).attr("selected","selected");
        }
    });
  
  
    document.forms["updateNewsForm"].elements["dataNews"].value=data;        
    document.forms["updateNewsForm"].elements["oraNews"].value=time;
     
    $("#updateNewsForm").validate({
        rules: {
            nomeNews: {
                required: true
            },
            descrizioneNews: {
                required: true
            },
            selectNews:{
                required:true,
                remote:{
                    url:"VerifyTypeNews",
                    type: "post",
                    data:{
                        valore:function(){
                            var valoreSelect=$("#selectNews").val();
                            return valoreSelect;
                     
                        }
                          
                    }
                            
                }
                   
            },
            dataNews:{
                required:true
            }
        },
        messages: {
            nomeNews: {
                required: "Inserisci il titolo."
            },
            descrizioneNews: {
                required: "Inserisci la descrizione."
            },
            selectNews:{
                required: "Non puoi selezionare il primo item.",
                remote: "Non puoi selezionare il primo item."
            },
            dataNews:{
                required:"Selezionare la data"
            }
        },
        submitHandler: function() { 
            var attached=$("#selectFile").val();
            var str=attached.split("\\");
            var s=str[str.length-1];
         /*   if(attached!="")
                document.getElementById("updateNewsForm").action="UploadFile";
            else
                document.getElementById("updateNewsForm").action="";
            $("#updateNewsForm").submit();*/
            
            $.post("UpdateNews", {
                artefactTitolo: $("#artefactTitolo2").val(),
                artefactDescrizione: $("#artefactDescrizione2").val(),
                artefactTipo: $("#artefactTipo2").val(),
                artefactData: $("#artefactData2").val(),
                artefactOra: $("#artefactOra2").val(),
                artefactAllegato:s,
                oldAllegato:allegato,
                idNews:""+id               
            });
            
            $("#updateNewsWindow").dialog("close"); 
            //   var oTable = $("#linksTable").dataTable();
            //   oTable.fnDraw();
            document.location.reload(true);
            $("#artefactTitolo2").val("");
            $("#artefactDescrizione2").val("");
            $("#artefactTitolo2").val("");
            $("#artefactData2").val("");
            $("#artefactOra2").val("");
        }
    });
    
}

function enableButtonUpdate(){
    document.getElementById("updateNews").style.visibility="hidden";
    document.getElementById("confirmUpdateNews").style.visibility="visible";
    document.getElementById("selectFile").disabled=false;
    document.getElementById("artefactTitolo2").disabled=false;
    document.getElementById("artefactDescrizione2").disabled=false;
    document.getElementById("artefactTipo2").disabled=false;
    document.getElementById("artefactData2").disabled=false;
    document.getElementById("artefactOra2").disabled=false;
    $("#selectFile").button();

   
}


function verifyOra(){
    var valore=document.getElementById("artefactTipo").value;
    if(valore!=2)
        document.getElementById("errOra").style.visibility="visible";       
}
function hiddenMessage(){
    if((document.getElementById("artefactOra").text!="") ||(document.getElementById("artefactOra2").text!=""))
        document.getElementById("errOra").style.visibility="hidden";       
}


function addNews(){
    $("#addLinkButton").click(function() {
        $("#addLinkWindow").dialog("open");
        $("#addLinkButton2").button();
        $("#addLinkButton3").button();
        $("#addLinkForm").validate({
            rules: {
                nomeNews: {
                    required: true
                },
                descrizioneNews: {
                    required: true
                },
                selectNews:{
                    required:true,
                    remote:{
                        url:"VerifyTypeNews",
                        type: "post",
                        data:{
                            valore:function(){
                                var valoreSelect=$("#selectNews").val();
                                return valoreSelect;
                            }
                          
                        }
                            
                    }
                   
                },
                dataNews:{
                    required:true
                }
            },
            messages: {
                nomeNews: {
                    required: "Inserisci il titolo."
                },
                descrizioneNews: {
                    required: "Inserisci la descrizione."
                },
                selectNews:{
                    required: "Non puoi selezionare il primo item.",
                    remote: "Non puoi selezionare il primo item."
                },
                dataNews:{
                    required:"Selezionare la data"
                }
                
            },
            submitHandler: function() {
                var attached=$("#addLinkButton2").val();
                var str=attached.split("\\");
                var s=str[str.length-1];  
                if(attached!="")
                    document.getElementById("addLinkForm").action="UploadFile";
                else
                    document.getElementById("addLinkForm").action="";
                $.post("InsertNews", {
                    artefactTitolo: $("#artefactTitolo").val(),
                    artefactDescrizione: $("#artefactDescrizione").val(),
                    artefactTipo: $("#artefactTipo").val(),
                    artefactData: $("#artefactData").val(),
                    artefactOra: $("#artefactOra").val(),
                    attachedName:s
                });
                
                $("#addLinkWindow").dialog("close"); 
                var oTable = $("#linksTable").dataTable();
                oTable.fnDraw();
                $("#artefactTitolo").val("");
                $("#artefactDescrizione").val("");
                $("#artefactTitolo").val("");
                $("#artefactData").val("");
                $("#artefactOra").val("");
                $("#artefactAllegato").val("");
       
            }
        });
    });
}




function buildShowTable(){
    $('#linkTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetNews",
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
            "sSearch":       "Contenuto News:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "aoColumns": [
        {
            "sWidth": "15%"
        },
        {
            "sWidth": "8%"
        },
        {
            "sWidth": "8%"
        },
        {
            "sWidth": "15%"
        },
        {
            "sWidth": "15%"  
        },
        {
            "sWidth": "10%"
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

