package com.arki.laboratory.snippet.socket;

import java.io.*;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1235);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.write("Hello from client.");
        printWriter.flush();
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }
        bufferedReader.close();
        inputStream.close();
        printWriter.close();
        outputStream.close();
        socket.close();
    }
}
