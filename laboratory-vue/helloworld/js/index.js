/*start: {{}} v-model v-show v-if*/
var vueApp = new Vue({
    el:"#vueDiv",
    data: {
        name:"A",
        age: undefined,
        gender: false
    }
});

/*v-for*/
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

/*v-bind*/
var vueApp2 = new Vue({
    el: "#vueDiv2",
    data: {
        targetURL: "http://www.arkizh.com",
        targetWebsite: "个人站",
        myClass: "redColor",
        isRed: true
    }
});

/*v-on*/
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

/*v-model*/
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

/*控制流指令v-if*/
var vueApp5= new Vue({
   el:"#vueDiv5",
   data:{
       roles:["HR","管理员","员工"],
       currentRole:"员工"
   }
});

/*计算属性*/
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

/*全局及局部组件*/
//组件名到页面中都转成了小写的
//vue.js:597 [Vue warn]: Unknown custom element: <alertpublic> - did you register the component correctly?
Vue.component("alert_public", {
    template: "<button @click='on_click_public'>全局组件按钮</button>",
    methods: {
        on_click_public: function () {
            alert("Hello 全局的！");
        }
    }
});
var alertPrivateComponent={
    alert_private:{
        template: "<button @click='on_click_private'>局部组件按钮</button>",
        methods:{
            on_click_private: function () {
                alert("Hello 局部的！");
            }
        }
    }
};
var vueApp7 = new Vue({
    el:"#vueDiv7",
    components:alertPrivateComponent
});

/*引用页面模板到组件*/
Vue.component("like", {
    /*也可以使用#htmlID来引用页面中的元素作为template，
    * 页面中引用时候是引用该id标签内部的代码，标签不用template命名也可以
    template:`<button :class='{liked:liked_flag}' @click='on_click_toggle'>
                      赞{{like_count}}
              </button>`,*/
    template:"#like_component_template",
    data:function () {
        return {
            like_count:10,
            liked_flag:false
        };
    },
    methods:{
        on_click_toggle:function () {
            if(this.liked_flag)this.like_count--;
            else this.like_count++;
            this.liked_flag = !this.liked_flag;
        }
    }
});
var vueApp8 = new Vue({
    el: "#vueDiv8"
});

/*页面参数向组件内部通信，父->子通信*/
var vueApp9 = new Vue({
    el:"#vueDiv9",
    components:{
        user_link:{
            props:["username"],
            template:`<a :href="'/user/'+username">@{{username}}</a>`
        }
    }
});