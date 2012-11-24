/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function createCalendar(){
    var layer;
    $('#calendar').fullCalendar({
        events:"GetTraineeActivity",
        
        header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			}
    })
}
