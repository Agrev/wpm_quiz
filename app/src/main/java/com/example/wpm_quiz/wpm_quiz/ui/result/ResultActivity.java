package com.example.wpm_quiz.wpm_quiz.ui.result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wpm_quiz.wpm_quiz.R;


public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultText = (TextView) findViewById(R.id.resultText);
        Button returnButton = (Button) findViewById(R.id.returnButton);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        resultText.setText("Twój wynik to: " + extras.getInt("Correct") + "/" + extras.getInt("Questions_size"));

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
