package com.darkstyler.whatyouknow.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.darkstyler.whatyouknow.controller.AppController;
import com.darkstyler.whatyouknow.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    ArrayList<Question> questionArrayList = new ArrayList<>();
    private String url = "https://raw.githubusercontent.com/statsing99/JsonDATA/main/data.json";

   public List<Question> getQuestions(final AnswerListAsyncResponse callBack){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, (JSONArray) null, (response) ->{
                    for(int i=0; i<response.length(); i++){
                        try{
                            Question question = new Question();
                            question.setQuestion(response.getJSONArray(i).getString(0));
                            question.setAnswer1(response.getJSONArray(i).getString(1));
                            question.setAnswer2(response.getJSONArray(i).getString(2));
                            question.setAnswer3(response.getJSONArray(i).getString(3));
                            question.setAnswer4(response.getJSONArray(i).getString(4));
                            question.setCorrectAnswer(response.getJSONArray(i).getString(5));
                            questionArrayList.add(question);
                        }
                        catch (JSONException je){
                            je.printStackTrace();
                        }
                    }
                    if(null != callBack)
                        callBack.processFinished(questionArrayList);
        },(error) ->{
            Log.d("JSON ERROR", "JSON ERROR MESSAGE AT:" +error);}
        );
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return questionArrayList;
    }

}
