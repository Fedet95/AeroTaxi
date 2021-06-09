package Project;
import java.util.List;
import java.util.Scanner;

public class Vuelo {
    private String origen;
    private String destino;
    private Usuario usuario;
    private String fecha;
    private Avion avion;

    public Vuelo() {
    }

    public Vuelo(String origen, String destino, Usuario usuario, String fecha, Avion avion) {
        this.origen = origen;
        this.destino = destino;
        this.usuario = usuario;
        this.fecha = fecha;
        this.avion = avion;
    }



    ///función para cargar vuelos: ///

    public void reserva(List<Vuelo> listaVuelos,List<Ruta> listaRutasAereas,Avion avioncito, Usuario usuarioFinal){

        Scanner entrada = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de Reservas ");
        System.out.println("Ingrese la fecha de su vuelo: ");
        String fecha = entrada.nextLine();
        System.out.println("Ingrese la ciudad de Origen: ");
        String origen = entrada.nextLine();
        System.out.println("Ingrese la ciudad de Destino: ");
        String destino = entrada.nextLine();

        ///PRIMERA VALIDACIÓN. NO PUEDEN SER IGUALES.
        if(origen.equals(destino)) {
            while (origen.equals(destino)) {
                System.out.println("Error en las rutas, ingrese nuevamente. ");
                System.out.println("Ingrese la ciudad de Origen: ");
                origen = entrada.nextLine();
                System.out.println("Ingrese la ciudad de Destino: ");
                destino = entrada.nextLine();
            }
        }

        System.out.println("Ingrese la cantidad de Pasajeros: ");
        int cantidadPasajeros = entrada.nextInt();
        /// SEGUNDA VALIDACIÓN - CAPACIDAD MÁXIMA DE LA FLOTA 50 PERSONAS//
        if(cantidadPasajeros>50){
            System.out.println("Disculpe, no tenemos servicios para esa cantidad de pasajeros");
            return;
        }

        ///VERIFICO QUE AVION ESTA DISPONIBLE EN ESA FECHA:

        int tarifa = Tarifas.seleccioneTarifa();
        System.out.println("la tarifa seleccionada es: $" + tarifa);

        /// CALCULAMOS LOS KMS DE ESA RUTA

        int km = Ruta.buscarRuta(listaRutasAereas,origen,destino);
        System.out.println("Cantidad de Kms: " + km);
        float costoTotal = (((float)km * avioncito.getCostoKm())+(cantidadPasajeros*3500)+tarifa);
        System.out.println("El costo total de su viaje es de $:" + costoTotal);

        /// GENERO LA RESERVA DEL VUELO:
        Vuelo nuevoVuelo = new Vuelo(origen,destino,usuarioFinal,fecha,avioncito);
        listaVuelos.add(nuevoVuelo);

    }


    @Override
    public String toString() {
        return "Vuelo{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", usuario=" + usuario +
                ", fecha='" + fecha + '\'' +
                ", avion=" + avion +
                '}';
    }
}
