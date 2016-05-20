package com.samblaise.tictactoe.activities.old;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samuel on 12/08/15.
 */
public class TaskAddPlayer extends AsyncTask<Object, Void, Void> {
    @Override
    protected Void doInBackground(Object... params) {
        HttpURLConnection connection;
        URL urlAddPlayer = (URL) params[0];
        String name = (String) params[1];
        String data = "name=" + name;
        try {
            connection = (HttpURLConnection) urlAddPlayer.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(data);
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
