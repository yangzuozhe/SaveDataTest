package com.example.savedatatest;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.ContentValues.TAG;

/**
 * SD卡的读写类
 *
 * @author HB.yangzuozhe
 * @date 2021-01-15
 */
public class SDFileHelper {
    private Context mContext;

    public SDFileHelper() {

    }

    public SDFileHelper(Context context) {
        mContext = context;
    }

    /**
     * 往SD卡写文件
     *
     * @param fileName    文件名
     * @param fileContent 文件内容
     */
    public void saveFileToSD(String fileName, String fileContent) {
        FileOutputStream outputStream = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                //获取SD卡的路径,用这种手动拼接和下一行 File 的方式都可以
//               String file = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + fileName;
                File file = new File(Environment.getExternalStorageDirectory(), fileName);
                //这里就不要用openFileOutput了,那个是往手机内存中写数据的
                outputStream = new FileOutputStream(file);
                //字符内容转为字节数组，通过字节输出流输出
                outputStream.write(fileContent.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //关闭输出流
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(mContext, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 读取 sd 卡的数据
     *
     * @param fileName 文件名
     * @return 文件内容
     */
    public String readFromSD(String fileName) {
        StringBuilder builder = new StringBuilder("");
        //文件的输入流
        FileInputStream inputStream = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                //这里和上面一样，都是使用拼接法
//                String file = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + fileName;
                File file = new File(Environment.getExternalStorageDirectory(), fileName);
                //实例化输入流
                inputStream = new FileInputStream(file);
                //准备一个字节数组容器
                byte[] temp = new byte[1024];
                //字节的大小
                int len = 0;
                //读取文件内容
                while ((len = inputStream.read(temp)) > 0) {
                    //拼接字符串
                    builder.append(new String(temp, 0, len));
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
        }
        return builder.toString();
    }

    public void deleteFromSd(){

    }
}
