package com.example.hito1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class VentanaModificacionServiciosGUI extends AppCompatActivity {

    VehiculoDP vehiculoDP = new VehiculoDP();
    VehiculoDM vehiculoDM = new VehiculoDM();
    Spinner comboboxEncargados;
    List<String> servicios;
    boolean primera=true;

    public ServicioDM servicioDM = new ServicioDM();
    public ServicioDP servicioDP=new ServicioDP();
    private EditText ingresoNombre;
    private EditText ingresoKmCadaCambio;
    private EditText ingresoUltimoCambio;
    private Button botonModificar;
    private Button botonEliminar;
    private String NombreAntiguo;
    private int codigoVehiculo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_modificacion_servicios_gui);



        ingresoNombre =(EditText)findViewById(R.id.textoNombreS2);
        ingresoKmCadaCambio =(EditText)findViewById(R.id.textoKmCadaCambio2);
        ingresoUltimoCambio =(EditText)findViewById(R.id.textoKilometrajeUltimoCambio2);
        botonModificar=(Button)findViewById(R.id.botonCrearNuevoServicio2);
        botonEliminar=(Button)findViewById(R.id.botonEliminarServicio);


        String placa=getIntent().getStringExtra("placaVehiculo");
        int codigoEncargado=getIntent().getIntExtra("codigoEncargado",0);
        List<String> vehiculoDatos = vehiculoDM.Consultar(0,placa,VentanaModificacionServiciosGUI.this,codigoEncargado);
        vehiculoDP.setCodigo(Integer.parseInt(vehiculoDatos.get(0)));
        vehiculoDP.setPlaca(vehiculoDatos.get(1));
        vehiculoDP.setMarca(vehiculoDatos.get(2));
        vehiculoDP.setColor(vehiculoDatos.get(3));
        vehiculoDP.setTipo(vehiculoDatos.get(4));
        vehiculoDP.setKilometraje(Integer.parseInt(vehiculoDatos.get(5)));
        vehiculoDP.codigoEncargado=Integer.parseInt(vehiculoDatos.get(6));
        codigoVehiculo =vehiculoDP.getCodigo();

        comboboxEncargados = (Spinner) findViewById(R.id.comboboxServicios);
        servicios=servicioDM.Consultar(0,"",this, vehiculoDP.codigo);

        for(int i=0;i<servicios.size();i++)
        {
            int pos=servicios.get(i).indexOf(" ");
            servicios.set(i,servicios.get(i).substring(pos+1));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, servicios);
        comboboxEncargados.setAdapter(adapter);

        comboboxEncargados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(servicios.size()<1)
                {

                }
                else
                {
                    String nombre=servicios.get(position);
                    List<String> serviciosDatos = servicioDM.Consultar(0,nombre,VentanaModificacionServiciosGUI.this,codigoVehiculo);

                    servicioDP.setCodigo(Integer.parseInt(serviciosDatos.get(0)));
                    servicioDP.setNombre(serviciosDatos.get(1));
                    servicioDP.setKmCadaCambio(Integer.parseInt(serviciosDatos.get(2)));
                    servicioDP.setKmUltimoCambio(Integer.parseInt(serviciosDatos.get(3)));
                    servicioDP.codigoVehiculo=Integer.parseInt(serviciosDatos.get(4));
                    NombreAntiguo=nombre;

                    ingresoNombre.setText(servicioDP.nombre);
                    ingresoKmCadaCambio.setText(servicioDP.kmCadaCambio+"");
                    ingresoUltimoCambio.setText(servicioDP.kmUltimoCambio+"");

                    ingresoNombre.setEnabled(true);
                    ingresoKmCadaCambio.setEnabled(true);
                    ingresoUltimoCambio.setEnabled(true);
                    botonModificar.setEnabled(true);
                    botonEliminar.setEnabled(true);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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
                boolean existeServicio=false;
                if(nombre.compareTo(NombreAntiguo)!=0)
                {
                    existeServicio=servicioDP.verificarNombre(VentanaModificacionServiciosGUI.this,codigoVehiculo);
                }


                if(!existeServicio &&  !nombre.isEmpty() )
                {
                    ingresoKmCadaCambio.setEnabled(true);
                }
                else
                {
                    Toast.makeText(VentanaModificacionServiciosGUI.this,"Nombre no Valido",Toast.LENGTH_SHORT).show();
                    ingresoKmCadaCambio.setEnabled(false);
                    ingresoUltimoCambio.setEnabled(false);
                    botonModificar.setEnabled(false);
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


                    botonModificar.setEnabled(false);
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

                    botonModificar.setEnabled(false);
                }
                else
                {
                    int kmUltimoCambio=Integer.parseInt(ingresoUltimoCambio.getText().toString());
                    servicioDP.setKmUltimoCambio(kmUltimoCambio);
                    botonModificar.setEnabled(true);
                }

            }
        });
    }


    public void ModificarS(View view)
    {
        servicioDM.Modificar(servicioDP,VentanaModificacionServiciosGUI.this);
        String nombreServicio = servicioDP.nombre;
        Intent intent = new Intent(VentanaModificacionServiciosGUI.this,VentanaDeVehiculoGUI.class);
        intent.putExtra("placaVehiculo",getIntent().getStringExtra("placaVehiculo"));
        intent.putExtra("codigoEncargado",getIntent().getIntExtra("codigoEncargado",0));
        startActivity(intent);
    }

    public void EliminarS(View view)
    {
        final CharSequence[] preguntas = {"si","no"};
        final AlertDialog.Builder preguntaEliminar = new AlertDialog.Builder(VentanaModificacionServiciosGUI.this);
        preguntaEliminar.setTitle("¿En verdad quiere eliminar?");
        preguntaEliminar.setItems(preguntas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(preguntas[which].equals("si"))
                {
                    servicioDM.Eliminar(servicioDP,VentanaModificacionServiciosGUI.this);
                    Intent intent = new Intent(VentanaModificacionServiciosGUI.this,VentanaIngresoGUI.class);
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
