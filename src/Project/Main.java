package Project;

import java.rmi.server.RemoteServer;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args){

        Gold avionGold = new Gold(5000,150,50,12000,Propulsion.PISTON,false);
        Usuario usuario1 = new Usuario("Federico","Tacchini",38829242,26);
        Usuario usuario2 = new Usuario("Samuel","Tocaimaza",32836243,34);




        /////PRUEBAS DE RUTAS AERES ////

        Ruta bsasCordoba = new Ruta("BsAs Cordoba",695);
        Ruta bsasSantiago = new Ruta("BsAs Santiago",1400);
        Ruta bsasMontevideo = new Ruta("BsAs Montevideo",950);
        Ruta cordobaMontevideo = new Ruta("Cordoba Montevideo",1190);
        Ruta cordobaSantiago = new Ruta("Cordoba Santiago",1050);
        Ruta montevideoSantiago = new Ruta("Montevideo Santiago",2100);

        List<Ruta> listaRutasAereas = new ArrayList<>();
        listaRutasAereas.add(bsasCordoba);
        listaRutasAereas.add(bsasSantiago);
        listaRutasAereas.add(bsasMontevideo);
        listaRutasAereas.add(cordobaMontevideo);
        listaRutasAereas.add(cordobaSantiago);
        listaRutasAereas.add(montevideoSantiago);


        ///// CREAMOS UNA LISTA DE VUELOS: (PASAR A JSON Y ALMACENAR)///
        Vuelo buenosAiresACordoba = new Vuelo("BsAs","Cordoba",usuario2,"10062021",avionGold);
        Vuelo cordobaASantiago = new Vuelo("Cordoba","Santiago",usuario1,"11062021",avionGold);
        List<Vuelo> listaVuelos = new ArrayList<>();
        listaVuelos.add(buenosAiresACordoba);
        listaVuelos.add(cordobaASantiago);


        /// CREAMOS UNA FUNCIÓN PARA CARGAR VUELOS:

        Vuelo nuevo = new Vuelo();
        nuevo.reserva(listaVuelos,listaRutasAereas,avionGold,usuario2);
        System.out.println(listaVuelos.toString());



    }
}
