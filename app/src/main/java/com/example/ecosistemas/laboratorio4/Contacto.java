package com.example.ecosistemas.laboratorio4;

import android.widget.ImageView;

public class Contacto {

    private String nombre;
    private String genero;
    private String telefono;

    public Contacto(String nombre, String telefono, String genero) {
        this.nombre = nombre;
        this.genero = genero;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getTelefono() {
        return telefono;
    }





}
