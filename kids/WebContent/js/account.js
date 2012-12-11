function initializeLinksManager2(){
    
    $("#avantiButton").button();
    $("#modifyButton").button();
    $("#eliminaButton").button();
    $("#modifyPassword").button();
    $("#addLinkButton").button();
    $("#ricarica").button();
    
    
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

function modifyPassword2(id){
        
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
                alert('La password è stata modificata con successo');
                
            else{
                alert('Errore! Le password non coincidono');   
                location.href="./accountInformationParent.jsp?id="+id;
            }
        });
        
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
            "sWidth": "15%",
            "sClass":"center"
        },
        {
            "sWidth": "15%",
            "sClass":"center"
        },
        {
            "sWidth": "15%",
            "sClass":"center"
        },
        {
            "sWidth": "10%",
            "sClass":"center"
        },
        {
            "sWidth": "10%",
            "sClass":"center"
        },
        {
            "sWidth": "10%",
            "sClass":"center"
        }
        ]
    });
    var oTable = $("#linkTable").dataTable();
    if (oTable.length > 0) {
        $("#accoutTable").css("width", "100%");
    }
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
    //    var oTable = $("#accountsTable").dataTable();
    //    oTable.fnDraw();
    buildAccountTable();
}

function showAccount(id){
    window.location.href="accountInformation.jsp?id="+id;
}

function modifyAccount(id){
   
    window.open("accountModifyParent.jsp?id="+id);
     
     
}

function modifyAccount2(id){
    

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

function verificaAccount(){
    var string=$('#choose option:selected').val();
    if(string=='Genitore'){
        document.getElementById('choose2').style.display="inline";
        document.getElementById('accountLabel2').style.display="inline";
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";     
        document.getElementById('contractExpirationDate').style.display="none";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
        document.getElementById('contractExpirationDate').value=null;
        document.getElementById('registrationDate').value=null;
        document.getElementById('faculty').value="";
    }
    if(string=='ResponsabileScentifico'){
        document.getElementById('choose2').style.display="none";
        document.getElementById('accountLabel2').style.display="none";
        document.getElementById('contractExpirationDate').style.display="inline";
        document.getElementById('accountLabel3').style.display="inline";
        document.getElementById('accountLabel4').style.display="none";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
        document.getElementById('registrationDate').value=null;
    }
    if((string=='DelegatoUfficio')||(string=='ScienzeFormazione')||(string=='Educatore')||(string=='CoordinatorePsicopedagogico')||(string=='ResponsabileAsilo')){
        
        document.getElementById('choose2').style.display="none";
        document.getElementById('accountLabel2').style.display="none";
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";
        document.getElementById('contractExpirationDate').style.display="none";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
        document.getElementById('contractExpirationDate').value=null;
        document.getElementById('registrationDate').value=null;
        document.getElementById('faculty').value="";
    }
}

function verificaGenitore(){
    var string=$('#choose2 option:selected').val();
    if((string=='ContrattoTempoDeterminato')||(string=='Dottorando')||(string=='Ricercatore')){
        document.getElementById('choose2').style.display="inline";
        document.getElementById('accountLabel3').style.display="inline";
        document.getElementById('accountLabel4').style.display="none";     
        document.getElementById('contractExpirationDate').style.display="inline";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
        document.getElementById('registrationDate').val=null;
        document.getElementById('faculty').val="";
        
    }
    if(string=='Studente'){
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="inline";     
        document.getElementById('contractExpirationDate').style.display="none";
        document.getElementById('registrationDate').style.display="inline";
        document.getElementById('faculty').style.display="inline";
        document.getElementById('accountLabel5').style.display="inline";
        document.getElementById('contractExpirationDate').value=null;
        
    }
    
    if((string=='Docente')||(string=='TecnicoAmministrativo')){
         
        document.getElementById('accountLabel3').style.display="none";
        document.getElementById('accountLabel4').style.display="none";     
        document.getElementById('contractExpirationDate').style.display="none";
        document.getElementById('registrationDate').style.display="none";
        document.getElementById('faculty').style.display="none";
        document.getElementById('accountLabel5').style.display="none";
        document.getElementById('contractExpirationDate').value=null;
        document.getElementById('registrationDate').value=null;
        document.getElementById('faculty').value="";
    }
   
}

/*accountsLabel3= Scadenza Contratto       questa è la mia ridenominazione a te sono settati come accountLabel
 *accountsLabel4= Data Immatricolazione
 *accountsLabel5= Facoltà
 *
 *la funzione verificaGenitore bisogna aggiungerla in tipologia Genitore nell evento "on change"
 *la funzione verificaAccount bisogna aggiungerla in tipologia Account nell evento "on change"
 *
 *aggiungi style="display : none "  per : accountsLabel3,accountLabel4, accountsLabel5, <input> di Scadenza Contratto,Data Immatricolazione, Facoltà
 */
 