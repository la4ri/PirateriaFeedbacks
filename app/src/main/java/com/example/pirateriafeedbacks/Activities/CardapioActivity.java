package com.example.pirateriafeedbacks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pirateriafeedbacks.Adapter.CardapioCustomAdapter;
import com.example.pirateriafeedbacks.HTTPSHelper;
import com.example.pirateriafeedbacks.MainActivity;
import com.example.pirateriafeedbacks.R;
import com.example.pirateriafeedbacks.model.Cardapio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardapioActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardapioCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_cardapio);

        ImageView logoImageView = findViewById(R.id.image_logo);
        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir a MainActivity
                Intent intent = new Intent(CardapioActivity.this, MainActivity.class);
                startActivity(intent);
                // Finalizar a Activity atual, se desejar
                finish();
            }
        });

// Configurar RecyclerView
        recyclerView = findViewById(R.id.customRecyclerView); // Corrigido para usar o RecyclerView correto
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CardapioActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        // Chamar a API para obter dados de cardápio
        HTTPSHelper.GetDataServiceCardapio service = HTTPSHelper.getRetrofitInstance().create(HTTPSHelper.GetDataServiceCardapio.class);
        Call<List<Cardapio>> call = service.getAllCardapio();
        call.enqueue(new Callback<List<Cardapio>>() {
            @Override
            public void onResponse(Call<List<Cardapio>> call, Response<List<Cardapio>> response) {
                if (response.isSuccessful()) {
                    generateDataList(response.body());
                } else {
                    Toast.makeText(CardapioActivity.this, "Erro ao obter dados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cardapio>> call, Throwable t) {
                Toast.makeText(CardapioActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para configurar o RecyclerView com os dados obtidos
    private void generateDataList(List<Cardapio> cardapioList) {
        adapter = new CardapioCustomAdapter(this, cardapioList);
        recyclerView.setAdapter(adapter);
    }
}
