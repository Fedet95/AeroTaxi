package Project;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Vuelo {

    private transient final Map<Ciudades, Map<Ciudades, Integer>> distancias = new HashMap<>() {{
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
    private String id = UUID.randomUUID().toString().replaceAll("[^0-1]", "");
    private String fecha;
    private Ciudades origen;
    private Ciudades destino;
    private String clientUsername;
    private int acompañantes;
    private Avion avion;
    private float costoVuelo;

    public Vuelo() {
    }

    public Vuelo(String fecha, Ciudades origen, Ciudades destino, String clientUsername, int acompañantes, Avion avion, float costoVuelo) {
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.clientUsername = clientUsername;
        this.acompañantes = acompañantes;
        this.avion = avion;
        this.costoVuelo = costoVuelo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

    public Map<Ciudades, Map<Ciudades, Integer>> getDistancias() {
        return distancias;
    }


    public Ciudades pedirOrigen() {
        Scanner auxi = new Scanner(System.in);
        System.out.println("Ingrese NUMERO de ciudad de Origen");
        Ciudades origen = null;
        int i = 1;
        for (var distancias : distancias.entrySet()) {
            System.out.println(i + ". " + distancias.getKey());
            i++;
        }
        int opcionOrigen = auxi.nextInt();
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

        return origen;
    }

    public Ciudades pedirDestino(Ciudades origen) {
        Scanner auxi = new Scanner(System.in);
        System.out.println("Ingrese NUMERO de ciudad de Destino: ");
        Ciudades destino = null;
        int x = 1;
        for (var distancias : distancias.get(origen).entrySet()) {
            System.out.println(x + ". " + distancias.getKey());
            x++;
        }
        int opcionDestino = auxi.nextInt();
        if (origen == Ciudades.BSAS) {
            if (opcionDestino == 1)
                destino = Ciudades.CORDOBA;
            else if (opcionDestino == 2)
                destino = Ciudades.SANTIAGO;
            else {
                destino = Ciudades.MONTEVIDEO;
            }
        }

        if (origen == Ciudades.CORDOBA) {
            if (opcionDestino == 1)
                destino = Ciudades.MONTEVIDEO;
            else
                destino = Ciudades.SANTIAGO;
        }

        if (origen == Ciudades.MONTEVIDEO) {
            destino = Ciudades.SANTIAGO;
        }

        return destino;
    }

    @Override
    public String toString() {
        return "\nVuelo: {" +
                "\nID Vuelo: " + id +
                "\nFecha:" + fecha +
                "\nOrigen:" + origen +
                "\nDestino:" + destino +
                "\nClientUsername:" + clientUsername +
                "\nAcompañantes:" + acompañantes +
                "\nAvion:" + avion +
                "\nCostoVuelo:" + costoVuelo +
                '}';
    }
}



