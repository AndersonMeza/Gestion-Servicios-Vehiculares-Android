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

public class VentanaModificarEncargadoGUI extends AppCompatActivity {

    public EncargadoDM encargadoDM = new EncargadoDM();
    public EncargadoDP encargadoDP = new EncargadoDP();
    private EditText ingresoNombre;
    private EditText ingresoUsuario;
    private EditText ingresoApellido;
    private Button botonModificar;
    private Button botonEliminar;
    private String UsuarioAntiguo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_modificar_encargado_gui);
        String usuario=getIntent().getStringExtra("usuarioEncargado");
        List<String> encargadoDatos = encargadoDM.Consultar(0,usuario,VentanaModificarEncargadoGUI.this);
        encargadoDP.setCodigo(Integer.parseInt(encargadoDatos.get(0)));
        encargadoDP.setUsuario(encargadoDatos.get(1));
        encargadoDP.setNombre(encargadoDatos.get(2));
        encargadoDP.setApellido(encargadoDatos.get(3));

        UsuarioAntiguo=encargadoDP.usuario;

        ingresoNombre =(EditText)findViewById(R.id.textNombre2);
        ingresoUsuario =(EditText)findViewById(R.id.textUsuario2);
        ingresoApellido =(EditText)findViewById(R.id.textApellido2);
        botonModificar=(Button)findViewById(R.id.botonIngresarEncargado2);
        botonEliminar=(Button)findViewById(R.id.botonEliminarEncargado);

        ingresoNombre.setText(encargadoDP.nombre);
        ingresoApellido.setText(encargadoDP.apellido);
        ingresoUsuario.setText(encargadoDP.usuario);

        ingresoUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(ingresoUsuario.getText().toString()!=UsuarioAntiguo)
                {
                    String usuario=ingresoUsuario.getText().toString();
                    encargadoDP.setUsuario(usuario);
                    boolean existeEncargado=encargadoDP.verificarUsuario(VentanaModificarEncargadoGUI.this);

                    if(!existeEncargado &&  !usuario.isEmpty() && usuario.indexOf(' ')<0)
                    {
                        ingresoNombre.setEnabled(true);
                    }
                    else
                    {
                        Toast.makeText(VentanaModificarEncargadoGUI.this,"Usuario no valido",Toast.LENGTH_SHORT).show();
                        ingresoNombre.setEnabled(false);
                        ingresoApellido.setEnabled(false);
                        botonModificar.setEnabled(false);
                    }
                }
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

                String nombre = ingresoNombre.getText().toString();
                encargadoDP.setNombre(nombre);

                if (ingresoNombre.getText().toString().isEmpty() ){
                    botonModificar.setEnabled(false);
                    ingresoApellido.setEnabled(false);
                }
                else
                    ingresoApellido.setEnabled(true);
            }
        });

        ingresoApellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String apellido=ingresoApellido.getText().toString();
                encargadoDP.setApellido(apellido);

                if(ingresoApellido.getText().toString().isEmpty())
                    botonModificar.setEnabled(false);
                else
                    botonModificar.setEnabled(true);
            }
        });

    }

    public void Modificar(View view)
    {
        encargadoDM.Modificar(encargadoDP,VentanaModificarEncargadoGUI.this);
        String usuarioEncargado = encargadoDP.usuario;
        Intent intent = new Intent(VentanaModificarEncargadoGUI.this,VentanaDeVehiculosGUI.class);
        intent.putExtra("usuarioEncargado",usuarioEncargado);
        startActivity(intent);
    }

    public void Eliminar(View view)
    {
        final CharSequence[] preguntas = {"si","no"};
        final AlertDialog.Builder preguntaEliminar = new AlertDialog.Builder(VentanaModificarEncargadoGUI.this);
        preguntaEliminar.setTitle("¿En verdad quiere eliminar?");
        preguntaEliminar.setItems(preguntas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(preguntas[which].equals("si"))
                {
                    encargadoDM.Eliminar(encargadoDP,VentanaModificarEncargadoGUI.this);
                    Intent intent = new Intent(VentanaModificarEncargadoGUI.this,VentanaIngresoGUI.class);
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

