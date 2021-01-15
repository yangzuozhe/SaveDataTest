package com.example.savedatatest;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileHelper {
    Context mContext;

    public FileHelper() {

    }

    public FileHelper(Context context) {
        mContext = context;
    }

    /**
     * 通过字节流的方式存储数据
     * 这里定义的是一个文件保存的方法，写入到文件中，所以是输出流
     *
     * @param fileName    文件名称
     * @param fileContent 文件内容
     * @throws Exception
     */
    public void save(String fileName, String fileContent) {
        //
        FileOutputStream outputStream = null;
        try {
            //MODE_PRIVATE 是默认的操作的模式，如果文件存在，新的数据会，覆盖，文件旧的内容。
            //MODE_APPEND 是表示如果文件存在就会往文件的，末尾，添加新的数据
            //通过 openFileOutput 打开输出流
            outputStream = mContext.openFileOutput(fileName, Context.MODE_APPEND);
            //将String字符串以字节流的形式写入到输出流中
            outputStream.write(fileContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭输出流
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 通过字节流读取本地存储的文件
     *
     * @param fileName 文件名称
     * @return 文件的内容
     * @throws IOException
     */
    public String read(String fileName) {
        //打开文件输入流
        FileInputStream inputStream = null;
        //拼接字符串
        StringBuilder sb = new StringBuilder("");
        try {
            //通过openFileInput 打开输入流
            inputStream = mContext.openFileInput(fileName);
            //准备一个字节数组容器
            byte[] temp = new byte[1024];
            int len = 0;
            //读取文件内容
            //将读取的流获取的数据存放到字节容器当中。
            while ((len = inputStream.read(temp)) > 0) {
                //通过String 的实例化将字节数组，转化为 字符串，然后拼接
                sb.append(new String(temp, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭输入流
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
