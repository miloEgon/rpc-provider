package com.cheham.dean.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class GatewayImpl implements Gateway {

    @Override
    public Object findGatewayById(String id) {
        System.out.println(id);
        Record record = Db.findFirst("select id, proxy_net_address, security_key, serial, mac, sys_ver, protocol_ver, zigbee_ver, product_id from gateway where id = ?", id);
//        Record record = Db.findById("gateway", id);
        System.out.println(record);
        JSONObject data = new JSONObject();
        data.put("data",record);
//        if ( record == null ) return null;
//        GatewayRpcBean gateway = JSON.parseObject(record.toJson(), GatewayRpcBean.class);
//        // get gateway online status
//        return gateway;
        return data;


        /*GatewayRpcBean bean = new GatewayRpcBean();
        bean.setId(record.get("id"));
        bean.setProxy_net_address(record.get("proxy_net_address"));
        bean.setSecurity_key(record.get("security_key"));
        bean.setSerial(record.get("serial"));
        bean.setMac(record.get("mac"));
        bean.setSys_ver(record.get("sys_ver"));
        bean.setProtocol_ver(record.get("protocol_ver"));
        bean.setZigbee_ver(record.get("zigbee_ver"));
        bean.setHttps_supported(record.get("https_supported"));
        bean.setProduct_id(record.get("product_id"));
        bean.setUpgrade_date(record.get("upgrade_date"));
        bean.setOnline_status_date(record.get("online_status_date"));
        bean.setMq_tag(record.get("mq_tag"));
        bean.setCreate_date(record.get("create_date"));
        bean.setModify_date(record.get("modify_date"));*/
    }
}
