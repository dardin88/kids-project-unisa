function initializeLinksManager2(){
    
    $("#avantiButton").button();
    $("#modifyButton").button();
    $("#eliminaButton").button();
    $("#modifyPassword").button();


    
    $("#removeAccountWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    
   
    $("#modifyPasswordWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
}
    function modifyPassword(id){
     $("#modifyPasswordWindow").dialog("open"); 
    
    $("#confirmModifyButton").button();
    $("#confirmModifyButton").click(function(){
     var old=document.getElementById("oldPass").value;
     var newPass=document.getElementById("newPass").value;
     var newPass2=document.getElementById("confNewPass").value;
        $.post("ModifyPassword", {
            id:""+id,
            old:""+old,
            newPass:""+newPass,
            newPass2:""+newPass2
        },
    function(jsonData, status) {
                if (jsonData.message == "OK")
                        alert('ok password uguali');
                else
                        alert('password non uguali');
        });
        var string="Password inviata alla servlet"
        alert(string);
        $("#modifyPasswordWindow").dialog("close"); 
    });
    $("#notConfirmModifyButton").button();
    $("#notConfirmModifyButton").click(function(){
        $("#modifyPasswordWindow").dialog("close");
    });
    
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
            "sWidth": "15%"
        },
        {
            "sWidth": "15%"
        },
        {
            "sWidth": "15%"
        },
        {
            "sWidth": "10%"
        },
        {
            "sWidth": "10%"
        },
        {
            "sWidth": "10%"
        }
        ]
    });
}

   
function removeAccount(id){
    $("#removeAccountWindow").dialog({
        autoOpen:true
    }); 
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

function close(){
    $("#confirm").dialog("close");
    
}
