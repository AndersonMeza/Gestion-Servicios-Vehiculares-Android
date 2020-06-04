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

public class VentanaModificacionRegistroGUI extends AppCompatActivity {

    TextView nombreServicio;
    TextView fechaRegistro;
    Button botonModifcar;
    RelacionencvehserDP relacionencvehserDP=new RelacionencvehserDP();
    RelacionencvehserDM relacionencvehserDM=new RelacionencvehserDM();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_modificacion_registro_gui);

        nombreServicio = (TextView) findViewById(R.id.textoModificarServicioR);
        fechaRegistro = (TextView) findViewById(R.id.TextoFechaModificar);
        botonModifcar=(Button) findViewById(R.id.BotonModificarRegistro);

        relacionencvehserDP.setCodigo(getIntent().getIntExtra("codigo",0));
        relacionencvehserDP.setNombreServicio(getIntent().getStringExtra("nombreServicio"));
        relacionencvehserDP.setCodigoEncargado(getIntent().getIntExtra("codigoEncargado",0));
        relacionencvehserDP.setCodigoVehiculo(getIntent().getIntExtra("codigoVehiculo",0));
        relacionencvehserDP.setCodigoServicio(getIntent().getIntExtra("codigoServicio",0));

        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date fecha = formato.parse(getIntent().getStringExtra("fecha"));
            relacionencvehserDP.setFecha(fecha);
            cargarRegistro();
        }
        catch (Exception e)
        {
            Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

        fechaRegistro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String ingreso_fecha=fechaRegistro.getText().toString();
                DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    Date fecha = formato.parse(ingreso_fecha);
                    relacionencvehserDP.setFecha(fecha);
                    botonModifcar.setEnabled(true);
                    Toast.makeText(getBaseContext(),"Fecha valida",Toast.LENGTH_SHORT).show();

                } catch (ParseException e) {
                    botonModifcar.setEnabled(false);
                }

            }
        });

    }

    public void cargarRegistro()
    {
        nombreServicio.setText(relacionencvehserDP.nombreServicio);
        fechaRegistro.setText(getIntent().getStringExtra("fecha")+"");
    }

    public void ModificarRegistro(View view)
    {
        relacionencvehserDM.Modificar(relacionencvehserDP,VentanaModificacionRegistroGUI.this);
        Intent intent = new Intent(VentanaModificacionRegistroGUI.this, VentanaDeRegistrosGUI.class);
        intent.putExtra("codigoEncargado",relacionencvehserDP.codigoEncargado);
        intent.putExtra("codigoVehiculo",relacionencvehserDP.codigoVehiculo);
        startActivity(intent);
    }
}
