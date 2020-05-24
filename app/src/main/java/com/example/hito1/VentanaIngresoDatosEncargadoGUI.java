package com.example.hito1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class VentanaIngresoDatosEncargadoGUI extends AppCompatActivity {


    public EncargadoDM encargadoDM = new EncargadoDM();
    public EncargadoDP encargadoDP = new EncargadoDP();
    private EditText ingresoNombre;
    private EditText ingresoUsuario;
    private EditText ingresoApellido;
    private Button botonIngresar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_ingreso_datos_encargado_gui);

        ingresoNombre =(EditText)findViewById(R.id.textNombre);
        ingresoUsuario =(EditText)findViewById(R.id.textUsuario);
        ingresoApellido =(EditText)findViewById(R.id.textApellido);
        botonIngresar=(Button)findViewById(R.id.botonIngresarEncargado);

        ingresoUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String usuario=ingresoUsuario.getText().toString();
                encargadoDP.setUsuario(usuario);
                boolean existeEncargado=encargadoDP.verificarUsuario(VentanaIngresoDatosEncargadoGUI.this);

                if(!existeEncargado &&  !usuario.isEmpty() && usuario.indexOf(' ')<0)
                {
                    ingresoNombre.setEnabled(true);
                }
                else
                {
                    Toast.makeText(VentanaIngresoDatosEncargadoGUI.this,"Usuario no valido",Toast.LENGTH_SHORT).show();
                    ingresoNombre.setEnabled(false);
                    ingresoApellido.setEnabled(false);
                    botonIngresar.setEnabled(false);
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
                    botonIngresar.setEnabled(false);
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
                    botonIngresar.setEnabled(false);
                else
                    botonIngresar.setEnabled(true);
            }
        });


    }

    public void Registrar(View view)
    {
        int nuevoCodigo=encargadoDM.getCodigo(this);
        encargadoDP.setCodigo(nuevoCodigo);

        encargadoDM.Crear(encargadoDP,this);
        ingresoNombre.setText("Nombre");
        ingresoUsuario.setText("Usuario");
        ingresoApellido.setText("Apellido");
        ingresoNombre.setEnabled(false);
        ingresoApellido.setEnabled(false);
        botonIngresar.setEnabled(false);

    }

}
