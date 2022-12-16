
package ModeloDAO;

import Config.Conexion;
import Interface.CRUD;
import Modelo.Categoria_Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaProductoDAO implements CRUD<Categoria_Producto>{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean create(Categoria_Producto clase) {
        String sql="insert into categoria_producto(nombre,descripcion) values (?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, clase.getNombre());
            ps.setString(2, clase.getDescripcion());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public List read() {
        List<Categoria_Producto> lista = new ArrayList<>();
        String sql = "select * from categoria_producto";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Categoria_Producto c = new Categoria_Producto();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                lista.add(c);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public Categoria_Producto read(String nombre){
        Categoria_Producto c = new Categoria_Producto();
        String sql = "select * from categoria_producto where nombre=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while(rs.next()){
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                
            }
        } catch (Exception e) {
        }
        return c;
    }

    @Override
    public Categoria_Producto read(int id) {
        Categoria_Producto c = new Categoria_Producto();
        String sql = "select * from categoria_producto where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                
            }
        } catch (Exception e) {
        }
        return c;
    }

    @Override
    public boolean update(Categoria_Producto clase) {
        String sql="update categoria_producto set nombre=?,descripcion=? where id=?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, clase.getNombre());
            ps.setString(2, clase.getDescripcion());
            ps.setInt(3, clase.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "CATEGORIA PRODUCTO MODIFICADO");
        } catch (Exception e) {
        }
        
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from categoria_producto where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "CATEGORIA PRODUCTO ELIMINADO");
        } catch (Exception e) {
        }
        return false;
    }
    
}
