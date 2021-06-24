package com.example.androidbigtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class change_test extends AppCompatActivity {

    EditText ETTestname;
    EditText ETTestclassroom;
    EditText ETTestdate;
    Button buttonChange;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_test);

        dbHelper = new MyDatabaseHelper(this,"UserInfo.db",null,4);
        dbHelper.getWritableDatabase();

        //bundle.putString("test",itemtestname);
        //          bundle.putString("classroom",itemtestclassroom);
        //                bundle.putString("date",itemtestdate);
        //                bundle.putInt("userid",itemtestuserid);
        Intent intent = getIntent();
        String testname = intent.getStringExtra("test");
        String testclassroom = intent.getStringExtra("classroom");
        String testdate = intent.getStringExtra("date");
        int testuserid = intent.getIntExtra("userid",0);
        int id = intent.getIntExtra("id",0)+1;


        ETTestname = (EditText) findViewById(R.id.changetest);
        ETTestclassroom=(EditText) findViewById(R.id.changeclassroom);
        ETTestdate=(EditText) findViewById(R.id.changedate);

        ETTestname.setText(testname);
        ETTestclassroom.setText(testclassroom);
        ETTestdate.setText(testdate);

        buttonChange = (Button) findViewById(R.id.button3);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase testChangedb = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("test",ETTestname.getText().toString());
                values.put("classroom",ETTestclassroom.getText().toString());
                values.put("date",ETTestdate.getText().toString());
                values.put("userid",testuserid);
                testChangedb.update("TestItem",values,"id= ?",new String[]{String.valueOf(id)});
                Intent intent = new Intent(change_test.this,calender.class);
                startActivity(intent);
            }
        });




    }
}