/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
            Stato:"required"
        },
        messages:{
            Nome:" Inserisci il nome",
            Stato:" Inserisci lo stato della richiesta"
        },
        submitHandler:function() {
            $.post("AddAccount",{  //non so qui come farlo XD
                matricolaAccount:$("#Name").val(),
                nomeAccount:$("#State").val()
            })
            location.href="AddClassBean";
        }
        
    });
});



