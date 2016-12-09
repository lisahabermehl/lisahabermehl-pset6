package com.example.lisahabermehl.lisahabermehl_pset6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lisahabermehl on 09/12/16.
 */

public class DetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        String previousActivity = extras.getString("callingActivity");


//        lv.setOnItemClickListener(new ListAdapter());

    }

    // FRAGMENTS
    // show the button to go back to the "master" page
//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    // create the detail fragment
//    Bundle arguments = new Bundle();

}
