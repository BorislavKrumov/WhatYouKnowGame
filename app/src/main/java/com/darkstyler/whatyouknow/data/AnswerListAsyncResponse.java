package com.darkstyler.whatyouknow.data;

import com.darkstyler.whatyouknow.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);
}
