
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

var vueApp4= new Vue({
    el:"#vueDiv4",
    data:{
        name:"v-model test",
        age:10,
        gender:"male",
        hobby:["Ping-pong","Skate"],
        article:"I come from Mar.",
        home:"Mars",
        destination:["Moon","Sea"]
    }
});

var vueApp5= new Vue({
   el:"#vueDiv5",
   data:{
       roles:["HR","管理员","员工"],
       currentRole:"员工"
   }
});

var vueApp6=new Vue({
    el:"#vueDiv6",
    data:{
        scoreInfoArray:[{
            course:"数学",
            score:100
        },{
            course:"英语",
            score:90
        },{
            course:"物理",
            score:99
        }]/*,
        statisticsArray:[{
            category:"总分",
            score:this.sum //引用不到computed中的属性
        },{
            category:"平均分",
            score:this.average
        }]*/
    },
    computed:{
        sum:function () {
            var temp = 0;
            for (var i in this.scoreInfoArray) {
                temp = temp + parseFloat(this.scoreInfoArray[i].score);
            }
            return temp;
        },
        average:function () {
            return Math.round(this.sum/this.scoreInfoArray.length);
        }
    }
})
