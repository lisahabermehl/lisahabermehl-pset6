package com.example.lisahabermehl.lisahabermehl_pset6;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * MainActivity and AsyncTask
 *
 * I used the following tutorials to make this:
 * http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
 * https://androidresearch.wordpress.com/2012/03/17/understanding-asynctask-once-and-forever/
 *
 * FRAGMENTS
 * https://inducesmile.com/android/android-fragment-masterdetail-flow-tutorial-in-android-studio/
 */

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;
    private EditText user_input;

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.list);

        user_input = (EditText) findViewById(R.id.user_input);

//        lv.setOnItemClickListener(new ListAdapter());

    }

//    @Override
//    public void onItemSelected(String id) {
//
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//
//        // associate with the xml file
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_search:
//                onClick();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void onClick(View view) {
        String user_input_string = user_input.getText().toString();

        int length = user_input_string.length();

        // check if there is a space in the user input
        // change this in a '+'
        for(int i = 0; i < length; i++) {
            if (user_input_string.charAt(i) == ' ') {

                char[] string_to_char = user_input_string.toCharArray();
                string_to_char[i] = '+';
                user_input_string = String.valueOf(string_to_char);

                Toast.makeText(this, user_input_string, Toast.LENGTH_SHORT).show();
                break;
            }
        }

        // make a new arraylist, everytime the user clicks on the SEARCH button
        contactList = new ArrayList<>();

        // initialize a new "GetData" class
        GetData getData = new GetData();
        // go to this class/execute this method with the user input string
        getData.execute(user_input_string);

    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetData extends AsyncTask<String, Integer, String> {

        String result = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Log.d("PARAMS[0]: ", params[0]);
                result = HttpRequestHelper.downloadFromServer(params);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int i = 0;
            publishProgress(i);

            Log.d("RESULT: ", result);
            Log.d("PARAMS[0]", params[0]);

            return result;

//            HttpHandler sh = new HttpHandler();
//
//            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(url);
//
//            Log.e(TAG, "Response from url: " + jsonStr);
//
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObj = new JSONObject(jsonStr);
//
//                    // Getting JSON Array node
//                    JSONArray contacts = jsonObj.getJSONArray("data");
//
//                    // looping through All Contacts
//                    for (int i = 0; i < contacts.length(); i++) {
//                        JSONObject c = contacts.getJSONObject(i);
//
//                        String id = c.getString("bitly_gif_url");
//                        String name = c.getString("bitly_url");
//                        String email = c.getString("content_url");
//                        String address = c.getString("embed_url");
//                        String gender = c.getString("id");
//
//                        // Phone node is JSON Object
//                        JSONObject phone = c.getJSONObject("images");
//                        JSONObject down = phone.getJSONObject("downsized");
//                        String mobile = down.getString("height");
//                        String home = down.getString("size");
//                        String office = down.getString("url");
//                        String width = down.getString("width");
//
//                        // tmp hash map for single contact
//                        HashMap<String, String> contact = new HashMap<>();
//
//                        // adding each child node to HashMap key => value
//                        contact.put("id", id);
//                        contact.put("bitly_gif_url", name);
//                        contact.put("embed_url", email);
//                        contact.put("height", mobile);
//
//                        // adding contact to contact list
//                        contactList.add(contact);
//                    }
//                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG)
//                                    .show();
//                        }
//                    });
//
//                }
//            } else {
//                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });
//
//            }
        }

        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            updateProgressBar(values[0]);

        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            Log.d("RESULT in onPost: ", result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            if (result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(result);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("data");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("bitly_url");
                        Log.d("BITLY URL", id);
                        String name = c.getString("slug");
                        Log.d("SLUG", name);
                        String email = c.getString("content_url");
                        Log.d("CONTENT_URL", email);
                        String address = c.getString("embed_url");
                        String gender = c.getString("id");

                        // Phone node is JSON Object
                        JSONObject phone = c.getJSONObject("images");
                        JSONObject down = phone.getJSONObject("downsized");
                        String mobile = down.getString("url");
                        String home = down.getString("size");
                        String office = down.getString("url");
                        String width = down.getString("width");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        int name_length = name.length();
                        int space_here = 0;

                        for(int j = 0; j < name_length; j++) {

                            char character = name.charAt(j);

                            int ascii_value = (int) character;

                            if (character == '-') {
                                char[] string_to_char = name.toCharArray();
                                string_to_char[j] = ' ';
                                space_here = j;
                                name = String.valueOf(string_to_char);
                                Log.d("- to space", name);
                            }

                            if (ascii_value < 97 && ascii_value > 122) {

                                char[] string_to_char = name.toCharArray();

                                for(int k = space_here; k < j; k++) {
                                    string_to_char[k] = ' ';
                                }
                                name = String.valueOf(string_to_char);
                                Log.d("int to space", name);

                            }
                        }

                        // adding each child node to HashMap key => value
                        contact.put("bitly_url", id);
                        contact.put("slug", name);
                        contact.put("content_url", email);
                        contact.put("url", mobile);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }

                /**
                 * Updating parsed JSON data into ListView
                 * */
                ListAdapter adapter = new SimpleAdapter(MainActivity.this, contactList, R.layout.list_item,
                        new String[]{"slug", "url"},
                        new int[]{R.id.name, R.id.mobile});

                lv.setAdapter(adapter);

            }

        }
    }
}

//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class MainActivity extends AppCompatActivity {
//
//    private static final String TAG = MainActivity.class.getSimpleName();
//    private TextView txtDetails;
//    private EditText inputName, inputEmail;
//    private Button btnSave;
//    private DatabaseReference mFirebaseDatabase;
//    private FirebaseDatabase mFirebaseInstance;
//
//    private String userId;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Displaying toolbar icon
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
//
//        txtDetails = (TextView) findViewById(R.id.txt_user);
//        inputName = (EditText) findViewById(R.id.name);
//        inputEmail = (EditText) findViewById(R.id.email);
//        btnSave = (Button) findViewById(R.id.btn_save);
//
//        mFirebaseInstance = FirebaseDatabase.getInstance();
//
//        // get reference to 'users' node
//        mFirebaseDatabase = mFirebaseInstance.getReference("users");
//
//        // store app title to 'app_title' node
//        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");
//
//        // app_title change listener
//        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e(TAG, "App title updated");
//
//                String appTitle = dataSnapshot.getValue(String.class);
//
//                // update toolbar title
//                getSupportActionBar().setTitle(appTitle);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.e(TAG, "Failed to read app title value.", error.toException());
//            }
//        });
//
//        // Save / update the user
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = inputName.getText().toString();
//                String email = inputEmail.getText().toString();
//
//                // Check for already existed userId
//                if (TextUtils.isEmpty(userId)) {
//                    createUser(name, email);
//                } else {
//                    updateUser(name, email);
//                }
//            }
//        });
//
//        toggleButton();
//    }
//
//    // Changing button text
//    private void toggleButton() {
//        if (TextUtils.isEmpty(userId)) {
//            btnSave.setText("Save");
//        } else {
//            btnSave.setText("Update");
//        }
//    }
//
//    /**
//     * Creating new user node under 'users'
//     */
//    private void createUser(String name, String email) {
//        // TODO
//        // In real apps this userId should be fetched
//        // by implementing firebase auth
//        if (TextUtils.isEmpty(userId)) {
//            userId = mFirebaseDatabase.push().getKey();
//        }
//
//        User user = new User(name, email);
//
//        mFirebaseDatabase.child(userId).setValue(user);
//
//        addUserChangeListener();
//    }
//
//    /**
//     * User data change listener
//     */
//    private void addUserChangeListener() {
//        // User data change listener
//        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//
//                // Check for null
//                if (user == null) {
//                    Log.e(TAG, "User data is null!");
//                    return;
//                }
//
//                Log.e(TAG, "User data is changed!" + user.name + ", " + user.email);
//
//                // Display newly updated name and email
//                txtDetails.setText(user.name + ", " + user.email);
//
//                // clear edit text
//                inputEmail.setText("");
//                inputName.setText("");
//
//                toggleButton();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.e(TAG, "Failed to read user", error.toException());
//            }
//        });
//    }
//
//    private void updateUser(String name, String email) {
//        // updating the user via child nodes
//        if (!TextUtils.isEmpty(name))
//            mFirebaseDatabase.child(userId).child("name").setValue(name);
//
//        if (!TextUtils.isEmpty(email))
//            mFirebaseDatabase.child(userId).child("email").setValue(email);
//    }
//
//}