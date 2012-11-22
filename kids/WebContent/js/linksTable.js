function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#addLinkWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#addLinkButton").button();    
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
    buildLinksTable();
    addLink();
}

function addLink(){
    $.validator.addMethod("notEqual", function(value, element) {
        return $('#artefact1').val() != $('#artefact2').val()
    }, "<br />I due artefatti devono essere diversi");
    $("#addLinkButton").click(function() {
        showArtefacts($("#artefact1"))
        showArtefacts($("#artefact2"));
        $("#addLinkWindow").dialog("open");
        $("#addLinkButton2").button();
        $("#addLinkForm").validate({
            rules: {
                artefact1: {
                    required: true
                },
                artefact2: {
                    required: true,
                    notEqual: true,
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
                }
            },
            messages: {
                artefact1: {
                    required: "Inserisci il primo artefatto."
                },
                artefact2: {
                    required: "Inserisci il secondo artefatto.",
                    remote: "Link già esistente."
                }
            },
            submitHandler: function() {
                $.post("AddTraceabilityLink?"+new Date().getTime(), {
                    artefact1: $("#artefact1").val(),
                    artefact2: $("#artefact2").val()
                });
                $("#addLinkWindow").dialog("close"); 
                var oTable = $("#linksTable").dataTable();
                oTable.fnDraw();
                $("#artefact1").val("");
                $("#artefact2").val("");
            }
        });
    });
}

function removeLink(tlId){
    $("#removeLinkWindow").dialog("open"); 
    $("#confirmRemoveLinkButton").button();
    $("#confirmRemoveLinkButton").click(function(){
        $.post("RemoveTraceabilityLink?"+new Date().getTime(), {
            tlId: tlId
        });
        $("#removeLinkWindow").dialog("close"); 
        var oTable = $("#linksTable").dataTable();
        oTable.fnDraw();
    });
    $("#notConfirmRemoveLinkButton").button();
    $("#notConfirmRemoveLinkButton").click(function(){
        $("#removeLinkWindow").dialog("close");
    });
}

function buildLinksTable(){
    $('#linksTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "SearchTraceabilityLinks",
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
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ link",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 link",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "aoColumns": [
        {
            "sWidth": "47%"
        },
        {
            "sWidth": "47%"
        },
        null
        ],
        "fnServerData": function (sSource, aoData, fnCallback){
            $.post(sSource,aoData,fnCallback,"json");
        }
    });
}

function showArtefacts(select){
    $.getJSON("GetArtefacts?"+new Date().getTime(),{
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