package com.samblaise.tictactoe.network;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by samuel on 12/08/15.
 */
public class TaskJoinAGame extends AsyncTask<Object, Void, Void> {
    @Override
    protected Void doInBackground(Object... params) {
        HttpURLConnection connection;
        URL urlJoinAGame = (URL) params[0];
        Integer idPlayer1 = (Integer) params[1];
        String namePlayer2 = (String) params[2];
        String data = null;
        try {
            data = "idPlayer1=" + URLEncoder.encode(idPlayer1.toString(), "UTF-8") +
                    "&name=" + URLEncoder.encode(namePlayer2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } try {
            connection = (HttpURLConnection) urlJoinAGame.openConnection();
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
