package com.cheham.dean.controller;

import com.cheham.dean.app.ProviderApp;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import java.io.IOException;

public class HelloController extends Controller {

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

}
