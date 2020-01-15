package com.example.nukev1java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.internal.NavigationMenu;

public class SneakerDetails extends AppCompatActivity {

    private TextView marca, talla, modelo, coleccionNo, compra, adquirida, precio, nuevas, color;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sneaker_details);
        marca= (TextView) findViewById(R.id.txtMarca);
        talla= (TextView) findViewById(R.id.txtTalla);
        modelo= (TextView) findViewById(R.id.txtModelo);
        coleccionNo= (TextView) findViewById(R.id.txtColeccionNo);
        compra= (TextView) findViewById(R.id.txtCompra);
        adquirida= (TextView) findViewById(R.id.txtAdquirida);
        precio= (TextView) findViewById(R.id.txtPrecio);
        nuevas= (TextView) findViewById(R.id.txtNuevas);
        color= (TextView) findViewById(R.id.txtColor);
        foto=(ImageView) findViewById(R.id.fotoZapa);
        Intent intent = getIntent();
        Zapatilla zapas = intent.getParcelableExtra("zapatilla");

        String marcaP = zapas.getMarca();
        marca.setText(marcaP);
        String tallaP = zapas.getTalla();
        talla.setText(tallaP);
        String modeloP = zapas.getModelo();
        modelo.setText(modeloP);
        String coleccionP = zapas.getColeccionNo();
        coleccionNo.setText(coleccionP);
        String compraP = zapas.getCompra();
        compra.setText(compraP);
        String adquiridaP = zapas.getAdquirida();
        adquirida.setText(adquiridaP);
        String precioP = zapas.getPrecio();
        precio.setText(precioP);
        String nuevasP = zapas.getNuevas();
        nuevas.setText(nuevasP);
        String colorP = zapas.getColor();
        color.setText(colorP);
        String fotoP = zapas.getFoto();
        Bitmap bmImg = BitmapFactory.decodeFile(fotoP);
        foto.setImageBitmap(bmImg);
    }
}
