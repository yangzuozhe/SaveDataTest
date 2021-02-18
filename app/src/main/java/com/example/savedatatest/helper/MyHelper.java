package com.example.savedatatest.helper;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 类描述
 *
 * @author HB.yangzuozhe
 * @date 2021-01-19
 */
public class MyHelper {
    public static void getFileData(Context context) {
        //内部存储文件夹的绝对路径
        String insideSave = context.getFilesDir().getAbsolutePath();
        //需要在内部存储创建的新的文件夹的绝对路径
        String newDir = insideSave + "/myYangDir";
        //将想要创建的文件夹的路径转为 file
        File newDirFile = new File(newDir);
        //内部存储创建的文件夹的绝对路径
        String newDirText = newDir + "/yang.txt";
        File newDirTextFile = new File(newDirText);

        try {

            //如果文件存在
            if (newDirTextFile.exists()){
                //删除文件
                newDirTextFile.delete();
            }
            //如果文件不存在,创建文件夹
            newDirFile.mkdir();
            newDirTextFile.createNewFile();


            FileOutputStream fileOutputStream = new FileOutputStream(newDirTextFile);
            fileOutputStream.write("ahjahaha".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
