package com.example.pirateriafeedbacks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pirateriafeedbacks.R;
import com.example.pirateriafeedbacks.model.Atividades;
import com.example.pirateriafeedbacks.model.Feedback;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AtividadeCustomAdapter extends RecyclerView.Adapter<AtividadeCustomAdapter.CustomViewHolder> {

    private List<Atividades> dataList;
    private Context context;

    public AtividadeCustomAdapter(Context context, List<Atividades> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getTituloAtividade());
        holder.txtTitle.setText(dataList.get(position).getDescricaoAtividade());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}