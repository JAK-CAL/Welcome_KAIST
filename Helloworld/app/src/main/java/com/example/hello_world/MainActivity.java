package com.example.hello_world;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
=======
import android.widget.Button;
>>>>>>> 91149bd22395e5e53fc1c180f45bbcb9635b6ea9

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

<<<<<<< HEAD
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

=======
public class MainActivity extends AppCompatActivity {
    static boolean next = true;
>>>>>>> 91149bd22395e5e53fc1c180f45bbcb9635b6ea9
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = findViewById(R.id.viewpager);
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
<<<<<<< HEAD
=======
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
>>>>>>> 91149bd22395e5e53fc1c180f45bbcb9635b6ea9
        vp.setAdapter(adapter);

        // 연동
        TabLayout tab = findViewById(R.id.tab);
        tab.setupWithViewPager(vp);

<<<<<<< HEAD
        //Image 추가지만 의미가 읎다..
        //ArrayList<Integer> images = new ArrayList<>();
        //images.add(R.drawable.cal);
        //images.add(R.drawable.sea);
        //images.add(R.drawable.set); No image

        //for(int i=0; i<3; i++) tab.getTabAt(i).setIcon(images.get(i));

=======
        boolean isFirstRun = sharedPreferences.getBoolean("IS_FIRST_RUN", true);

        if(isFirstRun) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            Dialog alert = alertDialog.create();

            alertDialog.setTitle("Welcome!");
            alertDialog.setMessage("Welcome to the KAIST INFO Application!");
            alertDialog.setIcon(R.drawable.ic_launcher_background);

            alertDialog.setPositiveButton("다음", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.setMessage("카이스트 신입생을 위해 만든 앱으로, 시간표 등의 정보를 제공합니다!");
                    if(next) {
                        next = false;
                        alertDialog.setPositiveButton("시작!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alertDialog.show();
                    }
                }
            });
            alertDialog.show();

            // Update
            editor.putBoolean("IS_FIRST_RUN", true);
            editor.commit();
        }
>>>>>>> 91149bd22395e5e53fc1c180f45bbcb9635b6ea9



    }

    public void displayPicture(View v) {
        int id = v.getId();
        LinearLayout layout = (LinearLayout) v.findViewById(id);
        String tag = (String) layout.getTag();


        Intent it = new Intent(this, Picture.class);
        it.putExtra("it_tag", tag);
        startActivity(it);
    }

<<<<<<< HEAD

=======
>>>>>>> 91149bd22395e5e53fc1c180f45bbcb9635b6ea9
}