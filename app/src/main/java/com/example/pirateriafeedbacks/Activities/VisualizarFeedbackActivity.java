package com.example.pirateriafeedbacks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.pirateriafeedbacks.Activities.FeedbackActivity;
import com.example.pirateriafeedbacks.R;
import com.example.pirateriafeedbacks.model.Feedback;

public class VisualizarFeedbackActivity extends AppCompatActivity {

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
        Feedback feedback = (Feedback) getIntent().getSerializableExtra("feedback");

        // Verificar se o feedback não é nulo e exibir seus detalhes
        if (feedback != null) {
            titleTextView.setText(feedback.getTituloFeedback());
            descriptionTextView.setText(feedback.getDescricaoFeedback());
            idTextView.setText(String.valueOf(feedback.getId_feedback()));
            statusTextView.setText(feedback.getStatusFeedback());

            // Configurar a cor do texto e do círculo com base no status do feedback
            if (feedback.getStatusFeedback().equalsIgnoreCase("Pendente")) {
                statusTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
                imgStatusCircle.setBackground(ContextCompat.getDrawable(this, R.drawable.circle_red)); // Círculo vermelho
            } else if (feedback.getStatusFeedback().equalsIgnoreCase("Resolvido")) {
                statusTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
                imgStatusCircle.setBackground(ContextCompat.getDrawable(this, R.drawable.circle_green)); // Círculo verde
            }

        }
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(VisualizarFeedbackActivity.this, FeedbackActivity.class);
            startActivity(intent);
            finish(); // Encerrar a atividade atual para voltar corretamente para a FeedbackActivity
        });
    }

}
