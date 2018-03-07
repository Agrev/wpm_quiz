package com.example.wpm_quiz.wpm_quiz.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.wpm_quiz.wpm_quiz.R;
import com.example.wpm_quiz.wpm_quiz.adapters.QuizesAdapter;
import com.example.wpm_quiz.wpm_quiz.models.Quiz;
import com.example.wpm_quiz.wpm_quiz.models.responses.QuizListResponse;
import com.example.wpm_quiz.wpm_quiz.network.NetworkClient;
import com.example.wpm_quiz.wpm_quiz.network.NetworkInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends Activity implements IMainView {

    private RecyclerView recyclerView;
    private ArrayList<Quiz> quizData;
    private QuizesAdapter quizesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAdapter();
        loadJSON();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setAdapter(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    public void loadJSON(){
        Retrofit retrofit = NetworkClient.getRetrofit();
        NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);
        Call<QuizListResponse> call = networkInterface.getJSON();
        call.enqueue(new Callback<QuizListResponse>() {


            @Override
            public void onResponse(Call<QuizListResponse> call, Response<QuizListResponse> response) {
                QuizListResponse quizListResponse = response.body();
                quizData = new ArrayList<>(Arrays.asList(quizListResponse.getItems()));
                quizesAdapter = new QuizesAdapter(quizData);
                recyclerView.setAdapter(quizesAdapter);
            }

            @Override
            public void onFailure(Call<QuizListResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
