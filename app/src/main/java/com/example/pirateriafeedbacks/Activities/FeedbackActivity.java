package com.example.pirateriafeedbacks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class FeedbackActivity extends AppCompatActivity {

    private RadioGroup statusRadioGroup;
    private RadioButton todosRadioButton;
    private RadioButton pendentesRadioButton;
    private RadioButton resolvidosRadioButton;

    private RecyclerView recyclerView;
    private FeedbackCustomAdapter adapter;
    private List<Feedback> feedbackList;
    private List<Feedback> filteredList; // Lista filtrada de feedbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacks);

        ImageView logoImageView = findViewById(R.id.image_logo);
        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir a MainActivity
                Intent intent = new Intent(FeedbackActivity.this, MainActivity.class);
                startActivity(intent);
                // Finalizar a Activity atual, se desejar
                finish();
            }
        });

        // Inicializar as listas
        feedbackList = new ArrayList<>();
        filteredList = new ArrayList<>();

        // Configurar RadioGroup para filtrar os feedbacks
        statusRadioGroup = findViewById(R.id.statusRadioGroup);
        statusRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                filterFeedbacks();
            }
        });

        // Inicializar RadioButtons
        todosRadioButton = findViewById(R.id.todosFeedbacksRadioButton);
        pendentesRadioButton = findViewById(R.id.pendentesFeedbacksRadioButton);
        resolvidosRadioButton = findViewById(R.id.resolvidosFeedbacksRadioButton);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.customRecyclerView);
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
                    filteredList.addAll(feedbackList); // Inicialmente, mostrar todos os feedbacks
                    adapter = new FeedbackCustomAdapter(FeedbackActivity.this, filteredList);
                    recyclerView.setAdapter(adapter);
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

    // Método para filtrar os feedbacks com base no RadioButton selecionado
    private void filterFeedbacks() {
        filteredList.clear(); // Limpar a lista filtrada

        if (pendentesRadioButton.isChecked()) {
            for (Feedback feedback : feedbackList) {
                if (feedback.getStatusFeedback().equalsIgnoreCase("Pendente")) {
                    filteredList.add(feedback);
                }
            }
        } else if (resolvidosRadioButton.isChecked()) {
            for (Feedback feedback : feedbackList) {
                if (feedback.getStatusFeedback().equalsIgnoreCase("Resolvido")) {
                    filteredList.add(feedback);
                }
            }
        } else {
            // Se nenhum RadioButton estiver selecionado, mostrar todos os feedbacks
            filteredList.addAll(feedbackList);
        }

        adapter.notifyDataSetChanged(); // Notificar o Adapter sobre a mudança na lista filtrada
    }
}