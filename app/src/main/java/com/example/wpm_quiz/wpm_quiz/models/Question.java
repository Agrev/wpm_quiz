package com.example.wpm_quiz.wpm_quiz.models;

import java.util.List;


public class Question {
    private String text;
    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }
    public String getText() {
        return text;
    }

}
