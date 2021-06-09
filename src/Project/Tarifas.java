package Project;

import java.sql.SQLOutput;
import java.util.Scanner;

public class  Tarifas {
    private int gold = 6000;
    private int silver = 4000;
    private int bronze = 3000;

    public static int seleccioneTarifa(){
        System.out.println("Seleccione que tarifa de avión desea: ");
        Scanner entrada = new Scanner(System.in);
        System.out.println("1) Gold: $6000");
        System.out.println("2) Silver: $4000");
        System.out.println("3) Bronze: $3000");
        int opcion = entrada.nextInt();
        int tarifa = 0;
        switch (opcion){
            case 1:
                tarifa = 6000;
                break;
            case 2:
                tarifa = 4000;
                break;
            case 3:
                tarifa = 3000;
        }
        return tarifa;
    }

    public int getGold() {
        return gold;
    }

    public int getSilver() {
        return silver;
    }

    public int getBronze() {
        return bronze;
    }
}
