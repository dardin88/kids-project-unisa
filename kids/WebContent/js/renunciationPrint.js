function initRenunciationPrintComponent() {
    $("#renunciationViewDetailsWindowPrint").button();
    
}
function printRenunciation() {
    var id = getValue("renunciationViewDetailsWindowId");
    
    comunicaConServlet(
        "PrintRenunciation", // LA SERVLET E' DA CREARE
        {
            Id: id
        }, 
        function(jsonObject) {
            if(jsonObject.IsSuccess) {
                setWindowVisibility("renunciationViewDetailsWindow", false);
                /*
                 * Implementare la stampa
                 */
            } else {
                openRenunciationAlertWindow("Errore nel caricamento delle informazioni", jsonObject.ErrorMsg);
            }
        }
    );
}