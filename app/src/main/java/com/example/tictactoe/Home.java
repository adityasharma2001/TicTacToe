package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {
    Button start;
    EditText p1,p2;
    String p1name, p2name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        start = (Button) findViewById(R.id.start);
        p1 = (EditText) findViewById(R.id.p1name);
        p2 = (EditText) findViewById(R.id.p2name);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }
    public void  startGame(){
        Intent startIntent = new Intent(Home.this , MainActivity.class);
        p1name = p1.getText().toString();
        p2name = p2.getText().toString();
        startIntent.putExtra("p1",p1name);
        startIntent.putExtra("p2",p2name);
        startActivity(startIntent);
    }

}
