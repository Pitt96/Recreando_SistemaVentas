
package ModeloDAO;

import Config.Conexion;
import Interface.CRUD;
import Modelo.Cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
/**
 *
 * @author Pitt
 */
public class CargoDAO implements CRUD<Cargo>{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public boolean create(Cargo clase) {
        String sql="insert into cargo(nombre,descripcion) values (?,?)";
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
        List<Cargo> lista = new ArrayList<>();
        String sql = "select * from cargo";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cargo c = new Cargo();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                lista.add(c);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public Cargo read(int id) {
        Cargo c = new Cargo();
        String sql = "select * from cargo where id=?";
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
    public Cargo read(String nombre){
        Cargo c = new Cargo();
        String sql = "select * from cargo where nombre=?";
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
    public boolean update(Cargo clase) {
        String sql="update cargo set nombre=?,descripcion=? where id=?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, clase.getNombre());
            ps.setString(2, clase.getDescripcion());
            ps.setInt(3, clase.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "CARGO MODIFICADO");
        } catch (Exception e) {
        }
        
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from cargo where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "CARGO ELIMINADO");
        } catch (Exception e) {
        }
        return false;
    }
    
}
