package com.example.hito1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import java.util.List;

public class VentanaDeRegistrosGUI extends AppCompatActivity {

    RelacionencvehserDM relacionencvehserDM = new RelacionencvehserDM();
    RelacionencvehserDP relacionencvehserDP=new RelacionencvehserDP();
    int codigoVehiculo;
    int codigoEncargado;
    List<String> listaRegistros;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_de_registros);

    try
    {
        codigoVehiculo = getIntent().getIntExtra("codigoVehiculo",0);
        codigoEncargado = getIntent().getIntExtra("codigoEncargado",0);
        listaRegistros = relacionencvehserDM.Consultar("",this,codigoVehiculo,null);

        cargarRegistros();
    }
    catch(Exception e)
    {
        Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_SHORT).show();
    }


    }

    public void cargarRegistros()
    {
            lista = (ListView) findViewById(R.id.listaRegistros);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.lista_de_registros, listaRegistros);
            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    final CharSequence[] preguntas = {"Eliminar", "Modificar"};
                    final AlertDialog.Builder preguntaModiElim = new AlertDialog.Builder(VentanaDeRegistrosGUI.this);
                    preguntaModiElim.setTitle("¿Qué desea hacer?");
                    preguntaModiElim.setItems(preguntas, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (preguntas[which].equals("Eliminar")) {

                            try
                            {
                                int posicionEspacio = listaRegistros.get(position).indexOf(" ");
                                final List<String> registros2 = relacionencvehserDM.Consultar(listaRegistros.get(position).substring(posicionEspacio+1), VentanaDeRegistrosGUI.this, codigoVehiculo, listaRegistros.get(position).substring(0, posicionEspacio));
                                relacionencvehserDP.setCodigo(Integer.parseInt(registros2.get(0)));
                                relacionencvehserDM.Eliminar(relacionencvehserDP, VentanaDeRegistrosGUI.this);

                                Intent intent = new Intent(VentanaDeRegistrosGUI.this, VentanaDeRegistrosGUI.class);
                                intent.putExtra("codigoEncargado", codigoEncargado);
                                intent.putExtra("codigoVehiculo", codigoVehiculo);
                                startActivity(intent);
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_SHORT).show();
                            }

                            } else if (preguntas[which].equals(("Modificar"))) {

                            }
                        }
                    });
                    preguntaModiElim.show();


                }
            });
    }
}
