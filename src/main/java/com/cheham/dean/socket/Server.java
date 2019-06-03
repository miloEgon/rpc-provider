package com.cheham.dean.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName Server
 * @Description TODO
 * @Author Cheham_Dean
 * @Date 2019/6/3 7:22
 * @Version 1.0.0
 **/
public class Server {

    private static final Logger log = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args){
        new Server().run();
    }

    public void run() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8888);
            log.info("启动服务器...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Socket socket = ss.accept();
                log.info("客户端"+socket.getInetAddress().getHostAddress()+"已连接到服务器");
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bw.write("你已和服务器建立通信，现在进入你的请求处理线程"+"\n");
                bw.flush();
                Scanner input = new Scanner(System.in);
                Thread readThread = new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                String msg = br.readLine();
                                log.info("客户端说："+msg);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                Thread writeThread = new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                bw.write(input.next()+"\n");
                                bw.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                readThread.start();
                writeThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





}
