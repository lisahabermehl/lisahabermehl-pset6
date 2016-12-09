//package com.example.lisahabermehl.lisahabermehl_pset6;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//import android.widget.Toast;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// * Async task class to get json by making HTTP call
// */
//public class GetData extends AsyncTask<Void, Void, Void> {
//
//    private ProgressDialog pDialog;
//    private ListView lv;
//    Context context;
//
//    // URL to get contacts JSON
//    private static String url = "http://api.giphy.com/v1/gifs/search?q=happy&api_key=dc6zaTOxFJmzC";
//    // use to log
//    private String TAG = MainActivity.class.getSimpleName();
//
//    // there will be an arraylist called contactList
//    ArrayList<HashMap<String, String>> contactList;
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        // Showing progress dialog
//        pDialog = new ProgressDialog(context);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//        pDialog.show();
//
//    }
//
//    @Override
//    protected Void doInBackground(Void... arg0) {
//        HttpHandler sh = new HttpHandler();
//
//        // Making a request to url and getting response
//        String jsonStr = sh.makeServiceCall(url);
//
//        Log.e(TAG, "Response from url: " + jsonStr);
//
//        if (jsonStr != null) {
//            try {
//                JSONObject jsonObj = new JSONObject(jsonStr);
//
//                // Getting JSON Array node
//                JSONArray contacts = jsonObj.getJSONArray("data");
//
//                // looping through All Contacts
//                for (int i = 0; i < contacts.length(); i++) {
//                    JSONObject c = contacts.getJSONObject(i);
//
//                    String id = c.getString("bitly_gif_url");
//                    String name = c.getString("bitly_url");
//                    String email = c.getString("content_url");
//                    String address = c.getString("embed_url");
//                    String gender = c.getString("id");
//
//                    // Phone node is JSON Object
//                    JSONObject phone = c.getJSONObject("images");
//                    JSONObject down = phone.getJSONObject("downsized");
//                    String mobile = down.getString("height");
//                    String home = down.getString("size");
//                    String office = down.getString("url");
//                    String width = down.getString("width");
//
//                    // tmp hash map for single contact
//                    HashMap<String, String> contact = new HashMap<>();
//
//                    // adding each child node to HashMap key => value
//                    contact.put("id", id);
//                    contact.put("bitly_gif_url", name);
//                    contact.put("embed_url", email);
//                    contact.put("height", mobile);
//
//                    // adding contact to contact list
//                    contactList.add(contact);
//                }
//            } catch (final JSONException e) {
//                Log.e(TAG, "Json parsing error: " + e.getMessage());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(context,
//                                "Json parsing error: " + e.getMessage(),
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });
//
//            }
//        } else {
//            Log.e(TAG, "Couldn't get json from server.");
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(context,
//                            "Couldn't get json from server. Check LogCat for possible errors!",
//                            Toast.LENGTH_LONG)
//                            .show();
//                }
//            });
//
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void result) {
//        super.onPostExecute(result);
//        // Dismiss the progress dialog
//        if (pDialog.isShowing())
//            pDialog.dismiss();
//        /**
//         * Updating parsed JSON data into ListView
//         * */
//        ListAdapter adapter = new SimpleAdapter(
//                MainActivity.this, contactList,
//                R.layout.list_item, new String[]{"bitly_gif_url", "embed_url",
//                "height"}, new int[]{R.id.name,
//                R.id.email, R.id.mobile});
//
//        lv.setAdapter(adapter);
//    }
//
//}
//
