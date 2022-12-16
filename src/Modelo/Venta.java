
package Modelo;


import java.util.List;


public class Venta {
    
    private String codigo;
    private double total;
    private String fecha;
    private Cliente cliente;
    private Usuario usuario;
    private List<Detalle_Venta> detalles;
    public Venta() {
    }

    public Venta(String codigo, double total, String fecha, Cliente cliente, Usuario usuario) {
        
        this.codigo = codigo;
        this.total = total;
        this.fecha = fecha;
        this.cliente = cliente;
        this.usuario = usuario;
    }
    
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Detalle_Venta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle_Venta> detalles) {
        this.detalles = detalles;
    }
    
    
}
