package com.example.savedatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEdName;
    EditText mEdContent;
    EditText mEdDelete;
    Button mBtnSave;
    Button mBtnClear;
    Button mBtnLoad;
    Button mBtnDelete;
    String mFileName;
    String mContent;

    FileHelper helper = new FileHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        mEdName = findViewById(R.id.edName);
        mEdContent = findViewById(R.id.edContent);
        mBtnSave = findViewById(R.id.btnSave);
        mBtnSave.setOnClickListener(this);
        mBtnClear = findViewById(R.id.btnClear);
        mBtnClear.setOnClickListener(this);
        mBtnLoad = findViewById(R.id.btnLoad);
        mBtnLoad.setOnClickListener(this);
        mBtnDelete = findViewById(R.id.btnDelete);
        mBtnDelete.setOnClickListener(this);
        mEdDelete = findViewById(R.id.edDelete);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnSave) {
            Save();
        } else if (i == R.id.btnLoad) {
            load();
        } else if (i == R.id.btnClear) {
            clear();
        } else if (i == R.id.btnDelete) {
            delete();
        }
    }

    /**
     * data目录里 文件的存储
     */
    public void Save() {
        mFileName = mEdName.getText().toString();
        mContent = mEdContent.getText().toString();
        if (!isEmpty(mFileName) && !isEmpty(mContent)) {
            helper.save(mFileName, mContent);
        }
    }

    /**
     * data目录里 文件的读取
     */
    public void load() {
        mFileName = mEdName.getText().toString();
        if (!isEmpty(mFileName)) {
            Toast.makeText(this, helper.read(mFileName), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * data目录保留前5个文件
     */
    public void clear() {
        //获取 app的data目录下的全部文件
        String[] list = fileList();
        //只保留前5个文件
        for (int j = list.length - 1; j > 4; j--) {
            deleteFile(list[j]);
        }
    }

    /**
     * data目录里 文件的删除
     */
    public void delete() {
        String deleteText = mEdDelete.getText().toString();
        if (!isEmpty(deleteText)) {
            //删除指定的文件，如果为真删除成功，如果为假，没有这个文件
            if (deleteFile(deleteText)) {
                Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "没有这个文件", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0 || "null".equals(s);
    }

}