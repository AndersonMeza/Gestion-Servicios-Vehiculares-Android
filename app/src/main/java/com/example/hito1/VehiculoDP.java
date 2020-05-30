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

    public void setNombre(String marca)
    {
        this.marca = marca;
    }

    public void setApellido(String color)
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

    public boolean verificarPlaca( Activity clase, EncargadoDP encargadoDP)
    {
        boolean existe = false;
        VehiculoDM nuevo = new VehiculoDM();
        existe=nuevo.VerificarPlaca(this.placa,encargadoDP,clase);
        return  existe;
    }
}
