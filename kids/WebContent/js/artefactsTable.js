function initializeArtefactsManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#addArtefactWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#addArtefactButton").button();    
    $("#removeArtefactWindow").dialog({
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
    buildArtefactsTable();
    addArtefact();
}

function addArtefact(){
    $("#addArtefactButton").click(function() {
        $("#addArtefactWindow").dialog("open");
        $("#addArtefactButton2").button();
        $("#addArtefactForm").validate({
            rules: {
                artefact: {
                    required: true,
                    remote: {
                        url:"VerifyArtefactExistence",
                        type: "post",
                        asyn: false,
                        data: {
                            artefact: function(){
                                var artefact = $("#artefact").val();
                                return artefact;
                            }
                        }
                    }
                }
            },
            messages: {
                artefact: {
                    required: "Inserisci l'artefatto.",
                    remote: "Artefatto già esistente."
                }
            },
            submitHandler: function() {
                $.post("AddArtefact?"+new Date().getTime(), {
                    artefact: $("#artefact").val()
                });
                $("#addArtefactWindow").dialog("close"); 
                var oTable = $("#artefactsTable").dataTable();
                oTable.fnDraw();
                $("#artefact").val("");
            }
        });
    });
}

function removeArtefact(aId){
    $("#removeArtefactWindow").dialog("open"); 
    $("#confirmRemoveArtefactButton").button();
    $("#confirmRemoveArtefactButton").click(function(){
        $.post("RemoveArtefact?"+new Date().getTime(), {
            aId: aId
        });
        $("#removeArtefactWindow").dialog("close"); 
        var oTable = $("#artefactsTable").dataTable();
        oTable.fnDraw();
    });
    $("#notConfirmRemoveArtefactButton").button();
    $("#notConfirmRemoveArtefactButton").click(function(){
        $("#removeArtefactWindow").dialog("close");
    });
}

function buildArtefactsTable(){
    $('#artefactsTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "SearchArtefacts",
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
            "sLengthMenu":   "Visualizza _MENU_ artefatti",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ artefatti",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 artefatti",
            "sInfoFiltered": "(filtrati da _MAX_ artefatti totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "fnServerData": function (sSource, aoData, fnCallback){
            $.post(sSource,aoData,fnCallback,"json");
        }
    });
}