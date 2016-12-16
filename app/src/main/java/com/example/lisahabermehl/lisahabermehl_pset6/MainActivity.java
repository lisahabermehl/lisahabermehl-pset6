package com.example.lisahabermehl.lisahabermehl_pset6;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * MainActivity and AsyncTask
 *
 * In order to be able to make this app I used the following tutorials:
 *
 * JSON and ASYNCTASK
 * http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
 * https://androidresearch.wordpress.com/2012/03/17/understanding-asynctask-once-and-forever/
 *
 * GLIDE
 * https://futurestud.io/tutorials/glide-displaying-gifs-and-videos
 *
 * FIREBASE
 * http://www.androidhive.info/2016/06/android-getting-started-firebase-simple-login-registration-auth/
 */

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private GridView gridView;
    private EditText user_input;

    public ArrayList<String> urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pDialog = new ProgressDialog(this);
        gridView = (GridView) findViewById(R.id.list);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // get the url of the picture that the user has clicked on
                String result = urls.get(position);

                // start a new activity and give the url
                Intent newActivity = new Intent(getApplicationContext(), DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putString("RESULT", result);
                newActivity.putExtras(extras);
                startActivity(newActivity);
            }
        });

        // initialize a new "GetData" class
        GetData getData = new GetData();
        // go to this class/execute this method with trending GIFs
        // for this will be the first thing that users will see when they log in
        getData.execute("trending?");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // intitialize the xml document that stores the settings for the menu
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            // first option: user clicks on "Search"
            case R.id.action_search:
                final EditText editText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Search for GIF")
                        .setView(editText)
                        .setPositiveButton("SEARCH", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        String user_input_string = String.valueOf(editText.getText());

                                        int length = user_input_string.length();

                                        if(length>0) {
                                            for(int j = 0; j < length; j++) {
                                                if (user_input_string.charAt(j) == ' ') {

                                                    char[] string_to_char = user_input_string.toCharArray();
                                                    string_to_char[j] = '+';
                                                    user_input_string = String.valueOf(string_to_char);

                                                    break;
                                                }
                                            }

                                            String user_input_string_new = "search?q=" + user_input_string;

                                            // initialize a new "GetData" class
                                            GetData getData = new GetData();

                                            // go to this class/execute this method with the user input string
                                            getData.execute(user_input_string_new);
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "You do have to write something ;)", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_LONG).show();
                                            onOptionsItemSelected(item);
                                        }
                                    }
                                })
                        .setNegativeButton("CANCEL", null).create();
                dialog.show();
                return true;
            // second option: users clicks on "My favorite GIFs"
            case R.id.favorites:
                Intent newActivity = new Intent(getApplicationContext(), MyFavorites.class);
                Bundle extras = new Bundle();
                extras.putString("ADD TO FAVORITES", null);
                newActivity.putExtras(extras);
                startActivity(newActivity);
            case R.id.log_out:
                Toast.makeText(this, "USER WILL BE LOGGED OUT", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            // user auth state is changed - user is null
                            // launch login activity
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                };
            // the default option
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetData extends AsyncTask<String, Integer, String> {

        String result = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // show progress dialog
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                result = HttpRequestHelper.downloadFromServer(params);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int i = 0;
            publishProgress(i);

            return result;
        }

        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            updateProgressBar(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            String[] list = null;

            super.onPostExecute(result);
            Log.d("RESULT in onPost: ", result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            if (result != null) {

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray contacts = jsonObject.getJSONArray("data");
                    int length = contacts.length();

                    if (length > 0) {

                        list = new String[length];

                        urls = new ArrayList<>();

                        for (int a = 0; a < length; a++) {
                            JSONObject c = contacts.getJSONObject(a);
                            JSONObject images = c.getJSONObject("images");
                            JSONObject resolution = images.getJSONObject("fixed_height_small");
                            String url = resolution.getString("url");

                            list[a] = resolution.getString("url");

                            urls.add(url);
                            Log.d(String.valueOf(a), url);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            // update parsed JSON data into the GridView
            gridView.setAdapter(new ImageListAdapter(MainActivity.this, list));
        }
    }
}