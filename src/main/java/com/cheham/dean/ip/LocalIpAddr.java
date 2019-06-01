package com.cheham.dean.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalIpAddr {

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address.getHostAddress());
            String hostName = address.getHostName();
            System.out.println(hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
