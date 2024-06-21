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

import com.example.pirateriafeedbacks.Adapter.AtividadeCustomAdapter;
import com.example.pirateriafeedbacks.HTTPSHelper;
import com.example.pirateriafeedbacks.MainActivity;
import com.example.pirateriafeedbacks.R;
import com.example.pirateriafeedbacks.model.Atividades;
import com.example.pirateriafeedbacks.model.Feedback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtividadeActivity extends AppCompatActivity {

    private RadioGroup statusRadioGroup;
    private RadioButton todosRadioButton;
    private RadioButton pendentesRadioButton;
    private RadioButton resolvidosRadioButton;

    private RecyclerView recyclerView;
    private AtividadeCustomAdapter adapter;
    private List<Atividades> atividadesList;
    private List<Atividades> filteredList; // Lista filtrada de feedbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades);
        ImageView logoImageView = findViewById(R.id.image_logo);
        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir a MainActivity
                Intent intent = new Intent(AtividadeActivity.this, MainActivity.class);
                startActivity(intent);
                // Finalizar a Activity atual, se desejar
                finish();
            }
        });

        // Inicializar as listas
        atividadesList = new ArrayList<>();
        filteredList = new ArrayList<>();

        // Configurar RadioGroup para filtrar os feedbacks
        statusRadioGroup = findViewById(R.id.statusRadioGroup);
        statusRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                filterAtividades();
            }
        });

        // Inicializar RadioButtons
        todosRadioButton = findViewById(R.id.todasAtividadesRadioButton);
        pendentesRadioButton = findViewById(R.id.pendentesAtividadesRadioButton);
        resolvidosRadioButton = findViewById(R.id.concluidasAtividadesRadioButton);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.customRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Chamar a API para obter dados de feedback
        HTTPSHelper.GetDataServiceAtividades service = HTTPSHelper.getRetrofitInstance().create(HTTPSHelper.GetDataServiceAtividades.class);
        Call<List<Atividades>> call = service.getAllAtividades();
        call.enqueue(new Callback<List<Atividades>>() {
            @Override
            public void onResponse(Call<List<Atividades>> call, Response<List<Atividades>> response) {
                if (response.isSuccessful()) {
                    atividadesList.addAll(response.body()); // Adicionar todos os feedbacks obtidos
                    filteredList.addAll(atividadesList); // Inicialmente, mostrar todos os feedbacks
                    adapter = new AtividadeCustomAdapter(AtividadeActivity.this, filteredList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(AtividadeActivity.this, "Erro ao obter dados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Atividades>> call, Throwable t) {
                Toast.makeText(AtividadeActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para filtrar os feedbacks com base no RadioButton selecionado
    private void filterAtividades() {
        filteredList.clear(); // Limpar a lista filtrada

        if (pendentesRadioButton.isChecked()) {
            for (Atividades atividades : atividadesList) {
                if (atividades.getStatusAtividade().equalsIgnoreCase("Pendente")) {
                    filteredList.add(atividades);
                }
            }
        } else if (resolvidosRadioButton.isChecked()) {
            for (Atividades atividades : atividadesList) {
                if (atividades.getStatusAtividade().equalsIgnoreCase("Concluída")) {
                    filteredList.add(atividades);
                }
            }
        } else {
            // Se nenhum RadioButton estiver selecionado, mostrar todos os feedbacks
            filteredList.addAll(atividadesList);
        }

        adapter.notifyDataSetChanged(); // Notificar o Adapter sobre a mudança na lista filtrada


    }
}