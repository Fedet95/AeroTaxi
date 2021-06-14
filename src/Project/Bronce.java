package Project;

public class Bronce extends Avion{

    public Bronce(String categoria, int capacidadCombustible, float costoKm, int capacidadMax, int velocidadMax, Propulsion propulsion) {
        super(categoria, capacidadCombustible, costoKm, capacidadMax, velocidadMax, propulsion);
        this.setTarifa(3000);
    }

    @Override
    public String toString() {
        return "Bronce{}" + super.toString();
    }
}
