package com.samblaise.tictactoe.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Project : TicTacToe
 * ${PACKAGE_NAME}
 * Created by samuel on 12/08/15.
 */
public class TaskGetLineOfTheWeb extends AsyncTask<Object, Void, JSONObject> {

    @Override
    protected JSONObject doInBackground(Object... params) {
        JSONObject jsonObject;
        HttpURLConnection connection;
        URL UrlGetLineOfGame = (URL) params[0];
        String namep1 = (String) params[1];
        String data = "namep1=" + namep1;
        Boolean finish = false;
        while (!finish) {
            try {
                connection = (HttpURLConnection) UrlGetLineOfGame.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestMethod("POST");

                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(data);
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
                JSONArray jsonArray = new JSONArray(response.toString());
                 jsonObject = jsonArray.getJSONObject(0);
                if (jsonObject.getString("idPlayer2") != null) {
                    finish = true;
                    return jsonObject;
                }
                in.close();
                connection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
