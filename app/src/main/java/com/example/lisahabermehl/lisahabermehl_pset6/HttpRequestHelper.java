package com.example.lisahabermehl.lisahabermehl_pset6;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lisahabermehl on 08/12/16.
 */

public class HttpRequestHelper {

    private static String TAG = MainActivity.class.getSimpleName();
    private static final String url = "http://api.giphy.com/v1/gifs/search?q=";

    static String response;

    protected static synchronized String downloadFromServer(String... params) throws IOException {

        Log.d("PARAMS_DFS: ", params[0]);

        String result = "";
        String apiKey = "dc6zaTOxFJmzC";
        String chosenTag = params[0];
        String completeURL = url + chosenTag + "&api_key=" + apiKey;

        Log.d("completeURL: ", completeURL);

        URL new_url = new URL(completeURL);

        HttpURLConnection connection;

        if (url != null) {

            Log.d("1", "URL not equal to null");

            connection = (HttpURLConnection) new_url.openConnection();

            connection.setRequestMethod("GET");
            Log.d("2", "got past setrequestmethod");

            // read the response
            InputStream in = new BufferedInputStream(connection.getInputStream());
            Log.d("3", "got past inputstream");

//            response = convertStreamToString(in);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
            response = sb.toString();

            }

        Log.d("RESPONSE: ", response);
        return response;
    }


}
