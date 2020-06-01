package com.example.hito1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class VentanaModificarVehiculosGUI extends AppCompatActivity {

    VehiculoDM vehiculoDM = new VehiculoDM();
    VehiculoDP vehiculoDP = new VehiculoDP();
    private EditText ingresoPlaca;
    private EditText ingresoMarca;
    private EditText ingresoColor;
    private EditText ingresoTipo;
    private EditText ingresoKilometraje;
    private Button botonModificar;
    private Button botonEliminar;
    private String PlacaAntigua;



    /*
    "placaVehiculo",vehiculoDP.placa);
            intent.putExtra("codigoEncargado"
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_modificar_vehiculos_gui);

        String placa=getIntent().getStringExtra("placaVehiculo");
        int codigoEncargado=getIntent().getIntExtra("codigoEncargado",0);
        List<String> vehiculoDatos = vehiculoDM.Consultar(0,placa,VentanaModificarVehiculosGUI.this,codigoEncargado);

        vehiculoDP.setCodigo(Integer.parseInt(vehiculoDatos.get(0)));
        vehiculoDP.setPlaca(vehiculoDatos.get(1));
        vehiculoDP.setMarca(vehiculoDatos.get(2));
        vehiculoDP.setColor(vehiculoDatos.get(3));
        vehiculoDP.setTipo(vehiculoDatos.get(4));
        vehiculoDP.setKilometraje(Integer.parseInt(vehiculoDatos.get(5)));
        vehiculoDP.codigoEncargado=Integer.parseInt(vehiculoDatos.get(6));


        PlacaAntigua=vehiculoDP.placa;

        ingresoPlaca =(EditText)findViewById(R.id.textoPlaca2);
        ingresoMarca =(EditText)findViewById(R.id.textoMarca2);
        ingresoColor =(EditText)findViewById(R.id.textoColor2);
        ingresoTipo =(EditText)findViewById(R.id.textoTipo2);
        ingresoKilometraje =(EditText)findViewById(R.id.textoKilometraje2);
        botonModificar=(Button)findViewById(R.id.botonNuevoVehiculo2);
        botonEliminar=(Button)findViewById(R.id.botonEliminarVehiculo);


        ingresoPlaca.setText(vehiculoDP.placa);
        ingresoMarca.setText(vehiculoDP.marca);
        ingresoColor.setText(vehiculoDP.color);
        ingresoTipo.setText(vehiculoDP.tipo);
        ingresoKilometraje.setText(vehiculoDP.kilometraje+"");

        ingresoPlaca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(ingresoPlaca.getText().toString()!=PlacaAntigua)
                {
                    String placa=ingresoPlaca.getText().toString();
                    vehiculoDP.setPlaca(placa);
                    boolean existeVehiculo=vehiculoDP.verificarPlaca(VentanaModificarVehiculosGUI.this,vehiculoDP.codigoEncargado);

                    if(!existeVehiculo &&  !placa.isEmpty() && placa.indexOf(' ')<0)
                    {
                        ingresoMarca.setEnabled(true);
                    }
                    else
                    {
                        Toast.makeText(VentanaModificarVehiculosGUI.this,"Placa no valido",Toast.LENGTH_SHORT).show();
                        ingresoMarca.setEnabled(false);
                        ingresoColor.setEnabled(false);
                        ingresoTipo.setEnabled(false);
                        ingresoKilometraje.setEnabled(false);
                        botonModificar.setEnabled(false);
                    }
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
                    botonModificar.setEnabled(false);
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

                    botonModificar.setEnabled(false);
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

                    botonModificar.setEnabled(false);
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
                    botonModificar.setEnabled(false);
                }
                else
                {
                    botonModificar.setEnabled(true);
                    int kilometraje=Integer.parseInt(ingresoKilometraje.getText().toString());
                    vehiculoDP.setKilometraje(kilometraje);
                }

            }
        });
    }

    public void ModificarV(View view)
    {
        vehiculoDM.Modificar(vehiculoDP,VentanaModificarVehiculosGUI.this);
        String placaVehiculo = vehiculoDP.placa;
        Intent intent = new Intent(VentanaModificarVehiculosGUI.this,VentanaDeVehiculoGUI.class);
        intent.putExtra("placaVehiculo",placaVehiculo);
        intent.putExtra("codigoEncargado",vehiculoDP.codigoEncargado);
        startActivity(intent);
    }

    public void EliminarV(View view)
    {
        final CharSequence[] preguntas = {"si","no"};
        final AlertDialog.Builder preguntaEliminar = new AlertDialog.Builder(VentanaModificarVehiculosGUI.this);
        preguntaEliminar.setTitle("¿En verdad quiere eliminar?");
        preguntaEliminar.setItems(preguntas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(preguntas[which].equals("si"))
                {
                    vehiculoDM.Eliminar(vehiculoDP,VentanaModificarVehiculosGUI.this);
                    Intent intent = new Intent(VentanaModificarVehiculosGUI.this,VentanaIngresoGUI.class);
                    startActivity(intent);
                    onBackPressed();
                    onBackPressed();
                }
                else
                {

                }
            }
        });
        preguntaEliminar.show();
    }
}
