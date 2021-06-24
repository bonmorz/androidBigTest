package com.example.androidbigtest;

public class Item {
    //        values.put("test","计算机组成原理");
    //        values.put("classroom","教3-201");
    //        values.put("date",date);
    //        values.put("userid",0);

    private String test;
    private String classroom;
    private String date;
    private int userid;
    private boolean active;

    public Item(){
        this.test=null;
        this.classroom=null;
        this.date=null;
        this.userid=0;

    }

    public Item(String test, String classroom,String date,int userid, boolean active) {
        this.test = test;
        this.classroom = classroom;
        this.date=date;
        this.userid=userid;
        this.active = active;
    }

    public Item(String test, String classroom,String date,int userid) {
        this.test = test;
        this.classroom = classroom;
        this.date=date;
        this.userid=userid;

    }

    public void setTest(String test){this.test = test;}

    public void setClassroom(String classroom){this.classroom = classroom;}

    public void setDate(String date){this.date = date;}

    public void setUserid(int id){this.userid = id;}

    public String getTest() {
        return test;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getDate(){return date;}

    public int getUserid(){return userid;}

    public boolean isActive() {
        return active;
    }

}
