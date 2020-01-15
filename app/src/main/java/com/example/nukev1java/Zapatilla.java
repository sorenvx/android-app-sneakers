package com.example.nukev1java;

import android.os.Parcel;
import android.os.Parcelable;

public class Zapatilla implements Parcelable {

    private String marca, modelo, coleccionNo, color, adquirida, foto, talla, precio, nuevas, compra;




    public Zapatilla() {
    }

    public Zapatilla(String modelo, String coleccionNo, String color, String adquirida, String marca, String foto, String talla, String precio, String nuevas, String compra) {
        this.modelo =modelo ;
        this.coleccionNo = coleccionNo;
        this.color = color;
        this.marca = marca;
        this.foto=foto;
        this.talla=talla;
        this.precio=precio;
        this.nuevas=nuevas;
        this.compra=compra;
    }

    protected Zapatilla(Parcel in) {
        marca = in.readString();
        modelo = in.readString();
        coleccionNo = in.readString();
        color = in.readString();
        adquirida = in.readString();
        foto = in.readString();
        talla = in.readString();
        precio = in.readString();
        nuevas = in.readString();
        compra = in.readString();
    }

    public static final Creator<Zapatilla> CREATOR = new Creator<Zapatilla>() {
        @Override
        public Zapatilla createFromParcel(Parcel in) {
            return new Zapatilla(in);
        }

        @Override
        public Zapatilla[] newArray(int size) {
            return new Zapatilla[size];
        }
    };

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColeccionNo() {
        return coleccionNo;
    }

    public void setColeccionNo(String coleccionNo) {
        this.coleccionNo = coleccionNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color= color;
    }

    public String getAdquirida() {
        return adquirida;
    }

    public void setAdquirida(String adquirida){
        this.adquirida=adquirida;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNuevas() {
        return nuevas;
    }

    public void setNuevas(String nuevas) {
        this.nuevas = nuevas;
    }

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(modelo);
        dest.writeString(coleccionNo);
        dest.writeString(color);
        dest.writeString(adquirida);
        dest.writeString(foto);
        dest.writeString(talla);
        dest.writeString(precio);
        dest.writeString(nuevas);
        dest.writeString(compra);
    }
}
