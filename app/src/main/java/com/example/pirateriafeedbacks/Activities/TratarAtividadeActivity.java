package com.example.pirateriafeedbacks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.pirateriafeedbacks.R;
import com.example.pirateriafeedbacks.model.Atividades;
import com.example.pirateriafeedbacks.model.Feedback;

public class TratarAtividadeActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView idTextView;
    private TextView statusTextView;
    private ImageView imgStatusCircle;
    private Button btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar_feedback);

        // Inicializar as views
        titleTextView = findViewById(R.id.textTitulo);
        descriptionTextView = findViewById(R.id.textDescricao);
        idTextView = findViewById(R.id.textId);
        statusTextView = findViewById(R.id.textStatus);
        imgStatusCircle = findViewById(R.id.statusCircle);
        btnVoltar = findViewById(R.id.Voltar);

        // Obter o feedback passado através do Intent
        Atividades atividade = (Atividades) getIntent().getSerializableExtra("atividade");

        // Verificar se o feedback não é nulo e exibir seus detalhes
        if (atividade != null) {
            titleTextView.setText(atividade.getTituloAtividade());
            descriptionTextView.setText(atividade.getDescricaoAtividade());
            idTextView.setText(String.valueOf(atividade.getIdAtividade()));
            statusTextView.setText(atividade.getStatusAtividade());

            // Configurar a cor do texto e do círculo com base no status do feedback
            if (atividade.getStatusAtividade().equalsIgnoreCase("Pendente")) {
                statusTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
                imgStatusCircle.setBackground(ContextCompat.getDrawable(this, R.drawable.circle_red)); // Círculo vermelho
            } else if (atividade.getStatusAtividade().equalsIgnoreCase("Resolvido")) {
                statusTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
                imgStatusCircle.setBackground(ContextCompat.getDrawable(this, R.drawable.circle_green)); // Círculo verde
            }

        }
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(TratarAtividadeActivity.this, AtividadeActivity.class);
            startActivity(intent);
            finish(); // Encerrar a atividade atual para voltar corretamente para a FeedbackActivity
        });
    }

}