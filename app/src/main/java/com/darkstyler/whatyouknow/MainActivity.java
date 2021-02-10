package com.darkstyler.whatyouknow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.darkstyler.whatyouknow.data.QuestionBank;
import com.darkstyler.whatyouknow.model.Question;
import com.darkstyler.whatyouknow.model.Score;
import com.darkstyler.whatyouknow.util.Prefs;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView questionCounter;
    private TextView questionText;
    private TextView highestPersistScore;
    private String currentQuestionAnswer;
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
        questionsList = new QuestionBank().getQuestions(questionArrayList -> {
            questionText.setText(questionArrayList.get(currentQuestionCounter).getQuestion());
            answer1.setText(questionArrayList.get(currentQuestionCounter).getAnswer1());
            answer2.setText(questionArrayList.get(currentQuestionCounter).getAnswer2());
            answer3.setText(questionArrayList.get(currentQuestionCounter).getAnswer3());
            answer4.setText(questionArrayList.get(currentQuestionCounter).getAnswer4());
            currentQuestionAnswer = questionArrayList.get(currentQuestionCounter).getCorrectAnswer();
            questionCounter.setText(currentQuestionCounter + "/" + questionArrayList.size());

        });

        //Adding small manual test
        Log.d("Main", "OnCreate" + questionsList);
        highestPersistScore.setText("Highest score: " +prefs.getHighestScore());

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_answer1: {

                if(currentQuestionAnswer.equals(answer1.toString())){

                }
            }
            case R.id.button_answer2: {

            }
            case R.id.button_answer3:{

            }
            case R.id.button_answer4:{
                Log.d("TEST","Test is working: " + currentQuestionAnswer + "answer 4 button:" + answer4.getText());
                if(currentQuestionAnswer.equals(answer4.getText())){
                    Log.d("TAG ANSWER: ", "ANSWER is correct"+ answer4);
                }


            }

        }

    }
}