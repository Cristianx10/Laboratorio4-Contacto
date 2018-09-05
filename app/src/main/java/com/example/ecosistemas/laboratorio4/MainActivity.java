package com.example.ecosistemas.laboratorio4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_agregar;
    private EditText et_nombre;
    private EditText et_telefono;
    private Switch s_genero;
    private ListView lv_contacto;

    private ContactosAdapter contactosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactosAdapter = new ContactosAdapter(this);
        lv_contacto = findViewById(R.id.lv_contacto);
        lv_contacto.setAdapter(contactosAdapter);

        btn_agregar = findViewById(R.id.btn_agregar);
        et_nombre = findViewById(R.id.et_nombre);
        et_telefono = findViewById(R.id.et_telefono);
        s_genero = findViewById(R.id.s_genero);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = et_nombre.getText().toString();
                String telefono = et_telefono.getText().toString();
                String genero = "";

                if(s_genero.isChecked()){
                    genero = "femenino";
                }else{
                    genero = "masculino";
                }

                Contacto c = new Contacto(nombre, telefono, genero);

                if(!nombre.equals("") && !telefono.equals("")) {
                    contactosAdapter.agregar(c);
                }else{
                    Toast.makeText(MainActivity.this, "Ingrese datos validos por favor", Toast.LENGTH_SHORT).show();
                }


                et_nombre.setText("");
                et_telefono.setText("");


                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                v.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);


            }
        });

    }
}
