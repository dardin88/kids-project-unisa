function initializeLinksSearch(){
    $.ajaxSetup({
        cache: false
    });
    showAllArtefacts($("#requirementToSearch"));
    $('#directLinksTableContainer').hide();
    $('#indirectLinksTableContainer').hide();
    $("#requirementToSearch").change(function(){
        if($("#requirementToSearch").val()==""){
            $('#directLinksTableContainer').hide();
            $('#indirectLinksTableContainer').hide();
        }else{
            $('#directLinksTableContainer').show();
            $('#indirectLinksTableContainer').show();
            buildDirectLinksTable($("#requirementToSearch").val());
            var oTable1 = $("#directLinksTable").dataTable();
            if (oTable1.length > 0) {
                $("#directLinksTable").css("width", "100%");
            }
            var oTable2 = $("#indirectLinksTable").dataTable();
            buildIndirectLinksTable($("#requirementToSearch").val());
            if (oTable2.length > 0) {
                $("#indirectLinksTable").css("width", "100%");
            }
        }
    });
}

function buildDirectLinksTable(requirementToSearch){
    $('#directLinksTable').show();
    $('#directLinksTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "SearchDirectTraceabilityLinks",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": true,
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "sServerMethod": "POST",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link diretti",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ link diretti",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 link diretti",
            "sInfoFiltered": "(filtrati da _MAX_ link diretti totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "fnServerParams": function(aoData){
            aoData.push({
                "name": "requirementToSearch",
                "value": requirementToSearch
            });
        }
    });
}

function buildIndirectLinksTable(requirementToSearch){
    $('#indirectLinksTable').show();
    $('#indirectLinksTable').dataTable({
        "bJQueryUI": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "SearchIndirectTraceabilityLinks",
        "bPaginate": true,
        "bLengthChange": false,
        "bFilter": true,
        "bSort": false,
        "bDestroy": true,
        "bInfo": true,
        "bAutoWidth": true,
        "sPaginationType": "full_numbers",
        "sServerMethod": "POST",
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ link indiretti",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ link indiretti",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 link indiretti",
            "sInfoFiltered": "(filtrati da _MAX_ link indiretti totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca:",
            "oPaginate": {
                "sFirst":    "<<",
                "sPrevious": "<",
                "sNext":     ">",
                "sLast":     ">>"
            }
        },
        "fnServerParams": function(aoData){
            aoData.push({
                "name": "requirementToSearch",
                "value": requirementToSearch
            });
        }
    });
}

function showAllArtefacts(select){
    $.getJSON("GetArtefacts?"+new Date().getTime(),{
        ajax: 'true',
        type: "POST",
        cache: false,
        async: false
    }, function (artefacts){
        select.html("<option value=\"\"></option>");
        $.each(artefacts, function(index, artefact){
            select.append(
                $('<option></option>').val(artefact.id).html(artefact.name)
                );
        });
    });
}