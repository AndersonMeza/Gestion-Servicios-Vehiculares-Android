package com.example.hito1;

import android.app.Activity;

public class VehiculoDP {

    VehiculoDP()
    {

    }

    public int codigo;
    public String placa ;
    public  String marca;
    public String color;
    public String tipo;
    public int kilometraje;
    public int codigoEncargado;

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }

    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public void setKilometraje(int kilometraje)
    {
        this.kilometraje = kilometraje;
    }

    public int getCodigo() {return this.codigo;}

    public void setCodigo( int codigo)
    {
        this.codigo=codigo;
    }

    public boolean verificarPlaca( Activity clase, int codigoEncargado)
    {
        boolean existe = false;
        VehiculoDM nuevo = new VehiculoDM();
        existe=nuevo.VerificarPlaca(this.placa,codigoEncargado,clase);
        return  existe;
    }
}
