package com.example.hello_world;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.view.View;
import android.widget.ListView;
import androidx.fragment.app.ListFragment;

public class fragment2 extends ListFragment {

    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        String strText = (String) l.getItemAtPosition(position) ;
        // TODO
    }
}