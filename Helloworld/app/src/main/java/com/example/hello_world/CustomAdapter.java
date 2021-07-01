package com.example.hello_world;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class CustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    public CustomAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return MainActivity.datas.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
            convertView = inflater.inflate(R.layout.fragment_item, null);

        TextView id = (TextView) convertView.findViewById(R.id.id);
        id.setText(position + "");

        TextView title = (TextView) convertView.findViewById(R.id.content);
        title.setText(MainActivity.datas.get(position).title);

        return convertView;
    }
}
