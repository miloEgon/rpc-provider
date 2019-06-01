package com.cheham.dean.chatroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private static final int PORT = 12306;

    private static final Logger log = LoggerFactory.getLogger(ChatServer.class);

    public static void main(String[] args) {
        new ChatServer().run();
    }

    public void run() {
        ServerSocket listener = null;
        Socket socket = null;
        BufferedReader receiver = null;
        BufferedReader input = null;
        PrintWriter replier = null;
        try {
            listener = new ServerSocket(PORT);//开启监听
            log.info("服务开启...");
            socket = listener.accept();//创建套接字
            receiver = new BufferedReader(new InputStreamReader(socket.getInputStream())); //接受者
            replier = new PrintWriter(socket.getOutputStream());  //回复者
            String msg;
            while ( (msg = receiver.readLine()) != null ) {
                if (msg.equalsIgnoreCase("bye")) break;
                log.info(String.format("客户端说：%s", msg));
                System.out.print("服务器说：");
                input = new BufferedReader(new InputStreamReader(System.in));
                replier.println(String.format("%s", input.readLine()));
                replier.flush();
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
