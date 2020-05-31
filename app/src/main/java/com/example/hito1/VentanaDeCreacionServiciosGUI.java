package com.example.hito1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VentanaDeCreacionServiciosGUI extends AppCompatActivity {

    public ServicioDM servicioDM = new ServicioDM();
    public ServicioDP servicioDP=new ServicioDP();
    private EditText ingresoNombre;
    private EditText ingresoKmCadaCambio;
    private EditText ingresoUltimoCambio;
    private Button botonIngresar;
    private int codigoVehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_de_creacion_servicios_gui);

        ingresoNombre =(EditText)findViewById(R.id.textoNombreS);
        ingresoKmCadaCambio =(EditText)findViewById(R.id.textoKmCadaCambio);
        ingresoUltimoCambio =(EditText)findViewById(R.id.textoKilometrajeUltimoCambio);
        botonIngresar=(Button)findViewById(R.id.botonCrearNuevoServicio);
        codigoVehiculo = getIntent().getIntExtra("codigoVehiculo",0);

        ingresoNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String nombre=ingresoNombre.getText().toString();
                servicioDP.setNombre(nombre);
                boolean existeServicio=servicioDP.verificarNombre(VentanaDeCreacionServiciosGUI.this,codigoVehiculo);

                if(!existeServicio &&  !nombre.isEmpty())
                {
                    ingresoKmCadaCambio.setEnabled(true);
                }
                else
                {
                    Toast.makeText(VentanaDeCreacionServiciosGUI.this,"Nombre no valido",Toast.LENGTH_SHORT).show();
                    ingresoKmCadaCambio.setEnabled(false);
                    ingresoUltimoCambio.setEnabled(false);
                    botonIngresar.setEnabled(false);
                }
            }
        });

        ingresoKmCadaCambio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (ingresoKmCadaCambio.getText().toString().length()==0 ){


                    botonIngresar.setEnabled(false);
                    ingresoUltimoCambio.setEnabled(false);
                }
                else
                {
                    int kmCadaCambio =Integer.parseInt(ingresoKmCadaCambio.getText().toString());
                    servicioDP.setKmCadaCambio(kmCadaCambio);
                    ingresoUltimoCambio.setEnabled(true);
                }

            }
        });

        ingresoUltimoCambio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if(ingresoUltimoCambio.getText().toString().isEmpty()) {

                    botonIngresar.setEnabled(false);
                }
                else
                {
                    int kmUltimoCambio=Integer.parseInt(ingresoUltimoCambio.getText().toString());
                    servicioDP.setKmUltimoCambio(kmUltimoCambio);
                    botonIngresar.setEnabled(true);
                }

            }
        });
    }

    public void RegistrarS(View view)
    {
        int nuevoCodigo=servicioDM.getCodigo(this);
        servicioDP.setCodigo(nuevoCodigo);
        servicioDP.codigoVehiculo=codigoVehiculo;

        servicioDM.Crear(servicioDP,this);

        ingresoKmCadaCambio.setText("");
        ingresoNombre.setText("Nombre");
        ingresoUltimoCambio.setText("");


        ingresoKmCadaCambio.setEnabled(false);
        ingresoUltimoCambio.setEnabled(false);
        botonIngresar.setEnabled(false);
    }
}
