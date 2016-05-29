package com.samblaise.tictactoe.network;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class who can remove lines on the DB
 */
class TaskRemoveLineOnGames extends AsyncTask<Object, Integer, Boolean> {
    HttpURLConnection connection;
    URL removeLineWithId;

    @Override
    protected Boolean doInBackground(Object... params) {

        removeLineWithId = (URL) params[0];
        Integer id = (Integer) params[1];
        String data = "id=" + id;
        try {
            connection = (HttpURLConnection) removeLineWithId.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(data);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + removeLineWithId);
            System.out.println("Post parameters : " + data);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
