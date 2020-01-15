package com.example.nukev1java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class NewSneaker extends AppCompatActivity {

    private EditText txtMarca, txtModelo, txtColor, txtColeccionNo, txtTalla, txtPrecio, txtAdquirida, txtNuevas, txtCompra;
    private TextView txtFilePath;
    SQLiteDatabase db = null;

    private static final int REQUEST_SELECT_PHOTO = 100;
    String imgDecodableString;
    ImageView img;
    private String rutaimagen="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sneaker);
        txtMarca= (EditText) findViewById(R.id.txtMarca);
        txtModelo= (EditText) findViewById(R.id.txtModelo);
        txtColor= (EditText) findViewById(R.id.txtColor);
        txtColeccionNo= (EditText) findViewById(R.id.txtColeccionNo);
        txtTalla= (EditText) findViewById(R.id.txtTalla);
        txtPrecio= (EditText) findViewById(R.id.txtPrecio);
        txtAdquirida= (EditText) findViewById(R.id.txtAdquirida);
        txtNuevas= (EditText) findViewById(R.id.txtNueva);
        txtCompra= (EditText) findViewById(R.id.txtCompra);
        img= (ImageView) findViewById(R.id.imageView3);
        txtFilePath= (TextView) findViewById(R.id.txtFilePath);
        checkPermission();
    }

    public void Alta(View view) {

        BaseDatosHelper usdbh = new BaseDatosHelper(this, "SneakersApp", null, 1);
        db = usdbh.getWritableDatabase();

        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("foto", txtFilePath.getText().toString());
        nuevoRegistro.put("marca", txtMarca.getText().toString());
        nuevoRegistro.put("modelo", txtModelo.getText().toString());
        nuevoRegistro.put("color", txtColor.getText().toString());
        nuevoRegistro.put("coleccion_no", txtColeccionNo.getText().toString());
        nuevoRegistro.put("talla", txtTalla.getText().toString());
        nuevoRegistro.put("precio", txtPrecio.getText().toString());
        nuevoRegistro.put("adquirida", txtAdquirida.getText().toString());
        nuevoRegistro.put("nuevas", txtNuevas.getText().toString());
        nuevoRegistro.put("fecha_compra", txtCompra.getText().toString());
        db.insert("Zapatilla", null, nuevoRegistro);

        Intent intent = new Intent(NewSneaker.this, Collection.class);
        startActivity(intent);
    }


    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    //EL BOTÓN ABRIR GALERIA LLAMARA A UN INTENT
    //QUE NOS PERMITIRA PODER ACCEDER A LA
    //GALERIA DE FOTOS DEL TELEFONO
    //POSTERIORMENTE, LO QUE ESTAMOS HACIENDO ES
    //CREAR UNA ACTIVIDAD ONACTIVITYRESULT EN LA
    //QUE ESPERAREMOS A QUE EL USUARIO NOS OFREZCA UNA RESPUESTA
    //SELECCIONANDO UNA FOTO.

    public void pickFromGallery(View view){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,REQUEST_SELECT_PHOTO);
    }



    public void onActivityResult(int requestCode,int resultCode,Intent data){
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case REQUEST_SELECT_PHOTO:
                    //data.getData return the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    // Set the Image in ImageView after decoding the String
                    txtFilePath.setText(imgDecodableString);
                    img.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    break;

            }
    }
}
