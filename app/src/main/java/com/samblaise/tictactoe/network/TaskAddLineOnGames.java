package com.samblaise.tictactoe.network;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class who can add lines on the DB
 * Created by samuel on 10/08/15.
 */
//TODO Complete this class
class TaskAddLineOnGames extends AsyncTask<Object, Integer, Boolean> {
    private URL addLinesURL;
    private Boolean finish = false;
    private HttpURLConnection connection;

    @Override
    protected Boolean doInBackground(Object... params) {
        addLinesURL = (URL) params[0];
        String name = (String) params[1];
        String data = "name=" + name;
        try {
            connection = (HttpURLConnection) addLinesURL.openConnection();
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
            finish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finish;
    }
}
