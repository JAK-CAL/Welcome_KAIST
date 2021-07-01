package com.example.hello_world;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import androidx.fragment.app.ListFragment;
public class MainActivity extends AppCompatActivity {
    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"} ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ... 코드 계속
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU);
        ListFragment menuListFrgmt = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.menulistfragment);
        menuListFrgmt.setListAdapter(adapter);

    }
}