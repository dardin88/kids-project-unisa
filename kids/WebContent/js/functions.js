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
        case 'artefactsTable.jsp':
            $("#artefactsTableMenuEL").css("background-color","#F82025");
            $("#artefactsTableMenuEL").css("color","#FFFFFF");
            break;
        case 'linksTable.jsp':
            $("#linksTableMenuEL").css("background-color","#F82025");
            $("#linksTableMenuEL").css("color","#FFFFFF");
            break;
        case 'searchLinks.jsp':
            $("#searchLinksMenuEL").css("background-color","#F82025");
            $("#searchLinksMenuEL").css("color","#FFFFFF");
            break;
    }
}