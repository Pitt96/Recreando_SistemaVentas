
package Modelo;


public class Producto {
    private int id;
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;
    private Categoria_Producto categoria;
    private Proveedor proveedor;

    public Producto() {
    }

    public Producto(String codigo, String nombre, double precio, int stock, Categoria_Producto categoria, Proveedor proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }
    
    
    public Producto(int id, String codigo, String nombre, double precio, int stock, Categoria_Producto categoria, Proveedor proveedor) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categoria_Producto getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria_Producto categoria) {
        this.categoria = categoria;
    }
    
    
}
