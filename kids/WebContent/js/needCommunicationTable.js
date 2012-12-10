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

    $("#solvedCommunicationWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    
    $("#showCommunicationWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 500
    });
    $.validator.setDefaults({
        highlight: function(input) {
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input) {
            $(input).removeClass("ui-state-highlight");
        }
    });
    addCommunication(); 
}

function showCommunication(id,type,idEducator,idChild,description,date,solved){
    $("#showCommunicationWindow").dialog("open"); 
    document.getElementById('labelIdEducator').innerHTML = idEducator; 
    document.getElementById('labelIdChild').innerHTML = idChild; 
    document.getElementById('labelDescription').innerHTML = description; 
    document.getElementById('labelDate').innerHTML = date;
    document.getElementById('labelSolved').innerHTML = solved;
}

function solvedCommunication(id,typeCommunication,idEducator,idChild,description,date,solved){
    $("#solvedCommunicationWindow").dialog("open");
    $("#confirmCommunicationNews").button();
    $("#solvedCommunication").button();
    /*
    document.forms["updateCommunicationForm"].elements["idEducator"].value=idEducator;        
    document.forms["updateCommunicationForm"].elements["idChild"].value=idChild;
    document.forms["updateCommunicationForm"].elements["description"].value=description;
    document.forms["updateCommunicationForm"].elements["date"].value=date;
    */
    $("#artefactSolved option").each(function() {
        if($(this).text()==solved){
            $(this).attr("selected","selected");
        }
    });
     
    $("#solvedCommunicationForm").validate({
        rules: {
            solved:{
                required:true
                /*remote:{
                    url:"VerifySolved",
                    type: "post",
                    data:{
                        valore:function(){
                            var valoreSelect=$("#solvedCommunication").val();
                            return valoreSelect;
                        }  
                    }        
                }  */
            }
        },
        messages: {
            solved:{
                required: "Non puoi selezionare il primo item."
              //  remote: "Non puoi selezionare il primo item."
            }
        },
        submitHandler: function() { 
            $.post("SolvedCommunication", {
                artefactTipo: $("#artefactSolved").val()            
            });
            $("#solvedCommunicationWindow").dialog("close");
            document.location.reload(true);
            $("#artefactSolved").val("")
        }
    });
}

function enableButtonUpdate(){
    document.getElementById("solvedCommunication").style.visibility="hidden";
    document.getElementById("confirmSolvedCommunication").style.visibility="visible";
    document.getElementById("artefactSolved").disabled=false;
}

function addCommunication(){
    $("#addLinkButton").click(function() {
        $("#addLinkWindow").dialog("open");
        $("#addLinkButton3").button();
                $("#addLinkForm").validate({
                    rules: {
                        childName: {
                            required: true
                        },
                        childSurname: {
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
                        childName: {
                            required: "Inserisci nome bambino"
                        },
                        childSurname: {
                            required: "Inserisci cognome bambino"
                        },
                        description: {
                            required: "Inserisci descrizione"
                        },
                        date: {
                            required: "Inserisci data"
                        }
                    },
                    submitHandler: function() {
                        $.post("AddCommunication", {
                            artefactType: "Bisogno",
                            artefactName: $("#artefactName").val(),
                            artefactSurname: $("#artefactSurname").val(),
                            artefactDescription: $("#artefactDescription").val(),
                            artefactDate: $("#artefactDate").val()
                        });
                        
                        $("#addLinkWindow").dialog("close"); 
                        var oTable = $("#linksTable").dataTable();
                        oTable.fnDraw();
                        $("#artefactType").val("");
                        $("#artefactName").val("");
                        $("#artefactSurname").val("");
                        $("#artefactDescription").val("");
                        $("#artefactDate").val("");
                    }
                });
    });
}

function buildShowTable(){
    $('#linkTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetNeedCommunication",
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
            "sInfoEmpty":    "<b>Vista da 0 a 0 di 0 di Communicazioni</b>",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Contenuto Communicazione:",
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