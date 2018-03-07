package com.example.wpm_quiz.wpm_quiz.ui.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wpm_quiz.wpm_quiz.R;
import com.example.wpm_quiz.wpm_quiz.models.responses.QuizResponse;
import com.example.wpm_quiz.wpm_quiz.models.Question;
import com.example.wpm_quiz.wpm_quiz.network.NetworkClient;
import com.example.wpm_quiz.wpm_quiz.network.NetworkInterface;
import com.example.wpm_quiz.wpm_quiz.ui.result.ResultActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuizActivity extends Activity implements IQuizActivity {

    private ArrayList<Question> questions;
    TextView question;
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    int k;
    int correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        loadJSON();

        question = (TextView) findViewById(R.id.triviaQuestion);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k < questions.size() && questions.get(k).getAnswers().get(0).getIsCorrect()==1) correct++; k++;
                if(k == questions.size()) endQuiz();
                else updateQuestionsAndAnswers(k);
            }
        });


        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k < questions.size() && questions.get(k).getAnswers().get(1).getIsCorrect()==1) correct++; k++;
                if(k == questions.size()) endQuiz();
                else updateQuestionsAndAnswers(k);

            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k < questions.size() && questions.get(k).getAnswers().get(2).getIsCorrect()==1) correct++; k++;
                if(k == questions.size()) endQuiz();
                else updateQuestionsAndAnswers(k);

            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k < questions.size() && questions.get(k).getAnswers().get(3).getIsCorrect()==1) correct++; k++;
                if(k == questions.size()) endQuiz();
                else updateQuestionsAndAnswers(k);

            }
        });
    }

    public void loadJSON(){
        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("id");

        Retrofit retrofit = NetworkClient.getRetrofit();
        NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);
        Call<QuizResponse> call = networkInterface.getQuiz("http://quiz.o2.pl/api/v1/quiz/" + id + "/0");

        call.enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {

                QuizResponse quizResponse = response.body();
                questions = new ArrayList<>(quizResponse.getQuestions());
                setDefaultText();

            }
            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void setDefaultText(){
        question.setText("Pytanie: " + String.valueOf(k + 1) + "/" + questions.size() + "\n\n" + questions.get(k).getText());
        buttonA.setText(questions.get(0).getAnswers().get(0).getText());
        buttonB.setText(questions.get(0).getAnswers().get(1).getText());
        if(questions.get(0).getAnswers().size()>=3) buttonC.setText(questions.get(0).getAnswers().get(2).getText()); else buttonC.setEnabled(false);
        if(questions.get(0).getAnswers().size()==4) buttonD.setText(questions.get(0).getAnswers().get(3).getText()); else buttonD.setEnabled(false);
    }

    public void endQuiz(){
        Intent i = new Intent(QuizActivity.this, ResultActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("Questions_size", questions.size());
        extras.putInt("Correct", correct);
        i.putExtras(extras);
        finish();
        startActivity(i);
    }

    public void updateQuestionsAndAnswers(int k){
        question.setText("Pytanie: " + String.valueOf(k + 1) + "/" + questions.size() + "\n\n" + questions.get(k).getText());
        buttonA.setText(questions.get(k).getAnswers().get(0).getText());
        buttonB.setText(questions.get(k).getAnswers().get(1).getText());
        if(questions.get(0).getAnswers().size()>=3) buttonC.setText(questions.get(k).getAnswers().get(2).getText());
        if(questions.get(0).getAnswers().size()==4) buttonD.setText(questions.get(k).getAnswers().get(3).getText());
    }
}
