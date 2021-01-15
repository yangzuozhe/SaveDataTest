package com.example.savedatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在使用SD卡进行读写之前，我们要配置 创建删除文件权限和写入数据的权限 去 AndroidManifest 配置
 */
public class SdActivity extends AppCompatActivity {
    private static final String TAG = "Demo";
    @BindView(R.id.btnLoad)
    Button mBtnLoad;
    SDFileHelper helper = new SDFileHelper(SdActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd);
        ButterKnife.bind(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                helper.saveFileToSD("ja", "hahah");
                Log.d("ContentValues", Environment.getExternalStorageDirectory().toString());
            }
        }).start();
    }



    @OnClick(R.id.btnLoad)
    public void onClick(View v) {
        if (v.getId() == R.id.btnLoad) {
            String fileContent = helper.readFromSD("ja");
            Log.d(TAG, "onClick: " + fileContent);
        }
    }
}