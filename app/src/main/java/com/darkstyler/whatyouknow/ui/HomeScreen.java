package com.darkstyler.whatyouknow.ui;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.darkstyler.whatyouknow.R;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    Button playGame;
    Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        playGame =findViewById(R.id.button_play);
        quit = findViewById(R.id.button_quit);
        playGame.setOnClickListener(this);
        quit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_play: {
                Intent intent = new Intent(HomeScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            case R.id.button_quit: {
                finish();
            }
        }
    }

}
