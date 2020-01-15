package com.example.nukev1java;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private List<Zapatilla> listaZapatillas;
    private OnNoteListener OnNoteListener;


    public Adaptador(List<Zapatilla> Zapatilla, OnNoteListener onNoteListener) {

        this.listaZapatillas = Zapatilla;
        this.OnNoteListener= onNoteListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_datos, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, OnNoteListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String modelo = listaZapatillas.get(position).getModelo();
        holder.txtModelo.setText(modelo);
        String coleccion = listaZapatillas.get(position).getColeccionNo();
        holder.txtColeccionNo.setText(coleccion);
        String color = listaZapatillas.get(position).getColor();
        holder.txtColor.setText(color);
        String adquirida = listaZapatillas.get(position).getAdquirida();
        holder.txtAdquirida.setText(adquirida);
        listaZapatillas.get(position).getMarca();
        listaZapatillas.get(position).getTalla();
        listaZapatillas.get(position).getCompra();
        listaZapatillas.get(position).getPrecio();
        String foto = listaZapatillas.get(position).getFoto();
        Bitmap bmImg = BitmapFactory.decodeFile(foto);
        holder.imageCardView.setImageBitmap(bmImg);

    }

    @Override
    public int getItemCount() {
        return listaZapatillas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private TextView txtModelo, txtColeccionNo, txtColor, txtAdquirida;
        private ImageView imageCardView;
        OnNoteListener onNoteListener;

        public ViewHolder(View v, OnNoteListener onNoteListener) {
            super(v);
            txtModelo = (TextView) v.findViewById(R.id.modelo);
            txtColeccionNo = (TextView) v.findViewById(R.id.coleccionNo);
            txtColor = (TextView) v.findViewById(R.id.color);
            txtAdquirida= (TextView) v.findViewById(R.id.adquirida);
            imageCardView = (ImageView) v.findViewById(R.id.imageCardView);
            this.onNoteListener=onNoteListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }


}