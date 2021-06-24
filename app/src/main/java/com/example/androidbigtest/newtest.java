package com.example.androidbigtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class newtest extends AppCompatActivity {

    EditText testname;//
    EditText testclass;
    EditText testdate;//
    EditText testtime;
    EditText testusername;
    Button buttonSet;//
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtest);

        dbHelper = new MyDatabaseHelper(this,"UserInfo.db",null,4);
        dbHelper.getWritableDatabase();

        testname = (EditText) findViewById(R.id.newtestname);
        testclass = (EditText) findViewById(R.id.newclassroomname);
        testdate = (EditText) findViewById(R.id.newdatename);
        testtime = (EditText) findViewById(R.id.newtimename);
        testusername = (EditText) findViewById(R.id.newtestname);

        buttonSet = (Button) findViewById(R.id.button2);
        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringname = testusername.getText().toString();
                SQLiteDatabase nameVerifydb = dbHelper.getReadableDatabase();
                String[] columns = new  String[] { "id","name" ,  "password" };
                Cursor cursor = nameVerifydb.query("User",columns,"name='"+stringname+"'",null,null,null,null);
                int id =0;
                if(cursor.moveToFirst()) {
                    id = cursor.getInt(cursor.getColumnIndex("id"));
                }


                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("test",testname.getText().toString());
                values.put("classroom",testclass.getText().toString());
                values.put("date",testdate.getText().toString()+" "+testtime.getText().toString());
                values.put("userid",id);
                db.insert("TestItem",null,values);
                Intent intent = new Intent(newtest.this,calender.class);
                startActivity(intent);
            }
        });



    }
}