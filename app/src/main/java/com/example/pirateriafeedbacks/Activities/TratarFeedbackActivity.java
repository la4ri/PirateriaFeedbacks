package com.example.pirateriafeedbacks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.pirateriafeedbacks.HTTPSHelper;
import com.example.pirateriafeedbacks.R;
import com.example.pirateriafeedbacks.model.Atividades;
import com.example.pirateriafeedbacks.model.Feedback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TratarFeedbackActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView idTextView;
    private TextView statusTextView;
    private ImageView imgStatusCircle;
    private EditText respostaFeedbackEditText;
    private Button btnVoltar;
    private RadioGroup radioGroup;
    private LinearLayout llAddActivity;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button btnAdicionarAtividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratar_feedback);

        // Inicializar as views
        titleTextView = findViewById(R.id.textTitulo);
        descriptionTextView = findViewById(R.id.textDescricao);
        idTextView = findViewById(R.id.textId);
        statusTextView = findViewById(R.id.textStatus);
        imgStatusCircle = findViewById(R.id.statusCircle);
        btnVoltar = findViewById(R.id.Voltar);
        respostaFeedbackEditText = findViewById(R.id.respostafeedback);
        radioGroup = findViewById(R.id.radioGroup);
        llAddActivity = findViewById(R.id.llAddActivity);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        btnAdicionarAtividade = findViewById(R.id.btnAdicionarAtividade);

        // Configurar o RadioGroup para controlar a visibilidade do LinearLayout
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButtonSim) {
                llAddActivity.setVisibility(View.VISIBLE); // Mostrar o LinearLayout para adicionar atividade
            } else {
                llAddActivity.setVisibility(View.GONE); // Esconder o LinearLayout se não for selecionado "Sim"
            }
        });

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
        // Configurar o botão enviar atividade
        btnAdicionarAtividade.setOnClickListener(v -> {
            String tituloAtividade = editTextTitle.getText().toString().trim();
            String descricaoAtividade = editTextDescription.getText().toString().trim();

            // Verificar se o título e a descrição da atividade não estão vazios
            if (tituloAtividade.isEmpty() || descricaoAtividade.isEmpty()) {
                Toast.makeText(TratarFeedbackActivity.this, "Preencha o título e a descrição da atividade", Toast.LENGTH_SHORT).show();
                return;
            }

            // Criar um objeto de Atividades com os dados da nova atividade
            Atividades novaAtividade = new Atividades();
            novaAtividade.setTituloAtividade(tituloAtividade);
            novaAtividade.setDescricaoAtividade(descricaoAtividade);
            novaAtividade.setStatusAtividade("Pendente");



            // Chamar a API para criar a atividade
            Call<Atividades> call = HTTPSHelper.getServiceAtividades().createAtividade(novaAtividade);
            call.enqueue(new Callback<Atividades>() {
                @Override
                public void onResponse(Call<Atividades> call, Response<Atividades> response) {
                    if (response.isSuccessful()) {
                        // Atividade criada com sucesso
                        Toast.makeText(TratarFeedbackActivity.this, "Atividade criada com sucesso!", Toast.LENGTH_SHORT).show();

                        // Reiniciar a atividade para limpar os campos
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    } else {
                        // Erro na criação da atividade
                        Toast.makeText(TratarFeedbackActivity.this, "Erro ao criar atividade. Tente novamente.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Atividades> call, Throwable t) {
                    // Erro de rede ou falha na requisição
                    Toast.makeText(TratarFeedbackActivity.this, "Erro de rede ao criar atividade: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Configurar o botão voltar
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(TratarFeedbackActivity.this, FeedbackPendentesActivity.class);
            startActivity(intent);
        });
    }
}
