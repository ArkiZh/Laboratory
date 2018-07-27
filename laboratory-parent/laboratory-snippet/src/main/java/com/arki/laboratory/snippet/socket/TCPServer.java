package com.arki.laboratory.snippet.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

    private static int count = 0;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1235);
        List<ServerThread> serverSocketList = new ArrayList<>();
        int maxSize = 20;
        // 建立连接
        for (int i = 0; i < maxSize; i++) {
            Socket socket = serverSocket.accept();
            count++;
            String threadName = "第" + count + "连接";
            ServerThread  serverThread= new ServerThread(threadName, socket);
            serverSocketList.add(serverThread);
            serverThread.start();
        }
        //Close socket.
        for (int i = 0; i < serverSocketList.size(); i++) {
            ServerThread serverThread = serverSocketList.get(i);
            serverThread.getSocket().close();
        }
    }

    private static class ServerThread extends Thread{
        private String name;
        private Socket socket;

        public ServerThread(String name, Socket socket) {
            super(name);
            this.name = name;
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("线程[" + name + "]开始执行：");

            try {
                InputStream inputStream = this.socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                System.out.println("接收到数据：");
                String s;
                while ((s = reader.readLine()) != null) {
                    System.out.println(s);
                }


                OutputStream outputStream = socket.getOutputStream();
                String message = "欢迎您，第[" + count + "]个到访用户！";
                outputStream.write(message.getBytes("UTF-8"));
                inputStream.close();
                reader.close();
                outputStream.close();
                //socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println("线程[" + name + "]执行结束!");
        }

        public Socket getSocket() {
            return socket;
        }

        public void setSocket(Socket socket) {
            this.socket = socket;
        }
    }

}
