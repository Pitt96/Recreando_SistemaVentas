
package ModeloDAO;

import Config.Conexion;
import Interface.CRUD;
import Modelo.Proveedor;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Pitt
 */
public class ProveedorDAO implements CRUD<Proveedor>{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public boolean create(Proveedor proveedor) {
        String sql="insert into proveedor(ruc,nombres,telefono,direccion,razonSocial) values (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, proveedor.getRuc());
            ps.setString(2, proveedor.getNombres());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getRazonSocial());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public List read() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "select * from proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Proveedor p = new Proveedor();
                p.setId(rs.getInt("id"));
                p.setRuc(rs.getString("ruc"));
                p.setNombres(rs.getString("nombres"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setRazonSocial(rs.getString("razonSocial"));
                lista.add(p);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public Proveedor read(String nombre){
        Proveedor p = new Proveedor();
        String sql = "select * from proveedor where nombres=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setRuc(rs.getString("ruc"));
                p.setNombres(rs.getString("nombres"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setRazonSocial(rs.getString("razonSocial"));
                
            }
        } catch (Exception e) {
        }
        return p;
    }
    @Override
    public Proveedor read(int id) {
        Proveedor p = new Proveedor();
        String sql = "select * from proveedor where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setRuc(rs.getString("ruc"));
                p.setNombres(rs.getString("nombres"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setRazonSocial(rs.getString("razonSocial"));
                
            }
        } catch (Exception e) {
        }
        return p;
    }

    @Override
    public boolean update(Proveedor proveedor) {
        String sql="update proveedor set ruc=?,nombres=?,telefono=?,direccion=?,razonSocial=? where id=?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, proveedor.getRuc());
            ps.setString(2, proveedor.getNombres());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getRazonSocial());
            ps.setInt(6, proveedor.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "PROVEEDOR MODIFICADO");
        } catch (Exception e) {
        }
        
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from proveedor where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "PROVEEDOR ELIMINADO");
        } catch (Exception e) {
        }
        return false;
    }
    
    public List buscar(String palabra){
        List<Proveedor> lista = new ArrayList<>();
        String sql ="select * from proveedor where ruc like '%"+palabra+"%' or nombres like'%"+palabra+"%' or telefono like '%"+palabra+"%' or direccion like '%"+palabra+"%' or razonSocial like '%"+palabra+"%'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Proveedor p = new Proveedor();
                p.setId(rs.getInt("id"));
                p.setRuc(rs.getString("ruc"));
                p.setNombres(rs.getString("nombres"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setRazonSocial(rs.getString("razonSocial"));
                lista.add(p);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public int getCantidad(){
        int cantProveedor=0;
        String sql = "select count(*) as cantidad from proveedor";
        try {
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
               cantProveedor=rs.getInt("cantidad"); 
            }
            
        } catch (Exception e) {
        }
        return cantProveedor;
    }
    
    
}
