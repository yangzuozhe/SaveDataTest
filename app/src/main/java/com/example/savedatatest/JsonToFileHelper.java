package com.example.savedatatest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonToFileHelper {
    /**
     * 保存数据到手机 内部存储 中
     *
     * @param context  上下文为了能够使用 openFileOutput
     * @param beanList bean 类的集合
     * @param fileName 要保存的文件名
     */
    public static void save(Context context, ArrayList<MyBean> beanList, String fileName) {
        //将我们的数据bean类的集合转为 jsonArray 数据
        JSONArray jsonArray = javaToJson(beanList);
        //字节输出流
        OutputStream outputStream = null;
        try {
            //用 openFileOutput 创建输出的对象，为了将数据写到内存里面
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            //通过输出流的 write 方法将我们创建的 json 字符串，通过输出流写道内存
            outputStream.write(jsonArray.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取某个文件的数据
     *
     * @param context  上下文
     * @param fileName 想要寻找的文件名
     * @return 返回数据 bean 类
     */
    public static ArrayList<MyBean> load(Context context, String fileName) {
        //准备输入流
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder("");
        ArrayList<MyBean> beanList = new ArrayList<>();
        try {
            //通过openFileInput，获得输入流的对象
            inputStream = context.openFileInput(fileName);
            byte[] data = new byte[1024];
            int len = 0;
            //输入流获取数据
            while ((len = inputStream.read(data)) > 0) {
                String fileContent = new String(data, 0, len);
                builder.append(fileContent);
            }
            //从输入流会获得 json 字符串。然后通过 json 转为 java
            JsonToJava(builder.toString(), beanList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return beanList;
    }

    /**
     * java 对象转为 json
     *
     * @param beanList 数据 bean 类
     * @return 返回 jsonArray 数据
     */
    private static JSONArray javaToJson(List<MyBean> beanList) {
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

    /**
     * Json 转 Java
     *
     * @param data 获得的 json 字符串
     */
    public static void JsonToJava(String data, ArrayList<MyBean> list) {
        //json数组对象
        JSONArray array = null;
        try {
            //实例化json数组对象
            array = new JSONArray(data);
            //通过for循环，得到jsonObject
            for (int i = 0; i < array.length(); i++) {
                //通过for循环，得到jsonObject
                JSONObject jsonObject = array.getJSONObject(i);
                //将jsonObject 里的字段一个个提取出来
                String name = jsonObject.getString("name");
                String pass = jsonObject.getString("pass");
                String sex = jsonObject.getString("sex");
                //将提取的数字放进数据 bean 类
                MyBean bean = new MyBean(name, pass, sex);
                //将数据bean类放进集合
                list.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过 gson 将 json 转为 json
     */
    public static void gsonToJava(List<MyBean> beanList){
        Gson gson = new Gson();
        new TypeToken<List<MyBean>>(){}.getType();
    }

}
