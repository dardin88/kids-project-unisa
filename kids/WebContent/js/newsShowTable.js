function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
     $("#addLinkButton").button();    
    /*   $("#addLinkWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#removeLinkWindow").dialog({
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
    addNews(); */
    buildShowTable();
}

function addNews(){
    $("#addLinkButton").click(function() {
        //      showArtefacts($("#artefactTitolo"))
        //    showArtefacts($("#artefactDescrizione"));
        $("#addLinkWindow").dialog("open");
        $("#addLinkButton2").button();
        $("#addLinkForm").validate({
            rules: {
                artefactTitolo: {
                    required: true
                },
                artefactDescrizione: {
                    required: true
                },
                /*notEqual: true,
                    remote: {
                        url:"VerifyTraceabilityLinkExistence",
                        type: "post",
                        asyn: false,
                        data: {
                            artefact1: function(){
                                var artefact1 = $("#artefact1").val();
                                return artefact1;
                            },
                            artefact2: function(){
                                var artefact2 = $("#artefact2").val();
                                return artefact2;
                            }
                        }
                    }
                },*/
                artefactTipo:{
                    required:true
                }
            },
            messages: {
                artefactTitolo: {
                    required: "Inserisci il titolo."
                },
                artefactDescrizione: {
                    required: "Inserisci la descrizione.",
                    remote: "Link già esistente."
                },
                artefactTipo:{
                    required: "Devi selezionare il tipo di news."
                }
            },
            submitHandler: function() {
                /* $.post("InsertNews", {
                    artefactTitolo: $("#artefactTitolo").val(),
                    artefactDescrizione: $("#artefactDescrizione").val(),
                    artefactTipo: $("#artefactTipo").val(),
                    artefactData: $("#artefactData").val(),
                    artefactOra: $("#artefactOra").val(),
                    artefactAllegato: $("#artectAllegato").val()

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
            */
                form.submit();
            }
        });
    });
}

/*function showNews(select){
    $.getJSON("GetNews",{
        ajax: 'true',
        type: "POST",
        cache: false,
        async: false
    }, function (artefacts){
        select.html("<option value=\"\"></option>");
        $.each(artefacts, function(index, artefact){
            select.append(
                $('<option></option>').val(artefact.id).html(artefact.name)
                );
        });
    });
}
*/

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
}
