package Project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

        /*Usuario usuario = new Usuario("Fede","Tacchini",38283232,23);
        Vuelo vuelo = new Vuelo();
        vuelo.reservarVuelo(archivoVuelos,usuario);
        System.out.println(vuelo);*/
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
                        menuUsuario(usuarioLogeado,archivoVuelos);
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
                    reservarVuelo(archivo,usuario);
                    break;
                case 2:
                    System.out.println("Cancelar un vuelo");
                    System.out.println("Ingrese Fecha y Horario de vuelo: yyyy/MM/dd HH:mm");
                    Scanner buscadorVuelo = new Scanner(System.in);
                    String fechaVuelo = buscadorVuelo.nextLine();
                    Vuelo vueloBuscado = null;
                    vueloBuscado = buscarVuelo(archivo,fechaVuelo,usuario);
                    if(vueloBuscado == null)
                        System.out.println("No se registra ningun vuelo con la fecha mencionada");
                    else
                        System.out.println("Vuelo encontrado");
                    System.out.println(vueloBuscado);
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

    public static LocalDateTime pedirFecha(){
        Scanner auxi = new Scanner(System.in);
        System.out.println("Ingrese fecha");
        String fechaprueba = auxi.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechahoravuelo = LocalDateTime.parse(fechaprueba,formatter);

        return fechahoravuelo;

    }

    public static Vuelo reservarVuelo (File archivo, Usuario usuario){ //TODAVIA NO COMPARA FECHAS Y AVIONES DISPONIBLES

        LocalDateTime fechaVuelo = pedirFecha();

        System.out.println("Ingrese NUMERO de ciudad de Origen");
        Ciudades origen;
        Vuelo vueloaux = new Vuelo();

        int i = 1;
        for(var distancias : vueloaux.getDistancias().entrySet()){
            System.out.println(i + ". " + distancias.getKey());
            i++;
        }
        int opcionOrigen = aux.nextInt();
        if(opcionOrigen == 1) {
            origen = Ciudades.MONTEVIDEO;
        }
        else{ if(opcionOrigen == 2) {
            origen = Ciudades.BSAS;
        }else{
            origen = Ciudades.CORDOBA;
        }}
        System.out.println("Ciudad de origen: " +origen);
        System.out.println("Ingrese NUMERO de ciudad de Destino: ");
        Ciudades destino = null;
        int x = 1;
        for(var distancias : vueloaux.getDistancias().get(origen).entrySet()){
            System.out.println(x + ". " + distancias.getKey());
            x++;
        }
        int opcionDestino = aux.nextInt();
        if(origen == Ciudades.BSAS){
            if(opcionDestino == 1)
                destino = Ciudades.MONTEVIDEO;
            else if(opcionDestino == 2)
                destino = Ciudades.CORDOBA;
            else{
                destino = Ciudades.SANTIAGO;
            }
        }

        if(origen == Ciudades.CORDOBA) {
            if (opcionDestino == 1)
                destino = Ciudades.MONTEVIDEO;
            else
                destino = Ciudades.SANTIAGO;
        }

        if(origen == Ciudades.MONTEVIDEO){
            destino = Ciudades.SANTIAGO;
        }


        //Seleccion de avion y tarifa
        System.out.println("Seleccione el tipo de avion");
        BufferedReader reader = null;
        List<Avion> avionList = new ArrayList<>();
        Avion avionElegido = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            reader = new BufferedReader(new FileReader(new File("Aviones.txt")));
            avionList = gson.fromJson(reader,(new TypeToken<List<Avion>>(){}.getType()));

            int p = 1;
            if(avionList !=null){
                for(Avion avion :avionList){
                    System.out.println(p + ". " + avion.getCategoria());
                    p++;
                }
            }
            int opcion = aux.nextInt();

            if (opcion<=3 && opcion>=1)
                avionElegido = avionList.get(opcion-1);
            else {
                System.out.println("Opcion incorrecta");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }  finally {
            if (reader != null) {
                try {
                    reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //(Cantidad de kms * Costo del km) + (cantidad de pasajeros * 3500) + (Tarifa del tipo de avión)
        System.out.println("Ingrese cantidad de acompañantes");
        int acompañantes = aux.nextInt();

        int cantKm = vueloaux.getDistancias().get(origen).get(destino);
        float costoKm = avionElegido.getCostoKm();

        float costoVuelo = (cantKm * costoKm) + ((acompañantes+1) * 3500) + avionElegido.getTarifa();

        Vuelo vuelo = new Vuelo(fechaVuelo.toString(),origen,destino,usuario.getUsername(),acompañantes,avionElegido,costoVuelo);
        System.out.println("VUELO RESERVADO");
        agregarVueloFile(archivo,vuelo);
        return vuelo;
    }

    public static Vuelo buscarVuelo (File archivo, String fechaBuscada,Usuario usuario){

        List<Vuelo> vueloList;
        Vuelo encontrado = null;

        if(archivo.exists()){
            BufferedReader reader = null;

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try{
                reader = new BufferedReader(new FileReader(new File("Vuelos.txt")));
                vueloList = gson.fromJson(reader,(new TypeToken<List<Vuelo>>() {}.getType()));

                if(vueloList == null){
                    System.out.println("No hay vuelos registrados");
                }
                else{
                    for(Vuelo vuelos : vueloList){
                        String fecha = vuelos.getFecha();
                        if(vuelos.getFecha().compareTo(fechaBuscada)==0){
                            if(vuelos.getClientUsername().compareTo(usuario.getUsername())==0)
                                encontrado = vuelos;
                            else{
                                System.out.println("El vuelo programado para su fecha solicitada no coincide con su Usuario");
                            }
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


    public static void agregarVueloFile (File archivo, Vuelo vuelo){ //retorno?

        List<Vuelo> vueloList;
        if(archivo.exists()) {
            BufferedWriter writer = null;
            BufferedReader reader = null;

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try {

                reader = new BufferedReader(new FileReader(new File("Vuelos.txt")));
                vueloList = gson.fromJson(reader,(new TypeToken<List<Usuario>>(){}.getType()));

                if(vueloList == null) {
                    vueloList = new ArrayList<>();

                }
                vueloList.add(vuelo);



                writer = new BufferedWriter(new FileWriter(new File("Vuelos.txt")));
                gson.toJson(vueloList, vueloList.getClass(), writer);

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
