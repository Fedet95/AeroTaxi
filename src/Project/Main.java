package Project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner aux = new Scanner(System.in);
    public static void main(String[] args){

        // CARGA DE ARCHIVO Y LISTAS
        File archivoUsuarios = new File("Usuarios.txt");
        List<Usuario> listaUsuarios = new ArrayList<>(10);

        File archivoVuelos = new File ("Vuelos.txt");

        File archivoAviones = new File ("Aviones.txt");

        List<Avion> avionList = new ArrayList<>();
        avionList.add(new Gold("Gold",20,300,10,200,Propulsion.REACCION,true));
        avionList.add(new Silver("Silver",15,225,7,180,Propulsion.REACCION));
        avionList.add(new Bronce("Bronce",12,150,5,150,Propulsion.PISTON));


        BufferedWriter writer = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if(archivoAviones.exists()){
            try{
                writer = new BufferedWriter(new FileWriter(new File("Aviones.txt")));
                gson.toJson(avionList,avionList.getClass(),writer);
            } catch (IOException e){
                e.getMessage();
            }catch (Exception e){
                e.getMessage();
            } finally {
                if(writer != null){
                    try {
                        writer.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        Usuario usuario = new Usuario("Fede","Tacchini",38283232,23);
        Vuelo vuelo = new Vuelo();
        vuelo.reservarVuelo(archivoVuelos,usuario);
        System.out.println(vuelo);
        //COMIENZO

        int opcion;
        boolean salir = false;
        System.out.println("AERO-TAXI");
        System.out.println("Bienvenido");

        while(!salir){
            System.out.println("Ingrese la opcion deseada:\n");

            System.out.println("1. Registrarse");
            System.out.println("2. Logearse");
            System.out.println("3. Ingresar como Administrador");
            System.out.println("4. Salir");

            System.out.println("Escriba una de las opciones: ");
            opcion = aux.nextInt();

            switch(opcion) {
                case 1:
                    System.out.println("REGISTRO DE USUARIO");
                    Usuario nuevo = crearUsuario();
                    System.out.println(nuevo);
                    agregarUsuarioFile(archivoUsuarios,nuevo);

                    break;
                case 2:
                    System.out.println("LOGEO DE USUARIO\n");
                    Usuario usuarioLogeado = logInUsuario(archivoUsuarios);

                    if(usuarioLogeado!= null)
                        menuUsuario(usuarioLogeado,archivoUsuarios);
                    break;
                case 3:
                    System.out.println("Ingreso como Administrador");
                    break;
                case 4:
                    salir=true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 4");
            }

        }


    }

    public static void menuUsuario(Usuario usuario, File archivo){
        int opcion;
        boolean salir = false;
        while(!salir){
            System.out.println("Ingrese la opcion deseada:\n");

            System.out.println("1. Reservar un vuelo");
            System.out.println("2. Cancelar un vuelo");
            System.out.println("3. Log out");

            System.out.println("Escriba una de las opciones: ");
            opcion = aux.nextInt();

            switch(opcion) {
                case 1:
                    System.out.println("Reservar un vuelo");
                    break;
                case 2:
                    System.out.println("Cancelar un vuelo");
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    salir=true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 4");
            }
        }
    }
    public static Usuario logInUsuario (File archivo){
        System.out.println("Ingrese Usuario");
        String username = aux.next();
        Usuario usuarioLogeado = buscarUsuario(archivo,username);
        if(usuarioLogeado == null)
            System.out.println("El usuario " + username + "no se encuentra logeado");
        else {
            System.out.println("Ingrese contraseña: ");
            String pw = aux.next();
            if(!usuarioLogeado.getPw().equals(pw)){
                System.out.println("Contraseña incorrecta");
            }else {
                System.out.println("Bienvenido: " + username);
                return usuarioLogeado;
            }
        }
        return null;
    }

    public static Usuario buscarUsuario (File archivo, String username) {

        List<Usuario> usuarioList;
        Usuario encontrado = null;

        if (archivo.exists()) {
            BufferedReader reader = null;

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try {

                reader = new BufferedReader(new FileReader(new File("Usuarios.txt")));
                usuarioList = gson.fromJson(reader, (new TypeToken<List<Usuario>>() {}.getType()));

                if(usuarioList == null)
                    System.out.println("El usuario no se encuentra registrado");
                else{
                    for( Usuario aux : usuarioList){
                        if(aux.getUsername().compareTo(username)==0) {
                            encontrado = aux;
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return encontrado;
    }

    public static Usuario crearUsuario() {
        System.out.println("Ingrese Nombre:");
        String nombre = aux.next();
        System.out.println("Ingrese Apellido");
        String apellido = aux.next();
        System.out.println("Ingrese DNI");
        int dni = aux.nextInt();
        System.out.println("Ingrese Edad");
        int edad = aux.nextInt();

        Usuario usuario = new Usuario(nombre, apellido, dni, edad);

        return usuario;
    }

    public static void agregarUsuarioFile (File archivo, Usuario usuario){ //retorno?

        List<Usuario> usuarioList;
        if(archivo.exists()) {
            BufferedWriter writer = null;
            BufferedReader reader = null;

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try {

                reader = new BufferedReader(new FileReader(new File("Usuarios.txt")));
                usuarioList = gson.fromJson(reader,(new TypeToken<List<Usuario>>(){}.getType()));

                if(usuarioList == null) {
                    usuarioList = new ArrayList<>();

                }
                    usuarioList.add(usuario);



                writer = new BufferedWriter(new FileWriter(new File("Usuarios.txt")));
                gson.toJson(usuarioList, usuarioList.getClass(), writer);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }  finally {
                if (writer != null && reader != null) {
                    try {
                        writer.close();
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //return archivo;
    }




}
