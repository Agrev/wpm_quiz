package com.example.wpm_quiz.wpm_quiz.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wpm_quiz.wpm_quiz.ui.quiz.QuizActivity;
import com.example.wpm_quiz.wpm_quiz.R;
import com.example.wpm_quiz.wpm_quiz.models.Quiz;

import java.util.ArrayList;

public class QuizesAdapter extends RecyclerView.Adapter<QuizesAdapter.ViewHolder> {
    private ArrayList<Quiz> quiz;

    public QuizesAdapter(ArrayList<Quiz> quiz) {
        this.quiz = quiz;
    }

    @Override
    public QuizesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuizesAdapter.ViewHolder holder, final int position) {
        holder.tv_title.setText(quiz.get(position).getTitle());
        holder.tv_content.setText(quiz.get(position).getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), QuizActivity.class);
                intent.putExtra("id", quiz.get(position).getId());
                System.out.println(quiz.get(position).getId());
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return quiz.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_title, tv_content;

        public ViewHolder(View view){
            super(view);
            tv_title = (TextView)view.findViewById(R.id.tv_title);
            tv_content = (TextView)view.findViewById(R.id.tv_content);


    }
}
}