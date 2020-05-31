package com.example.hito1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class VentanaDeVehiculoGUI extends AppCompatActivity {

    VehiculoDP vehiculoDP = new VehiculoDP();
    VehiculoDM vehiculoDM = new VehiculoDM();
    private TextView textoPlaca;

    public  ServicioDP servicioDP=new ServicioDP();
    public ServcioDM servcioDM=new ServcioDM();
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_de_vehiculo_gui);

        String placa=getIntent().getStringExtra("placaVehiculo");
        int codigoEncargado=getIntent().getIntExtra("codigoEncargado",0);
        List<String> vehiculoDatos = vehiculoDM.Consultar(0,placa,VentanaDeVehiculoGUI.this,codigoEncargado);

        vehiculoDP.setCodigo(Integer.parseInt(vehiculoDatos.get(0)));
        vehiculoDP.setPlaca(vehiculoDatos.get(1));
        vehiculoDP.setMarca(vehiculoDatos.get(2));
        vehiculoDP.setColor(vehiculoDatos.get(3));
        vehiculoDP.setTipo(vehiculoDatos.get(4));
        vehiculoDP.setKilometraje(Integer.parseInt(vehiculoDatos.get(5)));
        vehiculoDP.codigoEncargado=Integer.parseInt(vehiculoDatos.get(6));

        textoPlaca = (TextView) findViewById(R.id.textoPlaca);
        textoPlaca.setText("Placa: "+vehiculoDatos.get(1));

        //hito 2
        //cargarVehiculos();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_vehiculo,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id==R.id.itemModificarVehiculo){

            Intent intent = new Intent(VentanaDeVehiculoGUI.this,VentanaModificarVehiculosGUI.class);
            intent.putExtra("placaVehiculo",vehiculoDP.placa);
            intent.putExtra("codigoEncargado",vehiculoDP.codigoEncargado);
            startActivity(intent);
        }
        return true;
    }

}
