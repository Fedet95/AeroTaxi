package project;

import java.io.Serializable;
import java.util.UUID;

public class Avion implements Serializable {

    private final String id = UUID.randomUUID().toString().replaceAll("[^0-1]", "");
    private int tarifa;
    private String categoria;
    private int capacidadCombustible;
    private float costoKm;
    private int capacidadMax;
    private int velocidadMax;
    private Propulsion propulsion;


    public Avion() {
    }

    public Avion(String categoria, int capacidadCombustible, float costoKm, int capacidadMax, int velocidadMax, Propulsion propulsion) {
        this.categoria = categoria;
        this.capacidadCombustible = capacidadCombustible;
        this.costoKm = costoKm;
        this.capacidadMax = capacidadMax;
        this.velocidadMax = velocidadMax;
        this.propulsion = propulsion;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCapacidadCombustible() {
        return capacidadCombustible;
    }

    public void setCapacidadCombustible(int capacidadCombustible) {
        this.capacidadCombustible = capacidadCombustible;
    }

    public float getCostoKm() {
        return costoKm;
    }

    public void setCostoKm(float costoKm) throws Exception {
        if (costoKm < 150 || costoKm > 300) {
            throw new Exception();
        }
        this.costoKm = costoKm;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public int getVelocidadMax() {
        return velocidadMax;
    }

    public void setVelocidadMax(int velocidadMax) {
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
        return "\nAvion:{" +
                "\nCategoria:" + categoria +
                "\nID Avion:" + id +
                "\nCapacidadCombustible:" + capacidadCombustible +
                "\nCostoKm:" + costoKm +
                "\nCapacidadMax:" + capacidadMax +
                "\nVelocidadMax:" + velocidadMax +
                "\nPropulsion:" + propulsion +
                '}';
    }
}


