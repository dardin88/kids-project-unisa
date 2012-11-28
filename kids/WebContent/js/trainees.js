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
   
    
}
function buildTraineeTable(){
    $('#traineesTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "",
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

function removeTrainee(register){
    $("#removeTraineeWindow").dialog({
        autoOpen:true
    }); 
    $("#confirmRemoveLinkButton").button();
    $("#confirmRemoveLinkButton").click(function(){
        $.post("RemoveTrainee", {
            traineeRegister:""+register
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
function loadInformationTraineePage(register){
    window.open("traineeInformation.jsp?matricola="+register);
}

function openInsertTraineeDialog(){
    $("#insertTraineeWindow").dialog("open");

}