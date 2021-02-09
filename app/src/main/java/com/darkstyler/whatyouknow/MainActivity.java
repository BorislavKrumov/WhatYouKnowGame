package com.darkstyler.whatyouknow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.darkstyler.whatyouknow.model.Question;
import com.darkstyler.whatyouknow.model.Score;
import com.darkstyler.whatyouknow.util.Prefs;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView questionCounter;
    private TextView questionText;
    private TextView highestPersistScore;
    private TextView currentScoreText;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private int currentQuestionCounter = 0;
    private List<Question> questionsList;
    private int scoreCounter =0;
    private Score score;
    private Prefs prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answer1 = findViewById(R.id.button_answer1);
        answer2 = findViewById(R.id.button_answer2);
        answer3 = findViewById(R.id.button_answer3);
        answer4 = findViewById(R.id.button_answer4);
        score = new Score(scoreCounter);
        prefs = new Prefs(this);
        questionText = findViewById(R.id.question_textview);
        questionCounter = findViewById(R.id.counter_text);
        currentScoreText = findViewById(R.id.currentScore_text);
        highestPersistScore = findViewById(R.id.highestScore_text);

    }
}