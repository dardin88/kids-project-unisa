/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function initializeRectorDelegatePage() {
    $("#confirmButton").button();
    $("#confirm").dialog({
        autoOpen: true,
        modal: true,
        resizable: false,
        width: 400
    });
}