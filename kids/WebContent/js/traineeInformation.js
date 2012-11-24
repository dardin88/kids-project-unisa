/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
            CittaNascita:"required",
            CittaResidenza:"required",
            Indirizzo:"required",
            CAP:"required",
            Email:{
                required:true,
                email:true
            }
        },
        messages:{
            Matricola:" Inserisci la matricola",
            Nome:" Inserisci il nome",
            Cognome:" Inserisci il cognome",
            CittaNascita:" Inserisci la città di nascita",
            DataNascita:" Inserisci la data di nascita(gg/MM/AAAA)",
            CittaResidenza:" Inserisci la citt&agrave di residenza",
            Indirizzo:" Inserisci l'indirizzo",
            CAP:" Inserisci il CAP",
            Email:" Inserisci l'email"
        },
        submitHandler:function(form){
            form.submit();
        }
    });
});