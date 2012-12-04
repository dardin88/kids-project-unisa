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
            CapDomicilio:{required:true},
            TitoloDiStudio:"required",
            TipoAccount:"required",
            Reddito:"required",
            ScadenzaContratto:"required"
        },
        messages:{
            CapDomicilio:" Inserisci il cap del Domicilio",
            titoloStudio:" Inserisci il titolo di studio",
            Reddito:" Inserisci il reddito",
            ScadenzaContratto:"Inserisci la scadenza del contratto",
            ViaDomicilio:"Inserisci l'indirizzo del domicilio"
        },
        submitHandler:function() {
            $.post("ModifyAccount3",{
                id:$("#id").val(),
                capDomicilio:$("#capDomicile").val(),
                titoloStudio:$("#qualification").val(),
                tipoAccount:$("#typeAccount").val(),
                reddito:$("#income").val(),
                scadenzaContratto:$("#contractExpirationDate").val(),
                dataRegistrazione:$("#registrationDate").val(),
                situazioneFamiliaria:$("#familySituation").val(),
                tipoGenitore:$("#typeParent").val(),
                facolta:$("#faculty").val()
               })
            location.href="./accountInsert.jsp";
        }
        
    });
});