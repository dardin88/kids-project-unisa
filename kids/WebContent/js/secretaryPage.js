/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function messageDialog(){
    $("#confirm").dialog({
        autoOpen: true,
        modal: true,
        resizable: false,
        width: 400
    });
    $("#confirmButton").button();
};
