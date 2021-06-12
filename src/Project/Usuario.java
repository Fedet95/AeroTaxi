package Project;

import java.util.Scanner;

import static Project.Main.aux;

public class Usuario {

    private String nombre;
    private String apellido;
    private int DNI;
    private int edad;
    private String username;
    private String pw;

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

    public void setNombre(String nombre) {
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

    public String crearContraseña(){
        System.out.println("Ingrese una contraseña de 5 caracteres: ");
        String contraseña = new Scanner(System.in).next();
        if(contraseña.length()>5) {
            System.out.println("Se exedió el maximo. Reintente");
            contraseña = new Scanner(System.in).next();
        }
        return contraseña;
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", DNI=" + DNI +
                ", edad=" + edad +
                '}';
    }
}
