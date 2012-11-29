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

function removeNews(id){
    $("#removeNewsWindow").dialog("open");
    $("#notRemoveNewsButton").button();
    $("#notRemoveNewsButton").click(function(){
        $("#removeNewsWindow").dialog("close");
    });
    $("#removeNewsButton").button();
    $("#removeNewsButton").click(function(){
        $.post("RemoveNews",{
            idNews:""+id
        });
         $("#removeNewsWindow").dialog("close");
        var oTable = $("#linkTable").dataTable();
        //alert(responseText);
        oTable.fnDraw();
    }) 
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
                  //  equalTo:!"0"
                    remote:{
                        url:"VerifyTypeNews",
                        type: "post",
                        data:{
                            valore:function(){
                                var valoreSelect=$("#selectNews").val();
                                return valoreSelect;
                              //  var valoreOra=$("#selectNews").val();
                              //  return valoreOra;
                            }
                          
                        }
                            
                    }
                   
                },
                dataNews:{
                    required:true
                }/*,
                oraNews:{
                    remote:{
                        url:"VerifyTime",
                        type: "post",
                        data:{
                            valore:function(){
                                var valoreSelect=$("#selectNews").val();
                                return valoreSelect;
                            }
                        }
                    }
                }*/
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
                }/*,
                oraNews:{
                    remote:"Ora obbligatoria"
                }
               */ 
            },
            submitHandler: function() {
                 $.post("InsertNews", {
                    artefactTitolo: $("#artefactTitolo").val(),
                    artefactDescrizione: $("#artefactDescrizione").val(),
                    artefactTipo: $("#artefactTipo").val(),
                    artefactData: $("#artefactData").val(),
                    artefactOra: $("#artefactOra").val(),
                    artefactAllegato: $("#artectAllegato").val()

                });
                $("#addLinkWindow").dialog("close"); 
            //    alert(responseText);
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
            "sWidth": "20%"
        },
        {
            "sWidth": "35%"
        },
        {
            "sWidth": "15%"
        },
        {
            "sWidth": "10%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "15%"
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
