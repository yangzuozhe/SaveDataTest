package com.example.savedatatest.helper;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * 类描述
 *
 * @author HB.yangzuozhe
 * @date 2021-01-18
 */
public class DownLoadHelper {
    /**
     * 普通单线程下载文件：
     */
    public void normalDownLoad(String path, Context context) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            URL url = new URL(path);
            inputStream = url.openStream();
            //截取最后的文件名
            String end = path.substring(path.lastIndexOf("."));
            //打开手机对应的输出流，输入到文件中
            outputStream = context.openFileOutput("Cache_" + System.currentTimeMillis() + end, Context.MODE_PRIVATE);
            byte[] buffer = new byte[1024];
            int len = 0;
            //从输入流读取数据，读取到缓冲区
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭输入输出流
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
