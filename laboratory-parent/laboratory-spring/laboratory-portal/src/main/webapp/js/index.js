$(function(){
    $("#echoButton").click(function(){
        $.ajax({
            url : "/helloworld/hello",
            type: "GET",
            data: {name:$("#name").val()},
            success: function (message) {
                alert(message);
            }
        });
    });
});