package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];

    public String p1,p2;

    private Button reset;

    private Boolean player1Turn = true;

    private int roundCount;

    private int player1Point;
    private int player2Point;

    private TextView player1score;
    private TextView player2score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1 = getIntent().getStringExtra("p1");
        p2 = getIntent().getStringExtra("p2");

        player1score = (TextView) findViewById(R.id.p1score);
        player2score = (TextView) findViewById(R.id.p2score);

        player1score.setText(p1+": 0");
        player2score.setText(p2 + ": 0");

        for(int i=0 ; i<3; i++){
            for(int j=0; j<3; j++){
                String buttonID = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] =findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }

        }
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });
    }

    @Override
    public void onClick(View v) {

        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if(player1Turn){
            ((Button) v).setText("X");
        }
        else{
            ((Button) v).setText("O");
        }

        roundCount++;

        if(checkForWin()){
            if(player1Turn){
                player1Wins();
            }
            else{
                player2Wins();
            }
        }
        else if(roundCount==9){
            draw();
        }
        else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }

        }

        for(int i=0; i<3; i++){
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }

        for(int i=0; i<3; i++){
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }

        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }

        return false;
    }

    private void player1Wins(){
        player1Point++;
        Toast.makeText(this, p1 + " WINS", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }
    private void player2Wins(){
        player2Point++;
        Toast.makeText(this, p2 + " WINS", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }
    private void draw(){
        Toast.makeText(this,  "DRAW", Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatePoints(){
        player1score.setText(p1 + ":" +player1Point);
        player2score.setText(p2 +":"+ player2Point);
    }
    private void resetBoard(){
        for(int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        player1Turn = true;
    }
    private void resetGame(){
        player2Point=0;
        player1Point=0;
        updatePoints();
        resetBoard();
    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Point", player1Point);
        outState.putInt("player2Point", player2Point);
        outState.putBoolean("player1Turn", player1Turn);
        outState.putString("p1", p1);
        outState.putString("p2", p2);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Point = savedInstanceState.getInt("player1Point");
        player2Point = savedInstanceState.getInt("player2Point");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
        p1 = savedInstanceState.getString("p1");
        p2 = savedInstanceState.getString("p2");
    }
}
