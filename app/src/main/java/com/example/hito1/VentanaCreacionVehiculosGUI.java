package com.example.hito1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VentanaCreacionVehiculosGUI extends AppCompatActivity {

    public VehiculoDM vehiculoDM = new VehiculoDM();
    public VehiculoDP vehiculoDP=new VehiculoDP();
    private EditText ingresoPlaca;
    private EditText ingresoMarca;
    private EditText ingresoColor;
    private EditText ingresoTipo;
    private EditText ingresoKilometraje;
    private Button botonIngresar;
    private int codigoEncargado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_creacion_vehiculos_gui);

        ingresoPlaca =(EditText)findViewById(R.id.textoPlaca);
        ingresoMarca =(EditText)findViewById(R.id.textoMarca);
        ingresoColor =(EditText)findViewById(R.id.textoColor);
        ingresoTipo =(EditText)findViewById(R.id.textoTipo);
        ingresoKilometraje =(EditText)findViewById(R.id.textoKilometraje);
        botonIngresar=(Button)findViewById(R.id.botonNuevoVehiculo);
        codigoEncargado = getIntent().getIntExtra("codigoEncargado",0);

        ingresoPlaca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String placa=ingresoPlaca.getText().toString();
                vehiculoDP.setPlaca(placa);
                boolean existeVehiculo=vehiculoDP.verificarPlaca(VentanaCreacionVehiculosGUI.this,codigoEncargado);

                if(!existeVehiculo &&  !placa.isEmpty() && placa.indexOf(' ')<0)
                {
                    ingresoMarca.setEnabled(true);
                }
                else
                {
                    Toast.makeText(VentanaCreacionVehiculosGUI.this,"Placa no valido",Toast.LENGTH_SHORT).show();
                    ingresoMarca.setEnabled(false);
                    ingresoColor.setEnabled(false);
                    ingresoTipo.setEnabled(false);
                    ingresoKilometraje.setEnabled(false);
                    botonIngresar.setEnabled(false);
                }
            }
        });



        ingresoMarca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String marca = ingresoMarca.getText().toString();
                vehiculoDP.setMarca(marca);
                if (ingresoMarca.getText().toString().isEmpty() ){
                    botonIngresar.setEnabled(false);
                    ingresoKilometraje.setEnabled(false);
                    ingresoTipo.setEnabled(false);
                    ingresoColor.setEnabled(false);
                }
                else
                    ingresoColor.setEnabled(true);
            }
        });

        ingresoColor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String color=ingresoColor.getText().toString();
                vehiculoDP.setColor(color);

                if(ingresoColor.getText().toString().isEmpty()) {

                    botonIngresar.setEnabled(false);
                    ingresoTipo.setEnabled(false);
                    ingresoKilometraje.setEnabled(false);
                }
                else
                    ingresoTipo.setEnabled(true);
            }
        });

        ingresoTipo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String tipo=ingresoTipo.getText().toString();
                vehiculoDP.setTipo(tipo);

                if(ingresoTipo.getText().toString().isEmpty()) {

                    botonIngresar.setEnabled(false);
                    ingresoKilometraje.setEnabled(false);
                }
                else
                    ingresoKilometraje.setEnabled(true);
            }
        });

        ingresoKilometraje.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if(ingresoKilometraje.getText().toString().length()==0) {
                    botonIngresar.setEnabled(false);
                }
                else
                {
                    int kilometraje=Integer.parseInt(ingresoKilometraje.getText().toString());
                    vehiculoDP.setKilometraje(kilometraje);
                    botonIngresar.setEnabled(true);
                }

            }
        });


    }


    public void RegistrarV(View view)
    {
        int nuevoCodigo=vehiculoDM.getCodigo(this);
        vehiculoDP.setCodigo(nuevoCodigo);
        vehiculoDP.codigoEncargado=codigoEncargado;

        vehiculoDM.Crear(vehiculoDP,this);

        ingresoMarca.setText("Marca");
        ingresoPlaca.setText("Placa");
        ingresoColor.setText("Color");
        ingresoTipo.setText("Tipo Vehiculo");
        ingresoKilometraje.setText("");


        ingresoMarca.setEnabled(false);
        ingresoColor.setEnabled(false);
        ingresoKilometraje.setEnabled(false);
        ingresoTipo.setEnabled(false);
        botonIngresar.setEnabled(false);

    }
}
