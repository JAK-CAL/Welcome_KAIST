package com.example.hello_world;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CustomData> mArrayList;
    private CustomAdapter mAdapter;
    private int count = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        
        mArrayList = new ArrayList<>();

        mAdapter = new CustomAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        Button buttonInsert = (Button)findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;

                CustomData data = new CustomData(count + "", "Apple" + count, "사과" + count);
                // mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
                mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입

                mAdapter.notifyDataSetChanged();             }
        });

    }


    /**

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = findViewById(R.id.viewpager);
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        // 연동
        TabLayout tab = findViewById(R.id.tab);
        tab.setupWithViewPager(vp);

        //Image 추가지만 의미가 읎다..
        //ArrayList<Integer> images = new ArrayList<>();
        //images.add(R.drawable.cal);
        //images.add(R.drawable.sea);
        //images.add(R.drawable.set); No image

        //for(int i=0; i<3; i++) tab.getTabAt(i).setIcon(images.get(i));
    }*/
}