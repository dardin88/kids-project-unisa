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
            }
        },
        messages:{
            Nome:" Inserisci il nome"
        },
        submitHandler:function() {
            $.post("AddClassBean",{  
                Nome:$("#name").val()
            })
            location.href="classe.jsp";
        }
        
    });
});



