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
    $("#removeCommunicationWindow").dialog({
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
    buildNeedCommunicationTable();
    addNeedCommunication();
}
function removeCommunication(id){
    $("#removeCommunicationWindow").dialog("open");
    $("#notRemoveCommunicationButton").button();
    $("#notRemoveCommunicationButton").click(function(){
        $("#removeCommunicationWindow").dialog("close");
    });
    $("#removeCommunicationButton").button();
    $("#removeCommunicationButton").click(function(){
        $.post("RemoveCommunication",{
            idCommunication:""+id
        });
         $("#removeCommunicationWindow").dialog("close");
        var oTable = $("#linkTable").dataTable();
        //alert(responseText);
        oTable.fnDraw();
    }) 
}
function addNeedCommunication(){
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
                },
                solved: {
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
                },
                solved: {
                    required: "Risolvi"
                }
            },
         submitHandler: function() {
             $.post("InsertNeedCommunication",{
                 idEducator: $("idEducator").val(),
                 idChild: $("idChild").val(),
                 description: $("description").val(),
                 date: $("date").val(),
                 solved: $("solved").val()
                });
                $("#addLinkWindow").dialog("close"); 
                var oTable = $("#linksTable").dataTable();
                oTable.fnDraw();
                $("idEducator").val("");
                $("idChild").val("");
                $("description").val("");
                $("date").val("");
                $("solved").val("");
            }
        });
    });
}     
function buildNeedCommunicationTable(){
    $('#NeedCommunicationTable').dataTable({
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
    var oTable = $("#NeedCommunicationTable").dataTable();
    if (oTable.length > 0) {
            $("#NeedCommunicationTable").css("width", "100%");
    }
}
