
package Modelo;

/**
 *
 * @author Pitt
 */
public class Usuario {
    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String celular;
    private String user;
    private String pass;
    private Cargo cargo;
    public Usuario() {
    }

    public Usuario(String dni, String nombre, String apellido, String direccion, String celular, String user, String pass, Cargo cargo) {
        
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.celular = celular;
        this.user = user;
        this.pass = pass;
        this.cargo = cargo;
    }

    public Usuario(int id, String dni, String nombre, String apellido, String direccion, String celular, String user, String pass, Cargo cargo) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.celular = celular;
        this.user = user;
        this.pass = pass;
        this.cargo = cargo;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    
    
}
