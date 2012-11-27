/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function buildAccountTable(){
    $('#accountsTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableAccountSecretary",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "name", 
                "value" : $('#Nome').val()
                },

                {
                "name" : "surname", 
                "value" : $('#Cognome').val()
                },
                 {
                "name" : "taxCode", 
                "value" : $('#CodiceFiscale').val()
                },
                 {
                "name" : "type", 
                "value" : $('#TipoAccount').val()
                },
                 {
                "name" : "nickname", 
                "value" : $('#NickName').val()
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


        "oTableTools":{
            "aButtons":[
            "Modifica","Visualizza"
            ]
        },
        "aoColumns": [
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "10%"
        }
        ]
    });
}

   
function removeAccount(id){
    $("#removeAccount").dialog({
        autoOpen:true,
        modal:true,
        buttons:{
            "Annulla": function(){
                $(this).dialog("close");
            },
            "Elimina": function(){
                $.post("DeleteAccount", {
            id:""+id
        });
        $(this).dialog("close");
            }
        }
   })
}

function search(){
    var oTable = $("#accountsTable").dataTable();
    oTable.fnDraw();
        
}

function loadInformationAccountPage(id){
    window.open("accountInformation.jsp?id="+id);
}
