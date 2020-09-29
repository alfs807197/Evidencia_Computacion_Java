package com.Principal;

public class Paciente extends Persona{
    private String Nss;

    public Paciente(String Nss, String Nombre, String Apellido, String Genero, int Edad){
        super(Nombre, Apellido, Genero, Edad);
        this.Nss=Nss;
    }

    public String getNss() {
        return Nss;
    }

    public String getNombre() {
        return super.getNombre();
    }
    public String getApellido(){
        return super.getApellido();
    }
}
