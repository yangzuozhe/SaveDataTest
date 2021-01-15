package com.example.savedatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * 在使用SD卡进行读写之前，我们要配置 创建删除文件权限和写入数据的权限 去 AndroidManifest 配置
 */
public class SdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd);
        ButterKnife.bind(this);
        SDFileHelper.saveFileToSD(getApplicationContext(),"ja","hahah");
    }
}