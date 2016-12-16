package com.example.lisahabermehl.lisahabermehl_pset6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by lisahabermehl on 15/12/16.
 */

public class DetailActivity extends AppCompatActivity {

    MainActivity activity;
    public String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent previousActivity = getIntent();
        Bundle extras = previousActivity.getExtras();
        url = extras.getString("RESULT");

        ImageView gif = (ImageView) findViewById(R.id.gif);
        Glide
                .with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(gif);
    }

    public void toFavorites(View view) {
        Intent newActivity = new Intent(getApplicationContext(), MyFavorites.class);
        Bundle extras = new Bundle();
        extras.putString("ADD TO FAVORITES", url);
        newActivity.putExtras(extras);
        startActivity(newActivity);
    }
}
