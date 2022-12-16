
package ModeloDAO;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Pitt
 */
public class MyIconPersonalizado implements Icon{
    String ruta;
    public MyIconPersonalizado(String ruta) {
        this.ruta=ruta;
    }
    
    
    //"../Imagenes/opciones.png"
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Image image = new ImageIcon(getClass().getResource(this.ruta)).getImage();
        g.drawImage(image, x, y, c);
    }

    @Override
    public int getIconWidth() {
        return 64;
    }

    @Override
    public int getIconHeight() {
        return 64;
    }
    
}
