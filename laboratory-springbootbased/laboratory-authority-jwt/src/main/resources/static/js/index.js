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
    $("#validateJwtButton").click(function () {
        $.ajax({
            url: $("#targetValidateUrl").val(),
            type: "POST",
            data: {jwtTokenToValidate: $("#jwtTokenToValidate").val()},
            success: function (message) {
                $("#validateJwtResult").html(message);
            }
        });
    });
});