package com.cheham.dean.config;

import com.cheham.dean.controller.HelloController;
import com.jfinal.config.*;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {
        constants.setUrlParaSeparator("&");
        constants.setViewType(ViewType.JFINAL_TEMPLATE);
        constants.setInjectDependency(true);
        constants.setDevMode(true);
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/hello", HelloController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
