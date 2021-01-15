package com.example.savedatatest;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

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
     * @param fileName 文件名
     * @param fileContent 文件内容
     */
    public static void saveFileToSD(Context context,String fileName, String fileContent) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                //获取SD卡的路径
                fileName = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + fileName;
                Log.i(TAG, "saveFileToSD: "+fileName);
                //这里就不要用openFileOutput了,那个是往手机内存中写数据的
                FileOutputStream outputStream = new FileOutputStream(fileName);
                //字符内容转为字节数组，通过字节输出流输出
                outputStream.write(fileContent.getBytes());
                //关闭输出流
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }
    }

}
