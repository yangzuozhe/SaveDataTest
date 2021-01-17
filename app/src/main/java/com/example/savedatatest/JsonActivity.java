package com.example.savedatatest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JsonActivity extends AppCompatActivity {
    private static final String TAG = "ContentValues";
    @BindView(R.id.btnLoad)
    Button mBtnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ButterKnife.bind(this);
        ArrayList<MyBean> myBeansList = new ArrayList<>();
        myBeansList.add(new MyBean("yang", "10", "男"));
        myBeansList.add(new MyBean("wang", "9", "男"));
        myBeansList.add(new MyBean("wei", "2", "女"));
        myBeansList.add(new MyBean("tu", "4", "男"));
        JsonToFileHelper.save(JsonActivity.this, myBeansList, "hahaha");
    }

    @OnClick(R.id.btnLoad)
    public void onclick(View v) {
        if (v.getId() == R.id.btnLoad) {
            ArrayList<MyBean> list = JsonToFileHelper.load(JsonActivity.this, "hahaha");
            Toast.makeText(JsonActivity.this,list.toString(),Toast.LENGTH_LONG).show();
            for (MyBean bean : list){
                Log.d(TAG, bean.getName());
            }
        }
    }
}