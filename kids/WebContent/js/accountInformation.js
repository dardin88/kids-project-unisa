function initializeRegistrationFields(){
    $.ajaxSetup({
        cache: false
    });
    
    $("#registrationButton").button();
    
    $.validator.setDefaults({
        highlight: function(input){
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input){
            $(input).removeClass("ui-state-highlight");
        }
    });
}

$(document).ready(function(){
    $("#registrationForm").validate({
        rules:
        {
            
            Nome:{
                required:true
            },
            Cognome:"required",
            ComuneNascita:"required",
            DataNascita:{
                required:true,
                date:true
            },
            CodiceFiscale:"required",
            Cittadinanza:"required",
            ComuneResidenza:"required",
            ProvinciaResidenza:"required",
            IndirizzoResidenza:"required"
        },
        messages:{
            Nome:" Inserisci il nome",
            Cognome:" Inserisci il cognome",
            ComuneNascita:" Inserisci la città di nascita",
            DataNascita:" Inserisci la data di nascita(gg/MM/AAAA)",
            CodiceFiscale:" Inserisci il codice fiscale",
            Cittadinanza:" Inserisci la cittadinanza",
            ComuneResidenza:" Inserisci il comune del residenza",
            ProvinciaResidenza:"Inserisci la provincia della residenza",
            IndirizzoResidenza:"Inserisci la via della residenza"
        },
        submitHandler:function() {
            $.post("AddAccount",{
                matricolaAccount:$("#register").val(),
                nomeAccount:$("#accountName").val(),
                cognomeAccount:$("#accountSurname").val(),
                comuneNascitaAccount:$("#placeOfBirth").val(),
                dataNascitaAccount:$("#dateOfBirth").val(),
                codiceFiscaleAccount:$("#taxCode").val(),
                cittadinanzaAccount:$("#citizenship").val(),
                comuneResidenzaAccount:$("#municipalityResidence").val(),
                provinciaResidenzaAccount:$("#provinceResidence").val(),
                indirizzoResidenzaAccount:$("#viaResidence").val()
            })
        }
    });
});