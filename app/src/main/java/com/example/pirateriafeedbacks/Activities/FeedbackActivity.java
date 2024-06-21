package com.example.pirateriafeedbacks.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pirateriafeedbacks.Adapter.FeedbackCustomAdapter;
import com.example.pirateriafeedbacks.HTTPSHelper;
import com.example.pirateriafeedbacks.R;
import com.example.pirateriafeedbacks.model.Feedback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FeedbackCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacks);

// Configurar RecyclerView
        recyclerView = findViewById(R.id.customRecyclerView); // Corrigido para usar o RecyclerView correto
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FeedbackActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        // Chamar a API para obter dados de cardápio
        HTTPSHelper.GetDataServiceFeedback service = HTTPSHelper.getRetrofitInstance().create(HTTPSHelper.GetDataServiceFeedback.class);
        Call<List<Feedback>> call = service.getAllFeedback();
        call.enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {
                if (response.isSuccessful()) {
                    generateDataList(response.body());
                } else {
                    Toast.makeText(FeedbackActivity.this, "Erro ao obter dados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Feedback>> call, Throwable t) {
                Toast.makeText(FeedbackActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para configurar o RecyclerView com os dados obtidos
    private void generateDataList(List<Feedback> feedbackList) {
        adapter = new FeedbackCustomAdapter(this, feedbackList);
        recyclerView.setAdapter(adapter);
    }
}
