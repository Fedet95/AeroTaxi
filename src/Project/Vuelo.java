package Project;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Vuelo {

    private String id = UUID.randomUUID().toString();
    private Date fecha;
    private Ciudades origen;
    private Ciudades destino;
    private String clientUsername;
    private int acompañantes;
    private Avion avion;
    private float costoVuelo;

    private final Map<Ciudades, Map<Ciudades, Integer>> distancias = new HashMap<>() {{
        put(Ciudades.BSAS, new HashMap<>() {{
            put(Ciudades.CORDOBA, 695);
            put(Ciudades.SANTIAGO, 1400);
            put(Ciudades.MONTEVIDEO, 950);
        }});
        put(Ciudades.CORDOBA, new HashMap<>() {{
            put(Ciudades.MONTEVIDEO, 1190);
            put(Ciudades.SANTIAGO, 1050);
        }});
        put(Ciudades.MONTEVIDEO, new HashMap<>() {{
            put(Ciudades.SANTIAGO, 2100);
        }});

    }};
    public Date pedirFecha(){

        Date fecha = null;
        do {
            System.out.println("Ingrese fecha que desea viajar");
            System.out.println("IMPORTANTE: Introduzca fecha de vuelo con formato dd/mm/aaaa");

            Scanner auxi = new Scanner(System.in);
            String fechaString = auxi.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            try {
                fecha = sdf.parse(fechaString);

            } catch (ParseException e) {
                System.out.println("Fecha invalida");
            }
        }while(fecha == null);

        return fecha;

    }
    public Ciudades pedirOrigen (){
        Scanner auxi = new Scanner(System.in);
        System.out.println("Ingrese NUMERO de ciudad de Origen");
        Ciudades origen = null;
        int i = 1;
        for(var distancias : distancias.entrySet()){
            System.out.println(i + ". " + distancias.getKey());
            i++;
        }
        int opcionOrigen = auxi.nextInt();
        if(opcionOrigen == 1) {
            origen = Ciudades.BSAS;
        }
        else{ if(opcionOrigen == 2) {
            origen = Ciudades.CORDOBA;
        }else{
            origen = Ciudades.MONTEVIDEO;
        }}
        System.out.println("Ciudad de origen: " +origen);

        return origen;
    }

    public Ciudades pedirDestino(Ciudades origen){
        Scanner auxi = new Scanner(System.in);
        System.out.println("Ingrese NUMERO de ciudad de Destino: ");
        Ciudades destino = null;
        int x = 1;
        for(var distancias : distancias.get(origen).entrySet()){
            System.out.println(x + ". " + distancias.getKey());
            x++;
        }
        int opcionDestino = auxi.nextInt();
        if(origen == Ciudades.BSAS){
            if(opcionDestino == 1)
                destino = Ciudades.CORDOBA;
            else if(opcionDestino == 2)
                destino = Ciudades.SANTIAGO;
            else{
                destino = Ciudades.MONTEVIDEO;
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

        return destino;
    }


    public Vuelo reservarVuelo (File archivo, Usuario usuario){
        Scanner auxi = new Scanner(System.in);
        System.out.println("Ingrese NUMERO de ciudad de Origen");
        Ciudades origen;
        int i = 1;
        for(var distancias : distancias.entrySet()){
            System.out.println(i + ". " + distancias.getKey());
            i++;
        }
        int opcionOrigen = auxi.nextInt();
        if(opcionOrigen == 1) {
            origen = Ciudades.BSAS;
        }
        else{ if(opcionOrigen == 2) {
            origen = Ciudades.CORDOBA;
        }else{
            origen = Ciudades.MONTEVIDEO;
        }}
        System.out.println("Ciudad de origen: " +origen);
        System.out.println("Ingrese NUMERO de ciudad de Destino: ");
        Ciudades destino;
        int x = 1;
        for(var distancias : distancias.get(origen).entrySet()){
            System.out.println(x + ". " + distancias.getKey());
            x++;
        }
        int opcionDestino = auxi.nextInt();
        if(origen == Ciudades.BSAS){
            if(opcionDestino == 1)
                destino = Ciudades.CORDOBA;
            else if(opcionDestino == 2)
                destino = Ciudades.SANTIAGO;
            else{
                destino = Ciudades.MONTEVIDEO;
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


        System.out.println("Seleccione el tipo de avion");

        Vuelo vuelo = new Vuelo();


        return vuelo;
    }

    public Vuelo(){}



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Ciudades getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudades origen) {
        this.origen = origen;
    }

    public Ciudades getDestino() {
        return destino;
    }

    public void setDestino(Ciudades destino) {
        this.destino = destino;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public int getAcompañantes() {
        return acompañantes;
    }

    public void setAcompañantes(int acompañantes) {
        this.acompañantes = acompañantes;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public float getCostoVuelo() {
        return costoVuelo;
    }

    public void setCostoVuelo(float costoVuelo) {
        this.costoVuelo = costoVuelo;
    }




}



