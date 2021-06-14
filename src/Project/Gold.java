package Project;

public class Gold extends Avion implements Catering{

    private boolean wifi;

    public Gold(String categoria, int capacidadCombustible, float costoKm, int capacidadMax, int velocidadMax, Propulsion propulsion, boolean wifi) {
        super(categoria, capacidadCombustible, costoKm, capacidadMax, velocidadMax, propulsion);
        this.wifi = wifi;
        this.setTarifa(6000);
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    @Override
    public String toString() {
        return "Gold{" + super.toString() +
                "wifi=" + wifi +
                '}';
    }


}
