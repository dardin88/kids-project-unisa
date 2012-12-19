/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeSectionEduPage() {
    buildTabs();
    buildCommentEduTable();
    
    $("#insertCommentDialog").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 450
    });
    $("#insertComentBtn").button();
    
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
        
        });
}