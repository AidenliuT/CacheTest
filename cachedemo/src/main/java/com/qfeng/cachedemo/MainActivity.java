package com.qfeng.cachedemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    String url = "http://10.7.171.78:8080/share/shake_hide_img.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv = (ImageView) findViewById(R.id.iv);

        //ImageLoader.getInstance().display(url, iv);
        //用法
        Picasso.with(this).load(url).resize(100 , 100).centerCrop().config(Bitmap.Config.RGB_565).rotate(45).into(iv);

    }
}
