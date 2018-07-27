package com.arki.laboratory.snippet.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class SocketTest {
    public static void main(String[] args) throws Exception {
        testInetAddress();
        testURL();
        testURL1();
    }

    /**
     * 使用URL读取网页内容
     通过URL对象的openStream()方法可以得到指定资源的输入流，通过流能够读取或访问网页上的资源
     */
    public static void testURL1() throws IOException {
        URL url = new URL("http://www.baidu.com");
        InputStream inputStream = url.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            System.out.println(s);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();

    }

    /**
     *  InetAddress类用于标识网络上的硬件资源，标识互联网协议(IP)地址。
     该类没有构造方法
     * @throws MalformedURLException
     */
    public static void testURL() throws MalformedURLException {
        URL baidu = new URL("http://www.baidu.com");
        URL url = new URL(baidu, "/index.html?username=tom#test");//？表示参数，#表示锚点
        url.getProtocol();//获取协议
        url.getHost();//获取主机
        url.getPort();//如果没有指定端口号，根据协议不同使用默认端口。此时getPort()方法的返回值为 -1
        url.getPath();//获取文件路径
        url.getFile();//文件名，包括文件路径+参数
        url.getRef();//相对路径，就是锚点，即#号后面的内容
        url.getQuery();//查询字符串，即参数
        System.out.println("");
    }

    /**
     *  InetAddress类用于标识网络上的硬件资源，标识互联网协议(IP)地址。
     该类没有构造方法
     * @throws UnknownHostException
     */
    public static void testInetAddress() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        byte[] address = inetAddress.getAddress();
        String canonicalHostName = inetAddress.getCanonicalHostName();
        String hostAddress = inetAddress.getHostAddress();
        String hostName = inetAddress.getHostName();

        InetAddress byName = InetAddress.getByName("www.zcool.com.cn");
        System.out.println("");
    }

}
