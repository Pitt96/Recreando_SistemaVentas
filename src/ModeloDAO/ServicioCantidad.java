
package ModeloDAO;


public class ServicioCantidad {
    CantidadDAO dao = new CantidadDAO();
    
    public int getCantClientes(){
        return dao.getCantidad("select count(*) as cantidad from cliente");
    }
    public int getCantProveedores(){
        return dao.getCantidad("select count(*) as cantidad from proveedor");
    }
    public int getCantProductos(){
        return dao.getCantidad("select count(*) as cantidad from producto");
    }
    public int getCantUsuarios(){
        return dao.getCantidad("select count(*) as cantidad from usuarios");
    }
    public int getCantVentas(){
        return dao.getCantidad("select count(*) as cantidad from venta");
    }
}
