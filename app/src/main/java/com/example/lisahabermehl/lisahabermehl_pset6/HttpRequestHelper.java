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
 * This class
 */

public class HttpRequestHelper {

    private static final String url = "http://api.giphy.com/v1/gifs/";

    static String response;

    protected static synchronized String downloadFromServer(String... params) throws IOException {

        // make a complete URL by combining all the different pieces you've gathered
        String chosenTag = params[0];
        String apiKey = "dc6zaTOxFJmzC";
        String completeURL = url + chosenTag + "&api_key=" + apiKey;

        URL new_url = new URL(completeURL);

        HttpURLConnection connection;

        if (url != null) {

            // open a new HttpURLConnection with the GET method
            connection = (HttpURLConnection) new_url.openConnection();
            connection.setRequestMethod("GET");

            // read the response
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();

            // store/organize the response
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
            response = sb.toString();
        }
        return response;
    }
}
