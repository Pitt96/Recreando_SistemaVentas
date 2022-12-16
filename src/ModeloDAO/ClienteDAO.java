
package ModeloDAO;

import Config.Conexion;
import Interface.CRUD;
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ClienteDAO implements CRUD<Cliente>{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public boolean create(Cliente cliente) {
        String sql="insert into cliente(dni,nombre,apellido,direccion,celular) values (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getCelular());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public List read() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "select * from cliente";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDireccion(rs.getString("direccion"));
                c.setCelular(rs.getString("celular"));
                lista.add(c);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public Cliente read(String variable){
        Cliente c = new Cliente();
        String sql = "select * from cliente where dni=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, variable);
            rs = ps.executeQuery();
            while(rs.next()){
                c.setId(rs.getInt("id"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDireccion(rs.getString("direccion"));
                c.setCelular(rs.getString("celular"));
                
            }
        } catch (Exception e) {
        }
        return c;
    }
    @Override
    public Cliente read(int id) {
        Cliente c = new Cliente();
        String sql = "select * from cliente where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                c.setId(rs.getInt("id"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDireccion(rs.getString("direccion"));
                c.setCelular(rs.getString("celular"));
                
            }
        } catch (Exception e) {
        }
        return c;
    }
    
    public List buscarClientes(String palabra){
        List<Cliente> lista = new ArrayList<>();
        String sql ="select * from cliente where dni like '%"+palabra+"%' or nombre like'%"+palabra+"%' or apellido like'%"+ palabra+"%' or direccion like '%"+palabra+"%' or celular like '%"+palabra+"%'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setDireccion(rs.getString("direccion"));
                c.setCelular(rs.getString("celular"));
                lista.add(c);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + "ERROR EN LA CONSULTA");
        }
        return lista;
    }

    @Override
    public boolean update(Cliente cliente) {
        String sql="update cliente set dni=?,nombre=?,apellido=?,direccion=?,celular=? where id=?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getCelular());
            ps.setInt(6, cliente.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "CLIENTE MODIFICADO");
        } catch (Exception e) {
        }
        
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from cliente where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "CLIENTE ELIMINADO");
        } catch (Exception e) {
        }
        return false;
    }
    
}
