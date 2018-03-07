package com.example.wpm_quiz.wpm_quiz.network;

import com.example.wpm_quiz.wpm_quiz.models.responses.QuizResponse;
import com.example.wpm_quiz.wpm_quiz.models.responses.QuizListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NetworkInterface {
    @GET("api/v1/quizzes/0/100")
    Call<QuizListResponse> getJSON();

    @GET
    Call<QuizResponse> getQuiz(@Url String url);
}
