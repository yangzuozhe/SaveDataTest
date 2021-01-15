package com.example.savedatatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ArrayList<MyBean> myBeansList = new ArrayList<>();
        myBeansList.add(new MyBean("yang","10","男"));
        myBeansList.add(new MyBean("wang","9","男"));
        myBeansList.add(new MyBean("wei","2","女"));
        myBeansList.add(new MyBean("tu","4","男"));
        JsonToFileHelper.save(JsonActivity.this,myBeansList,"hahaha");
    }
}