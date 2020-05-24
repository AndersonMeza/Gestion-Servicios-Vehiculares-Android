package com.example.hito1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EncargadoDM
{


    EncargadoDM()
    {

    }

    public void Crear(EncargadoDP nuevoEncargado, Activity clase)
    {
        BaseDatos crear = new BaseDatos(clase,"encargados",null,1);
        SQLiteDatabase baseDatos= crear.getWritableDatabase();

        int codigo=nuevoEncargado.getCodigo();
        String nombre=nuevoEncargado.nombre;
        String apellido=nuevoEncargado.apellido;
        String usuario=nuevoEncargado.usuario;

        ContentValues registro= new ContentValues();
        registro.put("codigo", codigo);
        registro.put("nombre",nombre);
        registro.put("apellido",apellido);
        registro.put("usuario",usuario);

        baseDatos.insert("encargados",null,registro);
        baseDatos.close();

        Toast.makeText(clase,"Se ha ingresado correctamente",Toast.LENGTH_SHORT).show();
    }

    public void Modificar( EncargadoDP modificarEncargado)
    {

    }

    public void Eliminar(EncargadoDP eliminarEncargado)
    {

    }

    public List<String> Consultar(int codigoEncargado,String usuario, Activity clase)
    {
        BaseDatos consultar = new BaseDatos(clase,"encargados",null,1);
        SQLiteDatabase baseDatos= consultar.getReadableDatabase();
        List<String> usuarios = new ArrayList<>();

        if(codigoEncargado!=0)
        {
            String codigo = codigoEncargado+"";
            Cursor fila = baseDatos.rawQuery("select * from encargados where codigo="+codigo,null);
            String[] unitario= new String[4];

            if(fila.moveToFirst())
            {
                do{
                    unitario[0]=codigo;
                    unitario[1]=fila.getString(3);
                    unitario[2]=fila.getString(1);
                    unitario[3]=fila.getString(2);
                }
                while(fila.moveToNext());

                for (int i=0;i<4;i++)
                    usuarios.add(unitario[0]);
            }
        }
        else if(usuario=="")
        {

            Cursor fila = baseDatos.rawQuery("select * from encargados",null);
            if(fila.moveToFirst())
            {
                do{
                    usuarios.add(fila.getString(3));
                }
                while(fila.moveToNext());
            }
        }
        else
        {
            Cursor fila = baseDatos.rawQuery("select * from encargados where usuario= '"+usuario+"'",null);


            if(fila.moveToFirst())
            {
                usuarios.add(fila.getString(0));
                usuarios.add(fila.getString(3));
                usuarios.add(fila.getString(1));
                usuarios.add(fila.getString(2));
            }
        }

        return usuarios;
    }

    public boolean VerificarUsuario(String usuario, Activity clase)
    {
        boolean existe = false;
        List<String> consulta =Consultar(0,"",clase);

        if(consulta.indexOf(usuario)>=0)
                existe=true;

        return existe;
    }

    public int getCodigo(Activity clase)
    {
        int nuevoCodigo=1;
        BaseDatos consultar = new BaseDatos(clase,"encargados",null,1);
        SQLiteDatabase baseDatos= consultar.getReadableDatabase();
        List<String> usuarios = new ArrayList<>();

        Cursor fila = baseDatos.rawQuery("select * from encargados",null);


        if(fila.moveToFirst())
        {
            if(fila.moveToLast())
            {
                nuevoCodigo = Integer.parseInt(fila.getString(0))+1;
            }
        }

        return nuevoCodigo;
    }

}
