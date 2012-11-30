/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
        
    $("#removeTraineeWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#insertButton").button();
    $("#insertTraineeWindow").dialog({
        autoOpen:false,
        modal:true,
        resizable:false,
        width:400
    });
    $("#saveButton").button();
    $("#informationTraineeWindow").dialog({
        autoOpen:false,
        modal:true,
        resizable:false,
        width:400
    });
    $("#modifyButton").button();
   
    
}
function buildTraineeTable(){
    $('#traineesTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTraineesTable",
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


        "oTableTools":{
            "aButtons":[
            "Modifica","Visualizza","Contatta"
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
            "sWidth":"10%"
        },
        {
            "sWidth": "10%"
        }
        ]
    });
}

function removeTrainee(id){
    $("#removeTraineeWindow").dialog({
        autoOpen:true
    }); 
    $("#confirmRemoveLinkButton").button();
    $("#confirmRemoveLinkButton").click(function(){
        $.post("RemoveTrainee", {
            traineeId:""+id
        });
        $("#removeTraineeWindow").dialog("close"); 
        var oTable = $("#traineesTable").dataTable();
        oTable.fnDraw();
    });
    $("#notConfirmRemoveLinkButton").button();
    $("#notConfirmRemoveLinkButton").click(function(){
        $("#removeTraineeWindow").dialog("close");
    });
}
function search(){
    var oTable = $("#traineesTable").dataTable();
    oTable.fnDraw();
        
}
function loadInformationTraineePage(id){
        $("#informationTraineeWindow").dialog("open");
        $.post("GeTrainees", {
            traineeId:""+id
        });

}

function openInsertTraineeDialog(){
    $("#insertTraineeWindow").dialog("open");

}
function messageDialog(){
    $("#confirm").dialog({
        autoOpen: true,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#confirmButton").button();
};

function modify(){
    document.getElementById("modifyButton").value="Salva";
    document.getElementById("modifyButton").onclick="ModifyTrainee";
    document.getElementById("Matricola").readOnly=false;
    document.getElementById("Nome").readOnly=false;
    document.getElementById("Cognome").readOnly=false;
    document.getElementById("DataNascita").readOnly=false;
    document.getElementById("CittaNascita").readOnly=false;
    documnet.getElementById("CittaResidenza").readOnly=false;
    document.getElementById("Indirizzo").readOnly=false;
    document.getElementById("CAP").readOnly=false;
    document.getElementById("NumeroTelefonico").readOnly=false;
    document.getElementById("Email").readOnly=false;
    document.getElementById("TitoloStudio").readOnly=false;
}


$(document).ready(function(){
    $("#information").validate({
        rules:
        {
            
            DataNascita:{
                date:true
            },
            
            Email:{
                email:true
            }
        },
        messages:{
            
            DataNascita:" Inserisci la data di nascita nel formato corretto(gg/MM/AAAA)",
           
            Email:" Inserisci l'email nel formato corretto"
        },
        submitHandler:function(form){
            form.submit();
        }
    });
});
