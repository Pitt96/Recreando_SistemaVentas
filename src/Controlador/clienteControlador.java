package Controlador;

import Modelo.Cliente;

import ModeloDAO.ClienteDAO;
import ModeloDAO.ServicioCantidad;
import Vista.Menu;

import Vista.vistaClientes;
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
public class clienteControlador implements ActionListener {

    
    int idCliente;
    vistaClientes vc = new vistaClientes();
    Cliente cliente = new Cliente();
    ClienteDAO daoCliente = new ClienteDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vc.jtClientes.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                idCliente = Integer.valueOf(vc.jtClientes.getValueAt(fila, 0).toString());
                String dni = vc.jtClientes.getValueAt(fila, 1).toString();
                String nombre = vc.jtClientes.getValueAt(fila, 2).toString();
                String apellido = vc.jtClientes.getValueAt(fila, 3).toString();
                String direccion = vc.jtClientes.getValueAt(fila, 4).toString();
                String celular = vc.jtClientes.getValueAt(fila, 5).toString();

                vc.txtIdCliente.setText(String.valueOf(idCliente));
                vc.txtDniCliente.setText(dni);
                vc.txtNombreCliente.setText(nombre);
                vc.txtApellidoCliente.setText(apellido);
                vc.txtDireccionCliente.setText(direccion);
                vc.txtCelularCliente.setText(celular);
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

    public clienteControlador( vistaClientes vc, Cliente cliente, ClienteDAO daoCliente) {
        this.vc = vc;
        this.cliente = cliente;
        this.daoCliente = daoCliente;
        listarCliente();
        this.vc.btnGuardarCliente.addActionListener(this);
        this.vc.btnActualizarCliente.addActionListener(this);
        this.vc.btnEliminarCliente.addActionListener(this);
        this.vc.btnLimpiarCliente.addActionListener(this);
        this.vc.btnBuscar.addActionListener(this);
        this.vc.btnListar.addActionListener(this);
        this.vc.jtClientes.addMouseListener(oyenteRaton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vc.btnGuardarCliente) {
            agregarCliente();
            limpiarTabla();
            listarCliente();
            limpiarCampos();
        } else if (e.getSource() == vc.btnActualizarCliente) {
            modificarCliente();
            limpiarTabla();
            listarCliente();
            limpiarCampos();
        } else if (e.getSource() == vc.btnEliminarCliente) {
            eliminarCliente();
            limpiarTabla();
            listarCliente();
            limpiarCampos();
        } else if (e.getSource() == vc.btnLimpiarCliente) {
            limpiarCampos();
        }else if(e.getSource() == vc.btnBuscar){
            limpiarTabla();
            buscar();
        }else if(e.getSource() == vc.btnListar){
            limpiarTabla();
            listarCliente();
        }

    }

    void listarCliente() {
        List<Cliente> lista = daoCliente.read();
        modelo = (DefaultTableModel) vc.jtClientes.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getDni();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getApellido();
            ob[4] = lista.get(i).getDireccion();
            ob[5] = lista.get(i).getCelular();
            modelo.addRow(ob);
        }
        vc.jtClientes.setModel(modelo);
    }

    void agregarCliente() {

        if (vc.txtDniCliente.equals("") || vc.txtNombreCliente.equals("") || vc.txtApellidoCliente.equals("") || vc.txtDireccionCliente.equals("") || vc.txtCelularCliente.equals("")) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            String dni = vc.txtDniCliente.getText();
            String nombre = vc.txtNombreCliente.getText();
            String apellido = vc.txtApellidoCliente.getText();
            String direccion = vc.txtDireccionCliente.getText();
            String celular = vc.txtCelularCliente.getText();
            Cliente c1 = new Cliente();
            c1.setDni(dni);
            c1.setNombre(nombre);
            c1.setApellido(apellido);
            c1.setDireccion(direccion);
            c1.setCelular(celular);
            daoCliente.create(c1);
            
        }
    }

    void modificarCliente() {
        int fila = vc.jtClientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            if (vc.txtDniCliente.equals("") || vc.txtNombreCliente.equals("") || vc.txtApellidoCliente.equals("") || vc.txtDireccionCliente.equals("") || vc.txtCelularCliente.equals("")) {
                JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
            } else {
                
                String dni = vc.txtDniCliente.getText();
                String nombre = vc.txtNombreCliente.getText();
                String apellido = vc.txtApellidoCliente.getText();
                String direccion = vc.txtDireccionCliente.getText();
                String celular = vc.txtCelularCliente.getText();
                Cliente c1 = new Cliente();
                c1.setId(idCliente);
                c1.setDni(dni);
                c1.setNombre(nombre);
                c1.setApellido(apellido);
                c1.setDireccion(direccion);
                c1.setCelular(celular);
                daoCliente.update(c1);
            }
        }
    }

    void eliminarCliente() {
        int fila = vc.jtClientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            daoCliente.delete(idCliente);
            
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    void limpiarCampos() {
        vc.txtIdCliente.setText("");
        vc.txtDniCliente.setText("");
        vc.txtNombreCliente.setText("");
        vc.txtApellidoCliente.setText("");
        vc.txtDireccionCliente.setText("");
        vc.txtCelularCliente.setText("");
        vc.txtDniCliente.requestFocus();
    }
    
    void buscar(){
        String palabra=vc.txtDato.getText().trim();
        System.out.println(palabra);
        List<Cliente> lista = daoCliente.buscarClientes(palabra);
        modelo = (DefaultTableModel) vc.jtClientes.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getDni();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getApellido();
            ob[4] = lista.get(i).getDireccion();
            ob[5] = lista.get(i).getCelular();
            
            modelo.addRow(ob);
        }
        vc.jtClientes.setModel(modelo);
    }
    
    
    
}
