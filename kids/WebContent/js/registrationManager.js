function initializeRegistrationFields() {
    accountName = $("#accountName");
    accountName.val("Nome");
    accountName.css('color', 'grey');
    
    accountSurname = $("#accountSurname");
    accountSurname.val("Cognome");
    accountSurname.css('color', 'grey');
    
    $("#registrationButton").button();
    $("#registrationButton").click(function() {
        $("#registrationButton").submit();
    });
}

function initializeName() {
    accountName = $("#accountName");
    if (accountName.val()==""){
        accountName.css('color', 'grey');
        accountName.val("Nome");
    }
}

function enableTypingName(){
    accountname = $("#accountName");
    if (accountname.val()=="Nome"){
        accountname.css('color', 'black');
        accountname.val("");
    }
}

function initializeSurname() {
    accountSurname = $("#accountSurname");
    if (accountSurname.val()==""){
        accountSurname.css('color', 'grey');
        accountSurname.val("Cognome");
    }
}

function enableTypingSurname(){
    accountSurname = $("#accountSurname");
    if (accountSurname.val()=="Cognome"){
        accountSurname.css('color', 'black');
        accountSurname.val("");
    }
}