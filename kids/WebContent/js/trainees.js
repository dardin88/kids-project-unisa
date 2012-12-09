/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#saveChanges").button();
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
        close: function( event, ui ) {
            document.getElementById("modifyButton").style.visibility="visible";
            document.getElementById("saveChanges").style.visibility="hidden";
            document.getElementById("RegisterInf").disabled=true;
            document.getElementById("NameInf").disabled=true;
            document.getElementById("SurnameInf").disabled=true;
            document.getElementById("BirthDateInf").disabled=true;
            document.getElementById("CityOfBirthInf").disabled=true;
            document.getElementById("CityOfResidenceInf").disabled=true;
            document.getElementById("AddressInf").disabled=true;
            document.getElementById("CAPInf").disabled=true;
            document.getElementById("TelephoneNumberInf").disabled=true;
            document.getElementById("EmailInf").disabled=true;
            document.getElementById("QualificationInf").disabled=true;
        },

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
    $.post("GetTrainees", {
        traineeId:""+id
    },function(data){
        var result = data.toString().split(",");
        $("#RegisterInf").val(result[0]);
        $("#NameInf").val(result[1]);
        $("#SurnameInf").val(result[2]);
        $("#BirthDateInf").val(result[3]);
        $("#CityOfBirthInf").val(result[4]);
        $("#CityOfResidenceInf").val(result[5]);
        $("#AddressInf").val(result[6]);
        $("#CAPInf").val(result[7]);
        $("#TelephoneNumberInf").val(result[8]);
        $("#EmailInf").val(result[9]);
        $("#QualificationInf").val(result[10]);
        $("#IdInf").val(result[11]);
    },"text");

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
    document.getElementById("modifyButton").style.visibility="hidden";
    document.getElementById("saveChanges").style.visibility="visible";
    document.getElementById("RegisterInf").disabled=false;
    document.getElementById("NameInf").disabled=false;
    document.getElementById("SurnameInf").disabled=false;
    document.getElementById("BirthDateInf").disabled=false;
    document.getElementById("CityOfBirthInf").disabled=false;
    document.getElementById("CityOfResidenceInf").disabled=false;
    document.getElementById("AddressInf").disabled=false;
    document.getElementById("CAPInf").disabled=false;
    document.getElementById("TelephoneNumberInf").disabled=false;
    document.getElementById("EmailInf").disabled=false;
    document.getElementById("QualificationInf").disabled=false;
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
