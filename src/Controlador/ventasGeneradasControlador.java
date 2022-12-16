package Controlador;

import Modelo.Detalle_Venta;
import Modelo.Venta;
import ModeloDAO.Detalle_VentaDAO;
import ModeloDAO.ServicioCantidad;
import ModeloDAO.VentaDAO;
import Vista.Menu;
import Vista.vistaDetalleVenta;
import Vista.vistaVentasReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ventasGeneradasControlador implements ActionListener {

    vistaVentasReportes vista = new vistaVentasReportes();
    VentaDAO dao = new VentaDAO();
    Venta modelo = new Venta();
    DefaultTableModel modeloTabla = new DefaultTableModel();
    DefaultTableModel modeloTabla2 = new DefaultTableModel();
    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.jtVentas.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                System.out.println("Ingreso");
                String codigoV = vista.jtVentas.getValueAt(fila, 0).toString();
                System.out.println("Codigo : " + codigoV);
                vistaDetalleVenta vDT = new vistaDetalleVenta();
                Detalle_VentaDAO vdDao = new Detalle_VentaDAO();
                detalleVentaControlador control2 = new detalleVentaControlador(vDT);
                Venta modelo2 = new Venta();
                modelo2.setDetalles(vdDao.buscarVenta(codigoV));
                List<Detalle_Venta> lista2 = vdDao.buscarVenta(codigoV);
                modeloTabla2 = (DefaultTableModel) vDT.jtDVenta.getModel();
                Object[] ob = new Object[6];
                for (int i = 0; i < modelo2.getDetalles().size(); i++) {
                    ob[0] = (i + 1);
                    ob[1] = lista2.get(i).getProducto().getCodigo();
                    ob[2] = lista2.get(i).getProducto().getNombre();
                    ob[3] = lista2.get(i).getCantidad();
                    ob[4] = lista2.get(i).getPrecioUnitario();
                    ob[5] = lista2.get(i).getTotal();

                    modeloTabla2.addRow(ob);
                }
                vDT.jtDVenta.setModel(modeloTabla2);
                vDT.setIdVenta(codigoV);
                vDT.setVisible(true);
                vDT.setLocationRelativeTo(null);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    public ventasGeneradasControlador(vistaVentasReportes vista, VentaDAO dao, Venta modelo) {
        this.vista = vista;
        this.dao = dao;
        this.modelo = modelo;
        listarVentas();
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
        this.vista.jtVentas.addMouseListener(oyenteRaton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscar) {
            limpiarTabla();
            buscar();
        } else if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listarVentas();
        }
    }

    void listarVentas() {
        List<Venta> lista = dao.read();
        modeloTabla = (DefaultTableModel) vista.jtVentas.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getCodigo();
            ob[1] = lista.get(i).getCliente().getNombre();
            ob[2] = lista.get(i).getUsuario().getNombre();
            ob[3] = lista.get(i).getFecha();
            ob[4] = lista.get(i).getTotal();
            modeloTabla.addRow(ob);
        }
        vista.jtVentas.setModel(modeloTabla);
    }

    void buscar() {
        String palabra = vista.txtDato.getText().trim();
        List<Venta> lista = dao.buscarVentas(palabra);
        modeloTabla = (DefaultTableModel) vista.jtVentas.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getCodigo();
            ob[1] = lista.get(i).getCliente().getNombre();
            ob[2] = lista.get(i).getUsuario().getNombre();
            ob[3] = lista.get(i).getFecha();
            ob[4] = lista.get(i).getTotal();
            modeloTabla.addRow(ob);
        }
        vista.jtVentas.setModel(modeloTabla);
    }

    void limpiarTabla() {
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }

}
