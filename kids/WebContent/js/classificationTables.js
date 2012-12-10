function createTableClassification() {
        $('#classificationTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableClassification",
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
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Graduatorie",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Graduatorie",
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
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        {
            "sWidth": "25%"
        },
        ],
         "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });  
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

function createTableResult() {
        $('#classificationTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableResult",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function (aoData) {
        aoData.push({
            Id : getValue("classificationSelectedId")
        });
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
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Risultati",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Risultati",
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
            "sWidth": "10%"
        },
        {
            "sWidth": "20%"
        },
        {
            "sWidth": "22%"
        },
        {
            "sWidth": "22%"
        },
        {
            "sWidth": "10%"
        },
        {
            "sWidth":"16%"
        }
        ],
         "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });  
}