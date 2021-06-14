package Project;

public class Silver extends Avion implements Catering {

    public Silver() {
    }

    public Silver(String categoria, float capacidadCombustible, float costoKm, int capacidadMax, float velocidadMax, Propulsion propulsion) {
        super(categoria, capacidadCombustible, costoKm, capacidadMax, velocidadMax, propulsion);
    }

    @Override
    public String toString() {
        return "Silver{}"
                + super.toString();
    }


}
