package com.example.savedatatest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JSONHelper {
    /**
     * java 对象转为 json
     */
    public static JSONArray javaToJson(List<MyBean> beanList){
        //准备一个 jsonArray 用来存放多个 jsonObject 对象
        JSONArray jsonArray = new JSONArray();
        for (MyBean bean : beanList) {
            //每次遍历一个新的 jsonObject 对象
            JSONObject object = new JSONObject();
            try {
                object.put("name", bean.getName());
                object.put("pass", bean.getPass());
                object.put("sex", bean.getSex());
                //每次将 object 的对象放进 array 中
                jsonArray.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }
}
