package com.example.androidbigtest;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecylerAdpater extends RecyclerView.Adapter<RecylerAdpater.ViewHolder> {
    private List<Item> items;                   //Item类在之前定义
    private static final int ITEM_CONUT = 10;
    private LayoutInflater mLayoutInflater;
    private MyDatabaseHelper dbHelper;

    public RecylerAdpater(List<Item> result) {
        super();
        items = new ArrayList<>();
        items = result;
        //for (int i = 0; i < ITEM_CONUT; i++) {
         //   items.add(new Item("Item " + i, "This is the test Item number " + i));
        //}

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView test;
        TextView classroom;
        TextView date;
        CardView parent;
        //构造函数中传入的参数为recycleView的最外层布局
        public ViewHolder(View itemView) {
            super(itemView);
            test = (TextView) itemView.findViewById(R.id.test);
            classroom = (TextView) itemView.findViewById(R.id.classroom);
            date = (TextView) itemView.findViewById(R.id.date);
            parent = (CardView) itemView.findViewById(R.id.parent);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mLayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Item item = items.get(position);

        Random rnd = new Random();
        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        viewHolder.parent.setCardBackgroundColor(currentColor);


        viewHolder.test.setText(item.getTest());
        viewHolder.classroom.setText(item.getClassroom());
        viewHolder.date.setText(item.getDate());

        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView, pos);


                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(viewHolder.itemView, pos);
                    return false;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }





}
