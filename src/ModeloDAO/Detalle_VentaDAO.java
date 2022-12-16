
package ModeloDAO;

import Config.Conexion;
import Interface.CRUD;
import Modelo.Detalle_Venta;
import Modelo.Producto;
import Modelo.Venta;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class Detalle_VentaDAO implements CRUD<Detalle_Venta>{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean create(Detalle_Venta clase) {
        String sql="insert into detalle_venta(cantidad,precioUnitario,total,idProducto,idVenta) values (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, clase.getCantidad());
            ps.setDouble(2, clase.getPrecioUnitario());
            ps.setDouble(3, clase.getTotal());
            ps.setInt(4, clase.getProducto().getId());
            ps.setString(5, clase.getVenta().getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }
    
    @Override
    public List read() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Detalle_Venta read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Detalle_Venta clase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List buscarVenta(String codigo){
        List<Detalle_Venta> lista = new ArrayList<>();
        String sql = "select * from detalle_venta where idVenta=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                Detalle_Venta dv=new Detalle_Venta();
                dv.setId(rs.getInt("id"));
                dv.setCantidad(rs.getInt("cantidad"));
                dv.setPrecioUnitario(rs.getDouble("precioUnitario"));
                dv.setTotal(rs.getDouble("total"));
                int idProducto = rs.getInt("idProducto");
                String idVenta = rs.getString("idVenta");
                Venta venta = new Venta();
                Producto producto=new Producto();
                VentaDAO vDao=new VentaDAO();
                ProductoDAO pDao=new ProductoDAO();
                venta=vDao.read(idVenta);
                producto=pDao.read(idProducto);
                dv.setProducto(producto);
                dv.setVenta(venta);
                
                lista.add(dv);
                
            }
            
        } catch (Exception e) {
            System.out.println("Error al listar, "+e.getMessage());
        }
        return lista;
    }
    
}
