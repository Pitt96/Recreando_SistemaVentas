
package Modelo;


public class LocalStorage {
    private int cant;
    private double precio;
    private Producto producto;
    private double total;

    public LocalStorage() {
    }

    public LocalStorage(int cant, double precio, Producto producto, double total) {
        this.cant = cant;
        this.precio = precio;
        this.producto = producto;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
