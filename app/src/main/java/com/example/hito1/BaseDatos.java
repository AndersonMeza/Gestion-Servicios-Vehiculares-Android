package com.example.hito1;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table encargados( codigo int primary key, nombre char(15), apellido char(15), usuario char(15))");
        db.execSQL("create table vehiculos( codigo int primary key, placa char(15), marca char(15), color char(15), tipo char(15), kilometraje int,codigoEncargado int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
