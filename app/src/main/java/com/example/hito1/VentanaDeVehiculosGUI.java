package com.example.hito1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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


}
