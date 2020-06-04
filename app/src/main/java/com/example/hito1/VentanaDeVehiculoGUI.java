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
import android.widget.Toast;

import java.util.List;

public class VentanaDeVehiculoGUI extends AppCompatActivity {

    VehiculoDP vehiculoDP = new VehiculoDP();
    VehiculoDM vehiculoDM = new VehiculoDM();
    private TextView textoPlaca;

    public  ServicioDP servicioDP=new ServicioDP();
    public ServicioDM servcioDM=new ServicioDM();
    private ListView lista;
    int posEspacio=0;

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
        textoPlaca.setText("Placa: "+vehiculoDatos.get(1)+ " Km:"+vehiculoDatos.get(5));


        cargarServicios();
    }

    public void cargarServicios()
    {
        final List<String> servicios=servcioDM.Consultar(0,"",this, vehiculoDP.codigo);
        lista = (ListView) findViewById(R.id.ListaServicios);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.lista_de_servicios,servicios);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                posEspacio=servicios.get(position).indexOf(" ");
                String nombre=servicios.get(position).substring(posEspacio+1);
                List<String> serviciosDatos = servcioDM.Consultar(0,nombre,VentanaDeVehiculoGUI.this,vehiculoDP.codigo);
                Intent intent = new Intent(VentanaDeVehiculoGUI.this, VentanaDeRegistroGUI.class);
                intent.putExtra("nombreServicio",nombre);
                intent.putExtra("codigoEncargado",vehiculoDP.codigoEncargado);
                intent.putExtra("codigoVehiculo",vehiculoDP.codigo);
                intent.putExtra("codigoServicio",Integer.parseInt(serviciosDatos.get(0)));
                startActivity(intent);
            }
        });
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
        else if (id==R.id.itemModificarServicio)
        {
            Intent intent = new Intent(VentanaDeVehiculoGUI.this,VentanaModificacionServiciosGUI.class);
            intent.putExtra("placaVehiculo",vehiculoDP.placa);
            intent.putExtra("codigoEncargado",vehiculoDP.codigoEncargado);
            startActivity(intent);
        }
        else if (id==R.id.itemRegistros)
        {
            Intent intent = new Intent(VentanaDeVehiculoGUI.this, VentanaDeRegistrosGUI.class);
            intent.putExtra("codigoEncargado",vehiculoDP.codigoEncargado);
            intent.putExtra("codigoVehiculo",vehiculoDP.codigo);
            startActivity(intent);
        }
        return true;
    }


    public void AbrirVentanaCreacionServicios(View view)
    {
        Intent intent = new Intent(VentanaDeVehiculoGUI.this,VentanaDeCreacionServiciosGUI.class);
        intent.putExtra("codigoVehiculo",vehiculoDP.codigo);
        startActivity(intent);

        cargarServicios();
    }
}
