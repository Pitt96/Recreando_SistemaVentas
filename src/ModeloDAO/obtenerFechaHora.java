
package ModeloDAO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class obtenerFechaHora {
    public  String getFec(){
        Date dat=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dat);
    }
    public  String getHor(){
        Date dat=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
        return sdf.format(dat);
    }
}
