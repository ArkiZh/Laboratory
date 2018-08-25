
var vueApp = new Vue({
    el:"#vueDiv",
    data: {
        name:"A",
        age: undefined,
        gender: false
    }
});

var vueApp1 = new Vue({
    el: "#vueDiv1",
    data: {
        todos: [
            {time: "7:00", thing: "起床", price: "10", discount: "0.1"},
            {time: "7:05", thing: "刷牙", price: "23"},
            {time: "7:08", thing: "出门", discount: "0.3"}
        ]
    }
});

var vueApp2 = new Vue({
    el: "#vueDiv2",
    data: {
        targetURL: "http://www.arkizh.com",
        targetWebsite: "个人站",
        myClass: "redColor",
        isRed: true
    }
});

var vueApp3 = new Vue({
    el: "#vueDiv3",
    data: {
        buttonName: "OFF",
        mouseAction: undefined
    },
    methods: {
        onClickMethod: function () {
            if (vueApp3.buttonName == "ON") {
                vueApp3.buttonName = "OFF";
            } else {
                vueApp3.buttonName = "ON";
            }
        },
        onEnter: function () {
            vueApp3.mouseAction = "Mouse entered!";
        },
        onOut: function () {
            vueApp3.mouseAction = "Mouse leaved!";
        }
    }
});


