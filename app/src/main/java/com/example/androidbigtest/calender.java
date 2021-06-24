package com.example.androidbigtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class calender extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyDatabaseHelper dbHelper;
    MyDatabaseHelper myDBhelper = new MyDatabaseHelper(this,"UserInfo.db",null,4);
    private SQLiteDatabase stuDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        // String stringExtra = getIntent().getStringExtra("test");
        int intExtra = getIntent().getIntExtra("test",0);
        //dbHelper = new MyDatabaseHelper(this,"UserInfo.db",null,4);
        //dbHelper.getWritableDatabase();

        init();
        List<Item> pointList = new ArrayList<Item>();

        pointList = getTestDataToArray(intExtra);

        initEvent(pointList);
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }

    private void initEvent(List<Item> result) {
        RecylerAdpater myRVAdapter = new RecylerAdpater(result);
        myRVAdapter.setOnItemClickListener(new RecylerAdpater.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(),
                        "click: " + position, Toast.LENGTH_SHORT).show();

                int itemPosition = recyclerView.getChildLayoutPosition(view);
                String itemtestname = result.get(itemPosition).getTest();
                String itemtestclassroom = result.get(itemPosition).getClassroom();
                String itemtestdate = result.get(itemPosition).getDate();
                int itemtestuserid = result.get(itemPosition).getUserid();
                //String item = mList.get(itemPosition);



                Intent intent = new Intent(calender.this,change_test.class);

                Bundle bundle =new Bundle();
                bundle.putString("test",itemtestname);
                bundle.putString("classroom",itemtestclassroom);
                bundle.putString("date",itemtestdate);
                bundle.putInt("userid",itemtestuserid);
                bundle.putInt("id",itemPosition);
                intent.putExtras(bundle);
                //intent.putExtra("name",item);

                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),
                        "long click: " + position, Toast.LENGTH_SHORT).show();
            }
        });



        //recyclerView.setAdapter(new RecylerAdpater(result));
        recyclerView.setAdapter(myRVAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//垂直线性布局
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));//线性宫格显示，类似gridview
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));//线性宫格显示类似瀑布流

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "点击了设定按钮", Toast.LENGTH_LONG).show();
                break;
            case R.id.second:
                Toast.makeText(this, "点击了第二个菜单", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(calender.this,newtest.class);
                startActivity(intent);
                break;
            case R.id.takephoto:
                View dView = getWindow().getDecorView();
                dView.setDrawingCacheEnabled(true);
                dView.buildDrawingCache();
                Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
                if (bitmap != null) {
                    try {
                        // 获取内置SD卡路径
                        //String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                        // 图片文件路径
                        //String filePath = sdCardPath + File.separator + "screenshot.png";
                        //File file = new File(filePath);
                        //FileOutputStream os = new FileOutputStream(file);
                        //bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                        //
                        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, getRandomString(10), "description");

                        //os.flush();
                        //os.close();
                        //DebugLog.d("a7888", "存储完成");
                    } catch (Exception e) {
                    }
                }



                break;
            default:
                break;
        }
        return true;
    }




    public int getRandomColorCode(){

        Random random = new Random();

        return Color.argb(255, random.nextInt(256), random.nextInt(256),     random.nextInt(256));

    }

    public List<Item> getTestDataToArray(int intExtra){
        List<Item> pointList = new ArrayList<Item>();
        stuDb = myDBhelper.getReadableDatabase();
        String[] columns = new  String[] { "id","test" ,  "classroom","date","userid" };
        Cursor cursor = stuDb.query("TestItem",columns,"userid='"+intExtra+"'",null,null,null,null);
        Item ListItem = null;
        while (cursor.moveToNext()) {
            ListItem = new Item();
            ListItem.setTest(cursor.getString(cursor
                    .getColumnIndex("test")));
            ListItem.setClassroom(cursor.getString(cursor
                    .getColumnIndex("classroom")));
            ListItem.setDate(cursor.getString(cursor
                    .getColumnIndex("date")));
            ListItem.setUserid(intExtra);
            pointList.add(ListItem);
        }
        return pointList;
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

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}