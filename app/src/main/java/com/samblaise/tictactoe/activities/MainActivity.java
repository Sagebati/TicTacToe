package com.samblaise.tictactoe.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.samblaise.tictactoe.R;
import com.samblaise.tictactoe.network.Service_ConnectToDB;


public class MainActivity extends Activity {
    public static String PACKAGE_NAME;
    private EditText et;
    private ImageButton b;
    private Button play;
    private Boolean choice;
    private Button multilayer;
    private SharedPreferences prefs;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        b = (ImageButton) findViewById(R.id.drapeau);
        play = (Button) findViewById(R.id.play);
        multilayer = (Button) findViewById(R.id.multi);
        choice = true;
        final String no_name = getResources().getString(R.string.app_name);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        prefs = getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        et.setText(prefs.getString(PACKAGE_NAME, ""));

        /*
        Click listener for the multiplayer button, starts an Intent of ListPLayersActivity
         */
        multilayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), no_name, Toast.LENGTH_SHORT).show();
                } else {
                    service_connectToDB.addPlayer(et.getText().toString());
                    prefs.edit().putString(PACKAGE_NAME, et.getText().toString()).apply();
                    Intent intent = new Intent(getApplicationContext(), ListPlayersActivity.class);
                    intent.putExtra("name", et.getText().toString());
                    startActivity(intent);
                }
            }
        });

        /**
         * Click Listener on the button play
         */
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), no_name, Toast.LENGTH_SHORT).show();
                } else {
                    prefs.edit().putString(PACKAGE_NAME, et.getText().toString()).apply();
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("name", et.getText().toString());
                    startActivity(intent);
                }
            }
        });

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (choice) {
                    b.setImageResource(R.drawable.fra);
                    choice = false;
                } else {
                    b.setImageResource(R.drawable.eng);
                    choice = true;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = new Intent(getApplicationContext(), Service_ConnectToDB.class);
        bindService(i, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
    }
}


