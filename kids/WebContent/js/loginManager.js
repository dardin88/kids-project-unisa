function initializeLoginFields() {
    username = $("#username");
    falsePassword = $("#falsePassword");
    password = $("#password");
    username.css('color', 'grey');
    falsePassword.css('color','grey');
    username.val("Nome utente");
    falsePassword.val("Password")
    password.hide();
    $("#registerButton").button();
    $("#registerButton").click(function() {
        location.href="./accountInsertParent.jsp";
    });
    $("#loginButton").button();
    $("#loginButton").click(function() {
        $("#loginForm").submit();
    });
}

function initializeUsername() {
    username = $("#username");
    if (username.val()==""){
        username.css('color', 'grey');
        username.val("Nome utente");
    }
}

function initializePassword() {
    password = $("#password");
    falsePassword = $("#falsePassword");
    if (password.val()==""){
        password.hide();
        falsePassword.show();
    }
}

function enableTypingUsername(){
    username = $("#username");
    if (username.val()=="Nome utente"){
        username.css('color', 'black');
        username.val("");
    }
}

function enableTypingPassword(){
    password = $("#password");
    falsePassword = $("#falsePassword");
    falsePassword.hide();
    password.show();
    password.focus();
}