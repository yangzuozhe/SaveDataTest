package com.example.savedatatest;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class JsonToFileHelper {

    public static void save(Context context, ArrayList<MyBean> beanList, String fileName) {
        JSONArray jsonArray = JSONHelper.javaToJson(beanList);
        try {
            OutputStream out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            out.write(jsonArray.toString().getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
