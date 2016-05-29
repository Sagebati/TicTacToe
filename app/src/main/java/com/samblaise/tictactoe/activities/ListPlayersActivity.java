package com.samblaise.tictactoe.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.samblaise.tictactoe.R;
import com.samblaise.tictactoe.network.Service_ConnectToDB;

import java.util.Map;


/**
 * Class who list the player waiting for a challenger
 * Created by samuel on 09/08/15.
 */
public class ListPlayersActivity extends Activity {
    private Service_ConnectToDB service_connectToDB; //Service
    private String myId; // id for the db

    /* Declaring widgets */
    private ProgressBar progressBar;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private Map<Integer, String> players;
    private Button create;

    /** Connecting the service  */
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
        setContentView(R.layout.activity_listplayers);
        /* Get the extras og the main activity */
        myId = getIntent().getExtras().getString("myId");
        initWidgets();
        initSetOnClickListeners();
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

    /**
     * Method who inits the widgets of the activity
     */
    private void initWidgets(){
        /* Setting up widgets */
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        create = (Button) findViewById(R.id.create);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        progressBar.setProgress(0);
        progressBar.setMax(100);
    }

    /**
     * Method who inits click listeners
     */
    private void initSetOnClickListeners(){
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                players = service_connectToDB.getNamesAndIp();
                adapter.clear();
                adapter.addAll(players.values());
                adapter.notifyDataSetInvalidated();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                service_connectToDB.joinAGame(myId);
                Intent intent = new Intent(getApplicationContext(), WaitingForPlayerActivity.class);
                intent.putExtra("myId", myId);
                startActivity(intent);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service_connectToDB.addLineOnGame();
                Intent intent = new Intent(getApplicationContext(), WaitingForPlayerActivity.class);
                intent.putExtra("myId", myId);
                startActivity(intent);
            }
        });
    }
}