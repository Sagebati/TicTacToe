package com.samblaise.tictactoe.activities.old;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by samuel on 12/08/15.
 */
public class TaskGetBlob extends AsyncTask<Object, Void, String> {
    @Override
    protected String doInBackground(Object... params) {
        String result = null;
        HttpURLConnection connection;
        URL urlGetBlob = (URL) params[0];
        Integer idPlayer1 = (Integer) params[1];
        Integer idPlayer2 = (Integer) params[2];
        String data = null;
        boolean finish = false;
        while (!finish) {
            try {
                data = "idPlayer1=" + URLEncoder.encode(idPlayer1.toString(), "UTF-8") +
                        "&idPlayer2=" + URLEncoder.encode(idPlayer2.toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                connection = (HttpURLConnection) urlGetBlob.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestMethod("POST");

                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                if (data != null) {
                    outputStream.writeBytes(data);
                }
                outputStream.flush();
                outputStream.close();

                Integer responseCode = connection.getResponseCode();
                Log.d("Reponse code", responseCode.toString());

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                result = response.toString();
                if (!result.equals("")) {
                    finish = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
