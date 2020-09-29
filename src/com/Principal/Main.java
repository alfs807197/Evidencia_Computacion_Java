package com.Principal;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

public class Main {
    public  static BufferedReader entrada= new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
	// write your code here
        String Nombre, Apellido, Genero, especialidad, Id,Nss,hora,minuto,dia;
        int Edad;
        HashMap<String, Doctor> Doctores= new HashMap<>();
        HashMap<String, Paciente> Pacientes= new HashMap<>();
        HashMap<String, Agenda_Cita> Citas= new HashMap<>();
        FileWriter agenDoctores = new FileWriter("c:/Users/Usuario/Desktop/agendaDocs.txt",true);
        FileWriter agenPacientes = new FileWriter("c:/Users/Usuario/Desktop/agendaPacientes.txt",true);
        loadDoc((HashMap<String, Doctor>)Doctores);
        loadPac((HashMap<String, Paciente>)Pacientes);
        boolean d=true;
        int sel;
        while(d){
            System.out.println("**MENU**\n1.Dar de alta\n2.Crear una cita\n0.Salir");
            sel = Integer.parseInt(entrada.readLine());
            switch (sel){
                case 1://Agregar un doc o paciente
                    System.out.println("**MENU ALTA**\n1.Doctor\n2.Paciente");
                    sel = Integer.parseInt(entrada.readLine());
                    switch (sel){
                        case 1://Alta doc
                            System.out.println("Nombre:");
                            Nombre= entrada.readLine();
                            System.out.println("Apellido:");
                            Apellido= entrada.readLine();
                            System.out.println("Id:");
                            Id= entrada.readLine();
                            System.out.println("Especialida:");
                            especialidad= entrada.readLine();
                            System.out.println("Genero:");
                            Genero= entrada.readLine();
                            System.out.println("Edad:");
                            Edad=Integer.parseInt(entrada.readLine());
                            CrearDoc((HashMap<String,Doctor>)Doctores, Nombre,Apellido, Genero, especialidad, Id, Edad);

                            break;
                        case 2://Alta paciente
                            System.out.println("Nombre:");
                            Nombre= entrada.readLine();
                            System.out.println("Apellido:");
                            Apellido= entrada.readLine();
                            System.out.println("NSS:");
                            Nss= entrada.readLine();

                            System.out.println("Genero:");
                            Genero= entrada.readLine();
                            System.out.println("Edad:");
                            Edad=Integer.parseInt(entrada.readLine());
                            CrearPaciente((HashMap<String,Paciente>)Pacientes, Nombre,Apellido, Genero,Nss,Edad);

                            break;
                        default:
                            System.out.println("Opcion invalida\nVuelve a intentarlo");
                            break;
                    }
                    break;
                case 2://Crear una cita
                    System.out.println("Numero de seguro social del paciente");
                    Nss= entrada.readLine();
                    System.out.println("Selecione un dia:\nlunes\nmartes\nmiercoles\njueves\nviernes");
                    dia= entrada.readLine();
                    System.out.println("Seleciones una hora \n8\n9\n10\n11\n12\n13\n14\n15\n16\n17");
                    hora= entrada.readLine();
                    System.out.println("Selecione minuto\n0\n30");
                    minuto= entrada.readLine();
                    System.out.println("Selecione el Id de un doctor");
                    imprimirIdDocs((HashMap<String,Doctor>)Doctores);
                    Id= entrada.readLine();
                    saveCita((HashMap<String,Agenda_Cita>) Citas, Id, dia,hora,minuto, Nss);

                    break;
               /* case 3: //ver agendfa de citas
                    desplegarAgendaCitas((HashMap<String,Agenda_Cita>)Citas, (HashMap<String, Doctor>)Doctores,(HashMap<String,Paciente>)Pacientes);
                    break;*/
                case 0://Salir
                    d=false;
                    saveDoc((HashMap<String, Doctor>)Doctores);
                    savePac((HashMap<String, Paciente>)Pacientes);
                    break;
                default:
                    System.out.println("Opción incorrecta, vuelva a intentar");
                    break;
            }
        }

    }//fin de main

    private static void saveDoc(HashMap<String, Doctor> Doctores) {
        String Id, especialidad;
        String Nombre, Apellido, Genero;
        int Edad;
        if(Doctores.isEmpty()){
            System.out.println("No existen contactos para guardar");
        }else{
            try{
                Iterator num = Doctores.keySet().iterator();//se declara un marcador para recorrer el hashmap
                String txt;
                FileWriter archivo = new FileWriter("c:/Users/Usuario/Desktop/agendaDocs.txt");
                while(num.hasNext()){
                    Id = (String) num.next();
                    txt=Id+" "+Doctores.get(Id).getNombre()+" "+Doctores.get(Id).getApellido()+" "+Doctores.get(Id).getEspecialidad()+" "+Doctores.get(Id).getGenero()+" "+Doctores.get(Id).getEdad()+"\n";
                    archivo.write(txt);
                }
                archivo.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Contactos Actualizados");

        }

    }

    public static HashMap<String, Doctor> loadDoc(HashMap<String, Doctor> Doctores){
        String Id, especialidad;
        String Nombre, Apellido, Genero;
        int Edad;

        try{
            FileReader info =new FileReader("c:/Users/Usuario/Desktop/agendaDocs.txt");
            BufferedReader inf= new BufferedReader(info); //buffer para leer archivos
            String line="";

            while (line != null) {
                line = inf.readLine();
                if (line != null) {
                    String[] arrSplit = line.split(" ");//Divide el string por espacios
                    Id = arrSplit[0];
                    Nombre = arrSplit[1];
                    Apellido=arrSplit[2];
                    especialidad=arrSplit[3];
                    Genero=arrSplit[4];
                    Edad=Integer.parseInt(arrSplit[5]);
                    if (Doctores.containsKey(Id)==false) {
                        CrearDoc((HashMap<String,Doctor>)Doctores, Nombre,Apellido, Genero, especialidad, Id, Edad);
                    }

                }

            }
            info.close();



        }catch(IOException e){
            System.out.println("No se ha encontrado el archivo");
        }
        if(Doctores.isEmpty()) {
            System.out.println("No hay datos para cargar");
        }
        else {
            System.out.println("datoscargados");
        }

        return (HashMap<String, Doctor>)Doctores;
    }

    private static void savePac(HashMap<String, Paciente> Pacientes) {
        String Nss;
        String Nombre, Apellido, Genero;
        int Edad;
        if(Pacientes.isEmpty()){
            System.out.println("No existen contactos para guardar");
        }else{
            try{
                Iterator num = Pacientes.keySet().iterator();//se declara un marcador para recorrer el hashmap
                String txt;
                FileWriter archivo = new FileWriter("c:/Users/Usuario/Desktop/agendaPacientes.txt");
                while(num.hasNext()){
                    Nss = (String) num.next();
                    txt=Nss+" "+Pacientes.get(Nss).getNombre()+" "+Pacientes.get(Nss).getApellido()+" "+Pacientes.get(Nss).getGenero()+" "+Pacientes.get(Nss).getEdad()+"\n";
                    archivo.write(txt);
                }
                archivo.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Pacientes Actualizados");

        }

    }

    public static HashMap<String, Paciente> loadPac(HashMap<String, Paciente> Pacientes){
        String Nss;
        String Nombre, Apellido, Genero;
        int Edad;

        try{
            FileReader info =new FileReader("c:/Users/Usuario/Desktop/agendaPacientes.txt");
            BufferedReader inf= new BufferedReader(info); //buffer para leer archivos
            String line="";

            while (line != null) {
                line = inf.readLine();
                if (line != null) {
                    String[] arrSplit = line.split(" ");//Divide el string por espacios
                    Nss = arrSplit[0];
                    Nombre = arrSplit[1];
                    Apellido=arrSplit[2];

                    Genero=arrSplit[3];
                    Edad=Integer.parseInt(arrSplit[4]);
                    if (Pacientes.containsKey(Nss)==false) {
                        CrearPaciente((HashMap<String,Paciente>)Pacientes, Nombre,Apellido, Genero,Nss,Edad);

                    }

                }

            }
            info.close();



        }catch(IOException e){
            System.out.println("No se ha encontrado el archivo");
        }
        if(Pacientes.isEmpty()) {
            System.out.println("No hay datos para cargar");
        }
        else {
            System.out.println("datos cargados");
        }

        return (HashMap<String, Paciente>)Pacientes;
    }

    public static void desplegarAgendaCitas(HashMap<String,Agenda_Cita>Citas, HashMap<String,Doctor>Doctores, HashMap<String,Paciente>Pacientes){
        if(Citas.isEmpty())
            System.out.println("No hay citas agendadas");
        else{
            Iterator num = Citas.keySet().iterator();
            System.out.println("citas agendadas");

            while (num.hasNext()){
                String key=(String)num.next();

                System.out.println("Id " +Citas.get(key).getId()+" Doctor "+Doctores.get(Citas.get(key)).getNombre()+" "+Doctores.get(Citas.get(key)).getApellido()+" "+
                        Citas.get(key).getDia()+" "+Citas.get(key).getHora()+":"+Citas.get(key).getMinuto()+" Paciente: "+
                        Pacientes.get(Citas.get(key)).getNombre()+" "+Pacientes.get(Citas.get(key)).getApellido());
            }

        }

    }
    public static HashMap<String,Agenda_Cita> saveCita(HashMap<String,Agenda_Cita>Citas, String Id, String dia, String hora, String minuto, String Nss){
        String key=createKey( Id,dia,hora,minuto);
        if(Citas.containsKey(key)==false)
            Citas.put(key,new Agenda_Cita(dia,hora,minuto, Nss, Id));
        else System.out.println("Ya esta ocupado ese Doctor en ese horario");
        return (HashMap<String, Agenda_Cita>)Citas;
    }
    public static void imprimirIdDocs(HashMap<String,Doctor>Doctores){
        if(Doctores.isEmpty()) {
            System.out.println("No existen doctores");
        }else {
            Iterator num = Doctores.keySet().iterator();//se declara un marcador para recorrer el hashmap
            System.out.println("Doctores: ");
            while (num.hasNext()) {
                String key = (String) num.next();
                System.out.println("{" + Doctores.get(key).getId() + "} {"+ Doctores.get(key).getNombre()+" "+Doctores.get(key).getApellido()+"}");//se imprime el contenido del hashmap marcado por el marcador

            }
        }

    }
    public static HashMap<String, Doctor> CrearDoc(HashMap<String, Doctor> Doctores, String Nombre, String Apellido, String Genero, String especialidad, String Id, int Edad){

        Doctores.put(Id,new Doctor(especialidad, Nombre, Apellido, Genero, Id, Edad));
        return (HashMap<String, Doctor>)Doctores;
    }
    public static HashMap<String, Paciente> CrearPaciente(HashMap<String, Paciente> Pacientes, String Nombre, String Apellido, String Genero, String Nss, int Edad){
        Pacientes.put(Nss, new Paciente(Nss, Nombre, Apellido, Genero, Edad));
        return (HashMap<String, Paciente>)Pacientes;

    }
    public static String createKey(String dia, String hora, String minuto, String Id){
        String key=Id+" "+dia+" "+hora+" "+minuto;
        return key;
    }
}
