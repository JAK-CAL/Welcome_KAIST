package com.example.hello_world;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Dictionary;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<CustomData> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView department;
        protected TextView phone;

        public CustomViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.id_listitem);
            this.department = (TextView) view.findViewById(R.id.english_listitem);
            this.phone = (TextView) view.findViewById(R.id.korean_listitem);
        }
    }

    public CustomAdapter(ArrayList<CustomData> list) {
        this.mList = list;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.department.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.phone.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.name.setGravity(Gravity.CENTER);
        viewholder.department.setGravity(Gravity.CENTER);
        viewholder.phone.setGravity(Gravity.CENTER);

        viewholder.name.setText(mList.get(position).getName());
        viewholder.department.setText(mList.get(position).getDepartment());
        viewholder.phone.setText(mList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}