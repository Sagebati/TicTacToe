package com.samblaise.tictactoe.network;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Task who returns the acces to de DB all the names and ip on a JSONArray.
 * parameter URL = of the webservice
 * return JSONArray with names and
 * Created by samuel on 10/08/15.
 */
class TaskGetOpenGames extends AsyncTask<URL, Integer, JSONArray> {

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        ;
        super.onPostExecute(jsonArray);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected JSONArray doInBackground(URL... params) {
        JSONArray jsonArray = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) params[0].openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null) {
                    responseStrBuilder.append(inputStr);
                }
                jsonArray = new JSONArray(responseStrBuilder.toString());
                connection.disconnect();
                in.close();
                streamReader.close();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
