
package ModeloDAO;

import Config.Conexion;
import Interface.CRUD;
import Modelo.Cliente;
import Modelo.Usuario;
import Modelo.Venta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class VentaDAO implements CRUD<Venta>{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean create(Venta clase) {
        String sql="insert into venta(id,total,fecha,idCliente,idUsuario) values (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, clase.getCodigo());
            ps.setDouble(2, clase.getTotal());
            ps.setString(3,clase.getFecha());
            ps.setInt(4, clase.getCliente().getId());
            ps.setInt(5, clase.getUsuario().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR AL GENERAR LA VENTA");
            System.out.println("Error : "+e.getMessage());
        }
        return false;
    }

    @Override
    public List read() {
        List<Venta> lista = new ArrayList<>();
        String sql = "select * from venta";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Venta v = new Venta();
                v.setCodigo(rs.getString("id"));
                v.setTotal(rs.getDouble("total"));
                v.setFecha(rs.getString("fecha"));
                int idCliente= rs.getInt("idCliente");
                int idUsuario = rs.getInt("idUsuario");
                Cliente cliente = new Cliente();
                ClienteDAO cDao=new ClienteDAO();
                Usuario usuario=new Usuario();
                UsuarioDAO uDao=new UsuarioDAO();
                cliente=cDao.read(idCliente);
                usuario=uDao.read(idUsuario);
                v.setCliente(cliente);
                v.setUsuario(usuario);
                Detalle_VentaDAO ventaDao=new Detalle_VentaDAO();
                v.setDetalles(ventaDao.buscarVenta(v.getCodigo()));
                lista.add(v);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }
    public Venta read(String id){
        Venta v = new Venta();
        String sql = "select * from venta where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                v.setCodigo(rs.getString("id"));
                v.setTotal(rs.getDouble("total"));
                v.setFecha(rs.getString("fecha"));
                int idCliente = rs.getInt("idCliente");
                Cliente cliente = new Cliente();
                ClienteDAO cdao = new ClienteDAO();
                cliente=cdao.read(idCliente);
                v.setCliente(cliente);
                int idUsuario = rs.getInt("idUsuario");
                Usuario us=new Usuario();
                UsuarioDAO udao = new UsuarioDAO();
                us=udao.read(idUsuario);
                v.setUsuario(us);
            }
        } catch (Exception e) {
        }
        return v;
    }
    
    public List buscarVentas(String palabra){
        List<Venta> lista = new ArrayList<>();
        String sql ="select * from venta where id like '%"+palabra+"%' or fecha like'%"+palabra+"%'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Venta v = new Venta();
                v.setCodigo(rs.getString("id"));
                v.setTotal(rs.getDouble("total"));
                v.setFecha(rs.getString("fecha"));
                int idCliente= rs.getInt("idCliente");
                int idUsuario = rs.getInt("idUsuario");
                Cliente cliente = new Cliente();
                ClienteDAO cDao=new ClienteDAO();
                Usuario usuario=new Usuario();
                UsuarioDAO uDao=new UsuarioDAO();
                cliente=cDao.read(idCliente);
                usuario=uDao.read(idUsuario);
                v.setCliente(cliente);
                v.setUsuario(usuario);
                Detalle_VentaDAO ventaDao=new Detalle_VentaDAO();
                v.setDetalles(ventaDao.buscarVenta(v.getCodigo()));
                lista.add(v);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public boolean modificar(Venta venta){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Venta read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Venta clase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
