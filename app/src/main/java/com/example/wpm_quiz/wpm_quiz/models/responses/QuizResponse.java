package com.example.wpm_quiz.wpm_quiz.models.responses;

import com.example.wpm_quiz.wpm_quiz.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizResponse {
    private List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }
}
