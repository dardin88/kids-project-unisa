/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function initializeClassFields() {
    $("#registrationButton").button();
    $("#registrationButton").click(function() {
        $("#registrationButton").submit();
    });
    $("#removeAccountWindow").dialog({
       autoOpen: false,
       modal: true,
       resizable: false,
       width: 400
   });
}

function buildAccountTable(){
    $('#accountsTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableClassServlet", 
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "state", 
                "value" : $('#name').val()
            },

            {
                "name" : "name", 
                "value" : $('#surname').val()
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
            "sWidth": "50%"
        },
        {
            "sWidth": "50%"
        }
        ]
    });
}

   
function removeAccount(id){
    $("#confirmRemoveLinkButton").button();
    $("#confirmRemoveLinkButton").click(function(){
        $.post("DeleteAccount", {
            id:""+id
        });
        $("#removeAccountWindow").dialog("close"); 
        var oTable = $("#accountsTable").dataTable();
        oTable.fnDraw();
        location.href="./accountSecretary.jsp";
    });
    $("#notConfirmRemoveLinkButton").button();
    $("#notConfirmRemoveLinkButton").click(function(){
        $("#removeAccountWindow").dialog("close");
    });
}
    function removeAccountParent(id){
     $("#removeAccountWindow").dialog("open"); 
    $("#confirmRemoveLinkButton").button();
    $("#confirmRemoveLinkButton").click(function(){
        $.post("DeleteAccount", {
            id:""+id
        });
        $("#removeAccountWindow").dialog("close"); 
        location.href="./index.jsp";
    });
    $("#notConfirmRemoveLinkButton").button();
    $("#notConfirmRemoveLinkButton").click(function(){
        $("#removeAccountWindow").dialog("close");
    });
    
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
    })
    }

function back(id){
    
    location.href="./accountInformation.jsp?id="+id;
}