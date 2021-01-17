package com.example.savedatatest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
        //通过 BufferedWriter 输出数据更加的快。
        BufferedWriter bufOut = null;
        try {
            //用 openFileOutput 创建输出的对象，为了将数据写到内存里面
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            //构造方法里用 OutputStreamWriter 对象
            bufOut = new BufferedWriter(new OutputStreamWriter(outputStream));
            //直接写入字符串
            bufOut.write(jsonArray.toString());
            //记住这个刷新流，要放在这里，也就是 write 方法过后立刻刷新，如果你放在 finally 是发送不出去的
            bufOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufOut != null) {
                    bufOut.close();
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
        //使用 BufferedReader 读取的速度更快
        BufferedReader bufRead = null;
        StringBuilder builder = new StringBuilder("");
        String data;
        ArrayList<MyBean> beanList = new ArrayList<>();
        try {
            //通过openFileInput，获得输入流的对象
            inputStream = context.openFileInput(fileName);
            //这里BufferedReader构造方法里要放 InputStreamReader 对象，InputStreamReader 构造方法里放 InputStream 对象
            bufRead = new BufferedReader(new InputStreamReader(inputStream));
            while ((data = bufRead.readLine()) != null) {
                builder.append(data);
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
                if (bufRead != null) {
                    bufRead.close();
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
    public static void gsonToJava(List<MyBean> beanList) {
        Gson gson = new Gson();
        new TypeToken<List<MyBean>>() {
        }.getType();
    }

}
