package com.samblaise.tictactoe.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.samblaise.tictactoe.R;
import com.samblaise.tictactoe.network.Service_ConnectToDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class who initiates a server socket for waiting for a challenger.
 * Created by samuel on 10/08/15.
 */
public class WaitingForPlayerActivity extends Activity {
    private ImageButton[][] buttons;
    private Button start;
    private String name;
    private String namep2;
    private Integer count;
    private ArrayList<String> hits;
    private TextView tvWaiting;
    private Integer idPlayer1;
    private Integer idPlayer2;
    private String var;
    private int i;
    private int j;
    private TextView name1;
    private TextView name2;
    private Service_ConnectToDB service_connectToDB;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            service_connectToDB = ((Service_ConnectToDB.MyBinder) service).getMyService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            service_connectToDB = null;
        }
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutliplayergame);
        name = getIntent().getExtras().getString("name");
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        tvWaiting = (TextView) findViewById(R.id.textView6);
        hits = new ArrayList<>();
        count = 0;
        start = (Button) findViewById(R.id.start);
        buttons = new ImageButton[3][3];
        buttons[0][0] = (ImageButton) findViewById(R.id.button1);
        buttons[0][1] = (ImageButton) findViewById(R.id.button2);
        buttons[0][2] = (ImageButton) findViewById(R.id.button3);
        buttons[1][0] = (ImageButton) findViewById(R.id.button4);
        buttons[1][1] = (ImageButton) findViewById(R.id.button5);
        buttons[1][2] = (ImageButton) findViewById(R.id.button6);
        buttons[2][0] = (ImageButton) findViewById(R.id.button7);
        buttons[2][1] = (ImageButton) findViewById(R.id.button8);
        buttons[2][2] = (ImageButton) findViewById(R.id.button9);
        var = "o";
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                buttons[i][j].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ImageButton b = (ImageButton) v;
                        int x = i;
                        int y = j;
                        if (var == "o") {
                            var = "x";
                            b.setTag(var);
                            b.setImageResource(R.drawable.croix);
                            if (checkForCompleted("x")) showDialog("x");
                        } else {
                            var = "o";
                            b.setTag(var);
                            b.setImageResource(R.drawable.rond);
                            if (checkForCompleted("o")) showDialog("o");
                        }
                        played(x, y);
                        waitingPlay();
                    }
                });
            }
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initWait(name);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private boolean checkForCompleted(String symbol) {
        if (buttons[0][0].getTag() == symbol && buttons[1][1].getTag() == symbol && buttons[2][2].getTag() == symbol) {
            Log.v("GameActivity", "checkForCompleted: completed in cross - " + symbol);
            return true;
        }
        if (buttons[0][2].getTag() == symbol && buttons[1][1].getTag() == symbol && buttons[2][0].getTag() == symbol) {
            Log.v("GameActivity", "checkForCompleted: completed in cross - " + symbol);
            return true;
        }
        for (i = 0; i < 3; i++) {
            if (buttons[i][0].getTag() == symbol && buttons[i][1].getTag() == symbol && buttons[i][2].getTag() == symbol) {
                Log.v("GameActivity", "checkForCompleted: completed in row - " + symbol);
                return true;
            }
            if (buttons[0][i].getTag() == symbol && buttons[1][i].getTag() == symbol && buttons[2][i].getTag() == symbol) {
                Log.v("GameActivity", "checkForCompleted: completed in column - " + symbol);
                return true;
            }
        }
        return false;
    }

    private void showDialog(String team) {
        String Player = getResources().getString(R.string.Player);
        String just_won = getResources().getString(R.string.just_won);
        String winner;

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_gameover);
        if (team.equals("x")) winner = name;
        else winner = (String) (name2.getText());
        dialog.setTitle(Player + " " + winner + " '" + team + "' " + just_won);

        // set the custom dialog components -  image and button
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        if (team == "o") image.setImageResource(R.drawable.rond);
        if (team == "x") image.setImageResource(R.drawable.croix);
        Button dialogButtonMenu = (Button) dialog.findViewById(R.id.dialogButtonMenu);
        Button dialogButtonAgain = (Button) dialog.findViewById(R.id.dialogButtonAgain);

        dialog.show();

        // if button is clicked, close the custom dialog
        dialogButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        dialogButtonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
    }

    public void initWait(String name) {
        // TODO
        JSONObject jsonObject = null;
        try {
            idPlayer1 = jsonObject.getInt("idPlayer1");
            idPlayer2 = jsonObject.getInt("idPlayer2");
            namep2 = jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (this.name.equals(name2)) {
            name2.setText(this.name);
        }
        if (!this.name.equals(namep2)) {
            name1.setText(this.name);
            name2.setText(namep2);
        }
        tvWaiting.setText("Player Ready !");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = new Intent(getApplicationContext(), Service_ConnectToDB.class);
        bindService(i, serviceConnection, BIND_AUTO_CREATE);
    }

    public void waitingPlay() {
        buttons[Character.getNumericValue(hits.get(count).charAt(0))][Character.getNumericValue(hits.get(count).charAt(2))].callOnClick();
    }

    public void played(int x, int y) {
        hits.add(x + "" + y);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("WaitingForPlayer Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://host/path"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
