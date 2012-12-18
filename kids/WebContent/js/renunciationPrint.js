function initRenunciationPrintComponent() {
    $("#renunciationViewDetailsWindowPrint").button();
    
}
function printRenunciation() {
    var id = getValue("renunciationViewDetailsWindowId");
    location.href = "PdfRenunciation?Id=" + id;
}