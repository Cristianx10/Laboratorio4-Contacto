package com.example.ecosistemas.laboratorio4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public class ContactosAdapter extends BaseAdapter {

    private Activity activity;
    private LinkedList<Contacto> contactos;

    public ContactosAdapter(Activity activity) {
        this.activity = activity;
        contactos = new LinkedList<>();
    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();

        View contacto = inflater.inflate(R.layout.contacto, null, false);

        ImageButton llamar = contacto.findViewById(R.id.btn_llamar);
        ImageButton eliminar = contacto.findViewById(R.id.btn_eliminar);
        ImageView icono = contacto.findViewById(R.id.iv_genero);

        TextView et_nombre = contacto.findViewById(R.id.tv_nombre);
        final TextView et_telefono = contacto.findViewById(R.id.tv_telefono);

        et_nombre.setText(contactos.get(position).getNombre());
        et_telefono.setText(contactos.get(position).getTelefono());

        if (contactos.get(position).getGenero().equals("femenino")) {
            icono.setImageResource(R.drawable.femenino);
        } else {
            icono.setImageResource(R.drawable.maculino);
        }

        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numbero = et_telefono.getText().toString();

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + numbero));

                int permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    Log.i("Mensaje", "No se tiene permiso para realizar llamadas telefónicas.");
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 225);
                } else {
                    Log.i("Mensaje", "Se tiene permiso para realizar llamadas!");
                }

                activity.startActivity(intent);






            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contactos.remove(position);
                notifyDataSetChanged();
            }
        });

        return contacto;
    }

    public void agregar(Contacto contact){
        contactos.addFirst(contact);
        notifyDataSetChanged();
    }
}
