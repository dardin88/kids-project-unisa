function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#modifyButton").button();
    $("#genericButton").button();
    
    
}
$(document).ready(function(){
    $("#information").validate({
        rules:
        {
            Matricola:"required",
            Nome:"required",
            Cognome:"required",
            DataNascita:{
                required:true,
                date:true
            },
            ComuneNascita:"required",
            ComuneResidenza:"required",
            ComuneDomicilio:"required",
            ProvinciaResidenza:"required",
            ProvinciaDomicilio:"required",
            ViaDomicilio:"required",
            ViaResidenza:"required",
            NumeroCivicoResidenza:"required",
            NumeroCivicoDomicilio:"required",
            CAPDomicilio:"required",
            CAPResidenza:"required",
            Email:{
                required:true,
                email:true
            },
            Fax:"required"
        },
        messages:{
            Matricola:" Inserisci la matricola",
            Nome:" Inserisci il nome",
            Cognome:" Inserisci il cognome",
            ComuneNascita:" Inserisci la città di nascita",
            DataNascita:" Inserisci la data di nascita(gg/MM/AAAA)",
            ComuneResidenza:" Inserisci il comune del residenza",
            ComuneDomicilio:"Inserisci il comune del domicilio",
            Indirizzo:" Inserisci l'indirizzo",
            CAPDomicilio:" Inserisci il CAP del domicilio",
            CAPResidenza:"Inserisci il CAP della residenza",
            NumeroCivicoResidenza:"Inserisci il numero civico della residenza",
            NumeroCivicoDomicilio:"Inserisci il numero civico del domicilio",
            ProvinciaDomicilio:"Inserisci la provincia del domicilio",
            ProvinciaResidenza:"Inserisci la provincia della residenza",
            ViaResidenza:"Inserisci la via della residenza",
            Email:" Inserisci l'email",
            ViaDomicilio:" Inserisci la via del domicilio",
            Fax:"Inserisci il fax"
        },
        submitHandler:function(form){
            form.submit();
        }
    });
});