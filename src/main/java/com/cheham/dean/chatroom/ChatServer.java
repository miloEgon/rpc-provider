package com.cheham.dean.chatroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatServer {

    private static final int PORT = 12306;

    private static final Logger log = LoggerFactory.getLogger(ChatServer.class);

    private InetAddress localAddr = null;

    private String localHost = null;

    private String clientAddr = null;

    private String clientHost = null;

    public static void main(String[] args) {
        new ChatServer().run();
    }

    public String getLocalhost() {
        try {
            localAddr = InetAddress.getLocalHost();
            localHost = localAddr.getHostAddress();
            if (localHost.equals("127.0.0.1")) return "服务端";
            else return localHost;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getClienthost(Socket socket) {
        clientAddr = socket.getInetAddress().toString();
        clientHost = clientAddr.substring(1, clientAddr.length());
        if (clientHost.equals("127.0.0.1")) return "客户端";
        else return clientHost;
    }

    public void run() {
        ServerSocket listener = null;
        Socket socket = null;
        BufferedReader receiver = null;
        BufferedReader input = null;
        PrintWriter replier = null;
        try {
            listener = new ServerSocket(PORT);//开启监听
            log.info("服务 "+getLocalhost()+":"+ChatServer.PORT+"开启...");
            socket = listener.accept();//创建套接字
            receiver = new BufferedReader(new InputStreamReader(socket.getInputStream())); //接受者
            replier = new PrintWriter(socket.getOutputStream());  //回复者
            String receive_msg;
            String reply_msg;
            while ( (receive_msg = receiver.readLine()) != null ) {
                log.info(String.format(getClienthost(socket)+"说：%s", receive_msg));
                if (receive_msg.equalsIgnoreCase("bye")) break;

                System.out.print(getLocalhost()+"说：");
                input = new BufferedReader(new InputStreamReader(System.in));
                reply_msg = input.readLine();
                replier.println(String.format("%s", reply_msg));
                replier.flush();
                if (reply_msg.equalsIgnoreCase("bye")) break;
            }
            replier.close();
            input.close();
            receiver.close();
            socket.close();
            listener.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                replier.close();
                input.close();
                receiver.close();
                socket.close();
                listener.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
