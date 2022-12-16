package Controlador;

import Modelo.Proveedor;
import ModeloDAO.ProveedorDAO;
import ModeloDAO.ServicioCantidad;
import Vista.Menu;
import Vista.vistaProveedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pitt
 */
public class proveedorControlador implements ActionListener {

    int idProveedor;
    String menuCargo;
    Proveedor proveedor = new Proveedor();
    ProveedorDAO dao = new ProveedorDAO();
    vistaProveedores vista = new vistaProveedores();
    DefaultTableModel modelo = new DefaultTableModel();
    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.jtProveedores.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                idProveedor = Integer.valueOf(vista.jtProveedores.getValueAt(fila, 0).toString());
                String ruc = vista.jtProveedores.getValueAt(fila, 1).toString();
                String nombres = vista.jtProveedores.getValueAt(fila, 2).toString();
                String telefono = vista.jtProveedores.getValueAt(fila, 3).toString();
                String direccion = vista.jtProveedores.getValueAt(fila, 4).toString();
                String razon = vista.jtProveedores.getValueAt(fila, 5).toString();

                vista.txt_idProveedor.setText(String.valueOf(idProveedor));
                vista.txt_RucProveedor.setText(ruc);
                vista.txtNombresProveedor.setText(nombres);
                vista.txtTelefonoProveedor.setText(telefono);
                vista.txtDireccionProveedor.setText(direccion);
                vista.txtRazonProveedor.setText(razon);
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

    public proveedorControlador(Proveedor proveedor, ProveedorDAO dao, vistaProveedores vista,String menuCargo) {
        this.proveedor = proveedor;
        this.dao = dao;
        this.vista = vista;
        this.menuCargo=menuCargo;
        listarProveedor();
        roles();
        this.vista.btnActualizarProveedor.addActionListener(this);
        this.vista.btnEliminarProveedor.addActionListener(this);
        this.vista.btnGuardarProveedor.addActionListener(this);
        this.vista.btnLimpiarProveedor.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
        this.vista.jtProveedores.addMouseListener(oyenteRaton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardarProveedor) {
            agregarProveedor();
            limpiarTabla();
            listarProveedor();
            limpiarProveedor();
        } else if (e.getSource() == vista.btnActualizarProveedor) {
            modificarProveedor();
            limpiarTabla();
            listarProveedor();
            limpiarProveedor();
        } else if (e.getSource() == vista.btnEliminarProveedor) {
            eliminarProveedor();
            limpiarTabla();
            listarProveedor();
            limpiarProveedor();
        } else if (e.getSource() == vista.btnLimpiarProveedor) {
            limpiarProveedor();
        }else if(e.getSource() == vista.btnBuscar){
            limpiarTabla();
            buscar();
        }else if(e.getSource() == vista.btnListar){
            limpiarTabla();
            listarProveedor();
        }
    }

    void listarProveedor() {
        List<Proveedor> lista = dao.read();
        modelo = (DefaultTableModel) vista.jtProveedores.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getRuc();
            ob[2] = lista.get(i).getNombres();
            ob[3] = lista.get(i).getTelefono();
            ob[4] = lista.get(i).getDireccion();
            ob[5] = lista.get(i).getRazonSocial();
            modelo.addRow(ob);
        }
        vista.jtProveedores.setModel(modelo);
    }
    
    void roles(){
        if(menuCargo.equalsIgnoreCase("cajero")){
            vista.btnGuardarProveedor.setEnabled(false);
            vista.btnEliminarProveedor.setEnabled(false);
            vista.btnActualizarProveedor.setEnabled(false);
            vista.btnLimpiarProveedor.setEnabled(false);
            vista.btnBuscar.setEnabled(false);
            vista.jtProveedores.setEnabled(false);
        }
    }

    void agregarProveedor() {

        if (vista.txt_RucProveedor.equals("") || vista.txtNombresProveedor.equals("") || vista.txtTelefonoProveedor.equals("") || vista.txtDireccionProveedor.equals("") || vista.txtRazonProveedor.equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            String ruc = vista.txt_RucProveedor.getText();
            String nombres = vista.txtNombresProveedor.getText();
            String telefono = vista.txtTelefonoProveedor.getText();
            String direccion = vista.txtDireccionProveedor.getText();
            String razonSocial = vista.txtRazonProveedor.getText();
            Proveedor proveedor = new Proveedor();
            proveedor.setRuc(ruc);
            proveedor.setNombres(nombres);
            proveedor.setTelefono(telefono);
            proveedor.setDireccion(direccion);
            proveedor.setRazonSocial(razonSocial);
            dao.create(proveedor);
            
        }
    }

    void modificarProveedor() {
        int fila = vista.jtProveedores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            if (vista.txt_RucProveedor.equals("") || vista.txtNombresProveedor.equals("") || vista.txtTelefonoProveedor.equals("") || vista.txtDireccionProveedor.equals("") || vista.txtRazonProveedor.equals("")) {
                JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
            } else {
                int id = Integer.parseInt(vista.txt_idProveedor.getText());
                String ruc = vista.txt_RucProveedor.getText();
                String nombres = vista.txtNombresProveedor.getText();
                String telefono = vista.txtTelefonoProveedor.getText();
                String direccion = vista.txtDireccionProveedor.getText();
                String razonSocial = vista.txtRazonProveedor.getText();
                Proveedor proveedor = new Proveedor();
                proveedor.setId(id);
                proveedor.setRuc(ruc);
                proveedor.setNombres(nombres);
                proveedor.setTelefono(telefono);
                proveedor.setDireccion(direccion);
                proveedor.setRazonSocial(razonSocial);
                dao.update(proveedor);
            }
        }
    }

    void eliminarProveedor() {
        int fila = vista.jtProveedores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            dao.delete(idProveedor);
            
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void limpiarProveedor() {
        vista.txt_idProveedor.setText("");
        vista.txt_RucProveedor.setText("");
        vista.txtNombresProveedor.setText("");
        vista.txtTelefonoProveedor.setText("");
        vista.txtDireccionProveedor.setText("");
        vista.txtRazonProveedor.setText("");
        vista.txt_RucProveedor.requestFocus();
    }
    
    void buscar(){
        String palabra = vista.txtDato.getText().trim();
        List<Proveedor> lista = dao.buscar(palabra);
        modelo = (DefaultTableModel) vista.jtProveedores.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getRuc();
            ob[2] = lista.get(i).getNombres();
            ob[3] = lista.get(i).getTelefono();
            ob[4] = lista.get(i).getDireccion();
            ob[5] = lista.get(i).getRazonSocial();
            modelo.addRow(ob);
        }
        vista.jtProveedores.setModel(modelo);
    }
    
}
