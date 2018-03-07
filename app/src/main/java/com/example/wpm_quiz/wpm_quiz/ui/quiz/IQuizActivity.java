package com.example.wpm_quiz.wpm_quiz.ui.quiz;


public interface IQuizActivity {

    void loadJSON();

    void setDefaultText();

    void endQuiz();

    void updateQuestionsAndAnswers(int i);
}
