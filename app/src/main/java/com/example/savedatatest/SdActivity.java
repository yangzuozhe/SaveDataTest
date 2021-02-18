package com.example.savedatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在使用SD卡进行读写之前，我们要配置 创建删除文件权限和写入数据的权限 去 AndroidManifest 配置
 */
public class SdActivity extends AppCompatActivity {
    private static final String TAG = "Demo";

    /**
     * 文件名
     */
    @BindView(R.id.edName)
    EditText mEdName;
    /**
     * 文件内容
     */
    @BindView(R.id.edContent)
    EditText mEdContent;
    /**
     * 文件内容保存
     */
    @BindView(R.id.btnSave)
    Button btnSave;
    /**
     * 文件内容读取
     */
    @BindView(R.id.btnLoad)
    Button mBtnLoad;
    /**
     * 删除指定的文件的文件名
     */
    @BindView(R.id.edDelete)
    EditText mEdDelete;
    /**
     * 删除指定文件的按钮
     */
    @BindView(R.id.btnDelete)
    Button mBtnDelete;
    /**
     * 删除 Cache文件夹中所有的文件
     */
    @BindView(R.id.btnCacheClear)
    Button mBtnCacheClear;
    /**
     * 删除 files 文件夹中的所有文件
     */
    @BindView(R.id.btnFilesClear)
    Button mBtnFilesClear;
    /**
     * 注意这里的第二个参数就是我们需要存储的相应的目录
     */
    SDFileHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd);
        ButterKnife.bind(this);
//        helper = new SDFileHelper(SdActivity.this, getExternalFilesDir(null));
        helper = new SDFileHelper(SdActivity.this, getExternalCacheDir());
    }


    @OnClick({R.id.btnLoad, R.id.btnSave, R.id.btnDelete, R.id.btnCacheClear, R.id.btnFilesClear})
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnLoad) {
            if (!isEmpty(mEdName.getText().toString())) {
                String fileContent = helper.readFromSD(mEdName.getText().toString());
                if (!isEmpty(fileContent)) {
                    Toast.makeText(v.getContext(), fileContent, Toast.LENGTH_SHORT).show();
                }
            }
        } else if (i == R.id.btnSave) {
            if (!isEmpty(mEdName.getText().toString()) && !isEmpty(mEdContent.getText().toString())) {
                //文件名，文件内容
                helper.saveFileToSD(mEdName.getText().toString(), mEdContent.getText().toString());
            }
        } else if (i == R.id.btnDelete) {
            if (!isEmpty(mEdDelete.getText().toString())) {
                if (!isEmpty(mEdDelete.getText().toString())) {
                    helper.deleteFromSd(mEdDelete.getText().toString());
                }
            }
        } else if (i == R.id.btnCacheClear) {
            helper.clearAllFromSd(getExternalCacheDir());
        } else if (i == R.id.btnFilesClear) {
            helper.clearAllFromSd(getExternalFilesDir(null));
        }
    }

    public boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0 || "null".equals(s);
    }
}