/*var canSignup = require("./base").canSignup;*/
//ES6写法：
import {canSignup} from "./base";
import "../css/base.css";

if(canSignup){
    document.body.innerHTML =
        `<a href="signup.html">注册</a>`;
}else {
    document.body.innerHTML =
        `<a>欢迎访问，当前无法注册</a>`;
}