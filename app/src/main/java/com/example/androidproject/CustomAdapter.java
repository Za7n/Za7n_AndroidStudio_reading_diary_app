package com.example.androidproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList book_id, book_date, book_title, book_pages, book_child, book_teacher;


    //int position;

    CustomAdapter(Activity activity,Context context,
                  ArrayList book_id,
                  ArrayList book_date,
                  ArrayList book_title,
                  ArrayList book_pages,
                  ArrayList book_child,
                  ArrayList book_teacher) {
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_date = book_date;
        this.book_title = book_title;
        this.book_pages = book_pages;
        this.book_child = book_child;
        this.book_teacher = book_teacher;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //this.position = holder.getAdapterPosition();

        holder.book_date_txt.setText(String.valueOf(book_date.get(position)));
        holder.book_title_txt.setText(String.valueOf(book_title.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(book_id.get(position)));
                intent.putExtra("title", String.valueOf(book_title.get(position)));
                intent.putExtra("date", String.valueOf(book_date.get(position)));
                intent.putExtra("pages", String.valueOf(book_pages.get(position)));
                intent.putExtra("child", String.valueOf(book_child.get(position)));
                intent.putExtra("teacher", String.valueOf(book_teacher.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView book_date_txt, book_title_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_date_txt = itemView.findViewById(R.id.book_date_txt);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
