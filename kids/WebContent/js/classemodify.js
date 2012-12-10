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
    $("#registrationForm2").validate({
        rules:
        {
            
            Nome: "required"
        },
        messages:{
            Nome:" Inserisci il nome"
        },
        submitHandler:function() {
            $.post("UpdateClassBean",{
                id:$("#id").val(),
                Nome:$("#Nome").val()
            })
            location.href="classe.jsp";
        }
        
    });
});
