package com.example.hito1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ServicioDM {

    ServicioDM()
    {

    }

    public void Crear(ServicioDP nuevoServicio, Activity clase)
    {
        BaseDatos crear = new BaseDatos(clase,"servicios",null,1);
        SQLiteDatabase baseDatos= crear.getWritableDatabase();

        int codigo=nuevoServicio.getCodigo();
        String nombre=nuevoServicio.nombre;
        int kmCadaCambio=nuevoServicio.kmCadaCambio;
        int kmUltimoCambio=nuevoServicio.kmUltimoCambio;
        int codigoVehiculo=nuevoServicio.codigoVehiculo;

        ContentValues registro= new ContentValues();
        registro.put("codigo", codigo);
        registro.put("nombre",nombre);
        registro.put("kmCadaCambio",kmCadaCambio);
        registro.put("kmUltimoCambio",kmUltimoCambio);
        registro.put("codigoVehiculo",codigoVehiculo);

        baseDatos.insert("servicios",null,registro);
        baseDatos.close();

        Toast.makeText(clase,"Se ha ingresado correctamente",Toast.LENGTH_SHORT).show();
    }

    public void Modificar( ServicioDP modificarServicio, Activity clase)
    {
        BaseDatos modificar = new BaseDatos(clase,"servicios",null,1);
        SQLiteDatabase baseDatos= modificar.getWritableDatabase();

        int codigo=modificarServicio.getCodigo();
        String nombre=modificarServicio.nombre;
        int kmCadaCambio=modificarServicio.kmCadaCambio;
        int kmUltimoCambio=modificarServicio.kmUltimoCambio;

        baseDatos.execSQL("Update servicios set nombre='"+nombre+"', kmCadaCambio="+kmCadaCambio+", kmUltimoCambio="+kmUltimoCambio+" where codigo="+codigo);
        Toast.makeText(clase,"Se ha modificado correctamente",Toast.LENGTH_SHORT).show();
    }

    public void Eliminar(ServicioDP eliminarServicio,Activity clase)
    {
        BaseDatos eliminar = new BaseDatos(clase,"servicios",null,1);
        SQLiteDatabase baseDatos= eliminar.getWritableDatabase();

        int codigo=eliminarServicio.getCodigo();
        baseDatos.execSQL("Delete from servicios where codigo ="+codigo);
        Toast.makeText(clase,"Se ha eliminado correctamente",Toast.LENGTH_SHORT).show();
    }

    public List<String> Consultar(int codigoServicio, String nombre, Activity clase, int codigoVehiculo)
    {
        BaseDatos consultar = new BaseDatos(clase,"servicios",null,1);
        SQLiteDatabase baseDatos= consultar.getReadableDatabase();
        List<String> placas = new ArrayList<>();

        if(codigoServicio!=0)
        {
            String codigo = codigoServicio+"";
            Cursor fila = baseDatos.rawQuery("select * from servicios where codigo="+codigo ,null);
            String[] unitario= new String[5];

            if(fila.moveToFirst())
            {
                do{
                    unitario[0]=codigo;
                    unitario[1]=fila.getString(1);
                    unitario[2]=fila.getString(2);
                    unitario[3]=fila.getString(3);
                    unitario[4]=fila.getString(4);
                }
                while(fila.moveToNext());

                for (int i=0;i<5;i++)
                    placas.add(unitario[i]);
            }
        }
        else if(nombre=="")
        {

            Cursor fila = baseDatos.rawQuery("select * from servicios where codigoVehiculo="+codigoVehiculo,null);
            if(fila.moveToFirst())
            {
                do{
                    placas.add(fila.getString(1));
                }
                while(fila.moveToNext());
            }
        }
        else
        {
            Cursor fila = baseDatos.rawQuery("select * from servicio where nombre= '"+nombre+"' and codigoVehiculo="+codigoVehiculo,null);


            if(fila.moveToFirst())
            {
                placas.add(fila.getString(0));
                placas.add(fila.getString(1));
                placas.add(fila.getString(2));
                placas.add(fila.getString(3));
                placas.add(fila.getString(4));
            }
        }

        return placas;
    }

    public boolean VerificarNombre(String nombre, int codigoVehiculo, Activity clase)
    {
        boolean existe = false;
        List<String> consulta =Consultar(0,"",clase,codigoVehiculo);

        if(consulta.indexOf(nombre)>=0)
            existe=true;

        return existe;
    }

    public int getCodigo(Activity clase)
    {
        int nuevoCodigo=1;
        BaseDatos consultar = new BaseDatos(clase,"servicios",null,1);
        SQLiteDatabase baseDatos= consultar.getReadableDatabase();

        Cursor fila = baseDatos.rawQuery("select * from servicios",null);


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
