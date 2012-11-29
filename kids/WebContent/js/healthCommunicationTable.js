function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#addLinkWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
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
    buildHealthCommunicationTable();
    addHealthCommunication();
}
function addHealthCommunication(){
    $("#addLinkButton").click(function() {
        $("#addLinkWindow").dialog("open");
        $("#addLinkButton2").button();
        $("#addLinkForm").validate({
            rules: {
                idEducator: {
                    required: true
                },
                idChild: {
                    required: true
                },
                description: {
                    required: true
                },
                date: {
                    required: true
                }
            },
messages: {
                idEducator: {
                    required: "Inserisci id Educatore."
                },
                idChild: {
                    required: "Inserisci id Bambino."
                },
                description: {
                    required: "Inserisci descrizione"
                },
                date: {
                    required: "Inserisci data"
                }
            },
         submitHandler: function() {
             $.post("InsertHealthCommunication",{
                 idEducator: $("idEducator").val(),
                 idChild: $("idChild").val(),
                 description: $("description").val(),
                 date: $("date").val()
                });
                $("#addLinkWindow").dialog("close"); 
                var oTable = $("#linksTable").dataTable();
                oTable.fnDraw();
                $("idEducator").val("");
                $("idChild").val("");
                $("description").val("");
                $("date").val("");
            }
        });
    });
}
function removeHealthCommunication(tlId){
    $("#removeLinkWindow").dialog("open"); 
    $("#confirmRemoveLinkButton").button();
    $("#confirmRemoveLinkButton").click(function(){
        $("#removeLinkWindow").dialog("close"); 
        var oTable = $("#healthCommunicationTable").dataTable();
        oTable.fnDraw();
    });        
    $("#notConfirmRemoveLinkButton").button();
    $("#notConfirmRemoveLinkButton").click(function(){
        $("#removeLinkWindow").dialog("close");
    });
}           
function buildHealthCommunicationTable(){
    $('#HealthCommunicationTable').dataTable({
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
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ COMMUNICATION",
            "sInfoEmpty":    "<b>Vista da 0 a 0 di 0 di Comunicazione</b>",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Contenuto Comunicazione:",
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
    var oTable = $("#HealthCommunicationTable").dataTable();
    if (oTable.length > 0) {
            $("#HealthCommunicationTable").css("width", "100%");
    }
}
