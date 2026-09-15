const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // lintOnSave: false
  //开启代理服务器(方式一)
  /*devServer:{
    proxy:"http://localhost:8081/",
  }*/
  //开启代理服务器(方式二)
  devServer:{
    proxy:{
      '/api':{    // '/api' 代理前缀
          target: 'http://localhost:8081/',   //服务端的地址
          pathRewrite:{'^/api':''},    //将请求路径中 /api 替换为 ''     '^/api' 正则
          ws: true,       //用于支持webSocket
          changeOrange: true    //是否将请求的源地址改为target地址  默认true
      },
      '/atguigu':{
        target: 'http://localhost:8082/',   //服务端的地址
        pathRewrite:{'^/atguigu':''},    //将请求路径中 /api 替换为 ''     '^/api' 正则
        ws: true,       //用于支持webSocket
        changeOrange: true    //是否将请求的源地址改为target地址  默认true
      }
    }
  }
})
