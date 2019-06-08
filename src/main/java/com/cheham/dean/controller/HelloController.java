package com.cheham.dean.controller;

import com.cheham.dean.app.ProviderApp;
import com.cheham.dean.service.Gateway;
import com.cheham.dean.service.GatewayImpl;
import com.jfinal.core.ActionKey;

import java.io.IOException;

public class HelloController extends BaseController {

    Gateway service = new GatewayImpl();

    @ActionKey("/hello/say")
    public void sayHello() {
        renderText("hello");
    }

    @ActionKey("/hello/run")
    public void calc() {
        try {
            new ProviderApp().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ActionKey("/hello/gateway")
    public void gateway() {
        OK(service.findGatewayById("CXAA18AAA0100071"));
    }

}
