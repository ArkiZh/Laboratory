$(function(){
    $("#echoButton").click(function(){
        $.ajax({
            url : "http://localhost:8080/helloworld/hello",
            type: "GET",
            data: {name:$("#name").val()},
            success: function (message) {
                alert(message);
            }
        });
    });
});