package com.example.savedatatest;

import org.json.JSONArray;

public class MyBean {
    private String name;
    private String pass;
    private String sex;

    public MyBean(String name, String pass, String sex) {
        this.name = name;
        this.pass = pass;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
