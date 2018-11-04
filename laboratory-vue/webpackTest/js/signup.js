/*var base = require("./base");
var canSignup = base.canSignup*/
//ES6写法：
import {canSignup} from "./base";

if(canSignup){
    document.body.innerHTML =
        `<h5>欢迎注册</h5>`;
}else {
    document.body.innerHTML =
        `<h5>抱歉，当前无法注册</h5>`;
}