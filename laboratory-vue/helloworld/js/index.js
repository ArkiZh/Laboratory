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

/*子->父通信*/
//两个自定义组件:balance_block模块中需要加载show_balance_button模块
// 父组件            子组件
// balance_block    show_balance_button
// 局部组件          全局组件                       可行
// 局部组件          在balance_block中定义          可行
// 在后者中定义       局部组件                       不可行
// 局部组件          局部组件                       不可行
var vueApp10 = new Vue({
    el:"#vueDiv10",
    components:{
        balance_block: {
            template: `<div>
                             <show_balance_button @show_balance_listener="button_clicked"></show_balance_button>
                             <div>您的余额为：
                                 <span v-if="balanceVisibleFlag">￥1,000,000</span>
                                 <span v-else>***</span>
                             </div>
                             <div>按钮已点击过？{{buttonClicked}}</div>
                         </div>`,
            data: function () {
                return{
                    buttonClicked: false,
                    balanceVisibleFlag: false
                }
            },
            methods:{
                button_clicked:function (data) {
                    if(!this.buttonClicked) this.buttonClicked = true;
                    this.balanceVisibleFlag = data.balanceVisibleFlag;
                }
            },
            components:{
                show_balance_button:{
                    template:`<button @click="buttonClick">{{buttonName}}</button>`,
                    data:function () {
                        return {
                            balanceVisibleFlag: false,
                            buttonName: "显示余额"
                        };
                    },
                    methods:{
                        buttonClick: function () {
                            if (this.balanceVisibleFlag) {
                                this.buttonName = "显示余额";
                            } else {
                                this.buttonName = "隐藏余额";
                            }
                            this.balanceVisibleFlag = !this.balanceVisibleFlag;
                            this.$emit("show_balance_listener", {"balanceVisibleFlag": this.balanceVisibleFlag});
                        }
                    }
                }
            }
        }
    }
});

/*同级组件之间的通信：通过公共的Vue对象进行触发事件（$emit）,监听事件（$on）。适用于任意组件间通信*/
var Event = new Vue();
var vueApp11 = new Vue({
    el: "#vueDiv11",
    components: {
        keyboard: {
            template: `<div>键盘输入：<input @keyup="key_up" v-model="inputWords"></div>`,
            data: function () {
                return{inputWords: ""}
            },
            methods:{
                key_up: function () {
                    Event.$emit("keyboard_input_listener", this.inputWords);
                }
            }
        },
        screen:{
            template:`<div>显示器显示：{{outputWords}}</div>`,
            data: function () {
                return{outputWords: ""}
            },
            mounted: function () {
                var me = this;
                Event.$on("keyboard_input_listener", function (data) {
                    me.outputWords = data;
                });
            }
        }
    }
});

/*过滤器*/
Vue.filter("currency", function (val,unit) {
    val = val || 0;
    unit = unit || "元";
    return val + unit;
});
Vue.filter("lengthTransfer",function (val) {
    val = val || 0;
    return (val / 1000).toFixed(2) + "m";
})
var vueApp12 = new Vue({
    el: "#vueDiv12",
    data: {
        price: 99,
        length: 10
    }
});

/*自定义指令*/
Vue.directive("pin", function (el, binding) {
    if (binding.value) {
        el.style.position = "fixed";
        el.style.left = "10px";
        el.style.bottom = "10px";
    } else {
        el.style.position = "static";
    }
});
var vueApp13 = new Vue({
    el: "#vueDiv13",
    data:{
        pinned: true
    }
});
