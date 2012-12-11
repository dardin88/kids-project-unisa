count=0;



function initializeRegistrationFields(){
    $.ajaxSetup({
        cache: false
    });
    
    

    jQuery.validator.addMethod("codfiscale", function(value) { 
        // espressione migliorabile... ma sufficiente per il nostro esempio
        var regex = /[A-Z]{6}[\d]{2}[A-Z][\d]{2}[A-Z][\d]{3}[A-Z]/;  
        value =value.toUpperCase();
        return value. match(regex);  
    }, "Please insert a valid italian identification number");
   
    $.validator.setDefaults({
        highlight: function(input){
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input){
            $(input).removeClass("ui-state-highlight");
        }
    });
    
    $("#NickPassWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    
    $("#showNickPass").button();
    $("#showNickPass").click(function() {
        $("#NickPassWindow").dialog("close");
        location.href="http://localhost:8080/kids/";
    });
 
    $("#registration1").show();
    $("#registration2").hide();
    $("#registration3").hide(); 
    
    $("#notRegistrationButton1").button();
    $("#notRegistrationButton1").click(function(){
        if(count!=0) count--;
        location.href=("http://localhost:8080/kids/accountSecretary.jsp");
    });
    
    $("#notRegistrationButton2").button();
    $("#notRegistrationButton2").click(function(){
        $("#registration1").show();
        $("#registration2").hide();
        $("#registration3").hide();
        count--;
    });
    
    $("#notRegistrationButton3").button();
    $("#notRegistrationButton3").click(function(){
        $("#registration1").hide();
        $("#registration2").show();
        $("#registration3").hide();
        count--;
    });
   
    $("#registrationButton1").button();
    $("#registrationButton2").button();
    $("#registrationButton3").button();
    
    $("#registrationForm").validate({
        rules:
        {
            Nome:"required",
            Cognome:"required",
            ComuneNascita:"required",
            DataNascita:{
                required:true,
                date:true
            },
            CodiceFiscale:{
                required: true,
                codfiscale: true,
                maxlength: 16
            },
            Cittadinanza:"required",
            ComuneResidenza:"required",
            ProvinciaResidenza:"required",
            IndirizzoResidenza:"required",
            ViaResidenza:"required",
            CapResidenza:{
                required:true,
                number: true,
                maxlength: 5
            },
            Telefono:"number",
            Cellulare:"number",
            Fax:"number",
            Email:{
                required:true,
                email:true
            },
            ComuneDomicilio:"required",
            ProvinciaDomicilio:"required",
            ViaDomicilio:"required",
            CapDomicilio:{
                required:true,
                number: true,
                maxlength: 5
            },
            TitoloDiStudio:"required",
            TipoAccount:"required",
            Reddito:{
                required: true,
                number:true
            },
            ScadenzaContratto:{
                required: true,
                date: true
            }
        },
        messages:{
            Nome:" Inserisci il nome",
            Cognome:" Inserisci il cognome",
            ComuneNascita:" Inserisci la città di nascita",
            DataNascita:{
                required:" Inserisci la data di nascita",
                date:"Formato data non corretto"
            },
            CodiceFiscale:{
                required: "Inserisci il codice fiscale",
                codfiscale: "Formato non valido",
                maxlength: "Max 16 caratteri"
            },
            Cittadinanza:" Inserisci la cittadinanza",
            ComuneResidenza:" Inserisci il comune del residenza",
            ProvinciaResidenza:"Inserisci la provincia della residenza",
            IndirizzoResidenza:"Inserisci la via della residenza",
            ViaResidenza:" Inserisci l'indirizzo  di Residenza",
            CapResidenza:{
                required: "Inserisci il cap della Residenza",
                number: "Inserisci solo campi numerici",
                maxlength: "Inserire massimo 5 numeri"
            },
            Telefono:" Inserisci solo campi numerici",
            Cellulare:" Inserisci solo campi numerici",
            Fax:" Inserisci solo campi numerici",
            Email:{
                required: "Inserisci l'email",
                email: "Formato e-mail non valido"
            },
            ComuneDomicilio:" Inserisci il comune del domicilio",
            ProvinciaDomicilio:"Inserisci la provincia del domicilio",
            ViaDomicilio:"Inserisci l'indirizzo del domicilio",
            CapDomicilio:{
                required: "Inserisci il cap del Domicilio",
                number: "Inserisci solo campi numerici",
                maxlength: "Inserire massimo 5 numeri"
            },
            TitoloStudio:" Inserisci il titolo di studio",
            Reddito:{
                required: "Inserisci il reddito",
                number: "Inserisci solo campi numerici"
            },
            ScadenzaContratto:{
                required:"Inserisci la scadenza del contratto",
                date:"Formato data non corretto"
            }
        },
        submitHandler:function() {
            //alert("Registrazione ok. count: "+count);
            if(count==2){
                $.post("AddAccount",{
                    nomeAccount:$("#accountName").val(),
                    cognomeAccount:$("#accountSurname").val(),
                    comuneNascitaAccount:$("#placeOfBirth").val(),
                    dataNascitaAccount:$("#dateOfBirth").val(),
                    codiceFiscaleAccount:$("#taxCode").val(),
                    cittadinanzaAccount:$("#citizenship").val(),
                    comuneResidenzaAccount:$("#municipalityResidence").val(),
                    provinciaResidenzaAccount:$("#provinceResidence").val(),
                    indirizzoResidenzaAccount:$("#viaResidence").val(),
                    capResidenza:$("#capResidence").val(),
                    telefono:$("#telephoneNumber").val(),
                    cellulare:$("#cellularNumber").val(),
                    fax:$("#fax").val(),
                    email:$("#email").val(),
                    comuneDomicilio:$("#municipalityDomicile").val(),
                    provinciaDomicilio:$("#provinceDomicile").val(),
                    indirizzoDomicilio:$("#viaDomicile").val(),
                    capDomicilio:$("#capDomicile").val(),
                    titoloStudio:$("#qualification").val(),
                    tipoAccount:$("#typeAccount").val(),
                    reddito:$("#income").val(),
                    scadenzaContratto:$("#contractExpirationDate").val(),
                    dataRegistrazione:$("#registrationDate").val(),
                    situazioneFamiliaria:$("#familySituation").val(),
                    tipoGenitore:$("#typeParent").val(),
                    facolta:$("#faculty").val()
                },function(data) {
                    var result = data.split(",");
                    $("#accountNick").val(result[0]);
                    $("#accountPass").val(result[1]);
                    $("#NickPassWindow").dialog("open");
                //alert("Nick: "+result[0]+" pass: "+result[1]);
                },"text")
            }
            if(count==1){
                //alert("ci sono");
                $("#registration1").hide();
                $("#registration2").hide();
                $("#registration3").show();
                count++;
            }
            if(count==0){
                $("#registration1").hide();
                $("#registration2").show();
                $("#registration3").hide();
                count++;
            }
        },
        invalidHandler:function() {
            // alert("non ok");
            $("#registrationButton1").click(function(){
                $("#registration1").show();
                $("#registration2").hide();
                $("#registration3").hide();
            });
            $("#registrationButton2").click(function(){
                $("#registration1").hide();
                $("#registration2").show();
                $("#registration3").hide();
            });
            $("#registrationButton3").click(function(){
                $("#registration1").hide();
                $("#registration2").hide();
                $("#registration3").show();
            });
        }
    });
}

function verificaAccount(){
    var string=$('#typeAccount option:selected').val();
    if(string=='Nothing'){
        document.getElementById('typeParent').style.display="none";
        document.getElementById('accountLabel2').style.display="none";
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('contractExpirationDate').style.display="none";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    if(string=='Genitore'){
        document.getElementById('typeParent').style.display="inline";
        document.getElementById('typeParent').options[0].selected=true;
        document.getElementById('accountLabel2').style.display="inline";
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('contractExpirationDate').style.display="none";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    
    if(string=='Responsabile Scientifico'){
        document.getElementById('typeParent').style.display="none";
        document.getElementById('typeParent').options[0].selected=true;
        document.getElementById('accountLabel2').style.display="none";
        document.getElementById('contractExpirationDate').style.display="inline";
        document.getElementById('accountLabel3').style.display="inline";
        document.getElementById('accountLabel4').style.display="none";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    
    if((string=='Delegato Ufficio')||(string=='Delegato scienze della formazione')||(string=='Educatore')||(string=='Coordinatore Psicopedagogico')||(string=='Responsabile Asilo')){
        document.getElementById('typeParent').style.display="none";
        document.getElementById('typeParent').options[0].selected=true;
        document.getElementById('accountLabel2').style.display="none";
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";
        document.getElementById('contractExpirationDate').style.display="none";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
        document.getElementById('choose2').value='Studente';
    }
}
 
function verificaGenitore(){
    var string=$('#typeParent option:selected').val();
    if(string=='Nothing'){
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('contractExpirationDate').style.display="none";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    if((string=='Contratto Tempo Determinato')||(string=='Dottorando')||(string=='Ricercatore')){
        document.getElementById('accountLabel3').style.display="inline";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('contractExpirationDate').style.display="inline";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    
    if(string=='Studente'){
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="inline";    
        document.getElementById('contractExpirationDate').style.display="none";
        document.getElementById('registrationDate').style.display="inline";
        document.getElementById('faculty').style.display="inline";
        document.getElementById('accountLabel5').style.display="inline";
    }
   
    if((string=='Docente')||(string=='Tecnico Amministrativo')){
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('contractExpirationDate').style.display="none";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
   
}
