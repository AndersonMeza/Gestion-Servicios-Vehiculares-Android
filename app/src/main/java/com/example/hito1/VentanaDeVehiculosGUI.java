package com.example.hito1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class VentanaDeVehiculosGUI extends AppCompatActivity {

    public EncargadoDP encargadoDP=new EncargadoDP();
    public EncargadoDM encargadoDM = new EncargadoDM();
    TextView textoBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_de_vehiculos);

        String usuario=getIntent().getStringExtra("usuarioEncargado");
        List<String> encargadoDatos = encargadoDM.Consultar(0,usuario,VentanaDeVehiculosGUI.this);

        encargadoDP.setCodigo(Integer.parseInt(encargadoDatos.get(0)));
        encargadoDP.setUsuario(encargadoDatos.get(1));
        encargadoDP.setNombre(encargadoDatos.get(2));
        encargadoDP.setApellido(encargadoDatos.get(3));

        textoBienvenida = (TextView) findViewById(R.id.textoBienvenida);
        textoBienvenida.setText("Bienvenido "+encargadoDatos.get(2));
    }
}
