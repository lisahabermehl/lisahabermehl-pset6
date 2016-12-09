//package com.example.lisahabermehl.lisahabermehl_pset6;
//
//import android.app.ListFragment;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
///**
// * Created by lisahabermehl on 09/12/16.
// */
//
//public class ItemListFragment extends ListFragment {
//
//    MainActivity activity;
//
//    public interface Callbacks {
//        public void onItemSelected(String id);
//    }
//
//    Callbacks mCallbacks = (Callbacks) activity;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1,
//                android.R.id.text1, activity.contactList));
//    }
//
//    @Override
//    public void onListItemClick(ListView listView, View view, int position, long id) {
//        super.onListItemClick(listView, view, position, id);
//
//        mCallbacks.onItemSelected(activity.contactList.get(position).id);
//
//    }
//
//}
