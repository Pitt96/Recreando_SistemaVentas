
package Controlador;


import Reportes.Pdf;
import Vista.vistaDetalleVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;


public class detalleVentaControlador implements ActionListener{
    DefaultTableModel modeloTabla2 = new DefaultTableModel();
    vistaDetalleVenta vista = new vistaDetalleVenta();

    public detalleVentaControlador(vistaDetalleVenta vista) {
        this.vista=vista;
        this.vista.btnDescargarDV.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnDescargarDV){
            imprimirPdf();
        }
    }
    
    void imprimirPdf(){
        String idVenta=vista.getIdVenta();
        
        Pdf f = new Pdf();
        f.generarPdf(idVenta);
    }
    
}
