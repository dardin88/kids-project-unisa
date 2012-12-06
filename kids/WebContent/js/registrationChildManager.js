function initializeRegistrationFields(){
    $.ajaxSetup({
        cache: false
    });
    
    $.validator.setDefaults({
        highlight: function(input){
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input){
            $(input).removeClass("ui-state-highlight");
        }
    });
    
    //  NUOVA BOZZA - FINESTRA
    $("#newRegistrationChildWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    //  NUOVA BOZZA - BOTTONE DI APERTURA
    $("#newRegistrationChildButton").button();
    $("#newRegistrationChildButton").click(function() {
        $("#newRegistrationChildWindow").dialog("open");
    });
    //  NUOVA BOZZA - BOTTONE INSERIMENTO
    $("#createNewDraftButton").button();
    $("#createNewDraftButton").click(function() {
        $("#newRegistrationChildForm").validate({
            rules: {
                NewCognome: {
                    required: true
                },
                NewNome: {
                    required: true
                },
                NewDataNascita:    {
//                    date: true
                },
                NewCodiceFiscale:  {
                    //codicefiscale: true
                }
            },
            messages: {
                NewCognome: {
                    required: "Inserisci il cognome"
                },
                NewNome:   {
                    required: "Inserisci il nome"
                },
                NewDataNascita:    {
//                    date: "La data di nascita deve essere nel formato (AAAA/MM/GG)"
                },
                NewCodiceFiscale:  {
                   // codicefiscale: "Inserire il codice fiscale in modo corretto"
                }
            },
            submitHandler: function() {
                $.post("CreateDraftRegistrationChild", {
                    Cognome: $("#NewCognome").val(),
                    Nome: $("#NewNome").val(),
                    DataNascita: $("#NewDataNascita").val(),
                    ComuneNascita: $("#NewComuneNascita").val(),
                    CodiceFiscale: $("#NewCodiceFiscale").val(),
                    Cittadinanza: $("#NewCittadinanza").val(),
                    FasciaUtenza: $("#NewFasciaUtenza").val()
                });
                $("#newRegistrationChildWindow").dialog("close");
                //alert("La bozza è stata salvata");
                location.href = "./registrationChild.jsp";
            }
        });
    });
    //  NUOVA BOZZA - BOTTONE ANNULLA
    $("#undoNewDraftButton").button();
    $("#undoNewDraftButton").click(function(){
        $("#newRegistrationChildWindow").dialog("close");
        location.href = "./registrationChild.jsp";
    });
    //  FINE NUOVA BOZZA
    
    //  MODIFICA BOZZA - FINESTRA
    $("#editRegistrationChildWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    //  MODIFICA BOZZA - BOTTONE SALVA MODIFICHE
    $("#saveEditDraftButton").button();
    $("#saveEditDraftButton").click(function() {
        $("#editRegistrationChildForm").validate({
            rules: {
                EditCognome: {
                    required: true
                },
                EditNome: {
                    required: true
                },
                EditDataNascita:    {
//                    date: true
                },
                EditCodiceFiscale:  {
                    //codicefiscale: true
                }
            },
            messages: {
                EditCognome: {
                    required: "Inserisci il cognome"
                },
                EditNome:   {
                    required: "Inserisci il nome"
                },
                EditDataNascita:    {
//                    date: "La data di nascita deve essere nel formato (AAAA/MM/GG)"
                },
                EditCodiceFiscale:  {
                   // codicefiscale: "Inserire il codice fiscale in modo corretto"
                }
            },
            submitHandler: function() {
                $.post("EditRegistrationChild", {
                    Id: $("#EditIdDraft").val(),
                    Cognome: $("#EditCognome").val(),
                    Nome: $("#EditNome").val(),
                    DataNascita: $("#EditDataNascita").val(),
                    ComuneNascita: $("#EditComuneNascita").val(),
                    CodiceFiscale: $("#EditCodiceFiscale").val(),
                    Cittadinanza: $("#EditCittadinanza").val(),
                    FasciaUtenza: $("#EditFasciaUtenza").val()
                });
                $("#editRegistrationChildWindow").dialog("close");
                //alert("La bozza è stata modificata");
                location.href = "./registrationChild.jsp";
            }
        });
    });
    //  MODIFICA BOZZA - BOTTONE SOTTOMISSIONE
    $("#submitEditDraftButton").button();
    $("#submitEditDraftButton").click(function(){
        $("#editRegistrationChildForm").validate({
            rules: {
                EditCognome: {
                    required: true
                },
                EditNome: {
                    required: true
                },
                EditDataNascita:    {
                    required: true//,
//                    date: true
                },
                EditComuneNascita:  {
                    required: true
                },
                EditCodiceFiscale:  {
                    required: true//,
//                    codicefiscale: true
                },
                EditCittadinanza:   {
                    required: true
                }
            },
            messages: {
                EditCognome: {
                    required: "Inserisci il cognome"
                },
                EditNome:   {
                    required: "Inserisci il nome"
                },
                EditDataNascita:    {
                    required: "Inserire la data di nascita (AAAA-MM-GG)"//,
                    //date: "Inserire la data di nascita (AAAA-MM-GG)"
                },
                EditComuneNascita:  {
                    required: "Inserire il comune di nascita"
                },
                EditCodiceFiscale:  {
                    required: "Inserire il codice fiscale"//,
//                    codicefiscale: "Inserire il codice fiscale in modo corretto"
                },
                EditCittadinanza:   {
                    required: "Inserire la cittadinanza"
                }
            },
            submitHandler: function() {
                // Vengono salvate le modifica
                $.post("EditRegistrationChild", {
                    Id: $("#EditIdDraft").val(),
                    Cognome: $("#EditCognome").val(),
                    Nome: $("#EditNome").val(),
                    DataNascita: $("#EditDataNascita").val(),
                    ComuneNascita: $("#EditComuneNascita").val(),
                    CodiceFiscale: $("#EditCodiceFiscale").val(),
                    Cittadinanza: $("#EditCittadinanza").val(),
                    FasciaUtenza: $("#EditFasciaUtenza").val()
                });
                // La domanda viene sottomessa
                $.post("EditPhaseRegistrationChild", {
                    Id: $("#EditIdDraft").val(),
                    FaseDellIscrizione: "sottomessa"
                });
            }
        });
    });
    //  MODIFICA BOZZA - BOTTONE ANNULLA
    $("#undoEditDraftButton").button();
    $("#undoEditDraftButton").click(function(){
        $("#editRegistrationChildWindow").dialog("close");
        location.href = "./registrationChild.jsp";
    });
    // FINE MODIFICA BOZZA
    
    //  VISUALIZZA DETTAGLI DOMANDA - FINESTRA
    $("#viewDetailsRegistrationChildWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    //  VISUALIZZA DETTAGLI DOMANDA - SOTTOMETTI DOMANDA
    $("#submitViewDetailsDraftButton").button();
    $("#submitViewDetailsDraftButton").click(function() {
        $("#viewDetailsRegistrationChildForm").validate({
            rules: {
                viewDetailsCognome: {
                    required: true
                },
                viewDetailsNome: {
                    required: true
                },
                viewDetailsDataNascita:  {
                    required: true
                },
                viewDetailsComuneNascita: {
                    required: true
                },
                viewDetailsCodiceFiscale: {
                    required: true
                },
                viewDetailsCittadinanza: {
                    required: true
                }
            },
            messages: {
                viewDetailsCognome: {
                    required: "Modificare la bozza ed inserire il cognome"
                },
                viewDetailsNome: {
                    required: "Modificare la bozza ed inserire il nome"
                },
                viewDetailsDataNascita:  {
                    required: "Modificare la bozza ed inserire la data di nascita"
                },
                viewDetailsComuneNascita: {
                    required: "Modificare la bozza ed inserire il comune di nascita"
                },
                viewDetailsCodiceFiscale: {
                    required: "Modificare la bozza ed inserire il codice fiscale"
                },
                viewDetailsCittadinanza: {
                    required: "Modificare la bozza ed inserire la cittadinanza"
                }
            },
            submitHandler: function() {
                $.post("EditPhaseRegistrationChild", {
                    Id: $("#viewDetailsIdDraft").val(),
                    FaseDellIscrizione: "sottomessa"
                });
                $("#viewDetailsRegistrationChildWindow").dialog("close");
                //alert("La bozza è stata modificata");
                location.href = "./registrationChild.jsp";
            }
        });
    });
    //  VISUALIZZA DETTAGLI DOMANDA - BOTTONE CONFERMA
    $("#confirmViewDetailsDraftButton").button();
    $("#confirmViewDetailsDraftButton").click(function(){
        $("#viewDetailsRegistrationChildForm").validate({   // il submit della segreteria non dovrebbe validare in quanto è già completa
            submitHandler: function() {
                $.post("EditPhaseRegistrationChild", {
                    Id: $("#EditIdDraft").val(),
                    FaseDellIscrizione: "confermata"
                });
            }
        });
    });
    //  VISUALIZZA DETTAGLI DOMANDA - BOTTONE ANNULLA
    $("#undoViewDetailsDraftButton").button();
    $("#undoViewDetailsDraftButton").click(function(){
        $("#viewDetailsRegistrationChildWindow").dialog("close");
        location.href = "./registrationChild.jsp";
    });
    // FINE VISUALIZZA DETTAGLI DOMANDA
    
    // VISUALE DI CONFERMA ELIMINA/CONVALIDA BUTTON
    $("#confirmOperationRCWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    //  VISUALE DI CONFERMA - BOTTONE ANNULLA
    $("#undoOperationRCButton").button();
    $("#undoOperationRCButton").click(function(){
        $("#confirmOperationRCWindow").dialog("close");
        location.href = "./registrationChild.jsp";
    });
    // FINE VISUALE DI CONFERMA ELIMINA/CONVALIDA BUTTON
    
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

function editDraftRegistrationChild(id) {
    $("#EditIdDraft").val(id);
    
    /*
     * MANCA LA PARTE DI COMPLETAMENTO DELLA FORM PRENDENDO I DATI DAL DATABASE
     */
    
    $("#editRegistrationChildWindow").dialog("open");
}

function viewDetailsRegistrationChild(id) {
    $("#viewDetailsIdDraft").val(id);
    //alert("L'id: " + $("#viewDetailsIdDraft").val())
    
    /*
     * MANCA LA PARTE DI COMPLETAMENTO DELLA FORM PRENDENDO I DATI DAL DATABASE
     */
    
    $("#viewDetailsRegistrationChildWindow").dialog("open");
}

function removeRegistrationChild(id) {
    // Inizializzo la finestra di conferma
    $("#confirmOperationRCTitle").html("Sei sicuro di voler eliminare la Domanda?");
    $("#confirmOperationRCIdDraft").val(id);
    
    // Assegno la funzione al bottone
    $("#confirmOperationRCIdDraft").button();
    $("#confirmOperationRCIdDraft").click(function(){
        $("#confirmOperationRCForm").validate({
            submitHandler: function() {
                $.post("EditPhaseRegistrationChild", {
                    Id: $("#confirmOperationRCIdDraft").val(),
                    FaseDellIscrizione: "eliminata"
                });
                $("#confirmOperationRCWindow").dialog("close");
                location.href = "./registrationChild.jsp";
            }
        });
    });
    
    // la rendo visibile
    $("#confirmOperationRCWindow").dialog("open");
}

function confirmRegistrationChild(id) {
    // Inizializzo la finestra di conferma
    $("#confirmOperationRCTitle").html("Sei sicuro di voler confermare la Domanda?");
    $("#confirmOperationRCIdDraft").val(id);
    
    // Assegno la funzione al bottone
    $("#confirmOperationRCIdDraft").button();
    $("#confirmOperationRCIdDraft").click(function(){
        $("#confirmOperationRCForm").validate({
            submitHandler: function() {
                $.post("EditPhaseRegistrationChild", {
                    Id: $("#confirmOperationRCIdDraft").val(),
                    FaseDellIscrizione: "confermata"
                });
                $("#confirmOperationRCWindow").dialog("close");
                location.href = "./registrationChild.jsp";
            }
        });
    });
    
    // la rendo visibile
    $("#confirmOperationRCWindow").dialog("open");
}