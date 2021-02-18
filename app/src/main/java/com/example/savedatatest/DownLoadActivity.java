package com.example.savedatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.example.savedatatest.helper.DownLoadHelper;
import com.example.savedatatest.helper.MyHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownLoadActivity extends AppCompatActivity {

    DownLoadHelper mDownLoadHelper = new DownLoadHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        MyHelper.getFileData(DownLoadActivity.this);
    }


}