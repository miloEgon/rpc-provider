package com.cheham.dean.config;

import com.cheham.dean.common.MappingModel;
import com.jfinal.config.Plugins;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.mysql.jdbc.Connection;

public class PluginFactory {

    public static void startActiveRecordPlugin(Plugins me) {
        PropKit.use("play.properties");
        DruidPlugin dp = new DruidPlugin(
                PropKit.get("jdbc.url"),
                PropKit.get("jdbc.user"),
                PropKit.get("jdbc.pass").trim()
        );

        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        if ( PropKit.getBoolean("jfinal.devMode", false) ) {
            arp.setShowSql(true);
        }
        arp.setTransactionLevel(Connection.TRANSACTION_READ_COMMITTED);

        MappingModel.mapping(arp);

        me.add(dp);
        me.add(arp);
    }

}



