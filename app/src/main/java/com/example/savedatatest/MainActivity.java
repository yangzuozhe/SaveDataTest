package com.example.savedatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEdName;
    EditText mEdContent;
    Button mBtnSave;
    Button mBtnClear;
    Button mBtnLoad;

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
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnSave) {

        } else if (i == R.id.btnLoad) {

        } else if (i == R.id.btnClear) {

        }
    }
}