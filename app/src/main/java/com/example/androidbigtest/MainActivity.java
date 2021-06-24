package com.example.androidbigtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDatabaseHelper myDBhelper = new MyDatabaseHelper(this,"UserInfo.db",null,4);
    public CardView cardView;
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase stuDb;
    EditText login_userName;//用户帐号
    EditText login_password;//用户密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dbHelper = new MyDatabaseHelper(this,"UserInfo.db",null,1);
        //dbHelper.getWritableDatabase();


        Button buttonNewUser = (Button) findViewById(R.id.news);
        buttonNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,activityNewUser.class);
                startActivity(intent);
            }
        });


        Button buttonLogin = (Button) findViewById(R.id.login_button);
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                login_userName = (EditText) findViewById(R.id.name);
                login_password = (EditText) findViewById(R.id.password);
                String user_name = login_userName.getText().toString();
                String user_password = login_password.getText().toString();
                //dbHelper = new MyDatabaseHelper(SQLiteActivity.this,"UserInfo.db",null,1);

                //stuDb = SQLiteDatabase.openOrCreateDatabase("UserInfo.db", null);
                stuDb = myDBhelper.getReadableDatabase();
                String[] columns = new  String[] { "id","name" ,  "password" };
                Cursor cursor = stuDb.query("User",columns,"name='"+user_name+"'",null,null,null,null);
                //String name_in_db = cursor.getString(cursor.getColumnIndex("name"));
                String password_in_db="";
                if(cursor.moveToFirst()) {
                    password_in_db = cursor.getString(cursor.getColumnIndex("password"));
                }
                //String password_in_db = cursor.getString(cursor.getColumnIndex("password"));

                if(user_password.equals(password_in_db)){

                    int id=0;
                    id = cursor.getInt(cursor.getColumnIndex("id"));
                    Intent intent = new Intent();
                    intent.putExtra("test",id);
                    intent.setClass(MainActivity.this,calender.class);
                    startActivity(intent);



                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 这条表示加载菜单文件，第一个参数表示通过那个资源文件来创建菜单
        // 第二个表示将菜单传入那个对象中。这里我们用Menu传入menu
        // 这条语句一般系统帮我们创建好
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}