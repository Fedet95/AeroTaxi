package project.aviones;

import project.enums.Propulsion;

public class Silver extends Avion implements Catering {
    public Silver() {}

    public Silver(String categoria, int capacidadCombustible, float costoKm, int capacidadMax, int velocidadMax, Propulsion propulsion) {
        super(categoria, capacidadCombustible, costoKm, capacidadMax, velocidadMax, propulsion);
        this.setTarifa(4000);
    }

    @Override
    public String toString() {
        return "Silver{}"
                + super.toString() +
                "catering=" + catering;
    }


}
