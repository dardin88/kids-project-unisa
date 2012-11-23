/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeShowCalendar(){
    $.ajaxSetup({
        cache: false
    });
    $("meetingCalendar").dialog({
        autoOpen: false,
        modal: true,
        resizable: false,
        width: 400
    });
    $("meetingCalendar").onclik = mouseclickFun;
    $("meetingCalendar").dialog("open");
    function mouseclickFun()
    {
    alert("mouseover event detected!");
    }
}


