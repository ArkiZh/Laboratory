package com.arki.laboratory.snippet.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;
public class ParseDomainName {
    InetAddress myServer = null;
    InetAddress myIPaddress = null;
    String domainName = null;

    public ParseDomainName(String domainName) {
        this.domainName = domainName;
    }

    public InetAddress getServerIP() {
        try {
            myServer = InetAddress.getByName(domainName);
        } catch (UnknownHostException e) {
        }
        return (myServer);
    }

    // 取得LOCALHOST的IP地址
    public InetAddress getMyIP() {
        try {
            myIPaddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
        }
        return (myIPaddress);
    }

    public static void main(String[] args) {
        for (; ;) {
            ParseDomainName pdn = new ParseDomainName("www.baidu.com");
            System.out.println("Your host IP is: " + pdn.getMyIP().getHostAddress());
            System.out.println("The Server IP is :" + pdn.getServerIP().getHostAddress());
        }
    }
}