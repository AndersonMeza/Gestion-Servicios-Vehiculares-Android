package com.example.hito1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentanaDeRegistro extends AppCompatActivity {

    RelacionencvehserDP relacionencvehserDP=new RelacionencvehserDP();
    RelacionencvehserDM relacionencvehserDM=new RelacionencvehserDM();
    TextView ingresoNombreServicio;
    TextView ingresoFecha;
    Button botonIngresar;
    String nombreServicio;
    int codigoEncargado;
    int codigoVehiculo;
    int codigoServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_de_registro);

        nombreServicio=getIntent().getStringExtra("nombreServicio");
        codigoEncargado=getIntent().getIntExtra("codigoEncargado",0);
        codigoVehiculo=getIntent().getIntExtra("codigoVehiculo",0);
        codigoServicio=getIntent().getIntExtra("codigoServicio",0);
        relacionencvehserDP.setNombreServicio(nombreServicio);
        relacionencvehserDP.setCodigoEncargado(codigoEncargado);
        relacionencvehserDP.setCodigoVehiculo(codigoVehiculo);
        relacionencvehserDP.setCodigoServicio(codigoServicio);
        ingresoNombreServicio =(TextView) findViewById(R.id.textoNombreServicioR);
        ingresoFecha =(TextView) findViewById(R.id.textoFecha);
        botonIngresar =(Button) findViewById(R.id.botonCrearNuevoRegistro);
        cargarServicio();

        ingresoFecha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String ingreso_fecha=ingresoFecha.getText().toString();
                DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    Date fecha = formato.parse(ingreso_fecha);
                    relacionencvehserDP.setFecha(fecha);
                    botonIngresar.setEnabled(true);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void cargarServicio()
    {
        ingresoNombreServicio.setText(nombreServicio);
    }
    public void Registrar(View view)
    {
        int nuevoCodigo=relacionencvehserDM.getCodigo(this);
        relacionencvehserDP.setCodigo(nuevoCodigo);
        relacionencvehserDM.Crear(relacionencvehserDP,this);
        ingresoFecha.setText("");
    }


}
