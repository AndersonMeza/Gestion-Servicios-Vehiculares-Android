package com.example.hito1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VehiculoDM {


    VehiculoDM()
    {

    }

    public void Crear(EncargadoDP encargadoExistente,VehiculoDP nuevoVehiculo, Activity clase)
    {
        BaseDatos crear = new BaseDatos(clase,"vehiculos",null,1);
        SQLiteDatabase baseDatos= crear.getWritableDatabase();

        int codigo=nuevoVehiculo.getCodigo();
        String placa=nuevoVehiculo.placa;
        String marca=nuevoVehiculo.marca;
        String color=nuevoVehiculo.color;
        String tipo=nuevoVehiculo.tipo;
        int kilometraje=nuevoVehiculo.kilometraje;
        int codigoEncargado=encargadoExistente.codigo;

        ContentValues registro= new ContentValues();
        registro.put("codigo", codigo);
        registro.put("placa",placa);
        registro.put("marca",marca);
        registro.put("color",color);
        registro.put("tipo",tipo);
        registro.put("kilometraje",kilometraje);
        registro.put("codigoEncargado",codigoEncargado);

        baseDatos.insert("vehiculos",null,registro);
        baseDatos.close();

        Toast.makeText(clase,"Se ha ingresado correctamente",Toast.LENGTH_SHORT).show();
    }

    public void Modificar( VehiculoDP modificarVehiculo, Activity clase)
    {
        BaseDatos modificar = new BaseDatos(clase,"vehiculos",null,1);
        SQLiteDatabase baseDatos= modificar.getWritableDatabase();

        int codigo=modificarVehiculo.getCodigo();
        String placa=modificarVehiculo.placa;
        String marca=modificarVehiculo.marca;
        String color=modificarVehiculo.color;
        String tipo=modificarVehiculo.tipo;
        int kilometraje=modificarVehiculo.kilometraje;

        baseDatos.execSQL("Update vehiculos set placa='"+placa+"', marca='"+marca+"',color='"+color+"',tipo='"+tipo+"', kilometraje="+kilometraje+" where codigo="+codigo);
        Toast.makeText(clase,"Se ha modificado correctamente",Toast.LENGTH_SHORT).show();
    }

    public void Eliminar(VehiculoDP eliminarVehiculo,Activity clase)
    {
        BaseDatos eliminar = new BaseDatos(clase,"vehiculos",null,1);
        SQLiteDatabase baseDatos= eliminar.getWritableDatabase();

        int codigo=eliminarVehiculo.getCodigo();
        baseDatos.execSQL("Delete from vehiculos where codigo ="+codigo);
        Toast.makeText(clase,"Se ha eliminado correctamente",Toast.LENGTH_SHORT).show();
    }

    public List<String> Consultar(int codigoVehiculo, String placa, Activity clase, int codigoEncargado)
    {
        BaseDatos consultar = new BaseDatos(clase,"vehiculos",null,1);
        SQLiteDatabase baseDatos= consultar.getReadableDatabase();
        List<String> placas = new ArrayList<>();

        if(codigoVehiculo!=0)
        {
            String codigo = codigoVehiculo+"";
            Cursor fila = baseDatos.rawQuery("select * from vehiculos where codigo="+codigo ,null);
            String[] unitario= new String[7];

            if(fila.moveToFirst())
            {
                do{
                    unitario[0]=codigo;
                    unitario[1]=fila.getString(1);
                    unitario[2]=fila.getString(2);
                    unitario[3]=fila.getString(3);
                    unitario[4]=fila.getString(4);
                    unitario[5]=fila.getString(5);
                    unitario[6]=fila.getString(6);
                }
                while(fila.moveToNext());

                for (int i=0;i<7;i++)
                    placas.add(unitario[i]);
            }
        }
        else if(placa=="")
        {

            Cursor fila = baseDatos.rawQuery("select * from vehiculos where codigoEncargado="+codigoEncargado,null);
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
            Cursor fila = baseDatos.rawQuery("select * from vehiculos where placa= '"+placa+"' and codigoEncargado="+codigoEncargado,null);


            if(fila.moveToFirst())
            {
                placas.add(fila.getString(0));
                placas.add(fila.getString(1));
                placas.add(fila.getString(2));
                placas.add(fila.getString(3));
                placas.add(fila.getString(4));
                placas.add(fila.getString(5));
                placas.add(fila.getString(6));
            }
        }

        return placas;
    }

    public boolean VerificarPlaca(String placa, EncargadoDP encargado, Activity clase)
    {
        boolean existe = false;
        List<String> consulta =Consultar(0,"",clase,encargado.codigo);

        if(consulta.indexOf(placa)>=0)
            existe=true;

        return existe;
    }

    public int getCodigo(Activity clase)
    {
        int nuevoCodigo=1;
        BaseDatos consultar = new BaseDatos(clase,"vehiculos",null,1);
        SQLiteDatabase baseDatos= consultar.getReadableDatabase();
        List<String> placas = new ArrayList<>();

        Cursor fila = baseDatos.rawQuery("select * from vehiculos",null);


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
