package com.example.hello_world;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<CustomData> mList;
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);

    public CustomAdapter(ArrayList<CustomData> list) {
        this.mList = list;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView department;
        protected TextView phone;
        protected TextView email;

        public CustomViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name);
            this.department = (TextView) view.findViewById(R.id.department);
            this.phone = (TextView) view.findViewById(R.id.phonenumber);
            this.email = (TextView) view.findViewById(R.id.email);


        }

    }

    public void removeItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(CustomData item, int position) {
        mList.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<CustomData> getData() {
        return mList;
    }



    public interface OnItemClickListener{
        public void onClick(String name,String phone);
    }

    private OnItemClickListener onItemClickListener=null;



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {


        viewholder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClick(mList.get(position).getName(),mList.get(position).getPhone());
                }
            }
        });

        viewholder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClick(mList.get(position).getName(),mList.get(position).getPhone());
                }
            }
        });

        viewholder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClick(mList.get(position).getName(),mList.get(position).getPhone());
                }
            }
        });

        viewholder.department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClick(mList.get(position).getName(),mList.get(position).getPhone());
                }
            }
        });

        viewholder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        viewholder.department.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        viewholder.phone.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        viewholder.email.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

        viewholder.name.setGravity(Gravity.CENTER);
        viewholder.department.setGravity(Gravity.CENTER);
        viewholder.phone.setGravity(Gravity.CENTER);
        viewholder.email.setGravity(Gravity.CENTER);

        viewholder.name.setText(mList.get(position).getName());
        viewholder.department.setText(mList.get(position).getDepartment());
        viewholder.phone.setText(mList.get(position).getPhone());
        viewholder.email.setText(mList.get(position).getEmail());

    }


    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}