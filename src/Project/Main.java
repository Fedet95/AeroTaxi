package Project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){


        ////AVIONES ///
        Gold avionGold = new Gold("Gold",5000,150,50,12000,Propulsion.PISTON,false);
        Silver avionSilver = new Silver("Silver",4000F,180F,80,12000F,Propulsion.REACCION);
        Bronce avionBronce = new Bronce("Bronce",5000F,170,100,13000,Propulsion.PISTON);
        List<Avion> listaAviones = new ArrayList<>();
        listaAviones.add(avionGold);
        listaAviones.add(avionSilver);
        listaAviones.add(avionBronce);

        /////USUARIOS ////
        Usuario usuario1 = new Usuario("Federico","Tacchini",38829242,26);
        Usuario usuario2 = new Usuario("Samuel","Tocaimaza",32836243,34);

        List<Usuario>listaUsuario = new ArrayList<>();
        listaUsuario.add(usuario1);
        listaUsuario.add(usuario2);


        ////RUTAS AEREAS////
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


        Vuelo buenosAiresACordoba = new Vuelo("BsAs","Cordoba",usuario1,"2021-06-15",avionSilver,120000F);
        Vuelo cordobaASantiago = new Vuelo("Cordoba","Santiago",usuario2,"2021-06-12",avionGold,150000F);
        Vuelo lunes = new Vuelo("BsAs","Santiago",usuario2,"2021-06-14",avionBronce,100000F);
        Vuelo martes = new Vuelo("BsAs","Montevideo",usuario2,"2021-06-17",avionGold,50000F);
        Vuelo miercoles = new Vuelo("Montevideo","Santiago",usuario2,"2021-06-12",avionSilver,540000F);
        List<Vuelo> listaVuelos = new ArrayList<>();
        listaVuelos.add(buenosAiresACordoba);
        listaVuelos.add(cordobaASantiago);
        listaVuelos.add(lunes);
        listaVuelos.add(martes);
        listaVuelos.add(miercoles);

        //Avion.muestroAvionesDisponibles(listaAviones,listaVuelos);
        ///fecha de ejemplo: "2021-06-14"

        float tarifa = Avion.tarifaPorServicio(listaAviones,listaVuelos);
        System.out.println("La tarifa es: "+tarifa);







        //System.out.println(listaAviones.toString());


/*


        System.out.println("Bienvenido al Menu - Aerotaxi");
        int opcion;
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        while (!salir ==true){



        System.out.println("1 ) Registrar un vuelo");
        System.out.println("2 ) Cancelar un vuelo");
        System.out.println("3 ) Informe de vuelos reservados");
        System.out.println("4 ) Informe de clientes");
        System.out.println("5 ) Salir");




        System.out.println("Ingrese la opcion deseada: ");
        opcion = entrada.nextInt();
        System.out.println("Ingreso la opcion " + opcion);

        switch (opcion){
            case 1:
                Vuelo.reserva(listaVuelos,listaRutasAereas,avionGold,usuario2);
                System.out.println(listaVuelos.toString());
                break;
            case 2:
                Vuelo.borrarReserva(listaVuelos);
                System.out.println(listaVuelos.toString());
                break;
            case 3:
                Vuelo.muestroVuelosPorFecha(listaVuelos);
                break;
            case 4:
                System.out.println("Muestro datos de los Usuario: ");
                for (Usuario usuarito:listaUsuario) {
                    System.out.println(usuarito.toString());
                    System.out.println("Categoría mas alta de avión utilizado: ");
                    int rta = Vuelo.categoriaMejorAvionUtilizado(listaVuelos,usuarito);
                    if(rta == 1){
                        System.out.println("Utilizo GOLD");
                    }
                    if(rta == 2){
                        System.out.println("Utilizo Silver");
                    }
                    if(rta == 3){
                        System.out.println("Utilizo Bronce");
                    }
                    System.out.println("Total de dinero gastado en todos sus vuelos: ");
                    float monto = Vuelo.montoTotalViajes(listaVuelos,usuarito);
                    System.out.println(monto);
                }

                break;
            case 5:
                salir=true;
                System.out.println("Gracias, vuelva pronto");
                break;
        }
        }
*/


    }
    /// función que guarda en un JSON una lista de aviones:

    /*public static void crearJSONAviones(List<Avion> listaAviones){
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();

        BufferedWriter fSalidaDatos = null;
        try{
            fSalidaDatos = new BufferedWriter(new FileWriter(new File("aviones.json")));


            String json = gson1.toJson(listaAviones,listaAviones.getClass());

            fSalidaDatos.write(json);


        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(fSalidaDatos!=null){
                try{
                    fSalidaDatos.close();
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void cargarJSONAviones(){

        if(!new File("aviones.json").exists()){
            return;
        }

        BufferedReader bR = null;

        try {

            bR = new BufferedReader(new FileReader(new File("aviones.json")));

            Gson gson = new Gson();
            Type type = new TypeToken<List<Avion[]>>(){}.getType();
            List<Avion[]> listaAviones = gson.fromJson(bR, type);


        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally {
            if(bR!=null){
                try{
                    bR.close();
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }


    }*/



}
