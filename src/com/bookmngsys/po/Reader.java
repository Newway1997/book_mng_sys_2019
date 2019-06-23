package com.bookmngsys.po;

import java.util.Date;

public class Reader {
    private int id;
    private String name;
    private String username;
    private int age;
    private int sex;
    private String phone;
    private Date regdate;
    private int level=1;
    private int status=1;
    private int maxBrNum;
    private int maxBrDay;
    public Reader(){}
    public Reader(int id, String name, int age, int sex, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMaxBrNum() {
        return maxBrNum;
    }

    public void setMaxBrNum(int maxBrNum) {
        this.maxBrNum = maxBrNum;
    }

    public int getMaxBrDay() {
        return maxBrDay;
    }

    public void setMaxBrDay(int maxBrDay) {
        this.maxBrDay = maxBrDay;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
