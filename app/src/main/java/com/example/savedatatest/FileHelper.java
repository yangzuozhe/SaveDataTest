package com.example.savedatatest;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper {
    Context mContext;

    public FileHelper() {

    }

    public FileHelper(Context context) {
        mContext = context;
    }

    /**
     * 这里定义的是一个文件保存的方法，写入到文件中，所以是输出流
     */
    public void save(String fileName, String fileContent) throws Exception {
        FileOutputStream outputStream = mContext.openFileOutput(fileName, Context.MODE_APPEND);
        //将String字符串以字节流的形式写入到输出流中
        outputStream.write(fileContent.getBytes());
        //关闭输出流
        outputStream.close();
    }

    public String read(String fileName) throws IOException {
        //打开文件输入流
        FileInputStream inputStream = mContext.openFileInput(fileName);
        byte[] temp = new byte[1024];
        StringBuilder sb = new StringBuilder("");
        int len = 0;
        //读取文件内容
        while ((len = inputStream.read(temp)) > 0) {
            sb.append(new String(temp,0,len));
        }
        //关闭输入流
        inputStream.close();
        return sb.toString();
    }


}
