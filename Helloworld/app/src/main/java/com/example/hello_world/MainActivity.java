package com.example.hello_world;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
    }
}