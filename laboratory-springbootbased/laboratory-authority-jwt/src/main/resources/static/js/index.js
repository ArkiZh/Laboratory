$(function(){
    $("#jwtButton").click(function(){
        $.ajax({
            url : "/login",
            type: "POST",
            data: {username:$("#username").val(),password:$("#password").val()},
            success: function (message) {
                $("#jwtResult").text(message);
            }
        });
    });
});