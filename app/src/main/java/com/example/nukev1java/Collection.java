package com.example.nukev1java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Collection extends AppCompatActivity implements Adaptador.OnNoteListener {
    private Button salir, botonSneaker, botonMenuL;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    ArrayList<Zapatilla> Zapatilla = new ArrayList<>();
    private RecyclerView objRecyclerView;
    private RecyclerView.Adapter miAdapter;
    SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        salir = (Button) findViewById(R.id.button);
        botonSneaker = (Button) findViewById(R.id.button2);


        objRecyclerView = findViewById(R.id.RV1);

        BaseDatosHelper usdbh = new BaseDatosHelper(getApplicationContext(), "SneakersApp", null, 1);
        db= usdbh.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Zapatilla", null);

        if (c.moveToFirst()){
            do{
                Zapatilla unaZapatilla = new Zapatilla();
                unaZapatilla.setFoto(c.getString(0));
                unaZapatilla.setMarca(c.getString(1));
                unaZapatilla.setModelo(c.getString(2));
                unaZapatilla.setColor(c.getString(3));
                unaZapatilla.setColeccionNo(c.getString(4));
                unaZapatilla.setTalla(c.getString(5));
                unaZapatilla.setPrecio(c.getString(6));
                unaZapatilla.setAdquirida(c.getString(7));
                unaZapatilla.setNuevas(c.getString(8));
                unaZapatilla.setCompra(c.getString(9));
                Zapatilla.add(unaZapatilla);
            }while (c.moveToNext());
        }
        miAdapter = new Adaptador(Zapatilla, this);
        objRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        objRecyclerView.setAdapter(miAdapter);
        miAdapter.notifyDataSetChanged();

    }

    public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }

    public void newSneaker(View view) {
        Intent newSneaker = new Intent(this, NewSneaker.class);
        startActivity(newSneaker);
    }


    @Override
    public void onNoteClick(int position) {
        Zapatilla.get(position);
        Intent intent = new Intent(this, SneakerDetails.class);
        intent.putExtra("zapatilla", Zapatilla.get(position));
        startActivity(intent);
    }
}
