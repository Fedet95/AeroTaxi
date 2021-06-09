package Project;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Vuelo {
    private String id = UUID.randomUUID().toString().replaceAll("[^0-1]", "");
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
///FUNCIÓN PARA CARGAR VUELOS: ///

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

    /// FUNCIÓN PARA BORRAR UNA RESERVA ///


    public static void borrarReserva(List<Vuelo> listaVuelos){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese el numero de vuelo a cancelar: ");
        String numeroBuscado = entrada.nextLine();

        for (Vuelo vuelito:listaVuelos) {
            if(vuelito.id.equals(numeroBuscado)){
                ///VALIDACIÓN: NO SE PUEDE CANCELAR EL VUELO EL MISMO DIA:

                listaVuelos.remove(vuelito);
            }else{
                System.out.println("No se encontro ese vuelo");
            }
        }
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id=" + id +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", usuario=" + usuario +
                ", fecha='" + fecha + '\'' +
                ", avion=" + avion +
                '}';
    }
}
