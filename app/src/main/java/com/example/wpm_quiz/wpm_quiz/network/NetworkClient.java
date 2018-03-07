package com.example.wpm_quiz.wpm_quiz.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    public static Retrofit retrofit;

    public void NetworkClient(){}

    public static Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://quiz.o2.pl")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
