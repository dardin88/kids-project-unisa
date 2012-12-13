function initializeModifyClassFields(){
    $.ajaxSetup({
        cache: false
    });
    $("#backClassButton").button();
    $("#draftClassButton").button();
    $("#submitClassButton").button();
    $("#requestModifyClassButton").button();
    $("#acceptedClassButton").button();
    $.validator.setDefaults({
        highlight: function(input){
            $(input).addClass("ui-state-highlight");
        },
        unhighlight: function(input){
            $(input).removeClass("ui-state-highlight");
        }
    });
    $("#registrationForm2").validate({
        rules:
        {
            Nome: "required"
        },
        messages:{
            Nome:" Inserisci il nome"
        },
        submitHandler:function() {
            form.submit();
        }
        
    });
    buildChildrenTable();
    buildEducatorTable();
}

function buildChildrenTable(){
    $('#childrenTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableChildServlet", 
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "classId", 
                "value" : $('#classId').val()
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
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Bambini",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Bambini",
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
            "sWidth": "40%"
        },
        {
            "sWidth": "40%"
        },
        {
            "sWidth": "20%"
        }
        ]
    });
    var oTable = $("#childrenTable").dataTable();
    if (oTable.length > 0) {
        $("#childrenTable").css("width", "100%");
    }
}

function buildEducatorTable(){
    $('#educatorTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableEducator", 
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "classId", 
                "value" : $('#classId').val()
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
            "sLengthMenu":   "Visualizza _MENU_ Educatori",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Educatori",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Educatori",
            "sInfoFiltered": "(filtrati da _MAX_ Educatori totali)",
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
            "sWidth": "40%"
        },
        {
            "sWidth": "40%"
        },
        {
            "sWidth": "20%"
        }
        ]
    });
    var oTable = $("#educatorTable").dataTable();
    if (oTable.length > 0) {
        $("#educatorTable").css("width", "100%");
    }
}