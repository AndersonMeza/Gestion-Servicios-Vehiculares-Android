package com.example.hito1;

import android.app.Activity;

public class EncargadoDP
{
    EncargadoDP()
    {

    }

    public int codigo;
    public String nombre ;
    public  String apellido;
    public String usuario;

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public int getCodigo() {return this.codigo;}

    public void setCodigo( int codigo)
    {
        this.codigo=codigo;
    }

    public boolean verificarUsuario( Activity clase)
    {
        boolean existe = false;
        EncargadoDM nuevo = new EncargadoDM();
        existe=nuevo.VerificarUsuario(this.usuario,clase);
        return  existe;
    }


}
