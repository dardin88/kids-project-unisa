count=0;

function initializeModifyRegistrationFields(){
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
 
    $("#modifyRegistration1").show();
    $("#modifyRegistration2").hide();
    $("#modifyRegistration3").hide(); 
   
    $("#modifyButton1").button();
    $("#modifyButton2").button();
    $("#modifyButton3").button();
    
    $("#notModifyButton1").button();
    $("#notModifyButton1").click(function(){
        if(count!=0) count--;
        location.href=("http://localhost:8080/kids/");
    });
    
    $("#notModifyButton2").button();
    $("#notModifyButton2").click(function(){
        $("#modifyRegistration1").show();
        $("#modifyRegistration2").hide();
        $("#modifyRegistration3").hide();
        count--;
    });
    
    $("#notModifyButton3").button();
    $("#notModifyButton3").click(function(){
        $("#modifyRegistration1").hide();
        $("#modifyRegistration2").show();
        $("#modifyRegistration3").hide();
        count--;
    });
    
    $("#modifyForm").validate({
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
                codfiscale: true
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
                codfiscale: "Formato non valido"
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
                $.post("ModifyAccount4",{
                    id:$("#id").val(),
                    nomeAccount:$("#modifyAccountName").val(),
                    cognomeAccount:$("#modifyAccountSurname").val(),
                    comuneNascitaAccount:$("#modifyPlaceOfBirth").val(),
                    dataNascitaAccount:$("#modifyDateOfBirth").val(),
                    codiceFiscaleAccount:$("#modifyTaxCode").val(),
                    cittadinanzaAccount:$("#modifyCitizenship").val(),
                    comuneResidenzaAccount:$("#modifyMunicipalityResidence").val(),
                    provinciaResidenzaAccount:$("#modifyProvinceResidence").val(),
                    indirizzoResidenzaAccount:$("#modifyViaResidence").val(),
                    capResidenza:$("#modifyCapResidence").val(),
                    telefono:$("#modifyTelephoneNumber").val(),
                    cellulare:$("#modifyCellularNumber").val(),
                    fax:$("#modifyFax").val(),
                    email:$("#modifyEmail").val(),
                    comuneDomicilio:$("#modifyMunicipalityDomicile").val(),
                    provinciaDomicilio:$("#modifyProvinceDomicile").val(),
                    indirizzoDomicilio:$("#modifyViaDomicile").val(),
                    capDomicilio:$("#modifyCapDomicile").val(),
                    titoloStudio:$("#modifyQualification").val(),
                    tipoAccount:$("#modifyTypeAccount").val(),
                    reddito:$("#modifyIncome").val(),
                    scadenzaContratto:$("#modifyContractExpirationDate").val(),
                    dataRegistrazione:$("#modifyRegistrationDate").val(),
                    situazioneFamiliaria:$("#modifyFamilySituation").val(),
                    tipoGenitore:$("#modifyTypeParent").val(),
                    facolta:$("#modifyFaculty").val()
                })
                
                alert("Modifica Effettuata fare qualcosa");
                var id= document.getElementById('id').value;
                location.href=("./accountInformation.jsp?id="+id);
            }
            if(count==1){
                //alert("ci sono");
                $("#modifyRegistration1").hide();
                $("#modifyRegistration2").hide();
                $("#modifyRegistration3").show();
                count++;
            }
            
            if(count==0 ){
                $("#modifyRegistration1").hide();
                $("#modifyRegistration2").show();
                $("#modifyRegistration3").hide();
                count++;
                
            }
        },
        invalidHandler:function() {
            // alert("non ok");
            $("#modifyRegistrationButton1").click(function(){
                $("#modifyRegistration1").show();
                $("#modifyRegistration2").hide();
                $("#modifyRegistration3").hide();
            });
            $("#modifyRegistrationButton2").click(function(){
                $("#modifyRegistration1").hide();
                $("#modifyRegistration2").show();
                $("#modifyRegistration3").hide();
            });
            $("#modifyRegistrationButton3").click(function(){
                $("#modifyRegistration1").hide();
                $("#modifyRegistration2").hide();
                $("#modifyRegistration3").show();
            });
        }
    });
}

function verificaAccount(){
    var string=$('#modifyTypeAccount option:selected').val();
    if(string=='Nothing'){
        document.getElementById('modifyTypeParent').style.display="none";
        document.getElementById('accountLabel2').style.display="none";
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('modifyContractExpirationDate').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    if(string=='Genitore'){
        document.getElementById('modifyTypeParent').style.display="inline";
        document.getElementById('accountLabel2').style.display="inline";
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('modifyContractExpirationDate').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    
    if(string=='Responsabile Scientifico'){
        document.getElementById('modifyTypeParent').style.display="none";
        document.getElementById('modifyAccountLabel2').style.display="none";
        document.getElementById('modifyContractExpirationDate').style.display="inline";
        document.getElementById('accountLabel3').style.display="inline";
        document.getElementById('accountLabel4').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    
    if((string=='Delegato Ufficio')||(string=='Scienze Formazione')||(string=='Educatore')||(string=='Coordinatore Psicopedagogico')||(string=='Responsabile Asilo')){
        document.getElementById('modifyTypeParent').style.display="none";
        document.getElementById('accountLabel2').style.display="none";
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";
        document.getElementById('modifyContractExpirationDate').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
}
 
function verificaGenitore(){
    var string=$('#modifyTypeParent option:selected').val();
    if(string=='Nothing'){
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('modifyContractExpirationDate').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    if((string=='Contratto Tempo Determinato')||(string=='Dottorando')||(string=='Ricercatore')){
        document.getElementById('accountLabel3').style.display="inline";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('modifyContractExpirationDate').style.display="inline";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    
    if(string=='Studente'){
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="inline";    
        document.getElementById('modifyContractExpirationDate').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="inline";
        document.getElementById('modifyFaculty').style.display="inline";
        document.getElementById('accountLabel5').style.display="inline";
    }
   
    if((string=='Docente')||(string=='Tecnico amministrativo')){
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('modifyContractExpirationDate').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
}

function update(){
              
               var typeParent= document.getElementById('typeParent2').value;
               var typeAccount= document.getElementById('typeAccount2').value;
              
               alert(typeAccount);
               
     if(typeAccount=='Responsabile Scientifico'){
        document.getElementById('modifyTypeAccount').options[6].selected=true;
        document.getElementById('modifyTypeParent').style.display="none";
        document.getElementById('modifyAccountLabel2').style.display="none";
        document.getElementById('modifyContractExpirationDate').style.display="inline";
        document.getElementById('accountLabel3').style.display="inline";
        document.getElementById('accountLabel4').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
     
    if(typeAccount=='Genitore'){
       
       document.getElementById('modifyTypeAccount').options[1].selected=true;
        document.getElementById('modifyTypeParent').style.display="inline";
        document.getElementById('accountLabel2').style.display="inline";
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('modifyContractExpirationDate').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
        if(typeParent=='Studente'){
        
        document.getElementById('modifyTypeParent').options[1].selected=true;
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="inline";    
        document.getElementById('modifyContractExpirationDate').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="inline";
        document.getElementById('modifyFaculty').style.display="inline";
        document.getElementById('accountLabel5').style.display="inline";
    }
     if((typeParent=='Contratto Tempo Determinato')||(typeParent=='Dottorando')||(typeParent=='Ricercatore')){
        if(typeParent=='Contratto Tempo Determinato')
             document.getElementById('modifyTypeParent').options[4].selected=true;
            if(typeParent=='Dottorando')
         document.getElementById('modifyTypeParent').options[6].selected=true;
        if(typeParent=='Ricercatore')
         document.getElementById('modifyTypeParent').options[5].selected=true;
        document.getElementById('accountLabel3').style.display="inline";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('modifyContractExpirationDate').style.display="inline";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    

    
    if((typeParent=='Tecnico amministrativo')||(typeParent=='Docente')){
        alert("1");
         if(typeParent=='Docente')
              document.getElementById('modifyTypeParent').options[3].selected=true;

          if(typeParent=='Tecnico amministrativo')
                       document.getElementById('modifyTypeParent').options[2].selected=true;
          
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";    
        document.getElementById('modifyContractExpirationDate').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }
    
    
    if((typeAccount=='Delegato Ufficio')||(typeAccount=='Delegato scienze della formazione')||(typeAccount=='Educatore')||(typeAccount=='Coordinatore Psicopedagogico')||(typeAccount=='Responsabile Asilo')){
        if(typeAccount=='Delegato Ufficio')
              document.getElementById('modifyTypeAccount').options[2].selected=true;
          
        if(typeAccount=='Delegato scienze della formazione')
              document.getElementById('modifyTypeAccount').options[3].selected=true;
            if(typeAccount=='Educatore')
                  document.getElementById('modifyTypeAccount').options[4].selected=true;
         
                if(typeAccount=='Coordinatore Psicopedagogico')
                  document.getElementById('modifyTypeAccount').options[5].selected=true;
             if(typeAccount=='Responsabile Asilo')
                   document.getElementById('modifyTypeAccount').options[7].selected=true;
               
        document.getElementById('modifyTypeParent').style.display="none";
        document.getElementById('accountLabel2').style.display="none";
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";
        document.getElementById('modifyContractExpirationDate').style.display="none";
        document.getElementById('modifyRegistrationDate').style.display="none";
        document.getElementById('modifyFaculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
    }

 

    
   
   
               
   

}

    
    

