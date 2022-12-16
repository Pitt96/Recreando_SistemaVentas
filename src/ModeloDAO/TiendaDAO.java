
package ModeloDAO;

import Config.Conexion;
import Interface.CRUD;
import Modelo.Tienda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class TiendaDAO implements CRUD<Tienda>{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean create(Tienda clase) {
        String sql="insert into tienda (ruc,nombre,telefono,direccion,mensaje) values (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, clase.getRuc());
            ps.setString(2, clase.getNombre());
            ps.setString(3, clase.getTelefono());
            ps.setString(4, clase.getDireccion());
            ps.setString(5, clase.getMensaje());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }
    
    @Override
    public boolean update(Tienda clase) {
        String sql="update tienda set ruc=?,nombre=?,telefono=?, direccion=?,mensaje=? where id=?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, clase.getRuc());
            ps.setString(2, clase.getNombre());
            ps.setString(3, clase.getTelefono());
            ps.setString(4, clase.getDireccion());
            ps.setString(5, clase.getMensaje());
            ps.setInt(6, clase.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "INFORMACION DE LA TIENDA MODIFICADA");
        } catch (Exception e) {
        }
        
        return false;
    }
    
    public Tienda leer(){
        String sql = "select * from tienda";
        Tienda t = new Tienda();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                t.setId(rs.getInt("id"));
                t.setRuc(rs.getString("ruc"));
                t.setNombre(rs.getString("nombre"));
                t.setTelefono(rs.getString("telefono"));
                t.setDireccion(rs.getString("direccion"));
                t.setMensaje(rs.getString("mensaje"));
            }
        } catch (Exception e) {
        }
        return t;
    }
    
    
    //ESTOS METODOS ABSTRACTOS NO ES NECESARIO USARLOS
    @Override
    public List read() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Tienda read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
