package project;

import java.util.Scanner;

public class Usuario {

    private String nombre;
    private String apellido;
    private int DNI;
    private int edad;
    private String username;
    private String pw;
    private transient float totalGastado;
    private String mejorFlotaUtilizada;

    public Usuario() {

    }

    public Usuario(String nombre, String apellido, int DNI, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.edad = edad;
        this.username = (nombre + apellido).toUpperCase();
        this.pw = crearContraseña();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public float getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(float totalGastado) {
        this.totalGastado = totalGastado;
    }

    public String getMejorFlotaUtilizada() {
        return mejorFlotaUtilizada;
    }

    public void setMejorFlotaUtilizada(String mejorFlotaUtilizada) {
        this.mejorFlotaUtilizada = mejorFlotaUtilizada;
    }

    public String crearContraseña() {
        System.out.println("Ingrese una contraseña de 5 caracteres: ");
        String contraseña = new Scanner(System.in).next();
        if (contraseña.length() != 5) {
            System.out.println("La contraseña no es de 5 caracteres. Reintente");
            contraseña = new Scanner(System.in).next();
        }
        return contraseña;
    }


    @Override
    public String toString() {
        return "\nUsuario: " +
                "\nNombre: " + nombre +
                "\nApellido: " + apellido +
                "\nDNI: " + DNI +
                "\nEdad: " + edad +
                "\nTotal Gastado: $" + totalGastado +
                "\n-------------------------------";
    }
}
