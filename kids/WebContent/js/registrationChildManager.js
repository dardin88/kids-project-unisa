function initializeRegistrationFields(){
    $.ajaxSetup({
        cache: false
    });
    
    $("#registrationButton").button();
    
    $("#registrationForm").validate({
        rules: {
            Cognome: {
                required: true
            },
            Nome: {
                required: true
            },
            DataNascita:    {
                date: true
            },
            CodiceFiscale:  {
                codicefiscale: true
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
                date: "La data di nascita deve essere nel formato (gg/MM/AAAA)"
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
        
    $.validator.setDefaults({
        highlight: function(input){
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input){
            $(input).removeClass("ui-state-highlight");
        }
    });
}
