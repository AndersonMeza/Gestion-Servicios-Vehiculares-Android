package com.example.hito1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class VentanaIngresoGUI extends AppCompatActivity {

    public EncargadoDM encargadoDM = new EncargadoDM();
    public EncargadoDP encargadoDP = new EncargadoDP();
    private TextView textView;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_ingreso_gui);
        cargarEncargados();
    }

    public void AbrirVentanaIngresoDatosEncargado(View view)
    {
        Intent intent = new Intent(VentanaIngresoGUI.this,VentanaIngresoDatosEncargadoGUI.class);
        startActivity(intent);
        cargarEncargados();
    }

    public void cargarEncargados()
    {
        final List<String> encargados=encargadoDM.Consultar(0,"",this);
        lista = (ListView) findViewById(R.id.listaUsuarios);
        textView=(TextView)findViewById(R.id.labelSeleccionar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.lista_de_usuarios,encargados);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*List<String> valoresEncagado=encargadoDM.Consultar(0,lista.getItemIdAtPosition(position)+"",VentanaIngresoGUI.this);
                encargadoDP.setCodigo(Integer.parseInt(valoresEncagado.get(0)));
                encargadoDP.setUsuario(valoresEncagado.get(1));
                encargadoDP.setNombre(valoresEncagado.get(2));
                encargadoDP.setApellido(valoresEncagado.get(3));*/

                Intent intent = new Intent(VentanaIngresoGUI.this,VentanaDeVehiculosGUI.class);
                /*intent.putExtra("codigo",encargadoDP.getCodigo());
                intent.putExtra("usuario",encargadoDP.usuario);*/
                //intent.putExtra("nombre",encargadoDP.nombre);
               /* intent.putExtra("apellido",encargadoDP.apellido);*/
                startActivity(intent);
            }
        });
    }


}
