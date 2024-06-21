package com.example.pirateriafeedbacks.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pirateriafeedbacks.Adapter.AtividadeCustomAdapter;
import com.example.pirateriafeedbacks.HTTPSHelper;
import com.example.pirateriafeedbacks.R;
import com.example.pirateriafeedbacks.model.Atividades;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtividadeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AtividadeCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades);

// Configurar RecyclerView
        recyclerView = findViewById(R.id.customRecyclerView); // Corrigido para usar o RecyclerView correto
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AtividadeActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        // Chamar a API para obter dados de cardápio
        HTTPSHelper.GetDataServiceAtividades service = HTTPSHelper.getRetrofitInstance().create(HTTPSHelper.GetDataServiceAtividades.class);
        Call<List<Atividades>> call = service.getAllAtividades();
        call.enqueue(new Callback<List<Atividades>>() {
            @Override
            public void onResponse(Call<List<Atividades>> call, Response<List<Atividades>> response) {
                if (response.isSuccessful()) {
                    generateDataList(response.body());
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

    // Método para configurar o RecyclerView com os dados obtidos
    private void generateDataList(List<Atividades> AtividadesList) {
        adapter = new AtividadeCustomAdapter(this, AtividadesList);
        recyclerView.setAdapter(adapter);
    }
}
