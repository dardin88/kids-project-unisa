function initCriteriaWindow() {
    
    $("#classificationCriteriaWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#classificationAddCriterionWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
}
function openCriteriaWindow() {
    setWindowVisibility("classificationCriteriaWindow", true);
}
function openAddCriteriaWindow() {
    setWindowVisibility("classificationCriteriaWindow", true);
}
function addCriterion() {
    
}
function createTableCriteria() {
        $('#classificationTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableCriteria",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function (aoData) {
//        aoData.push({
//            
//        });
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
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Criteri",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Criteri",
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
            "Elimina", "Modifica","Visualizza"
            ]
        },
        "aoColumns": [
        {
            "sWidth": "65%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "10%"
        }],
         "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });  
}