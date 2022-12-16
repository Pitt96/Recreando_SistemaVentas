
package ModeloDAO;

import Config.Conexion;
import Interface.CRUD;
import Modelo.Categoria_Producto;
import Modelo.Producto;
import Modelo.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ProductoDAO implements CRUD<Producto>{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean create(Producto clase) {
        String sql="insert into producto(codigo,nombre,precio,stock,idCatProduct,idProveedor) values (?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, clase.getCodigo());
            ps.setString(2, clase.getNombre());
            ps.setDouble(3, clase.getPrecio());
            ps.setInt(4, clase.getStock());
            ps.setInt(5, clase.getCategoria().getId());
            ps.setInt(6, clase.getProveedor().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("No se pudo agregar");
            System.out.println("Mensaje : "+e.getMessage());
        }
        return false;
    }

    @Override
    public List read() {
        List<Producto> lista = new ArrayList<>();
        String sql = "select * from producto";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                int idCategoria=rs.getInt("idCatProduct");
                CategoriaProductoDAO catPdao = new CategoriaProductoDAO();
                p.setCategoria(catPdao.read(idCategoria));
                int idProveedor = rs.getInt("idProveedor");
                ProveedorDAO proDao=new ProveedorDAO();
                p.setProveedor(proDao.read(idProveedor));
                
                lista.add(p);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public Producto read(String variable){
        Producto p = new Producto();
        String sql = "select * from producto where codigo=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, variable);
            rs = ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                int idCategoria=rs.getInt("idCatProduct");
                CategoriaProductoDAO catPdao = new CategoriaProductoDAO();
                p.setCategoria(catPdao.read(idCategoria));
                int idProveedor = rs.getInt("idProveedor");
                ProveedorDAO proDao=new ProveedorDAO();
                p.setProveedor(proDao.read(idProveedor));
                
            }
        } catch (Exception e) {
        }
        return p;
    }
    
    public boolean actualizarStock(Producto clase,int  cant){
       String sql="update producto set stock=? where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, clase.getStock()-cant);
            ps.setInt(2, clase.getId());
            ps.executeUpdate();
            
        } catch (Exception e) {
        }
        return false;
    }
    
    @Override
    public Producto read(int id) {
        Producto p = new Producto();
        String sql = "select * from producto where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                int idCategoria=rs.getInt("idCatProduct");
                CategoriaProductoDAO catPdao = new CategoriaProductoDAO();
                p.setCategoria(catPdao.read(idCategoria));
                int idProveedor = rs.getInt("idProveedor");
                ProveedorDAO proDao=new ProveedorDAO();
                p.setProveedor(proDao.read(idProveedor));
                
            }
        } catch (Exception e) {
        }
        return p;
    }

    @Override
    public boolean update(Producto clase) {
        String sql="update producto set codigo=?,nombre=?,precio=?, stock=?,idCatProduct=?,idProveedor=? where id=?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, clase.getCodigo());
            ps.setString(2, clase.getNombre());
            ps.setDouble(3, clase.getPrecio());
            ps.setInt(4, clase.getStock());
            ps.setInt(5, clase.getCategoria().getId());
            ps.setInt(6, clase.getProveedor().getId());
            ps.setInt(7, clase.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "PRODUCTO MODIFICADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR "+e.getMessage());
        }
        
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from producto where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "PRODUCTO ELIMINADO");
        } catch (Exception e) {
        }
        return false;
    }
    public List buscarProductos(String palabra){
        List<Producto> lista = new ArrayList<>();
        
        String sql ="select * from producto where codigo like '%"+palabra+"%' or nombre like'%"+palabra+"%'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                int idCategoria=rs.getInt("idCatProduct");
                CategoriaProductoDAO catPdao = new CategoriaProductoDAO();
                p.setCategoria(catPdao.read(idCategoria));
                int idProveedor = rs.getInt("idProveedor");
                ProveedorDAO proDao=new ProveedorDAO();
                p.setProveedor(proDao.read(idProveedor));
                
                lista.add(p);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }
}
