package com.example.pirateriafeedbacks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pirateriafeedbacks.Adapter.FeedbackCustomAdapter;
import com.example.pirateriafeedbacks.HTTPSHelper;
import com.example.pirateriafeedbacks.MainActivity;
import com.example.pirateriafeedbacks.R;
import com.example.pirateriafeedbacks.model.Feedback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackPendentesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FeedbackCustomAdapter adapter;
    private List<Feedback> feedbackList;
    private List<Feedback> pendentesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacks_pendentes);

        ImageView logoImageView = findViewById(R.id.image_logo);
        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir a MainActivity
                Intent intent = new Intent(FeedbackPendentesActivity.this, MainActivity.class);
                startActivity(intent);
                // Finalizar a Activity atual, se desejar
                finish();
            }
        });
        // Inicializar as listas
        feedbackList = new ArrayList<>();
        pendentesList = new ArrayList<>();

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.pendentesRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Chamar a API para obter dados de feedback
        HTTPSHelper.GetDataServiceFeedback service = HTTPSHelper.getRetrofitInstance().create(HTTPSHelper.GetDataServiceFeedback.class);
        Call<List<Feedback>> call = service.getAllFeedback();
        call.enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {
                if (response.isSuccessful()) {
                    feedbackList.addAll(response.body()); // Adicionar todos os feedbacks obtidos
                    filterPendentesFeedbacks();
                    adapter = new FeedbackCustomAdapter(FeedbackPendentesActivity.this, pendentesList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(FeedbackPendentesActivity.this, "Erro ao obter dados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Feedback>> call, Throwable t) {
                Toast.makeText(FeedbackPendentesActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterPendentesFeedbacks() {
        for (Feedback feedback : feedbackList) {
            if (feedback.getStatusFeedback().equalsIgnoreCase("Pendente")) {
                pendentesList.add(feedback);
            }
        }
    }
}
