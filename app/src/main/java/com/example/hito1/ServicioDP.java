package com.example.hito1;

import android.app.Activity;

public class ServicioDP {

    ServicioDP()
    {

    }

    public int codigo;
    public String nombre ;
    public int kmCadaCambio;
    public int kmUltimoCambio;
    public int codigoVehiculo;

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setKmCadaCambio(int kmCadaCambio)
    {
        this.kmCadaCambio = kmCadaCambio;
    }

    public void setKmUltimoCambio(int kmUltimoCambio)
    {
        this.kmUltimoCambio = kmUltimoCambio;
    }

    public int getCodigo() {return this.codigo;}

    public void setCodigo( int codigo)
    {
        this.codigo=codigo;
    }

    public boolean verificarNombre(Activity clase, int codigoVehiculo)
    {
        boolean existe = false;
        ServicioDM nuevo = new ServicioDM();
        existe=nuevo.VerificarNombre(this.nombre,codigoVehiculo,clase);
        return  existe;
    }
}
