package Project;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        Gold avionGold = new Gold(5000,150,50,12000,Propulsion.PISTON,false);
        /*
        System.out.println(avionGold);

        Usuario usuario1 = new Usuario("Federico","Tacchini",38829242,26);

        System.out.println(usuario1);*/

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

        System.out.println(listaRutasAereas.toString());

        ////a traves de estas dos funciones puedo ingresar por teclado la ciudad de origen y destino
        //// y retornar un entero con la cantidad de kilometros///.
        //
        int kms = bsasCordoba.buscarRuta(listaRutasAereas);
        System.out.println("Los kilometros desde Cordoba a Montevideo son:" + kms);
        //
        /////////////////////////////////////////////////////////////////////////////////
        //// prueba de como calcular las tarifas ////////////
        ////
        // Calculamos un vuelvo de BsAs a Cordoba, de 10 personas en avion Gold: 145.250
        //
        float costoVuelo = bsasCordoba.costoPorVuelo(avionGold.getCostoKm(),10,listaRutasAereas);
        System.out.println("El costo del vuelo es de: " + costoVuelo);






    }
}
