package com.darkstyler.whatyouknow.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.darkstyler.whatyouknow.R;
import com.darkstyler.whatyouknow.data.QuestionBank;
import com.darkstyler.whatyouknow.model.Question;
import com.darkstyler.whatyouknow.model.Score;
import com.darkstyler.whatyouknow.util.Prefs;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String colorCorrectAnswer = "Color.GREEN";
    public static final String colorWrongAnswer = "Color.RED";
    private static final String colorDefaultAnswer= "Color.GREEN";
    private TextView questionCounter;
    private TextView questionText;
    private TextView highestPersistScore;
    private String currentQuestionAnswer;
    private TextView currentScoreText;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private Button half;
    private Button callFriend;
    private Button askPublic;
    private int currentQuestionCounter = 0;
    private int questionAnsweredCounter = 0;
    private List<Question> questionsList;
    private int scoreCounter = 0;
    private Score score;
    private Prefs prefs;
    int highsScore;
    private int multiplier = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answer1 = findViewById(R.id.button_answer1);
        answer2 = findViewById(R.id.button_answer2);
        answer3 = findViewById(R.id.button_answer3);
        answer4 = findViewById(R.id.button_answer4);
        half =findViewById(R.id.button_50percent);
        callFriend = findViewById(R.id.button_callFriend);
        askPublic = findViewById(R.id.button_askPublic);
        score = new Score(scoreCounter);
        prefs = new Prefs(this);
        //Set highScore from previous state
        highsScore = prefs.getHighestScore();
        questionText = findViewById(R.id.question_textview);
        questionCounter = findViewById(R.id.counter_text);
        currentScoreText = findViewById(R.id.currentScore_text);
        highestPersistScore = findViewById(R.id.highestScore_text);
        questionsList = new QuestionBank().getQuestions(questionArrayList -> {
            Random random = new Random();
            currentQuestionCounter= random.nextInt(questionArrayList.size());
            questionText.setText(questionArrayList.get(currentQuestionCounter).getQuestion());
            answer1.setText(questionArrayList.get(currentQuestionCounter).getAnswer1());
            answer2.setText(questionArrayList.get(currentQuestionCounter).getAnswer2());
            answer3.setText(questionArrayList.get(currentQuestionCounter).getAnswer3());
            answer4.setText(questionArrayList.get(currentQuestionCounter).getAnswer4());
            currentQuestionAnswer = questionArrayList.get(currentQuestionCounter).getCorrectAnswer();
            questionCounter.setText(MessageFormat.format("{0}/{1}", questionAnsweredCounter, questionArrayList.size()));

        });
        highestPersistScore.setText(MessageFormat.format("Highest score: {0}", prefs.getHighestScore()));
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
        half.setOnClickListener(this);
        callFriend.setOnClickListener(this);
        askPublic.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_answer1: {

                if(currentQuestionAnswer.contentEquals(answer1.getText())){
                    updateQuestion();
                    addPoints();
                    break;
                }
                else {
                    gameOver();
                    break;
                }
            }
            case R.id.button_answer2: {
                  if(currentQuestionAnswer.contentEquals(answer2.getText())){
                      updateQuestion();
                      addPoints();
                      break;
                  }
                  else{
                        gameOver();
                        break;
                  }
            }
            case R.id.button_answer3:{
                if(currentQuestionAnswer.contentEquals(answer3.getText())){
                    updateQuestion();
                    addPoints();
                    break;
                }
                else{
                        gameOver();
                        break;
                }
            }
            case R.id.button_answer4:{
                if(currentQuestionAnswer.contentEquals(answer4.getText())){
                    updateQuestion();
                    addPoints();
                    break;
                }
                else {
                    gameOver();
                    break;
                }
            }
            case R.id.button_50percent: {
                fiftyPercent();
                break;
            }
            case R.id.button_callFriend:{
                callFriend();
                break;
            }
            case R.id.button_askPublic:{
                askPublic();
                break;
            }
        }

    }
    private void addPoints(){
        multiplier +=1;
        scoreCounter +=10 + multiplier *3.76;
        score.setScore((int) (scoreCounter));
        currentScoreText.setText(MessageFormat.format("Current score:{0}", score.getScore()));
        if(scoreCounter > highsScore){
            highsScore = scoreCounter;
            highestPersistScore.setText(MessageFormat.format("Highest score: {0}", highsScore));
            prefs.saveHighestScore(scoreCounter);
        }
    }

    //Method for updating questions textview and answers buttons
    private void updateQuestion(){
        questionAnsweredCounter++;
        //TODO Needs some optimizations, not crucial for this state
        currentQuestionCounter = (currentQuestionCounter + 1) %questionsList.size();
        //Set current Question text
        questionText.setText(questionsList.get(currentQuestionCounter).getQuestion());
        //Set current answers
        answer1.setText(questionsList.get(currentQuestionCounter).getAnswer1());
        answer2.setText(questionsList.get(currentQuestionCounter).getAnswer2());
        answer3.setText(questionsList.get(currentQuestionCounter).getAnswer3());
        answer4.setText(questionsList.get(currentQuestionCounter).getAnswer4());
        //Set String QuestionAnswer to the right answer
        currentQuestionAnswer = questionsList.get(currentQuestionCounter).getCorrectAnswer();
        questionCounter.setText(MessageFormat.format("{0} / {1}", questionAnsweredCounter, questionsList.size()));
        buttonVisibilityReset();
       // prefs.saveHighestScore(scoreCounter);
        //Needs optimizations
    }

    @Override
    protected void onPause() {
        prefs.saveHighestScore(score.getScore());
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        prefs.saveHighestScore(score.getScore());
        super.onDestroy();
    }
    //Todo improve this method
    public void fiftyPercent(){
        half.setVisibility(View.GONE);
        if(currentQuestionAnswer.contentEquals(answer1.getText())){
           answer3.setVisibility(View.GONE);
           answer2.setVisibility(View.GONE);
        }
        else if(currentQuestionAnswer.contentEquals(answer2.getText())){
            answer1.setVisibility(View.GONE);
            answer3.setVisibility(View.GONE);
        }
        else if(currentQuestionAnswer.contentEquals(answer3.getText())){
             answer4.setVisibility(View.GONE);
             answer1.setVisibility(View.GONE);
        }
        else if(currentQuestionAnswer.contentEquals(answer4.getText()))
        {
            answer2.setVisibility(View.GONE);
            answer3.setVisibility(View.GONE);
        }
    }
    //Todo need another logic
    public void buttonVisibilityReset(){
        answer1.setVisibility(View.VISIBLE);
        answer2.setVisibility(View.VISIBLE);
        answer3.setVisibility(View.VISIBLE);
        answer4.setVisibility(View.VISIBLE);

    }
    public void callFriend(){
        callFriend.setVisibility(View.GONE);
        List<String> callFriendListAnswers = new ArrayList<>();
        callFriendListAnswers.add("Брат не ме занимавай с глупостите ти");
        callFriendListAnswers.add("Иван каза че верния отговор е: " + currentQuestionAnswer);
        callFriendListAnswers.add("Не съм сигурен, спи ми се, май е: "+currentQuestionAnswer);
        callFriendListAnswers.add("Питай Илиян.");
        callFriendListAnswers.add("Абе пиши там: " + currentQuestionAnswer + ". Пък квото стане.");
        callFriendListAnswers.add("Абе ти някаква игра ли играеш. Кви са тия въпроси?");
        Random random = new Random();
        int answer = random.nextInt(6);
        Toast.makeText(this,callFriendListAnswers.get(answer),Toast.LENGTH_LONG).show();
    }
    public void askPublic() {
        askPublic.setVisibility(View.GONE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.activity_help_from_public, findViewById(R.id.ask_public));
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setView(layout).create();
        Button close = (Button) layout.findViewById(R.id.close);
        TextView answer1Percentage = (TextView) layout.findViewById(R.id.answer1);
        TextView answer2Percentage = (TextView) layout.findViewById(R.id.answer2);
        TextView answer3Percentage = (TextView) layout.findViewById(R.id.answer3);
        TextView answer4Percentage = (TextView) layout.findViewById(R.id.answer4);
        int percentage = 100;
        int currentPercentage;
        Random random = new Random();
        if(currentQuestionAnswer.contentEquals(answer1.getText())){
            currentPercentage = random.nextInt(35) + 35;
            percentage = percentage - currentPercentage;
            answer1Percentage.setText(MessageFormat.format("{0}:{1}%", answer1.getText(), currentPercentage));
            currentPercentage = random.nextInt(35);
            percentage = percentage - currentPercentage;
            answer2Percentage.setText(MessageFormat.format("{0}:{1}%", answer2.getText(), currentPercentage));
            currentPercentage = random.nextInt(percentage);
            percentage = percentage - currentPercentage;
            answer3Percentage.setText(MessageFormat.format("{0}:{1}%",answer3.getText(),currentPercentage));
            answer4Percentage.setText(MessageFormat.format("{0}:{1}%",answer4.getText(),percentage));
        }
        else if(currentQuestionAnswer.contentEquals(answer2.getText())){
            currentPercentage = random.nextInt(35) + 35;
            percentage = percentage - currentPercentage;
            answer2Percentage.setText(MessageFormat.format("{0}:{1}%", answer2.getText(), currentPercentage));
            currentPercentage = random.nextInt(35);
            percentage = percentage - currentPercentage;
            answer1Percentage.setText(MessageFormat.format("{0}:{1}%", answer1.getText(), currentPercentage));
            currentPercentage = random.nextInt(percentage);
            percentage = percentage - currentPercentage;
            answer3Percentage.setText(MessageFormat.format("{0}:{1}%",answer3.getText(),currentPercentage));
            answer4Percentage.setText(MessageFormat.format("{0}:{1}%",answer4.getText(),percentage));
        }
        else {
            currentPercentage = random.nextInt(35) + 35;
            percentage = percentage - currentPercentage;
            if(currentQuestionAnswer.contentEquals(answer3.getText())) {
                answer3Percentage.setText(MessageFormat.format("{0}:{1}%", answer3.getText(), currentPercentage));
                currentPercentage = random.nextInt(35);
                percentage = percentage - currentPercentage;
                answer2Percentage.setText(MessageFormat.format("{0}:{1}%", answer2.getText(), currentPercentage));
                currentPercentage = random.nextInt(percentage);
                percentage = percentage - currentPercentage;
                answer1Percentage.setText(MessageFormat.format("{0}:{1}%", answer1.getText(), currentPercentage));
                answer4Percentage.setText(MessageFormat.format("{0}:{1}%", answer4.getText(), percentage));
            }
            else {
                answer4Percentage.setText(MessageFormat.format("{0}:{1}%", answer4.getText(), currentPercentage));
                currentPercentage = random.nextInt(35);
                percentage = percentage - currentPercentage;
                answer2Percentage.setText(MessageFormat.format("{0}:{1}%", answer2.getText(), currentPercentage));
                currentPercentage = random.nextInt(percentage);
                percentage = percentage - currentPercentage;
                answer3Percentage.setText(MessageFormat.format("{0}:{1}%", answer3.getText(), currentPercentage));
                answer1Percentage.setText(MessageFormat.format("{0}:{1}%", answer1.getText(), percentage));
            }
        }
        close.setOnClickListener(view -> alertDialog.dismiss());
        alertDialog.show();
    }
    public void gameOver(){
        Intent intent = new Intent(this, GameOver.class);
        startActivity(intent);
        finish();
    }
}