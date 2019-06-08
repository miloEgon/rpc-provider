package com.cheham.dean.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;

public class BaseController extends Controller {

    protected void result(Object code, String message, Object data) {
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("message", message);
        obj.put("data", data);
        renderJson(obj);
    }

    public void OK() {
        result(0, "ok", null);
    }

    public void OK(Object data) {
        result(0, "ok", data);
    }

    public void ERROR(int code, String message) {
        result(code, message, null);
    }

    public void ERROR(int code, String message, Object data) {
        result(code, message, data);
    }


}
