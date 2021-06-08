package Project;

import java.util.List;
import java.util.Scanner;

public class Ruta {
    private String nombre;
    private int km;

    public Ruta() {
    }

    public Ruta(String nombre, int km) {
        this.nombre = nombre;
        this.km = km;
    }

    public int kmPorRuta(String rutaBuscada, List<Ruta> listasRutas){
        int km = 0;
        for (Ruta buscada:listasRutas) {
            if(rutaBuscada.equals(buscada.getNombre()) == true){
                km = buscada.getKm();
            }
        }
        return km;
    }

    public int buscarRuta(List<Ruta> listasRutas){
        String origen;
        String destino;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese la ciudad de Origen: ");
        origen = entrada.nextLine();
        System.out.println("Ingrese la ciudad de destino: ");
        destino = entrada.nextLine();
        String rutaBuscada = origen + " "+destino;
        int km = kmPorRuta(rutaBuscada,listasRutas);
        return km;
    }

    public float costoPorVuelo(float costoKm, int pasajeros, List<Ruta> listasRutas){
        float costoTotal=0.0F;
        int km = buscarRuta(listasRutas);
        Tarifas tarifa = new Tarifas();
        int tari = tarifa.getGold();
        costoTotal = (((float)km * costoKm) + (pasajeros * 3500) + (tari));
        return costoTotal;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "nombre='" + nombre + '\'' +
                ", km=" + km +
                '}';
    }
}
