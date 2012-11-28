var asInitVals = new Array();
var oTable = null;
var checked = false;

function initIndex(){
    paginaAttiva();
    costruisciTabella();
    $(".search_init").each(function(i) {
        $(this).keyup(function() {
            oTable.fnFilter($(this).val(), i);
        });
    });
    $(".search_init").each(function(i) {
        asInitVals[i] = $(this).val();
    });
    $(".search_init").focus(function() {
        if (this.className == "search_init"){
            this.className = "";
            this.value = "";
        }
    });
    $(".search_init").blur(function() {
        if (this.value == ""){
            this.className = "search_init";
            this.value = asInitVals[$(".search_init").index(this)];
        }
    });
    $(".search_init_select").each(function(i) {
        $(this).change(function(){
            oTable.fnFilter($(this).val(), i+1);
        });
    } );
    $(".search_init_checkbox").each(function(i) {
        $(this).change(function (){
            if ($(this).attr('checked')=='checked'){
                oTable.fnFilter($(this).val(), i+2);
            } else {
                oTable.fnFilter('', i+2);
            }
        });
    });
    $("#selezionaTutti").click(function(){
        if (checked==false){
            $('.check').each(function(){
                $(this).attr('checked',true);
            });
            checked=true;
        }else{
            $('.check').each(function(){
                $(this).attr('checked',false);
            });
            checked=false;
        }
    });
    errore = 0;
    $('#bottoneStampaPDF').click(function(){
        value = "";
        $('.check:checked', oTable.fnGetNodes()).each(function() {
            value += $(this).val() + " ";
        });
        if (value == ""){
            $(function() {
                $("#alert-dialog").dialog({
                    autoOpen: false,
                    modal: true,
                    resizable: false,
                    width: 300
                });
            });
            document.getElementById("alert-dialog").innerHTML='<p style=\'text-align:center\'>Devi selezionare almeno un sito.</p>';
            $("#alert-dialog").dialog("open");
            return;
        }
        $('#idSiti').val(value);
        $('#stampaPDF').submit();
    });
    $('#bottoneStampaTabellaRiassuntiva').click(function(){
        value = "";
        $('.check:checked', oTable.fnGetNodes()).each(function(){
            value += $(this).val() + " ";
        });
        if (value == ""){
            $(function() {
                $("#alert-dialog").dialog({
                    autoOpen: false,
                    modal: true,
                    resizable: false,
                    width: 300
                });
            });
            document.getElementById("alert-dialog").innerHTML='<p style=\'text-align:center\'>Devi selezionare almeno un sito.</p>';
            $("#alert-dialog").dialog("open");
            return;
        }
        $('#idSiti2').val(value);
        $('#stampaTabellaRiassuntiva').submit();
    });
    $("#inserisciSito").click(function(){
        window.location="inserisciSito.jsp";
    });
}

function costruisciTabella(){
    oTable = $("#tabellaSiti").dataTable({ 
        "aaSorting": [[1,'asc'],[0,'asc']],
        "bJQueryUI": true,
        "bFilter": true,
        "sPaginationType": "full_numbers",
        "bSort": true,
        "bServerSide": true,
        "bProcessing": true,
        "sAjaxSource": "RestituisciSiti",
        "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "Tutti"]],
        "oLanguage": {
            "sProcessing":   "Caricamento...",
            "sLengthMenu":   "Visualizza _MENU_ siti",
            "sZeroRecords":  "La ricerca non ha portato alcun risultato.",
            "sInfo":         "Vista da _START_ a _END_ di _TOTAL_ siti",
            "sInfoEmpty":    "Vista da 0 a 0 di 0 siti",
            "sInfoFiltered": "(filtrati da _MAX_ siti totali)",
            "sInfoPostFix":  "",
            "sSearch":       "Cerca:",
            "oPaginate": {
                "sFirst":    "Inizio",
                "sPrevious": "Precedente",
                "sNext":     "Successivo",
                "sLast":     "Fine"
            }
        },
        "aoColumns": [
        null,null,null,null,null,null,null,
        {
            "bSortable": false
        },{
            "bSortable": false
        }]
    });
}
