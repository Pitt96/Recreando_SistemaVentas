
package Modelo;

/**
 *
 * @author Pitt
 */
public class Proveedor {
    private int id;
    private String ruc;
    private String nombres;
    private String telefono;
    private String direccion;
    private String razonSocial;

    public Proveedor() {
    }

    public Proveedor(int id, String ruc, String nombres, String telefono, String direccion, String razonSocial) {
        this.id = id;
        this.ruc = ruc;
        this.nombres = nombres;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
