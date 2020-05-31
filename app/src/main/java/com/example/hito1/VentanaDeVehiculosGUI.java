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

public class VentanaDeVehiculosGUI extends AppCompatActivity {

    public EncargadoDP encargadoDP=new EncargadoDP();
    public EncargadoDM encargadoDM = new EncargadoDM();
    private TextView textoBienvenida;

    //hito 2
    public VehiculoDP vehiculoDP=new VehiculoDP();
    public VehiculoDM vehiculoDM=new VehiculoDM();
    private ListView lista;
    //


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

        //hito 2
        cargarVehiculos();
        //
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_vehiculos,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id==R.id.itemModificarEncargado){

            Intent intent = new Intent(VentanaDeVehiculosGUI.this,VentanaModificarEncargadoGUI.class);
            intent.putExtra("usuarioEncargado",encargadoDP.usuario);
            startActivity(intent);
        }
        return true;
    }


    public void cargarVehiculos()
    {
        final List<String> vehiculos=vehiculoDM.Consultar(0,"",this, encargadoDP.codigo);
        lista = (ListView) findViewById(R.id.ListaVehiculos);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.lista_de_vehiculos,vehiculos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String placaVehiculo = vehiculos.get(position);
                Intent intent = new Intent(VentanaDeVehiculosGUI.this,VentanaDeVehiculoGUI.class);
                intent.putExtra("placaVehiculo",placaVehiculo);
                intent.putExtra("codigoEncargado",encargadoDP.codigo);
                startActivity(intent);
            }
        });
    }

    public void AbrirVentanaCreacionVehiculos(View view)
    {
        Intent intent = new Intent(VentanaDeVehiculosGUI.this,VentanaCreacionVehiculosGUI.class);
        intent.putExtra("codigoEncargado",encargadoDP.codigo);
        startActivity(intent);

        cargarVehiculos();
    }





}
