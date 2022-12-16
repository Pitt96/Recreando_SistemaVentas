
package ModeloDAO;
import Config.Conexion;
import Interface.CRUD;
import Modelo.Cargo;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class UsuarioDAO implements CRUD<Usuario>{
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean create(Usuario clase) {
        String sql="insert into usuarios(dni,nombre,apellido,direccion,celular,nick,pass,idCargo) values (?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, clase.getDni());
            ps.setString(2, clase.getNombre());
            ps.setString(3, clase.getApellido());
            ps.setString(4, clase.getDireccion());
            ps.setString(5, clase.getCelular());
            ps.setString(6, clase.getUser());
            ps.setString(7, clase.getPass());
            ps.setInt(8, clase.getCargo().getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public List read() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "select * from usuarios";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setDni(rs.getString("dni"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setDireccion(rs.getString("direccion"));
                u.setCelular(rs.getString("celular"));
                u.setUser(rs.getString("nick"));
                u.setPass(rs.getString("pass"));
                int idCargo=rs.getInt("idCargo");
                Cargo cargo = new Cargo();
                CargoDAO cDao=new CargoDAO();
                cargo=cDao.read(idCargo);
                u.setCargo(cargo);
                
                lista.add(u);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public Usuario read(int id) {
        Usuario u = new Usuario();
        String sql = "select * from usuarios where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                u.setId(rs.getInt("id"));
                u.setDni(rs.getString("dni"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setDireccion(rs.getString("direccion"));
                u.setCelular(rs.getString("celular"));
                u.setUser(rs.getString("nick"));
                u.setPass(rs.getString("pass"));
                int idCargo=rs.getInt("idCargo");
                Cargo cargo = new Cargo();
                CargoDAO cDao=new CargoDAO();
                cargo=cDao.read(idCargo);
                u.setCargo(cargo); 
                
            }
        } catch (Exception e) {
        }
        return u;
    }

    @Override
    public boolean update(Usuario clase) {
        String sql="update usuarios set dni=?,nombre=?,apellido=?, direccion=?,celular=?,nick=?,pass=?,idCargo=? where id=?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, clase.getDni());
            ps.setString(2, clase.getNombre());
            ps.setString(3, clase.getApellido());
            ps.setString(4, clase.getDireccion());
            ps.setString(5, clase.getCelular());
            ps.setString(6, clase.getUser());
            ps.setString(7, clase.getPass());
            ps.setInt(8, clase.getCargo().getId());
            ps.setInt(9, clase.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "USUARIO MODIFICADO");
        } catch (Exception e) {
        }
        
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from usuarios where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO");
        } catch (Exception e) {
        }
        return false;
    }
    
    public List buscarUsuarios(String palabra){
        List<Usuario> lista = new ArrayList<>();
        String sql ="select * from usuarios where dni like '%"+palabra+"%' or nombre like'%"+palabra+"%' or apellido like'%"+ palabra+"%' or direccion like '%"+palabra+"%' or celular like '%"+palabra+"%'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setDni(rs.getString("dni"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setDireccion(rs.getString("direccion"));
                u.setCelular(rs.getString("celular"));
                u.setUser(rs.getString("nick"));
                u.setPass(rs.getString("pass"));
                int idCargo=rs.getInt("idCargo");
                Cargo cargo = new Cargo();
                CargoDAO cDao=new CargoDAO();
                cargo=cDao.read(idCargo);
                u.setCargo(cargo);
                
                lista.add(u);
                
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
}
