package com.example.hello_world;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class FragmentOne extends Fragment {

    ListView listView;

    public FragmentOne() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        listView = (ListView) view.findViewById(R.id.listView);

        CustomAdapter adapter = new CustomAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 메인액티비티의 함수를 호출해서 상세 fragment 로 position 값을 넘기면서 이동한다
                MainActivity activity = (MainActivity) getActivity();
                // 클릭했을 때 셀의 포지션...
                activity.position = position;
                activity.goDetail();
            }
        });

        return view;
    }
}


출처: https://devvkkid.tistory.com/41 [개발자입니까?]