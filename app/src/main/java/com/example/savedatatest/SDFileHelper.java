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
    private File mFile;

    public SDFileHelper() {

    }

    /**
     * @param context
     * @param file    我们文件要存储的目录
     */
    public SDFileHelper(Context context, File file) {
        mContext = context;
        mFile = file;
    }

    /**
     * 往SD卡写文件
     *
     * @param fileName    文件名
     * @param fileContent 文件内容
     */
    public void saveFileToSD(String fileName, String fileContent) {
        FileOutputStream outputStream = null;
        //时候可读写，如果是 Environment.MEDIA_MOUNTED_READ_ONLY 就是是否可读
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                //获取SD卡的路径,用这种手动拼接和下一行 File 的方式都可以
//               String file = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + fileName;
                //外部存储根目录，在Android 10 以下可以使用这个
//                File file = new File(Environment.getExternalStorageDirectory(), fileName);
                //但是如果大于Android10就只能用，Context 的 getExternalFilesDir 方法比较好。当然 getExternalFilesDir 方法任何版本都可以用
                File file = new File(mFile, fileName);
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
//                File file = new File(Environment.getExternalStorageDirectory(), fileName);
                //我们最好使用，这样子，保证每个Android 版本都可以使用
                File file = new File(mFile, fileName);
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

    /**
     * 删除某个文件
     *
     * @param fileName 文件名称
     */
    public void deleteFromSd(String fileName) {
        //是否可读写
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(mFile, fileName);
            if (file.delete()) {
                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 清空 cache 或是 Files 文件夹中的所有文件
     *
     * @param dirPath 需要删除的文件的目录
     */
    public void clearAllFromSd(File dirPath) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File[] fileDirs = new File[1];
            //如果我们要清空的是 外部存储的 Files 文件夹
            if (dirPath.equals(mContext.getExternalFilesDir(null))) {
                //实际上获得的结果等同于 mContext.getExternalFilesDir(null)
                fileDirs = mContext.getExternalFilesDirs(null);

            } else if (dirPath.equals(mContext.getExternalCacheDir())) {
                //如果我们要清空的是 外部存储的 cache 文件夹
                //实际上获得的结果的等同于 mContext.getExternalCacheDir()
                fileDirs = mContext.getExternalCacheDirs();
            }
            //如果索引值为1的话，那么就是 外置的sd卡 ，但是现在的手机一般都不用装外置sd存储卡了，因此我们就使用索引值为0的 内置sd卡 目录
            //这就是我们 内置SD卡存储 的目录的路径
            File files1 = fileDirs[0];
            // files1.listFiles() 就是路径中的所有文件
            //实际上，我们可以不用上面的操作，我们可以上面的步骤都不写，直接将 dirPath.listFiles() 这样我们就可以得到相应目录的文件了,之所以写上面的步骤是因为，如果有外置sd卡还可以进行调整
            File[] files = files1.listFiles();
            int i = 0;
            //如果有文件，我们就删除
            if (files != null && files.length != 0) {
                for (File file : files) {
                    if (file.delete()) {
                        i++;
                    }
                }
                if (i > 0) {
                    Toast.makeText(mContext, i + "个文件被删除", Toast.LENGTH_SHORT).show();
                }
            } else {
                //如果没有文件弹出失败
                Toast.makeText(mContext, "失败", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
