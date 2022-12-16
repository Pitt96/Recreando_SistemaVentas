
package Modelo;


public class Detalle_Venta {
    private int id;
    private int cantidad;
    private double precioUnitario;
    private double total;
    private Producto producto;
    private Venta venta;

    public Detalle_Venta() {
    }

    public Detalle_Venta(int id, int cantidad, double precioUnitario, double total, Producto producto, Venta venta) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
        this.producto = producto;
        this.venta = venta;
    }

    public Detalle_Venta(int cantidad, double precioUnitario, double total, Producto producto, Venta venta) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
        this.producto = producto;
        this.venta = venta;
    }
    
    
    

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
