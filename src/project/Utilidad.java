package project;

import java.util.Scanner;

public class Utilidad {

    public static int pedirInt() {
        return new Scanner(System.in).nextInt();
    }

    public static String pedirString() {
        return new Scanner(System.in).next();
    }

}
