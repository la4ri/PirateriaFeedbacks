package com.example.pirateriafeedbacks;


import com.example.pirateriafeedbacks.model.Atividades;
import com.example.pirateriafeedbacks.model.Cardapio;
import com.example.pirateriafeedbacks.model.Feedback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class HTTPSHelper {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://trabalhofinal-production-b0c8.up.railway.app/api/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public interface GetDataServiceCardapio {
        @GET("item-cardapio")
        Call<List<Cardapio>> getAllCardapio();
    }

    public interface GetDataServiceFeedback {
        @GET("feedbacks")
        Call<List<Feedback>> getAllFeedback();
    }

    public interface GetDataServiceAtividades {
        @GET("atividades")
        Call<List<Atividades>> getAllAtividades();
    }
}