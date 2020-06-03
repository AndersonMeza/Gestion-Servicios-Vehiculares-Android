package com.example.hito1;

import android.app.Activity;

import java.util.Date;

public class RelacionencvehserDP {

    RelacionencvehserDP()
    {

    }

    public int codigo;
    public Date fecha ;
    public  String nombreServicio;
    public int codigoEncargado;
    public int codigoVehiculo;
    public int codigoServicio;

    public void setCodigo( int codigo)
    {
        this.codigo=codigo;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public void setNombreServicio(String nombreServicio)
    {
        this.nombreServicio = nombreServicio;
    }

    public void setCodigoEncargado(int codigoEncargado){this.codigoEncargado = codigoEncargado;}

    public void setCodigoVehiculo(int codigoVehiculo){this.codigoVehiculo = codigoVehiculo;}

    public void setCodigoServicio(int codigoServicio){this.codigoServicio = codigoServicio;}

    public int getCodigo() {return this.codigo;}

}
