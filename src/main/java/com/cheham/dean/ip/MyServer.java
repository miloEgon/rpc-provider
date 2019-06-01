package com.cheham.dean.ip;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyServer extends Thread {

    ServerSocket mServer;

    public MyServer() {
    }

    public String getLocalhost() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        try {
            System.out.println(getLocalhost()+":12306 Startup...");
            mServer = new ServerSocket(12306);
            while(true){
                Socket client =  mServer.accept();
                System.out.println(client.getLocalSocketAddress().toString());
                System.out.println(client.getInetAddress().toString());
                System.out.println(client.getLocalAddress().toString());
                System.out.println(client.getRemoteSocketAddress().toString());
                System.out.println(client.getLocalSocketAddress().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyServer().start();
    }

}
