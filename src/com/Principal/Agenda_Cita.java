package com.Principal;

public class Agenda_Cita {
    String Id,Nss, dia,hora,minuto;

    public Agenda_Cita(){

    }
    public Agenda_Cita(String dia, String hora, String minuto, String Nss, String Id){
        this.dia=dia;
        this.hora=hora;
        this.minuto=minuto;
        this.Nss=Nss;
        this.Id=Id;

    }

    public String getId() {
        return Id;
    }

    public String getNss() {
        return Nss;
    }

    public String getDia() {
        return dia;
    }

    public String getHora() {
        return hora;
    }

    public String getMinuto() {
        return minuto;
    }
}
