package com.arki.laboratory.snippet.process;

import java.io.*;
import java.nio.charset.Charset;

public class ProcessTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Charset consoleCharset = getConsoleCharset();
        //String cmd = "ping localhost";
        //String cmd = "cmd /c mspaint"; //打开画图板
        String cmd = "cmd /c dir";
        Runtime rt = Runtime.getRuntime();
        Process exec = rt.exec(cmd);
        int i = exec.waitFor();
        System.out.println(i);
        InputStream inputStream = exec.getInputStream();
        String s = transferInpuStreamToString(inputStream,consoleCharset);
        inputStream.close();
        System.out.println(s);
    }


    public static Charset getConsoleCharset() throws InterruptedException, IOException {
        Process exec = Runtime.getRuntime().exec("cmd /c chcp");
        int i = exec.waitFor();
        System.out.println("获取字符集命令执行" + (i == 0 ? "[正常]" : "[失败]") + i);
        InputStream inputStream = exec.getInputStream();
        String s = transferInpuStreamToString(inputStream,Charset.forName("GBK"));
        System.out.println(s);
        if(s.indexOf("636")!=-1) return Charset.forName("GBK");
        if(s.indexOf("65001")!=-1) return Charset.forName("UTF-8");
        if(s.indexOf("437")!=-1) return Charset.forName("US-ASCII");
        return Charset.forName("GBK");
    }

    public static String transferInpuStreamToString(InputStream inputStream,Charset charset) throws IOException {
        int available = inputStream.available();
        byte[] bytes = new byte[available];
        inputStream.read(bytes);
        String s = new String(bytes,charset);
        return s;
    }
}
