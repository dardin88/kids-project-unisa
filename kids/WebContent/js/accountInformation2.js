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
            
            ViaResidenza:{
                required:true
            },
            CapResidenza:"required",
            Email:"required",
            ComuneDomicilio:"required",
            ProvinciaDomicilio:"required",
            ViaDomicilio:"required"
        },
        messages:{
            ViaResidenza:" Inserisci l'indirizzo  di Residenza",
            CapResidenza:" Inserisci il cap",
            Email:" Inserisci l'email",
            ComuneDomicilio:" Inserisci il comune del domicilio",
            ProvinciaDomicilio:"Inserisci la provincia del domicilio",
            ViaDomicilio:"Inserisci l'indirizzo del domicilio"
        },
        submitHandler:function() {
            $.post("ModifyAccount2",{
                viaResidenza:$("#viaResidence").val(),
                capResidenza:$("#capResidence").val(),
                telefono:$("#telephoneNumber").val(),
                cellulare:$("#cellularNumber").val(),
                fax:$("#fax").val(),
                email:$("#email").val(),
                comuneDomicilio:$("#municipalityDomicile").val(),
                provinciaDomicilio:$("#provinceDomicile").val(),
                indirizzoDomicilio:$("#viaDomicile").val()
               })
               
            location.href="./accountInsert3.jsp";
              
        }
        
    });
});