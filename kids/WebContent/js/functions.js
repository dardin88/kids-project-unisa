function logout(){
    window.location='Logout';
}

function goToIndex(){
    window.location.href="index.jsp";
}

function activePage(){
    var url=document.URL;
    urlArray=new Array();
    urlArray=url.split('/');
    currentPage=urlArray[urlArray.length-1];
    switch(currentPage){
        case 'meetingCalendar.jsp':
            $("#meetingCalendarMenuEL").css("background-color","#444");
            $("#meetingCalendarMenuEL").css("color","#FFFFFF");
            $("#title").html("Gestione riunioni");
            break;
         case 'addMeeting.jsp':
            $("#meetingCalendarMenuEL").css("background-color","#444");
            $("#meetingCalendarMenuEL").css("color","#FFFFFF");
            $("#title").html("Gestione riunioni");
            break;   
    }
}