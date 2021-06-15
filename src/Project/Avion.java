package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Avion implements Serializable {

    private String id = UUID.randomUUID().toString();
    private String categoria;
    private float capacidadCombustible;
    private float costoKm;
    private int capacidadMax;
    private float velocidadMax;
    private Propulsion propulsion;

    public Avion() {
    }



    public Avion(String categoria,float capacidadCombustible, float costoKm, int capacidadMax, float velocidadMax, Propulsion propulsion) {
        this.categoria = categoria;
        this.capacidadCombustible = capacidadCombustible;
        this.costoKm = costoKm;
        this.capacidadMax = capacidadMax;
        this.velocidadMax = velocidadMax;
        this.propulsion = propulsion;
    }

    public static float tarifaPorServicio(List<Avion> listaAviones,List<Vuelo> listaVuelos){
        float tarifaServicio=0F;
        System.out.println("Estos son los aviones disponibles para la fecha ingresada: ");
        Avion.muestroAvionesDisponibles(listaAviones,listaVuelos);
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese el servicio deseado: ");
        String servicioDeseado = entrada.nextLine();

        if(servicioDeseado.equals("Gold")){
            tarifaServicio = 6000F;
        }if(servicioDeseado.equals("Bronce")){
            tarifaServicio = 3000F;
        }if(servicioDeseado.equals("Silver")){
            tarifaServicio = 4000F;
        }
        return tarifaServicio;
    }



    public static void muestroAvionesDisponibles(List<Avion> listaAviones,List<Vuelo> listaVuelos){
        String fecha = "2021-06-14";
        for (Avion avioncito:listaAviones) {
            boolean rta = avionDisponible(listaVuelos,fecha,avioncito);
            if(rta == true){
                System.out.println(avioncito.getCategoria().toString());
            }
        }
    }

    public static boolean avionDisponible(List<Vuelo> listaVuelos,String fecha, Avion avioncito){
        for (Vuelo vuelito:listaVuelos) {
            if(vuelito.getAvion().equals(avioncito)){
                if(vuelito.getFecha().equals(fecha)){
                    return false;
                }
            }
        }
        return true;
    }

    public float getCapacidadCombustible() {
        return capacidadCombustible;
    }

    public void setCapacidadCombustible(float capacidadCombustible) {
        this.capacidadCombustible = capacidadCombustible;
    }

    public float getCostoKm() {
        return costoKm;
    }

    public void setCostoKm(float costoKm) throws Exception {
        if(costoKm < 150 || costoKm >300) {
            throw new Exception();
        }
        this.costoKm = costoKm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public float getVelocidadMax() {
        return velocidadMax;
    }

    public void setVelocidadMax(float velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

    public Propulsion getPropulsion() {
        return propulsion;
    }

    public void setPropulsion(Propulsion propulsion) {
        this.propulsion = propulsion;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "id='" + id + '\'' +
                ", categoria='" + categoria + '\'' +
                ", capacidadCombustible=" + capacidadCombustible +
                ", costoKm=" + costoKm +
                ", capacidadMax=" + capacidadMax +
                ", velocidadMax=" + velocidadMax +
                ", propulsion=" + propulsion +
                '}';
    }
}


