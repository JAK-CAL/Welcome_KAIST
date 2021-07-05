package com.example.hello_world;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> items;
    private ArrayList<String> itext = new ArrayList<String>();

    public VPAdapter(FragmentManager fm) {
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new fragment1());
        items.add(new fragment2());
        items.add(new fragment3());
<<<<<<< HEAD

        itext.add("TEST1");
=======
        itext.add("PhoneBook");
>>>>>>> 91149bd22395e5e53fc1c180f45bbcb9635b6ea9
        itext.add("TEST2");
        itext.add("TEST3");
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return itext.get(position);
    }
}