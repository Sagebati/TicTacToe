package com.samblaise.tictactoe.activities.old;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by samuel on 12/08/15.
 */
public class TaskPostBlob extends AsyncTask<Object, Void, Void> {

    @Override
    protected Void doInBackground(Object... params) {

        File file;
        HttpURLConnection connection;
        URL urlConnection = (URL) params[0];
        Integer idPlayer1 = (Integer) params[1];
        Integer idPlayer2 = (Integer) params[2];
        String hits = (String) params[3];
        String data = null;
        try {
            data = "idPlayer1=" + URLEncoder.encode(idPlayer1.toString(), "UTF-8") +
                    "&idPlayer2=" + URLEncoder.encode(idPlayer2.toString(), "UTF-8") +
                    "&data=" + hits;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            connection = (HttpURLConnection) urlConnection.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            if (data != null) {
                outputStream.writeBytes(data);
            }
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
