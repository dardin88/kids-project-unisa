function doSomeRequest(servletName, servletArguments){
    var servlet = servletName;                //the name (URI) of your servlet
    var arg = servletArguments                //any attributes you want to send
    var req = servlet + "?" + arg;            //compiling the request

    addrequest(req);                          //calls the addrequest function
    request.onreadystatechange = function(){  //this is used to listen for changes in the request's status
        
    }
}

function addrequest(req) {
    try {                                     //create a request for netscape, mozilla, opera, etc.
        request = new XMLHttpRequest();
    }catch (e) {

        try {                                 //create a request for internet explorer
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }catch (e) {                           //do some error-handling
            alert("XMLHttpRequest error: " + e);
        }
    }

    request.open("GET", element, true);       //prepare the request
    request.send(null);                       //send it
    return request;                           //return the request
}