package com.example.androidbigtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelperList extends SQLiteOpenHelper {
    public static final String CREATE_TEST="create table TestItem ("
            +"id integer primary key autoincrement, "
            +"test text, "
            +"classroom text, "
            +"date text)";
    private Context mContext;
    public MyDatabaseHelperList(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TEST);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
