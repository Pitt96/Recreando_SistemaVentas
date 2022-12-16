package Controlador;

import Modelo.Cargo;
import Modelo.Usuario;
import ModeloDAO.CargoDAO;
import ModeloDAO.ServicioCantidad;
import ModeloDAO.UsuarioDAO;
import Vista.Menu;
import Vista.vistaUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class usuarioControlador implements ActionListener {

    int idUsuario;
    vistaUsuarios vista = new vistaUsuarios();
    Usuario modelo = new Usuario();
    String cargoMenu;
    UsuarioDAO dao = new UsuarioDAO();
    DefaultTableModel modeloTabla = new DefaultTableModel();
    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.jtUsuarios.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                idUsuario = Integer.valueOf(vista.jtUsuarios.getValueAt(fila, 0).toString());
                String dni = vista.jtUsuarios.getValueAt(fila, 1).toString();
                String nombre = vista.jtUsuarios.getValueAt(fila, 2).toString();
                String apellido = vista.jtUsuarios.getValueAt(fila, 3).toString();
                String direccion = vista.jtUsuarios.getValueAt(fila, 4).toString();
                String celular = vista.jtUsuarios.getValueAt(fila, 5).toString();
                String usu = vista.jtUsuarios.getValueAt(fila, 6).toString();
                String password = vista.jtUsuarios.getValueAt(fila, 7).toString();
                String cargo = vista.jtUsuarios.getValueAt(fila, 8).toString();

                vista.txtId.setText(String.valueOf(idUsuario));
                vista.txtDni.setText(dni);
                vista.txtNombre.setText(nombre);
                vista.txtApellido.setText(apellido);
                vista.txtDireccion.setText(direccion);
                vista.txtCelular.setText(celular);
                vista.txtUser.setText(usu);
                vista.txtPass.setText(password);
                vista.jcbCargo.setSelectedItem(cargo);

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

    public usuarioControlador(vistaUsuarios vista, Usuario modelo, UsuarioDAO dao, String cargoMenu) {
        this.vista = vista;
        this.modelo = modelo;
        this.dao = dao;
        this.cargoMenu = cargoMenu;
        llenarComboBoxCargo();
        listarUsuarios();

        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
        this.vista.jtUsuarios.addMouseListener(oyenteRaton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            agregarUsuario();
            limpiarTabla();
            listarUsuarios();
            limpiarCampos();
        } else if (e.getSource() == vista.btnActualizar) {
            actualizarUsuario();
            limpiarTabla();
            listarUsuarios();
            limpiarCampos();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        } else if (e.getSource() == vista.btnEliminar) {
            eliminarUsuario();
            limpiarTabla();
            listarUsuarios();
            limpiarCampos();
        } else if (e.getSource() == vista.btnBuscar) {
            limpiarTabla();
            buscar();
        } else if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listarUsuarios();
        }
    }

    void listarUsuarios() {
        List<Usuario> lista = dao.read();
        modeloTabla = (DefaultTableModel) vista.jtUsuarios.getModel();

        Object[] ob = new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getDni();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getApellido();
            ob[4] = lista.get(i).getDireccion();
            ob[5] = lista.get(i).getCelular();
            ob[6] = lista.get(i).getUser();
            ob[7] = lista.get(i).getPass();
            ob[8] = lista.get(i).getCargo().getNombre();
            modeloTabla.addRow(ob);
        }
        vista.jtUsuarios.setModel(modeloTabla);
    }

    void agregarUsuario() {
        if (vista.txtDni.equals("") || vista.txtNombre.equals("") || vista.txtApellido.equals("")
                || vista.txtDireccion.equals("") || vista.txtCelular.equals("") || vista.txtUser.equals("")
                || vista.txtPass.equals("") || vista.jcbCargo.getSelectedItem().toString().equals("Seleccione Cargo...")
                || vista.jcbCargo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            String dni = vista.txtDni.getText();
            String nombre = vista.txtNombre.getText();
            String apellido = vista.txtApellido.getText();
            String direccion = vista.txtDireccion.getText();
            String celular = vista.txtCelular.getText();
            String usu = vista.txtUser.getText();
            String password = vista.txtPass.getText();
            CargoDAO daoCargo = new CargoDAO();
            Cargo cargo = new Cargo();
            cargo = daoCargo.read(vista.jcbCargo.getSelectedItem().toString());
            Usuario u = new Usuario(dni, nombre, apellido, direccion, celular, usu, password, cargo);
            dao.create(u);

        }
    }

    void actualizarUsuario() {
        int fila = vista.jtUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            if (vista.txtDni.equals("") || vista.txtNombre.equals("") || vista.txtApellido.equals("")
                    || vista.txtDireccion.equals("") || vista.txtCelular.equals("") || vista.txtUser.equals("")
                    || vista.txtPass.equals("") || vista.jcbCargo.getSelectedItem().toString().equals("Seleccione Cargo...")
                    || vista.jcbCargo.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
            } else {
                String dni = vista.txtDni.getText();
                String nombre = vista.txtNombre.getText();
                String apellido = vista.txtApellido.getText();
                String direccion = vista.txtDireccion.getText();
                String celular = vista.txtCelular.getText();
                String usu = vista.txtUser.getText();
                String password = vista.txtPass.getText();
                CargoDAO daoCargo = new CargoDAO();
                Cargo cargo = new Cargo();
                cargo = daoCargo.read(vista.jcbCargo.getSelectedItem().toString());
                Usuario u = new Usuario(idUsuario, dni, nombre, apellido, direccion, celular, usu, password, cargo);
                dao.update(u);
            }
        }
    }

    void eliminarUsuario() {
        int fila = vista.jtUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            dao.delete(idUsuario);

        }
    }

    void llenarComboBoxCargo() {
        if (cargoMenu.equalsIgnoreCase("Administrador")) {
            CargoDAO daoCargo = new CargoDAO();
            List<Cargo> lista = daoCargo.read();
            vista.jcbCargo.removeAllItems();
            vista.jcbCargo.addItem("Seleccione Cargo...");
            for (int i = 0; i < lista.size(); i++) {
                if(lista.get(i).getNombre().equalsIgnoreCase("Cajero")){
                    vista.jcbCargo.addItem(lista.get(i).getNombre());
                }
            }
        }else{
            CargoDAO daoCargo = new CargoDAO();
            List<Cargo> lista = daoCargo.read();
            vista.jcbCargo.removeAllItems();
            vista.jcbCargo.addItem("Seleccione Cargo...");
            for (int i = 0; i < lista.size(); i++) {
                vista.jcbCargo.addItem(lista.get(i).getNombre());
            }
        }

    }

    void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtDni.setText("");
        vista.txtNombre.setText("");
        vista.txtApellido.setText("");
        vista.txtDireccion.setText("");
        vista.txtCelular.setText("");
        vista.txtUser.setText("");
        vista.txtPass.setText("");
        vista.jcbCargo.setSelectedIndex(0);
        vista.txtDni.requestFocus();
    }

    void limpiarTabla() {
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }

    void buscar() {
        String palabra = vista.txtDato.getText().trim();
        List<Usuario> lista = dao.buscarUsuarios(palabra);
        modeloTabla = (DefaultTableModel) vista.jtUsuarios.getModel();

        Object[] ob = new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getDni();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getApellido();
            ob[4] = lista.get(i).getDireccion();
            ob[5] = lista.get(i).getCelular();
            ob[6] = lista.get(i).getUser();
            ob[7] = lista.get(i).getPass();
            ob[8] = lista.get(i).getCargo().getNombre();
            modeloTabla.addRow(ob);
        }
        vista.jtUsuarios.setModel(modeloTabla);

    }
}
