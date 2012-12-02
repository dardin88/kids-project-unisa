/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function activeTabs() {
    $("#paymentTabGroup").tabs();
}

x = true;
function test() {
    if (x) {
        $("#paymentTabGroup").tabs("disable");
        x = false;
    }
    else {
        $("#paymentTabGroup").tabs("enable");
        x = true;
    }
}