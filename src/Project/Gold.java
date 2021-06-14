package Project;

public class Gold extends Avion implements Catering{

    private boolean wifi;

    public Gold(String categoria, float capacidadCombustible, float costoKm, int capacidadMax, float velocidadMax, Propulsion propulsion, boolean wifi) {
        super(categoria, capacidadCombustible, costoKm, capacidadMax, velocidadMax, propulsion);
        this.wifi = wifi;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }



}
