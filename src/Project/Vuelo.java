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
    private float montoTotalReserva;


    public Vuelo() {
    }

    public Vuelo(String origen, String destino, Usuario usuario, String fecha, Avion avion, float montoTotalReserva) {
        this.origen = origen;
        this.destino = destino;
        this.usuario = usuario;
        this.fecha = fecha;
        this.avion = avion;
        this.montoTotalReserva = montoTotalReserva;
    }
///FUNCIÓN PARA CARGAR VUELOS: ///

    public static void reserva(List<Vuelo> listaVuelos,List<Ruta> listaRutasAereas,Avion avioncito, Usuario usuarioFinal){

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
        Vuelo nuevoVuelo = new Vuelo(origen,destino,usuarioFinal,fecha,avioncito,costoTotal);
        listaVuelos.add(nuevoVuelo);

    }

    /// FUNCIÓN PARA BORRAR UNA RESERVA ///


    public static void borrarReserva(List<Vuelo> listaVuelos){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese el numero de vuelo a cancelar: ");
        String numeroBuscado = entrada.nextLine();
        String fechaActual = LocalDate.now().toString();
        boolean encontrado = false;
        for (Vuelo vuelito:listaVuelos) {
            if(vuelito.id.equals(numeroBuscado)){
                if(vuelito.fecha.equals(fechaActual)) {
                    System.out.println("No se puede cancelar este vuelo porque faltan menos de 24 hs");
                    return;
                    }
                listaVuelos.remove(vuelito);
                encontrado = true;
            }
        }
        if(encontrado==false){
            System.out.println("No se encuentra ese ID de vuelo");
        }
    }



    public static void muestroVuelosPorFecha(List<Vuelo> listaVuelos){
        System.out.println("- Buscamos vuelos por fecha: ");
        System.out.println("Ingrese fecha: ");
        Scanner entrada = new Scanner(System.in);
        String fecha = entrada.nextLine();
        for (Vuelo vuelito:listaVuelos) {
            if(vuelito.fecha.equals(fecha)){
                System.out.println(vuelito.toString());
            }
        }
    }

    public static float montoTotalViajes(List<Vuelo> listaVuelos,Usuario usuario){
        float rta = 0F;
            for (Vuelo vuelito:listaVuelos) {
                if(vuelito.usuario.equals(usuario)){
                    rta+= vuelito.montoTotalReserva;
                }
            }
        return rta;
    }

    public static int categoriaMejorAvionUtilizado(List<Vuelo> listaVuelos,Usuario usuario){
       int rta = 0;
            for (Vuelo vuelito:listaVuelos) {
                if(vuelito.usuario.equals(usuario)){
                    rta = utilizoServicioGold(listaVuelos,usuario);
                    if(rta==1){return rta;}
                }
            }

            for (Vuelo vuelito:listaVuelos) {
                if(vuelito.usuario.equals(usuario)){
                    rta = utilizoServicioSilver(listaVuelos,usuario);
                    if(rta==2){return rta;}
                }
            }

            for (Vuelo vuelito:listaVuelos) {
                if(vuelito.usuario.equals(usuario)){
                    rta = utilizoServicioBronce(listaVuelos,usuario);
                    if(rta==3){return rta;}
                }
            }
        return rta;
    }


    public static int utilizoServicioGold(List<Vuelo> listaVuelos,Usuario usuario) {
        int rta = 0;
        String aux;
        for (Vuelo vuelito : listaVuelos) {
            if(vuelito.usuario.equals(usuario)){
                aux = vuelito.avion.getClass().getSimpleName();
                if (aux.equals("Gold")) {
                    rta = 1;
                }
            }
        }
        return rta;
    }

    public static int utilizoServicioSilver(List<Vuelo> listaVuelos,Usuario usuario) {
        int rta = 0;
        String aux;
        for (Vuelo vuelito : listaVuelos) {
            if(vuelito.usuario.equals(usuario)){
                aux = vuelito.avion.getClass().getSimpleName();
                if (aux.equals("Silver")) {
                    rta = 2;
                }
            }
        }
        return rta;
    }

    public static int utilizoServicioBronce(List<Vuelo> listaVuelos,Usuario usuario) {
        int rta = 0;
        String aux;
        for (Vuelo vuelito : listaVuelos) {
            if(vuelito.usuario.equals(usuario)){
                aux = vuelito.avion.getClass().getSimpleName();
                if (aux.equals("Bronce")) {
                    rta = 3;
                }
            }
        }
        return rta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public float getMontoTotalReserva() {
        return montoTotalReserva;
    }

    public void setMontoTotalReserva(float montoTotalReserva) {
        this.montoTotalReserva = montoTotalReserva;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id='" + id + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", usuario=" + usuario +
                ", fecha='" + fecha + '\'' +
                ", avion=" + avion +
                ", montoTotalReserva=" + montoTotalReserva +
                '}';
    }
}
