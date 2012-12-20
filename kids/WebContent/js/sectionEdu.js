/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeSectionEduPage() {
    buildTabs();
    buildCommentEduTable();
    
    $("#postMessageDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 450
    });
    $("#postConfirmButton").button();
    
    $("#insertCommentDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 450
    });
    $("#insertComentBtn").button();
    
    $("#insertActivityDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 450
    });
    $("#insertActivityBtn").button();
    $("#StartDate").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    $("#StartDate").datepicker("setDate", new Date());
    $("#EndDate").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    $("#EndDate").datepicker("setDate", new Date());
    
    $("#updateActivityDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 450
    });
    $("#updateActivityBtn").button();
    $("#StartDateMod").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    $("#StartDateMod").datepicker("setDate", new Date());
    $("#EndDateMod").datepicker({
        dateFormat: "yy-mm-dd",
        changeYear: true
    });
    $("#EndDateMod").datepicker("setDate", new Date());
    
    $("#saveDraftBtn").button();
    $("#submitBtn").button();
    $("#requestModBtn").button();
    $("#acceptDocRectBtn").button();
    $("#acceptDocScientBtn").button();
}

function buildTabs() {
    $("#sectionEduTabs").tabs();
}

function buildClassTable(classId) {
    $("#table" + classId).dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetSectionActivitiesTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "classId", 
                "value" : classId
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
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Attivit&agrave;",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Attivit&agrave;",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        }
    });
}

function buildActButton(classId) {
    $("#insAct" + classId).button();
    $("#insAct" + classId).click(function() {
        $("#hiddenActClassId").val(classId);
        $("#insertActivityDialog").dialog("open");
    });
}

function buildCommentEduTable(classId) {
    $("#comm" + classId).dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetCommentTable",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": false,
        "fnServerParams": function ( aoData ) {
            aoData.push(
            {
                "name" : "classId", 
                "value" : classId
            },
            {
                "name" : "commentType",
                "value" : "class_comm"
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
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Commenti;",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Commenti",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        }
    });
}

function buildCommentButton(classId) {
    $("#insComm" + classId).button();
    $("#insComm" + classId).click(function() {
        $("#hiddenClassId").val(classId);
        $("#insertCommentDialog").dialog("open");
    });
}

function saveSectionDraft() {
    var active = $( "#sectionEduTabs" ).tabs( "option", "active" );
    var classId = $(".sectionEduClass:eq(" + active + ")").attr('id');
    
    $.post("UpdateClassStatus",
    {
        classId: classId,
        classStatus: "Bozza"
    },
    function(jsonData, status) {
        var message = jsonData.message;
        $("#bodyMessage").html(message);
        $("#postMessageDialog").dialog("open");
    });
}

function submitSectionProgram() {
    var active = $( "#sectionEduTabs" ).tabs( "option", "active" );
    var classId = $(".sectionEduClass:eq(" + active + ")").attr('id');
    
    $.post("UpdateClassStatus",
    {
        classId: classId,
        classStatus: "Sottomessa"
    },
    function(jsonData, status) {
        var message = jsonData.message;
        $("#bodyMessage").html(message);
        $("#postMessageDialog").dialog("open");
    });
}

function requestModSectProg() {
    var active = $( "#sectionEduTabs" ).tabs( "option", "active" );
    var classId = $(".sectionEduClass:eq(" + active + ")").attr('id');
    
    $.post("UpdateClassStatus",
    {
        classId: classId,
        classStatus: "RichiestaMod"
    },
    function(jsonData, status) {
        var message = jsonData.message;
        $("#bodyMessage").html(message);
        $("#postMessageDialog").dialog("open");
    });
}

function acceptDocumentRect() {
    var active = $( "#sectionEduTabs" ).tabs( "option", "active" );
    var classId = $(".sectionEduClass:eq(" + active + ")").attr('id');
    
    $.post("UpdateClassStatus",
    {
        classId: classId,
        classStatus: "AccettaRett"
    },
    function(jsonData, status) {
        var message = jsonData.message;
        $("#bodyMessage").html(message);
        $("#postMessageDialog").dialog("open");
    });
}

function acceptDocumentScient() {
    var active = $( "#sectionEduTabs" ).tabs( "option", "active" );
    var classId = $(".sectionEduClass:eq(" + active + ")").attr('id');
    
    $.post("UpdateClassStatus",
    {
        classId: classId,
        classStatus: "AccettaScient"
    },
    function(jsonData, status) {
        var message = jsonData.message;
        $("#bodyMessage").html(message);
        $("#postMessageDialog").dialog("open");
    });
}

function updateActivity(activityId) {
    $.post("GetActivityData",
    {
        activityId: activityId
    },
    function(jsonData, status) {
        $("#activityNameMod").val(jsonData.actName);
        $("#StartDateMod").val(jsonData.actStartDate);
        $("#EndDateMod").val(jsonData.actEndDate);
        $("#activityContentMod").val(jsonData.actContent);
    });
    $("#updateActivityDialog").dialog("open");
    $("#hiddenActIdMod").val(activityId);
}

function removeActivity(activityId) {
    $.post("DeleteActivity",
    {
        activityId: activityId
    },
    function(jsonData, status) {
        var message = jsonData.message;
        $("#bodyMessage").html(message);
        $("#postMessageDialog").dialog("open");
    });
}