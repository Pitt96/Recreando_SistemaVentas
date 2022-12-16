
package ModeloDAO;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class CantidadDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public int getCantidad(String sql){
        int cant=0;
        try {
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
               cant=rs.getInt("cantidad"); 
            }
        } catch (Exception e) {
        }
        return cant;
    }
}
