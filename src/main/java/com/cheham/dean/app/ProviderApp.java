package com.cheham.dean.app;

import com.cheham.dean.request.CalculateRpcRequest;
import com.cheham.dean.request.GatewayRpcBean;
import com.cheham.dean.service.Calculater;
import com.cheham.dean.service.CalculaterImpl;
import com.cheham.dean.service.Gateway;
import com.cheham.dean.service.GatewayImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ProviderApp {

    private static Logger logger = LoggerFactory.getLogger(ProviderApp.class);

    private Calculater calcService = new CalculaterImpl();

    private Gateway gatewayService = new GatewayImpl();

    public void run() throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        logger.info("服务开启...");
        try {
            while (true) {
                Socket socket = listener.accept();
                //将请求反序列化
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                try {
                    Object object = ois.readObject();
                    calculateHandler(object, oos);
//                    gatewayHandler(object, oos);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("fail", e);
                } finally {
                    oos.close();
                    ois.close();
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }

    public void gatewayHandler(Object object, ObjectOutputStream oos) throws IOException {
        Object result = null;
        if (object instanceof GatewayRpcBean) {
            GatewayRpcBean bean = (GatewayRpcBean) object;
            switch (bean.getMethod()) {
                case "findGatewayById":
                    result = gatewayService.findGatewayById(bean.getId());
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
        oos.writeObject(result);
    }

    public void calculateHandler(Object object, ObjectOutputStream oos) throws IOException {
        //调用服务
        Integer result = null;
        if (object instanceof CalculateRpcRequest) {
            CalculateRpcRequest request = (CalculateRpcRequest) object;
            switch (request.getMethod()) {
                case "add":
                    result = calcService.add(request.getParam1(), request.getParam2());
                    break;
                case "subtract":
                    result = calcService.subtract(request.getParam1(), request.getParam2());
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
        //返回结果
        logger.info(result.toString());
        oos.writeObject(result);
    }

    public static void main(String[] args) {
        try {
            new ProviderApp().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
