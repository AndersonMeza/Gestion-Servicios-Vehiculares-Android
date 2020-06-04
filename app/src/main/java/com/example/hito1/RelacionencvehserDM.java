package com.example.hito1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RelacionencvehserDM {

    RelacionencvehserDM()
    {

    }

    public void Crear(RelacionencvehserDP nuevoRegistro, Activity clase)
    {
        BaseDatos crear = new BaseDatos(clase,"registros",null,1);
        SQLiteDatabase baseDatos= crear.getWritableDatabase();

        //codigo int primary key, fecha date, nombreServicio char(15), codigoEncargado int,codigoVehiculo int, codigoServicio int
        int codigo=nuevoRegistro.getCodigo();
        Date fecha=nuevoRegistro.fecha;
        String nombreServicio=nuevoRegistro.nombreServicio;
        int codigoEncargado=nuevoRegistro.codigoEncargado;
        int codigoVehiculo=nuevoRegistro.codigoVehiculo;
        int codigoServicio=nuevoRegistro.codigoServicio;


        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(fecha);
        int año =fecha.getYear()+1900;
        int mes=fecha.getMonth()+1;
        int dia=cal.get(Calendar.DAY_OF_MONTH);

        ContentValues registro= new ContentValues();
        registro.put("codigo", codigo);
        registro.put("fecha", año+"-"+mes+"-"+dia);
        registro.put("nombreServicio",nombreServicio);
        registro.put("codigoEncargado",codigoEncargado);
        registro.put("codigoVehiculo",codigoVehiculo);
        registro.put("codigoServicio",codigoServicio);


        baseDatos.insert("registros",null,registro);
        baseDatos.close();

        Toast.makeText(clase,"Se ha ingresado correctamente",Toast.LENGTH_SHORT).show();
    }

    public void Modificar( RelacionencvehserDP modificarRegistro, Activity clase)
    {
        BaseDatos modificar = new BaseDatos(clase,"registros",null,1);
        SQLiteDatabase baseDatos= modificar.getWritableDatabase();

        int codigo=modificarRegistro.getCodigo();
        Date fecha=modificarRegistro.fecha;

        baseDatos.execSQL("Update registros set fecha='"+fecha.toString()+"' where codigo="+codigo);
        Toast.makeText(clase,"Se ha modificado correctamente",Toast.LENGTH_SHORT).show();
    }

    public void Eliminar(RelacionencvehserDP eliminarRegistro,Activity clase)
    {
        BaseDatos eliminar = new BaseDatos(clase,"registros",null,1);
        SQLiteDatabase baseDatos= eliminar.getWritableDatabase();

        int codigo=eliminarRegistro.getCodigo();
        baseDatos.execSQL("Delete from registros where codigo ="+codigo);
        Toast.makeText(clase,"Se ha eliminado correctamente",Toast.LENGTH_SHORT).show();
    }

    public List<String> Consultar( String nombreServicio, Activity clase, int codigoVehiculo, String fecha)
    {
        BaseDatos consultar = new BaseDatos(clase,"registros",null,1);
        SQLiteDatabase baseDatos= consultar.getReadableDatabase();
        List<String> placas = new ArrayList<>();

        if(nombreServicio=="")
        {

            Cursor fila = baseDatos.rawQuery("select * from registros where codigoVehiculo="+codigoVehiculo+" order by fecha desc",null);
            if(fila.moveToFirst())
            {
                do{
                    placas.add(fila.getString(1) + " "+ fila.getString(2));
                }
                while(fila.moveToNext());
            }
        }
        else
        {
            Cursor fila = baseDatos.rawQuery("select * from registros where fecha='"+fecha+"' and nombreServicio='"+nombreServicio+"' and codigoVehiculo="+codigoVehiculo,null);


            if(fila.moveToFirst())
            {
                placas.add(fila.getString(0));
                placas.add(fila.getString(1));
                placas.add(fila.getString(2));
                placas.add(fila.getString(3));
                placas.add(fila.getString(4));
                placas.add(fila.getString(5));
            }
        }

        return placas;
    }

    public int getCodigo(Activity clase)
    {
        int nuevoCodigo=1;
        BaseDatos consultar = new BaseDatos(clase,"registros",null,1);
        SQLiteDatabase baseDatos= consultar.getReadableDatabase();

        Cursor fila = baseDatos.rawQuery("select * from registros",null);

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
