package com.example.pirateriafeedbacks.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pirateriafeedbacks.Activities.TratarAtividadeActivity;
import com.example.pirateriafeedbacks.R;
import com.example.pirateriafeedbacks.model.Atividades;

import java.util.List;

public class AtividadeCustomAdapter extends RecyclerView.Adapter<AtividadeCustomAdapter.CustomViewHolder> {
    private List<Atividades> dataList;
    private Context context;

    public AtividadeCustomAdapter(Context context, List<Atividades> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public AtividadeCustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.feedbacks_custom_row, parent, false);
        return new AtividadeCustomAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AtividadeCustomAdapter.CustomViewHolder holder, int position) {
        Atividades item = dataList.get(position);

        holder.txtTitle.setText(item.getTituloAtividade());
        holder.txtDescription.setText(item.getDescricaoAtividade());
        holder.txtID.setText(String.valueOf(item.getIdAtividade()));
        holder.txtStatus.setText(item.getStatusAtividade());

        // Definir a cor do texto e do círculo com base no status do feedback
        if (item.getStatusAtividade().equalsIgnoreCase("Pendente")) {
            holder.txtStatus.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
            holder.imgStatusCircle.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_red)); // Círculo vermelho
        } else if (item.getStatusAtividade().equalsIgnoreCase("Concluída")) {
            holder.txtStatus.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark));
            holder.imgStatusCircle.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_green)); // Círculo verde
        }

        // Configurar o OnClickListener para o item da RecyclerView
        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Atividades clickedAtividade = dataList.get(pos);
                Intent intent = new Intent(context, TratarAtividadeActivity.class);
                intent.putExtra("Atividade", clickedAtividade); // Passar o feedback para a próxima Activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtDescription;
        TextView txtID;
        TextView txtStatus;
        ImageView imgStatusCircle;

        CustomViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            txtDescription = itemView.findViewById(R.id.description);
            txtID = itemView.findViewById(R.id.idfeedback);
            txtStatus = itemView.findViewById(R.id.status);
            imgStatusCircle = itemView.findViewById(R.id.status_circle);
        }
    }
}
