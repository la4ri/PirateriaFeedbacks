package com.example.pirateriafeedbacks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pirateriafeedbacks.Activities.AtividadeActivity;
import com.example.pirateriafeedbacks.Activities.CardapioActivity;
import com.example.pirateriafeedbacks.Activities.FeedbackActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnConfiguracaoCardapio;
    private Button btnTodosFeedbacks;
    private Button btnListaAfazeres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnConfiguracaoCardapio = findViewById(R.id.btnConfiguracaoCardapio);
        btnConfiguracaoCardapio.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CardapioActivity.class);
            startActivity(intent);
        });

        btnTodosFeedbacks = findViewById(R.id.btnTodosFeedbacks);
        btnTodosFeedbacks.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FeedbackActivity.class);
            startActivity(intent);
        });

        btnListaAfazeres = findViewById(R.id.btnListaAfazeres);
        btnListaAfazeres.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AtividadeActivity.class);
            startActivity(intent);
        });


    }
}