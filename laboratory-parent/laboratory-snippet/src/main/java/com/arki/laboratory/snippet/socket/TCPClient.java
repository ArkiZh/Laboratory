package com.arki.laboratory.snippet.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 20; i++) {
            Socket socket = new Socket("localhost", 1235);
            OutputStream outputStream = socket.getOutputStream();
            String message = "Hello from client count:" + i;
            outputStream.write(message.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            int temp;
            StringBuilder sb = new StringBuilder();
            while ((temp = inputStreamReader.read()) != -1) {
                System.out.print((char) temp);
            }
            System.out.println();

            inputStreamReader.close();
            inputStream.close();
            //socket.close();
        }

    }
}
