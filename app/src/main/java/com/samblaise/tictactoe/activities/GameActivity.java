package com.samblaise.tictactoe.activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.samblaise.tictactoe.R;


public class GameActivity extends Activity {
    private ImageButton[][] buttons;
    private String name;
    private String var;
    private int i,j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        name = getIntent().getExtras().getString("name");

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
                        b.setEnabled(false);
                    }
                });
            }
        }
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

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_gameover);
        dialog.setTitle(Player + " '" + team + "' " + just_won);

        // set the custom dialog components - text, image and button
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

}
