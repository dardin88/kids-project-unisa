function initializeClassFields() {
    $("#ricerca").button();
    $("#ricerca").click(function() {
        $("#ricerca").submit();
    });
    $("#backClassButton").button();
    $("#modifyClassButton").button();
    $("#deleteClassButton").button();
    $("#addLinkButton").button();
    $("#removeClassWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    buildClassTable();
}

function initializeChildrenInformationFields() {
    $("#ricerca").button();
    $("#ricerca").click(function() {
        $("#ricerca").submit();
    });
    $("#backClassButton").button();
    $("#modifyClassButton").button();
    $("#deleteClassButton").button();
    $("#addLinkButton").button();
    $("#removeClassWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    buildChildrenTable();
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
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Classi",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Classi",
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
                "name" : "id", 
                "value" : $('#id').val()
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


        "oTableTools":{
            "aButtons":[
            "Modifica","Visualizza"
            ]
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