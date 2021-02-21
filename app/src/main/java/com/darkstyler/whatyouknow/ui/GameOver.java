package com.darkstyler.whatyouknow.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.darkstyler.whatyouknow.R;

public class GameOver extends AppCompatActivity  {
    Button quit;
    Button newGame;
    Button menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        quit = findViewById(R.id.exit);
        newGame = findViewById(R.id.new_game);
        menu = findViewById(R.id.homeMenu);

        quit.setOnClickListener(view -> finish());

        newGame.setOnClickListener(view -> {
            Intent intent = new Intent(GameOver.this, MainActivity.class);
            startActivity(intent);
            finish();
        });


        menu.setOnClickListener(view -> {
            Intent intent = new Intent(GameOver.this, HomeScreen.class);
            startActivity(intent);
            finish();
        });

    }


}
