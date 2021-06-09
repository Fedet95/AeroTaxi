package Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Avion implements Serializable {

    private String id = UUID.randomUUID().toString();
    private float capacidadCombustible;
    private float costoKm;
    private int capacidadMax;
    private float velocidadMax;
    private Propulsion propulsion;

    public Avion() {
    }



    public Avion(float capacidadCombustible, float costoKm, int capacidadMax, float velocidadMax, Propulsion propulsion) {
        this.capacidadCombustible = capacidadCombustible;
        this.costoKm = costoKm;
        this.capacidadMax = capacidadMax;
        this.velocidadMax = velocidadMax;
        this.propulsion = propulsion;
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
                "id=" + id +
                "capacidadCombustible=" + capacidadCombustible +
                ", costoKm=" + costoKm +
                ", capacidadMax=" + capacidadMax +
                ", velocidadMax=" + velocidadMax +
                ", propulsion='" + propulsion + '\'' +
                '}';
    }


}


