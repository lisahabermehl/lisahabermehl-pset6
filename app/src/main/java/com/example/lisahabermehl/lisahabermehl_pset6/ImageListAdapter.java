package com.example.lisahabermehl.lisahabermehl_pset6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Class that is needed to load the GIFs.
 */

public class ImageListAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private String[] imageUrls;

    public ImageListAdapter(Context context, String[] imageUrls) {
        super(context, R.layout.list_item, imageUrls);

        this.context = context;
        this.imageUrls = imageUrls;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        // load the images
        // with diskCacheStrategy: makes the GIF's load faster
        Glide
                .with(context)
                .load(imageUrls[position])
                .asBitmap()
                .thumbnail(0.5f)
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) convertView);

        return convertView;
    }

}