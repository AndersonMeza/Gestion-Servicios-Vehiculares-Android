package com.example.hito1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class VentanaDeVehiculosGUI extends AppCompatActivity {

    //public EncargadoDP encargadoDP=new EncargadoDP();
    //public EncargadoDM encargadoDM = new EncargadoDM();
    //TextView textoBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_de_vehiculos);
        //encargadoDP.setCodigo(Integer.parseInt(getIntent().getStringExtra("codigo")));
        //encargadoDP.setUsuario(getIntent().getStringExtra("usuario"));
        //encargadoDP.setNombre("Anderson");
        //encargadoDP.setApellido(getIntent().getStringExtra("apellido"));
        //textoBienvenida = (TextView) findViewById(R.id.textoBienvenida);
        //textoBienvenida.setText("Bienvenido "+encargadoDP.nombre);
    }
}
