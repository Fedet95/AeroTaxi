package project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static project.Utilidad.pedirInt;
import static project.Utilidad.pedirString;

public class Main {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static List<Avion> crearListaAvion() {
        List<Avion> avionList = new ArrayList<>();
        avionList.add(new Gold("Gold", 20, 300, 10, 200, Propulsion.REACCION, true));
        avionList.add(new Silver("Silver", 15, 225, 7, 180, Propulsion.REACCION));
        avionList.add(new Bronce("Bronce", 12, 150, 5, 150, Propulsion.PISTON));
        return avionList;
    }

    private static void crearArchivoAvion(List<Avion> avionList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Aviones.txt"))) {
            gson.toJson(avionList, avionList.getClass(), writer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        // CARGA DE ARCHIVO Y LISTAS
        File archivoUsuarios = new File("Usuarios.txt");
        File archivoVuelos = new File("Vuelos.txt");

        List<Avion> avionList = crearListaAvion();
        crearArchivoAvion(avionList);

        //COMIENZO

        int opcion;
        boolean salir = false;
        System.out.println("AERO-TAXI");
        System.out.println("Bienvenido");

        while (!salir) {
            try {
                System.out.println("Ingrese la opcion deseada:\n");

                System.out.println("1. Registrarse");
                System.out.println("2. Logearse");
                System.out.println("3. Ingresar como Administrador");
                System.out.println("4. Salir");
                System.out.println("Escriba una de las opciones: ");
                opcion = pedirInt();

                switch (opcion) {
                    case 1:
                        System.out.println("REGISTRO DE USUARIO");
                        Usuario nuevo = crearUsuario();
                        System.out.println(nuevo);
                        agregarUsuarioFile(archivoUsuarios, nuevo);
                        break;
                    case 2:
                        System.out.println("LOGEO DE USUARIO\n");
                        Usuario usuarioLogeado = logInUsuario(archivoUsuarios);

                        if (usuarioLogeado != null)
                            menuUsuario(usuarioLogeado, archivoVuelos);
                        break;
                    case 3:
                        System.out.println("Ingreso como Administrador");
                        break;
                    case 4:
                        System.out.println("Cerrando Aero-Taxi");
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un numero entre 1 y 4");
            }
        }

    }


    public static void menuUsuario(Usuario usuario, File archivo) {
        int opcion;
        boolean salir = false;
        while (!salir) {
            try {
                System.out.println("Ingrese la opcion deseada:\n");

                System.out.println("1. Reservar un vuelo");
                System.out.println("2. Cancelar un vuelo");
                System.out.println("3. Log out");

                System.out.println("Escriba una de las opciones: ");
                opcion = pedirInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Reservar un vuelo");
                        reservarVuelo(archivo, usuario);
                        break;
                    case 2:
                        System.out.println("Cancelar un vuelo");
                        System.out.println("Ingrese ID de vuelo reservado: ");
                        String idBuscado = pedirString();
                        Vuelo vueloBuscado = buscarVuelo(archivo, idBuscado, usuario);
                        if (vueloBuscado != null) {
                            System.out.println("Vuelo encontrado");
                            System.out.println(vueloBuscado);
                            System.out.println("------------------------");
                            System.out.println("Vuelo eliminado");
                            borrarVueloFile(archivo, vueloBuscado);

                        } else
                            System.out.println("Regresando al Menu");
                        break;
                    case 3:
                        System.out.println("Saliendo...");
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un numero entre 1 y 3");
            }
        }
    }

    public static LocalDate pedirFecha() {
        String fechaprueba = pedirString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechahoravuelo = LocalDate.parse(fechaprueba, formatter);
        return fechahoravuelo;
    }

    public static LocalDate confirmarFecha() {
        LocalDate fecha;
        System.out.println("Ingrese fecha(YYYY-MM-dd): ");
        while (true) {
            try {
                fecha = pedirFecha();
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Reintente(YYYY-MM-dd):");
            }
        }
        return fecha;

    }

    public static Vuelo reservarVuelo(File archivo, Usuario usuario) {

        LocalDate fechaVuelo = confirmarFecha();

        System.out.println("Ingrese NUMERO de ciudad de Origen");
        Ciudades origen;
        Vuelo vueloaux = new Vuelo();

        int i = 1;
        for (var distancias : vueloaux.getDistancias().entrySet()) {
            System.out.println(i + ". " + distancias.getKey());
            i++;
        }
        int opcionOrigen = pedirInt("Debe ingresar un numero entre 1 y 3");
        if (opcionOrigen == 1) {
            origen = Ciudades.BSAS;
        } else {
            if (opcionOrigen == 2) {
                origen = Ciudades.CORDOBA;
            } else {
                origen = Ciudades.MONTEVIDEO;
            }
        }
        System.out.println("Ciudad de origen: " + origen);
        System.out.println("Ingrese NUMERO de ciudad de Destino: ");
        Ciudades destino = null;
        int x = 1;
        for (var distancias : vueloaux.getDistancias().get(origen).entrySet()) {
            System.out.println(x + ". " + distancias.getKey());
            x++;
        }

        if (origen == Ciudades.BSAS) {
            int opcionDestino = pedirInt("Debe ingresar un numero entre 1 y 3");
            if (opcionDestino == 1) {
                destino = Ciudades.CORDOBA;

            } else if (opcionDestino == 2) {
                destino = Ciudades.SANTIAGO;

            } else {
                destino = Ciudades.MONTEVIDEO;

            }
        }

        if (origen == Ciudades.CORDOBA) {
            int opcionDestino = pedirInt("Debe ingresar un numero entre 1 y 2");
            if (opcionDestino == 1) {
                destino = Ciudades.MONTEVIDEO;
            } else
                destino = Ciudades.SANTIAGO;
        }

        if (origen == Ciudades.MONTEVIDEO) {
            destino = Ciudades.SANTIAGO;
        }

        System.out.println("Ciudad de Destino: " + destino);
        //Seleccion de avion y tarifa
        Avion avionElegido = mostrarAvionesDisponibles(fechaVuelo.toString());


        //(Cantidad de kms * Costo del km) + (cantidad de pasajeros * 3500) + (Tarifa del tipo de avión)

        System.out.println("Ingrese cantidad de acompañantes");
        int acompañantes = pedirInt("Debe ingresar un numero");
        if (acompañantes > avionElegido.getCapacidadMax()) {
            while (acompañantes > avionElegido.getCapacidadMax()) {
                System.out.println("La cantidad de pasajeros seleccionada supera el limite del Avión");
                System.out.println("Seleccione una cantidad menor a " + avionElegido.getCapacidadMax());
                acompañantes = pedirInt("Debe ingresar un numero");
            }
        }


        int cantKm = vueloaux.getDistancias().get(origen).get(destino);
        float costoKm = avionElegido.getCostoKm();

        float costoVuelo = (cantKm * costoKm) + ((acompañantes + 1) * 3500) + avionElegido.getTarifa();

        Vuelo vuelo = new Vuelo(fechaVuelo.toString(), origen, destino, usuario.getUsername(), acompañantes, avionElegido, costoVuelo);
        System.out.println("VUELO RESERVADO");
        agregarVueloFile(archivo, vuelo);
        return vuelo;
    }

    public static Vuelo buscarVuelo(File archivo, String idBuscado, Usuario usuario) {

        List<Vuelo> vueloList;
        Vuelo encontrado = null;
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActual = now.format(formatter);
        LocalDate fechaActualLD = LocalDate.parse(fechaActual, formatter);

        boolean id = false;
        if (archivo.exists()) {


            try (BufferedReader reader = new BufferedReader(new FileReader("Vuelos.txt"))) {
                vueloList = gson.fromJson(reader, (new TypeToken<List<Vuelo>>() {
                }.getType()));

                if (vueloList == null) {
                    System.out.println("No hay vuelos registrados");
                } else {
                    for (Vuelo vuelos : vueloList) {
                        if (vuelos.getId().compareTo(idBuscado) == 0) {
                            id = true;
                            if (vuelos.getClientUsername().compareTo(usuario.getUsername()) == 0) {
                                String fechaVuelo = vuelos.getFecha();
                                LocalDate fechaVueloLD = LocalDate.parse(fechaVuelo, formatter);
                                if (fechaActualLD.isAfter(fechaVueloLD))
                                    System.out.println("El vuelo ID " + idBuscado + "no puede ser cancelado ya que tiene fecha anterior a la actual");
                                if (fechaActualLD.isEqual(fechaVueloLD)) {
                                    System.out.println("El vuelo ID " + idBuscado + " no puede ser cancelado debido a estar programado para el día de hoy");
                                } else
                                    encontrado = vuelos;

                            } else
                                System.out.println("El vuelo ID " + idBuscado + "no puede ser borrado por un usuario distinto a quien figura en la reserva");

                        }

                    }
                    if (id == false)
                        System.out.println("No existe un vuelo reservado bajo el ID " + idBuscado);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return encontrado;
    }

    public static Avion mostrarAvionesDisponibles(String fecha) {


        Avion avionElegido = null;

        try (BufferedReader vueloReader = new BufferedReader(new FileReader("Vuelos.txt"));
             BufferedReader avionReader = new BufferedReader(new FileReader("Aviones.txt"))) {

            List<Vuelo> vuelosList = gson.fromJson(vueloReader, (new TypeToken<List<Vuelo>>() {
            }.getType()));
            List<Avion> avionList = gson.fromJson(avionReader, (new TypeToken<List<Avion>>() {
            }.getType()));

            System.out.println("Seleccione el tipo de avion para la fecha " + fecha.replace('T', ' '));

            if (vuelosList == null) {
                int i = 1;
                if (avionList != null) {
                    for (Avion avion : avionList) {
                        System.out.println(i + ". " + avion.getCategoria());
                        i++;
                    }
                    int opcion = pedirInt("Debe ingresar un numero");

                    if (opcion <= i && opcion >= 1)
                        avionElegido = avionList.get(opcion - 1);
                    else {
                        System.out.println("Opcion incorrecta");
                    }
                }
            } else {
                if (avionList != null) {
                    for (Vuelo vuelos : vuelosList) {
                        if (vuelos.getFecha().compareTo(fecha.replace('T', ' ')) == 0) {
                            avionList.removeIf(avion -> vuelos.getAvion().getCategoria().compareTo(avion.getCategoria()) == 0);
                        }
                    }
                    int i = 1;
                    for (Avion avion : avionList) {
                        System.out.println(i + ". " + avion.getCategoria());
                        i++;
                    }
                    int opcion = pedirInt("Debe ingresar un numero");

                    if (opcion <= i && opcion >= 1)
                        avionElegido = avionList.get(opcion - 1);
                    else {
                        System.out.println("Opcion incorrecta");
                    }

                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return avionElegido;
    }

    public static void agregarVueloFile(File archivo, Vuelo vuelo) {


        if (archivo.exists()) {

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Vuelos.txt")));
                BufferedReader reader = new BufferedReader(new FileReader(new File("Vuelos.txt")));) {


                List<Vuelo> vueloList = gson.fromJson(reader, (new TypeToken<List<Vuelo>>() {
                }.getType()));

                if (vueloList == null) {
                    vueloList = new ArrayList<>();

                }

                vueloList.add(vuelo);

                gson.toJson(vueloList, vueloList.getClass(), writer);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void borrarVueloFile(File archivo, Vuelo vuelo) {


        if (archivo.exists()) {

            try(BufferedReader reader = new BufferedReader(new FileReader(new File("Vuelos.txt")));
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Vuelos.txt")));) {

                List<Vuelo> vueloList = gson.fromJson(reader, (new TypeToken<List<Vuelo>>() {
                }.getType()));

                if (vueloList == null) {
                    vueloList = new ArrayList<>();

                } else {

                    for (Vuelo vuelos : vueloList) {
                        if (vuelos.getId().compareTo(vuelo.getId()) == 0) {
                            vueloList.remove(vuelos);
                            System.out.println("eliminado");
                        }
                    }
                }



                gson.toJson(vueloList, vueloList.getClass(), writer);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static Usuario logInUsuario(File archivo) {
        System.out.println("Ingrese Usuario");
        String username = pedirString();
        Usuario usuarioLogeado = buscarUsuario(archivo, username);
        if (usuarioLogeado == null)
            System.out.println("El usuario " + username + " no se encuentra logeado");
        else {
            System.out.println("Ingrese contraseña: ");
            String pw = pedirString();
            if (!usuarioLogeado.getPw().equals(pw)) {
                System.out.println("Contraseña incorrecta");
            } else {
                System.out.println("Bienvenido: " + username);
                return usuarioLogeado;
            }
        }
        return null;
    }

    public static Usuario buscarUsuario(File archivo, String username) {

        List<Usuario> usuarioList;
        Usuario encontrado = null;

        if (archivo.exists()) {
            BufferedReader reader = null;


            try {

                reader = new BufferedReader(new FileReader("Usuarios.txt"));
                usuarioList = gson.fromJson(reader, (new TypeToken<List<Usuario>>() {
                }.getType()));

                if (usuarioList == null)
                    System.out.println("El usuario no se encuentra registrado");
                else {
                    for (Usuario aux : usuarioList) {
                        if (aux.getUsername().compareTo(username) == 0) {
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
        String nombre = pedirString();
        System.out.println("Ingrese Apellido");
        String apellido = pedirString();
        System.out.println("Ingrese DNI");
        int dni = pedirInt("El DNI debe contener solo numeros. Intente nuevamente");
        System.out.println("Ingrese Edad");
        int edad = pedirInt("La edad debe contener solo numeros. Intente nuevamente");

        Usuario usuario = new Usuario(nombre, apellido, dni, edad);

        return usuario;
    }

    public static void agregarUsuarioFile(File archivo, Usuario usuario) {

        if (archivo.exists()) {

            try (BufferedReader reader = new BufferedReader(new FileReader(new File("Usuarios.txt")));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Usuarios.txt")));) {

                List<Usuario> usuarioList = gson.fromJson(reader, (new TypeToken<List<Usuario>>() {
                }.getType()));

                if (usuarioList == null) {
                    usuarioList = new ArrayList<>();

                }
                usuarioList.add(usuario);

                gson.toJson(usuarioList, usuarioList.getClass(), writer);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                gson.toJson(usuarioList, usuarioList.getClass(), writer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
