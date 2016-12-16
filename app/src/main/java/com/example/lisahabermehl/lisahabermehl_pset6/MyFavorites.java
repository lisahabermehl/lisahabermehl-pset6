package com.example.lisahabermehl.lisahabermehl_pset6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Everthing that is needed for the "My favorites" activity.
 */

public class MyFavorites extends AppCompatActivity{

    private String url;
    private TextView hyperlink_to_GIF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Intent previousActivity = getIntent();
        Bundle extras = previousActivity.getExtras();
        url = extras.getString("ADD TO FAVORITES");

        hyperlink_to_GIF = (TextView) findViewById(R.id.hyperlink_to_gif);
        hyperlink_to_GIF.setText(url);
    }
}
