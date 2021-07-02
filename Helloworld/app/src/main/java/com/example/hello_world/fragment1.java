package com.example.hello_world;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class fragment1 extends Fragment {

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList mSearchData;
    private int count = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mAdapter = new CustomAdapter(mSearchData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Button buttonInsert = (Button)view.findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                count++;
                CustomData data = new CustomData(count + "", "Apple" + count, "사과" + count);
                // mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
                mSearchData.add(data); // RecyclerView의 마지막 줄에 삽입
                **/
                Toast.makeText(getActivity(), "새 연락처를 추가합니다.", Toast.LENGTH_LONG).show();
                // 액티비티 전환 코드
                Intent intent = new Intent(getActivity(), AddPhoneBook.class);
                startActivityForResult(intent,0);
                mAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }


    private void initDataset() {
        //for Test
        mSearchData = new ArrayList<>();
        //mSearchData.add(new CustomData("test","hi", "JOY MINI (48/50)"));
        //mSearchData.add(new CustomData("anothertest","english", "RALLYIST (5/50)"));
        //mSearchData.add(new CustomData("tester","hello", "TEST (10/30)"));
    }
}