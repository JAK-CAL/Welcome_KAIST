package com.example.hello_world;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class fragment1 extends Fragment {

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList CustomData;
    private ArrayList mSearchData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mAdapter = new CustomAdapter(CustomData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
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
        mSearchData.add(new CustomData("test","hi", "JOY MINI (48/50)"));
        mSearchData.add(new CustomData("anothertest","english", "RALLYIST (5/50)"));
        mSearchData.add(new CustomData("tester","hello", "TEST (10/30)"));
    }
}