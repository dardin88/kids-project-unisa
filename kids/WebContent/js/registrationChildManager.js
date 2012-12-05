function initializeRegistrationFields(){
    $.ajaxSetup({
        cache: false
    });
    
    /*
     * Impostare le finestre di dialogo invisibili all'apertura della pagina
     */
    $("#newRegistrationChildWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    
    $.validator.setDefaults({
        highlight: function(input){
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input){
            $(input).removeClass("ui-state-highlight");
        }
    });
    
    /*
     * Impostare le azioni di apertura delle finestre di dialogo
     */
    $("#newRegistrationChildButton").button();
    $("#newRegistrationChildButton").click(function() {
        $("#newRegistrationChildWindow").dialog("open");
    });
    
    /*
     * Si associano ai button le relative funzioni e servlet
     */
    $("#createNewDraftButton").button();
    $("#createNewDraftButton").click(function() {
        $("#newRegistrationChildForm").validate({
            rules: {
                Cognome: {
                    required: true
                },
                Nome: {
                    required: true
                },
                DataNascita:    {
//                    date: true
                },
                CodiceFiscale:  {
                    //codicefiscale: true
                }
            },
            messages: {
                Cognome: {
                    required: "Inserisci il cognome"
                },
                Nome:   {
                    required: "Inserisci il nome"
                },
                DataNascita:    {
//                    date: "La data di nascita deve essere nel formato (AAAA/MM/GG)"
                },
                CodiceFiscale:  {
                   // codicefiscale: "Inserire il codice fiscale in modo corretto"
                }
            },
            submitHandler: function() {
                $.post("CreateDraftRegistrationChild", {
                    Cognome: $("#Cognome").val(),
                    Nome: $("#Nome").val(),
                    DataNascita: $("#DataNascita").val(),
                    ComuneNascita: $("#ComuneNascita").val(),
                    CodiceFiscale: $("#CodiceFiscale").val(),
                    Cittadinanza: $("#Cittadinanza").val(),
                    FasciaUtenza: $("#FasciaUtenza").val()
                });
                $("#newRegistrationChildWindow").dialog("close");
                //alert("La bozza è stata salvata");
                location.href = "./registrationChild.jsp";
            }
        });
    });
    
    $("#submitNewDraftButton").button();
    $("#submitNewDraftButton").click(function(){
        $("#newRegistrationChildForm").validate({
            rules: {
                Cognome: {
                    required: true
                },
                Nome: {
                    required: true
                },
                DataNascita:    {
                    required: true//,
//                    date: true
                },
                ComuneNascita:  {
                    required: true
                },
                CodiceFiscale:  {
                    required: true,
                    codicefiscale: true
                },
                Cittadinanza:   {
                    required: true
                },
                FasciaUtenza:   {
                    required: true
                }
            },
            messages: {
                Cognome: {
                    required: "Inserisci il cognome."
                },
                Nome:   {
                    required: "Inserisci il nome"
                },
                DataNascita:    {
                    required: "Inserire la data di nascita (AAAA-MM-GG)"//,
                    //date: "Inserire la data di nascita (AAAA-MM-GG)"
                },
                ComuneNascita:  {
                    required: "Inserire il comune di nascita"
                },
                CodiceFiscale:  {
                    required: "Inserire il codice fiscale",
                    codicefiscale: "Inserire il codice fiscale in modo corretto"
                },
                Cittadinanza:   {
                    required: "Inserire la cittadinanza"
                },
                FasciaUtenza:   {
                    required: "Selezionare la fascia d'utenza"
                }
            },
            submitHandler: function() {
                $.post("SubmitDraftRegistrationChild?", {
                    Cognome: $("#Cognome").val(),
                    Nome: $("#Nome").val(),
                    DataNascita: $("#DataNascita").val(),
                    ComuneNascita: $("#ComuneNascita").val(),
                    CodiceFiscale: $("#CodiceFiscale").val(),
                    Cittadinanza: $("#Cittadinanza").val(),
                    FasciaUtenza: $("#FasciaUtenza").val()
                });
            }
        });
    });
    
    $("#notSubmitDraftButton").button();
    $("#notSubmitDraftButton").click(function(){
        $("#newRegistrationChildWindow").dialog("close"); 
    });
    
}

function createTableRegistrationChild() {
        $('#registrationChildTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableRegistrationChild",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
//        "fnServerParams": function ( aoData ) {
//           aoData.push(// Parametri di ricerca aggiuntivi
//            {
//                "name" : "FaseDellIscrizione", 
//                "value" : $('#FaseDellIscrizione').val()
//            }
//            );
//     
//        },

        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
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

function search(){
   /* var oTable = $("#traineesTable").dataTable();
    oTable.fnDraw();
       */ 
}