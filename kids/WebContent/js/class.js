function initializeClassFields() {
    $("#ricerca").button();
    $("#ricerca").click(function() {
        $("#ricerca").submit();
    });
    $("#backClassButton").button();
    $("#modifyClassButton").button();
    $("#deleteClassButton").button();
    $("#addClassButton").button();
    $("#removeClassWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    buildClassTable();
}

function initializeClassInformationFields() {
    $("#backClassButton").button();
    $("#requestModifyClassButton").button();
    $("#acceptedClassButton").button();
    $("#requestModifyClassWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 335
    });
    buildChildrenTable();
    buildEducatorTable();
}

function buildClassTable(){
    $('#classTable').dataTable({
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
                "name" : "name", 
                "value" : $('#className').val()
            },

            {
                "name" : "state", 
                "value" : $('#classState').val()
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
            "sLengthMenu":   "Visualizza _MENU_ Classi",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Classi",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Classi",
            "sInfoFiltered": "(filtrati da _MAX_ Classi totali)",
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
    var oTable = $("#classTable").dataTable();
    if (oTable.length > 0) {
        $("#classTable").css("width", "100%");
    }
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
            "sLengthMenu":   "Visualizza _MENU_ Bambini",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Bambini",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Bambini",
            "sInfoFiltered": "(filtrati da _MAX_ Bambini totali)",
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
            "sWidth": "50%"
        },
        {
            "sWidth": "50%"
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
            "sWidth": "50%"
        },
        {
            "sWidth": "50%"
        }
        ]
    });
    var oTable = $("#educatorTable").dataTable();
    if (oTable.length > 0) {
        $("#educatorTable").css("width", "100%");
    }
}
  
function removeClass(id){
    $("#removeClassWindow").dialog({
        autoOpen:true
    }); 
    $("#confirmRemoveLinkButton").button();
    $("#confirmRemoveLinkButton").click(function(){
        $.post("DeleteClassBean", {
            id:""+id
        });
        $("#removeClassWindow").dialog("close"); 
        var oTable = $("#classTable").dataTable();
        oTable.fnDraw();
        location.href="./class.jsp";
    });
    $("#notConfirmRemoveLinkButton").button();
    $("#notConfirmRemoveLinkButton").click(function(){
        $("#removeClassWindow").dialog("close");
    });
}

function search(){
    var oTable = $("#classTable").dataTable();
    oTable.fnDraw();
}

function showClass(id){
    window.location.href="classInformation.jsp?id="+id;
}

function modifyClass(id){
    window.location.href="classModify.jsp?id="+id;
}

function requestClassModify(){
    $("#requestModifyClassWindow").dialog("open");
    $("#requestModifyClassButton2").button();
}

function sendMail(){
    $("#requestModifyClassButton2").click(function(){
        $.post("SendMailRequestClassModify",{
            nomeClasse: $("#className").val(),
            messaggio: $("#artefactMessaggio").val()
        });
        $.post("UpdateClassBean",{
            classId: $("#classId").val(),
            className: $("#className").val(),
            isRequestModify: true
        });
        $("#requestModifyClassWindow").dialog("close");
        window.location.href="class.jsp";
    });
}