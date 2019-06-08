package com.cheham.dean.config;

import com.cheham.dean.controller.HelloController;
import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {
        PropKit.use("play.properties");
        constants.setUrlParaSeparator("&");
        constants.setViewType(ViewType.JFINAL_TEMPLATE);
        constants.setInjectDependency(true);
        constants.setDevMode(PropKit.getBoolean("jfinal.devMode", false));
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
        //配置Mysql数据库连接相关配置
        PluginFactory.startActiveRecordPlugin(plugins);
    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
