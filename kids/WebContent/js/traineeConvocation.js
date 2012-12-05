/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function buildTraineeConvocationTable(){
    $('#traineeConvocationTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetConvocationTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "Nome", 
                "value" : $('#Nome').val()
            },

            {
                "name" : "Cognome", 
                "value" : $('#Cognome').val()
            }
            );
     
        },
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Tirocinanti",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Tirocinanti",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },


        
        "aoColumns": [
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "10%"
        },
        {
            "sWidth":"10%"
        },
        {
            "sWidth": "25%"
        }
        ]
    });
}

function updateParticipation(id,check){
    if(check.checked){
        $.post("ModifyTraineeConvocation",{
            id:id,
            checked:true
        })
    }
    else{
        $.post("ModifyTraineeConvocation",{
            id:id,
            checked:false
        })
    }
    
}