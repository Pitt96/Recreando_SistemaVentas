
package Controlador;

import Modelo.Tienda;
import ModeloDAO.TiendaDAO;
import Vista.vistaConfig;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class tiendaControlador implements ActionListener{
    
    vistaConfig vista = new vistaConfig();
    Tienda modelo=new Tienda();
    TiendaDAO dao = new TiendaDAO();
    String menuCargo;
    public tiendaControlador(vistaConfig vista,Tienda modelo,TiendaDAO dao,String menuCargo) {
        this.vista=vista;
        this.modelo=modelo;
        this.dao=dao;
        this.menuCargo=menuCargo;
        listar();
        roles();
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            crearInfo();
            listar();
        } else if (e.getSource() == vista.btnActualizar) {
            actualizar();
            listar();
        }
    }
    
    void roles(){
        if(menuCargo.equalsIgnoreCase("cajero")){
            vista.txtID.setEnabled(false);
            vista.txtNombre.setEnabled(false);
            vista.txtRuc.setEnabled(false);
            vista.txtDireccion.setEnabled(false);
            vista.txtTelefono.setEnabled(false);
            vista.txtAreaMensaje.setEnabled(false);
            vista.btnGuardar.setEnabled(false);
            vista.btnActualizar.setEnabled(false);
        }
    }
    void listar(){
        modelo=dao.leer();
        vista.txtID.setText(String.valueOf(modelo.getId()));
        vista.txtRuc.setText(modelo.getRuc());
        vista.txtNombre.setText(modelo.getNombre());
        vista.txtTelefono.setText(modelo.getTelefono());
        vista.txtDireccion.setText(modelo.getDireccion());
        vista.txtAreaMensaje.setText(modelo.getMensaje());
    }
    
    void actualizar(){
        if(vista.txtID.equals("") || vista.txtRuc.equals("") || vista.txtNombre.equals("") || vista.txtTelefono.equals("") || vista.txtDireccion.equals("") || vista.txtAreaMensaje.equals("")){
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        }else{
            modelo.setId(Integer.parseInt(vista.txtID.getText()));
            modelo.setRuc(vista.txtRuc.getText());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setTelefono(vista.txtTelefono.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
            modelo.setMensaje(vista.txtAreaMensaje.getText());
            dao.update(modelo);
            
        }
    }
    void crearInfo(){
        if(vista.txtID.equals("") || vista.txtRuc.equals("") || vista.txtNombre.equals("") || vista.txtTelefono.equals("") || vista.txtDireccion.equals("") || vista.txtAreaMensaje.equals("")){
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        }else{
            modelo.setId(Integer.parseInt(vista.txtID.getText()));
            modelo.setRuc(vista.txtRuc.getText());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setTelefono(vista.txtTelefono.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
            modelo.setMensaje(vista.txtAreaMensaje.getText());
            dao.create(modelo);
            JOptionPane.showMessageDialog(null, "INFORMACION DE LA TIENDA AGREGADA");
        }
    }
}
