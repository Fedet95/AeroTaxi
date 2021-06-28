package project.utilidad;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidad {

    public static int pedirInt() {
        return new Scanner(System.in).nextInt();
    }

    public static int pedirInt(String mensajeDeError) {
        int entero;
        while (true) {
            try {
                entero = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println(mensajeDeError);
            }
        }
        return entero;
    }

    public static String pedirString() {
        return new Scanner(System.in).next();
    }

}
