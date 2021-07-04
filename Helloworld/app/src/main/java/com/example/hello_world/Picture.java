package com.example.hello_world;

import android.content.res.Resources;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
public class Picture extends AppCompatActivity{

    private ViewPager2 sliderViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture);

        TextView tv_imagetitle_kor = (TextView)findViewById(R.id.imagetitle_kor);
        TextView tv_imagedetail = (TextView)findViewById(R.id.imagedetail);
        ImageView iv_picture = (ImageView)findViewById(R.id.picture);

        Intent it = getIntent();
        String tag = it.getStringExtra("it_tag");

        Resources res = getResources();

        int id_imagetitle_kor = res.getIdentifier("imagetitle_kor" + tag, "string", getPackageName());
        String imagetitle_kor = res.getString(id_imagetitle_kor);
        tv_imagetitle_kor.setText(imagetitle_kor);

        int id_imagedetail = res.getIdentifier("imagedetail" + tag, "string", getPackageName());
        String imagedetail = res.getString(id_imagedetail);
        tv_imagedetail.setText(imagedetail);

        int id_picture = res.getIdentifier("picture" + tag, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(id_picture);
        iv_picture.setBackground(drawable);

    }

    public void closePicture(View v) {
        finish();
    }



}
