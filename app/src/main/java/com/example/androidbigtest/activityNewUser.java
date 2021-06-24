package com.example.androidbigtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class activityNewUser extends AppCompatActivity {
    //private MyDatabaseHelperList dbHelperTest;
    private MyDatabaseHelper dbHelper;
    EditText login_edit_userName;//用户帐号
    EditText login_edit_password;//首次用户密码
    EditText login_edit_passwordAgain;//第二次用户密码
    Button login_btn_register;//注册按钮


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        dbHelper = new MyDatabaseHelper(this,"UserInfo.db",null,4);
        dbHelper.getWritableDatabase();

        //dbHelperTest = new MyDatabaseHelperList(this,"UserInfo.db",null,1);
        System.out.println("成功注册");
        //dbHelperTest.getWritableDatabase();
        //dbHelperTestWritein();//写入测试数据的测试版模块，id部分就填0（也就是用户fzx）

        initComponent();
        login_btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                int count_db = findMaxId("User");

                values.put("id",count_db);
                values.put("name",login_edit_userName.getText().toString());
                values.put("password",login_edit_password.getText().toString());
                db.insert("User",null,values);
                values.clear();
                Intent intent = new Intent(activityNewUser.this,MainActivity.class);
                startActivity(intent);

            }

        });



    }

    public void dbHelperTestWritein(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String fzx="fzx";
        int id =0;
        //开始组装第一条数据
        String date = "6.27 14:00~16:00";
        //values.put("id",id);
        values.put("test","数据结构");
        values.put("classroom","教4-602");
        values.put("date",date);
        values.put("userid",0);
        db.insert("TestItem",null,values);
        values.clear();
        //开始组装第二条数据
        date = "6.29 9:00~11:00";
        //values.put("id",id+1);
        values.put("test","计算机组成原理");
        values.put("classroom","教3-201");
        values.put("date",date);
        values.put("userid",0);
        db.insert("TestItem",null,values);
        values.clear();
        //开始组装第三条数据
        date = "7.1 8:00~10:00";
        //values.put("id",id);
        values.put("test","操作系统");
        values.put("classroom","教3-400");
        values.put("date",date);
        values.put("userid",0);
        db.insert("TestItem",null,values);
        values.clear();



    }


    public int findMaxId(String table) {
        // TODO Auto-generated method stub
        SQLiteDatabase database = dbHelper.getWritableDatabase();
    //Cursor cursor = database.query(table, null, null, null, null, null, " _id DESC");
        Cursor cursor= database.rawQuery("select count(2) from "+table,null);
        // cursor.getCount();
        cursor.moveToFirst();
        long count_long = cursor.getLong(0);
        int count = (int)count_long;
        cursor.close();
        return count;

    }

    private void initComponent() {
        login_btn_register = (Button) findViewById(R.id.button);
        login_edit_userName = (EditText) findViewById(R.id.editTextTextPersonName);
        login_edit_password = (EditText) findViewById(R.id.editTextTextPassword);
        login_edit_passwordAgain = (EditText) findViewById(R.id.editTextTextPassword2);

    }

}