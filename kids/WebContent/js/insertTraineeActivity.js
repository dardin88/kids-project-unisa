/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function initializeLinksManager(){
    $.ajaxSetup({
        cache: false
    });
    $("#send").button();
}

function createTable(){
    $('#table').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTraineeTableActivity",
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
            "sWidth": "10%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        }
        ]
    });
}

function search(){
    var oTable = $("#table").dataTable();
    oTable.fnDraw();
        
}

function qualifyFields(){
    document.getElementById("date").readOnly=false;
    document.getElementById("name").readOnly=false;
    document.getElementById("description").readOnly=false;
    document.getElementById("startTime").readOnly=false;
    document.getElementById("endTime").readOnly=false;


}

$(document).ready(function(){
    $("#information").validate({
        rules:
        {
            
            Data:{
                required:true,
                date:true
            },
            Nome:"required",
            OraInizio:"required",
            OraFine:"required"
            
        },
        messages:{
            
            Data:" Inserisci il giorno in cui il tirocinante deve scolgere l'attivit&agrave",
            Nome:" Inserisci il nome dell'attivit&agrave",
            OraInizio:" Inserisci l'ora di inzio dell'attivit&agrave",
            OraFine:" Inserisci l'ora di fine dell'attivit&agrave"
        },
        submitHandler:function(form){
            form.submit();
        }
    });
});


