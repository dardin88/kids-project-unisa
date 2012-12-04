function initializeLinksManager2(){
        
    var id =document.getElementById('id');
    $("#removeAccount").dialog({
        
        modal:true,
        autoOpen : false,
        buttons:{
            "Annulla": function(){
                $(this).dialog("close");
            },
            "Conferma": function(){
                $.post("DeleteAccount", {
                    id:""+id
                }
                );
                $(this).dialog("close");
                location.href="accountInsert.jsp";
            }
        }
    })
   
}
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
                "value" : $('#name').val()
            },

            {
                "name" : "surname", 
                "value" : $('#surname').val()
            },
            {
                "name" : "taxCode", 
                "value" : $('#taxCode').val()
            },
            {
                "name" : "type", 
                "value" : $('#type').val()
            },
            {
                "name" : "nickname", 
                "value" : $('#nickname').val()
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
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Account",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Account",
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

   
function removeAccount(){
   
    $("#removeAccount").dialog("open");
}
   

function search(){
    var oTable = $("#accountsTable").dataTable();
    oTable.fnDraw();
        
}

function showAccount(id){
    window.location.href="accountInformation.jsp?id="+id;
}

function modifyAccount(id){
    alert(id);

    window.open("accountModify.jsp?id="+id);
     
     
}
function showPartTwoAccount(id){
    $.post("GetAccount",{
                id:id
})}

function back(id){
    
location.href="./accountInformation.jsp?id="+id;
}

