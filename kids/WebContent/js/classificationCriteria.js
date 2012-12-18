function initCriteriaWindow() {
    
    $("#classificationCriteriaWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    if(getValue("user") == "Segreteria") {  // il pulsante è da inizializzare solo se l'utente è 'Segreteria'
        $("#classificationOpenCriteriaWindow").button();
    }
    
    $("#classificationAddCriterionWindow").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 600
    });
    $("#classificationAddCriterion").button();
    
    $("#classificationAddCriterionSubmit").button();
    
    $("#classificationAddCriterionUndoSubmit").button();
    $("#classificationAddCriterionUndoSubmit").click(function() {
        setWindowVisibility("classificationAddCriterionWindow", false);
        // svuoto i campi della form "classificationAddWindowForm"
        getElement("classificationNewCriterionDescrizione").value = "";
        getElement("selectCampoEmpty").selected = true;
        getElement("selectOperandoEmpty").selected = true;
        getElement("classificationNewCriterionCondizione").value = "";
        getElement("classificationNewCriterionPeso").value = "";
    });
    
}
function openCriteriaWindow() {
    setWindowVisibility("classificationCriteriaWindow", true);
}
function openAddCriteriaWindow() {
    setWindowVisibility("classificationAddCriterionWindow", true);
}
function addCriterion() {
    
}
function createTableCriteria() {
        $('#classificationCriteriaTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "GetTableCriteria",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": true,
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link",
            "sZeroRecords":  "Non sono presenti criteri di valutazione.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ Criteri",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 Criteri",
            "sInfoFiltered": "(filtrati da _MAX_ link totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca criterio:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "aoColumns": [
        {
            "sWidth": "20%",
            "sClass": "center"
        },
        {
            "sWidth": "15%",
            "sClass": "center"
        },
        {
            "sWidth": "7%",
            "sClass": "center"
        },
        {
            "sWidth": "20%",
            "sClass": "center"
        },
        {
            "sWidth": "15%",
            "sClass": "center"
        },
        {
            "sWidth": "15%",
            "sClass": "center"
        },
        {
            "sWidth": "8%",
            "sClass": "center"
        }],
         "fnServerData": function (sSource, aoData, fnCallback){ 
            $.post(sSource,aoData,fnCallback,"json");
        }
    });  
}
function updateCriteriaTable() {
    var oTable = $("#classificationCriteriaTable").dataTable();
    oTable.fnDraw();
}
function modifyCriteriaWindow(id) {
    
}
function deleteCriteriaWindow(id) {
    
}
function changeCriterionActive(id, isActive) {
    
}