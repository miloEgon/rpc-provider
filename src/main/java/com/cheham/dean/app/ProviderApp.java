package com.cheham.dean.app;

import com.cheham.dean.request.CalculateRpcRequest;
import com.cheham.dean.service.Calculater;
import com.cheham.dean.service.CalculaterImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ProviderApp {

    private static Logger logger = LoggerFactory.getLogger(ProviderApp.class);

    private Calculater calculater = new CalculaterImpl();

    public void run() throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        logger.info("服务开启...");
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    //将请求反序列化
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    Object object = ois.readObject();
                    //调用服务
                    int result = 0;
                    if (object instanceof CalculateRpcRequest) {
                        CalculateRpcRequest request = (CalculateRpcRequest) object;
                        switch (request.getMethod()) {
                            case "add":
                                result = calculater.add(request.getParam1(), request.getParam2());
                                break;
                            case "subtract":
                                result = calculater.subtract(request.getParam1(), request.getParam2());
                                break;
                            default:
                                throw new UnsupportedOperationException();
                        }
                    }
                    //返回结果
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(Integer.valueOf(result));
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("fail", e);
                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }

    public static void main(String[] args) {
        try {
            new ProviderApp().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
