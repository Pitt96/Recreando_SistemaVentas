
package ModeloDAO;

import Config.Conexion;
import Modelo.Cargo;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;





public class LoginDAO {
    PreparedStatement ps;
    ResultSet rs;
    
    
    Conexion con=new Conexion();
    Connection acceso;
    public Usuario validarUsuario(String user,String pass){
        Usuario u = new Usuario();
        String sql="select * from usuarios where nick=? and pass=?";
        try {
            acceso = con.getConnection();
            ps=acceso.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs=ps.executeQuery();
            while(rs.next()){
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setNombre(rs.getString(3));
                u.setApellido(rs.getString(4));
                u.setDireccion(rs.getString(5));
                u.setCelular(rs.getString(6));
                u.setUser(rs.getString(7));
                u.setPass(rs.getString(8));
                int idCargo=rs.getInt(9);
                Cargo c = new Cargo();
                CargoDAO dao=new CargoDAO();
                c=dao.read(idCargo);
                u.setCargo(c);
            }
        } catch (Exception e) {
        }
        return u;
    }
    
}
