//package com.example.lisahabermehl.lisahabermehl_pset6;
//
//import android.app.ListFragment;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.widget.ArrayAdapter;
//
//
//
///**
// * Created by lisahabermehl on 09/12/16.
// */
//
//public class ListActivity extends FragmentActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list);
//
//    }
//
//    public void onItemSelected(String id) {
//        // start the detail activity for the selected id
//        Intent detailIntent = new Intent(this, DetailActivity.class);
//        detailIntent.putExtra("item_id", id);
//        startActivity(detailIntent);
//    }
//
//    public static class ItemListActivity extends ListFragment {
//
//
//
//        public void onCreate (Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            Content content = new Content();
//            ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
//                    android.R.layout.simple_list_item_activated_1, android.R.id.text1, content.ITEMS);
//            setListAdapter(adapter);
//        }
//    }
//}
//
//
