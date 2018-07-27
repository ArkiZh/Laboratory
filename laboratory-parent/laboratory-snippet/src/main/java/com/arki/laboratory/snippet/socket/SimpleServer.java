package com.arki.laboratory.snippet.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        //1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
        ServerSocket serverSocket = new ServerSocket(1234);
        //2、调用accept()方法开始监听，等待客户端的连接
        Socket accept = serverSocket.accept();
        //3、获取输入流，并读取客户端信息
        InputStream inputStream = accept.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }
        //4、获取输出流，响应客户端的请求
        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("Hello from server".getBytes("UTF-8"));
        outputStream.flush();
        //5、关闭资源
        outputStream.close();
        bufferedReader.close();
        inputStream.close();
        accept.close();
        serverSocket.close();
    }
}
