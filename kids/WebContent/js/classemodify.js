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

function buildAccountTable(){
    $('#accountsTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableChildServlet", 
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
//        credo che non ci vogliono perchè non devo ricercare niente in questa tabella 
//        "fnServerParams": function ( aoData ) {
//            aoData.push(
//            {
//                "name" : "name", 
//                "value" : $('#name').val()
//            },
//
//            {
//                "name" : "state", 
//                "value" : $('#state').val()
//            }
//            );
//     
//        },
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Bambini",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Classi",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },


        "oTableTools":{
            "aButtons":[
            "Modifica","Visualizza"
            ]
        },
        "aoColumns": [
        {
            "sWidth": "40%"
        },
        {
            "sWidth": "40%"
        },
        {
            "sWidth": "20%"
        }
        ]
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
