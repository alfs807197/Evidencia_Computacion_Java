package com.Principal;

public class Persona {
    protected String Nombre, Apellido, Genero;
    protected int Edad;

    public Persona(){

    }
    public Persona(String Nombre, String Apellido, String Genero, int Edad){
        this.Nombre=Nombre;
        this.Apellido=Apellido;
        this.Genero=Genero;
        this.Edad=Edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getGenero() {
        return Genero;
    }

    public int getEdad() {
        return Edad;
    }
}
