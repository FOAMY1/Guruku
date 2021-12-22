package com.foamy.guruku;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.ViewHolder> {
    Context context;
    private ArrayList<Materi> listMateri;

    public MateriAdapter(ArrayList<Materi> listMateri) {
        this.listMateri = listMateri;
    }

    @NonNull
    @Override
    public MateriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ViewHolder(inflater.inflate(R.layout.materilayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MateriAdapter.ViewHolder holder, int position) {
        Materi materi = listMateri.get(position);
        holder.NamaMateri.setText(materi.getNamaMateri());
        holder.Id_gambar.setImageResource(materi.getIdGambar());
        holder.itemView.setOnClickListener(v -> {
            Intent inton = new Intent(context, SoalPage.class);
            inton.putExtra("jeneng",materi.getNamaMateri());
            inton.putExtra("idgmbr", materi.getIdGambar());
            inton.putExtra("pilihangan",materi.getPilgan());
            context.startActivities(new Intent[]{inton});
        });
    }

    @Override
    public int getItemCount() {
        return listMateri.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView NamaMateri;
        public ImageView Id_gambar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NamaMateri = itemView.findViewById(R.id.TxtNamaMateri);
            Id_gambar = itemView.findViewById(R.id.ImgGambarMateri);
        }
    }
}
