module.exports = {
    /*entry:"./js/index.js",
    output:{
        filename:"bundle.js",
        path:__dirname+"/js"
    }*/
    entry: {
        index: "./js/index.js",
        signup: "./js/signup.js"
    },
    output: {
        filename: "[name].bundle.js",
        path: __dirname + "/dist"
    },
    mode:"development", //"production"
    module: {
        rules: [{
            test: /\.css$/,
            use: ["style-loader", "css-loader"]
        }]
    }
};