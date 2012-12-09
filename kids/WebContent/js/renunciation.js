function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#buttonInsertRenunciation").button();   
    $("#insertRenunciationWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 700
    });
  
    $.validator.setDefaults({
        highlight: function(input) {
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input) {
            $(input).removeClass("ui-state-highlight");
        }
    });
    
    // inizializzazione delle DataTable di jQuery
    createTableRenunciation();
    
    
    addRenunciation();
}

//Creazione tabella

function createTableRenunciation() {
    $('#showRenunciationTable').dataTable({
        "bJQueryUI": true,
        //"bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "InsertTableChildServlet",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "Non ci sono figli presenti.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Domande d'iscrizione",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Domande d'iscrizione",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },


        "oTableTools":{
            "aButtons":[
            "Elimina", "Modifica","Visualizza"
            ]
        },
        "aoColumns": [
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth":"10%"
        }
        ],
        "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });
    
}

function addRenunciation(){
    $("#annullaButton").button();
    $("#annullaButton").click(function(){
        $("#insertRenunciationWindow").dialog("close");
   
    });
    
    $("#buttonInsertRenunciation").click(function(){
        $("#insertRenunciationWindow").dialog("open");
        
        $("#printButton").button();
    
        $("#printButton").validate({
            rules: {
                Cognome: {
                    required: true
                },
                Nome: {
                    required: true
                },
                CodiceFiscale:{
                    required:true
                },
                        
                CognomeFiglio:{
                    required:true
                },
                NomeFiglio: {
                    required: true
                },
                DataNascita: {
                    required: true
                }
            },
            
            messages: {
                Cognome:{
                    required: "Inserisci il cognome."
                },
                Nome:{
                    required: "Inserisci il nome."
                },
                CodiceFiscale:{
                    required: "Inserisci il codice fiscale ."
                },
                
                NomeFiglio:{
                    required:"Inserisci il nome del figlio"
                },
                CognomeFiglio:{
                    required:"Inserisci il cognome del figlio"
                },
                DataNascita:{
                    required:"Inserisci la data di nascita del figlio"
                }
            },
            
            submitHandler: function() {
                alert("gdgsgsd");
                $.post("InsertRenunciation", {
                    Cognome: $("#Cognome").val(),
                    Nome: $("#Nome").val(),
                    CodiceFiscale: $("#CodiceFiscale").val(),
                    CognomeFiglio: $("#CognomeFiglio").val(),
                    NomeFiglio: $("#NomeFiglio").val(),
                    DataNascita: $("#DataNascita").val()

                });
                $("#insertRenunciationWindow").dialog("close"); 
                /*   var oTable = $("#linksTable").dataTable();
                oTable.fnDraw();
               */
                $("#Cognome").val("");
                $("#Nome").val("");
                $("#CodiceFiscale").val("");
                $("#CognomeFiglio").val("");
                $("#NomeFiglio").val("");
                $("#DataNascita").val("");
            }
        });
    });
    
    
    function search(id){
        $("#accountsTable").dataTable().fnDraw();
    }
}
