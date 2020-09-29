package com.Principal;

public class Doctor extends Persona {
    private String especialidad, Id;

//Constructor de doctor
    public Doctor(String especialidad, String Nombre, String Apellido, String Genero, String Id, int Edad){
        super(Nombre, Apellido, Genero, Edad);
        this.especialidad=especialidad;
        this.Id=Id;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void mostrarDatos(){
        System.out.println("ID: "+Id);
        System.out.println("Nombre: "+Nombre);
        System.out.println("Apellido: "+Apellido);
        System.out.println("Especialidad: "+especialidad);

    }

    public String getId() {
        return Id;
    }

    public String getNombre() {
        return super.getNombre();
    }
    public String getApellido(){
        return super.getApellido();
    }
}
