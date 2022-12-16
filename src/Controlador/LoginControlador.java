package Controlador;

import Modelo.Cliente;
import Modelo.Usuario;
import ModeloDAO.CantidadDAO;
import ModeloDAO.ClienteDAO;
import ModeloDAO.LoginDAO;
import ModeloDAO.ProveedorDAO;
import ModeloDAO.ServicioCantidad;
import Vista.Login;
import Vista.Menu;
import Vista.vistaVender;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginControlador implements ActionListener {

    Login login = new Login();
    Usuario u = new Usuario();
    LoginDAO dao = new LoginDAO();

    vistaVender venta = new vistaVender();

    public LoginControlador(Login login, Usuario u, LoginDAO dao) {
        this.login = login;
        this.u = u;
        this.dao = dao;
        this.login.btnIngresar.addActionListener(this);
        this.login.btnSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.btnIngresar) {
            validar();
        } else if (e.getSource() == login.btnSalir) {
            System.exit(0);
        }
    }

    public void validar() {
        String usuario = login.txtUsuario.getText();
        String contra = login.txtContraseña.getText();

        if (usuario.equals("") || contra.equals("")) {
            JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
            login.txtUsuario.requestFocus();
        } else {
            u = (Usuario) dao.validarUsuario(usuario, contra);
            System.out.println(u.getUser());
            if (u.getUser() != null && u.getPass() != null) {
                JOptionPane.showMessageDialog(null, "BIENVENIDO\n " + u.getNombre().toUpperCase());
                Menu m = new Menu();
                m.setVisible(true);
                login.dispose();
                m.setVisible(true);
                actualizarCantidades(m);
                m.u = u;
                m.setLocationRelativeTo(null);
                if(u.getCargo().getNombre().equalsIgnoreCase("cajero")){
                    m.btn_Usuarios.setEnabled(false);
                    
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Deben ingresar datos validos");
                limpiarCasillas();
                login.txtUsuario.requestFocus();
            }
        }
    }

    public void limpiarCasillas() {
        login.txtUsuario.setText("");
        login.txtContraseña.setText("");
    }

    void actualizarCantidades(Menu m) {
        ServicioCantidad serv = new ServicioCantidad();
        int cantClientes = serv.getCantClientes();
        int cantProveedor = serv.getCantProveedores();
        int cantProductos = serv.getCantProductos();
        int cantUsuarios = serv.getCantUsuarios();
        int cantVentas = serv.getCantVentas();

        m.lblNombreUsuario.setText(u.getNombre().toUpperCase());
        m.lblCargoUsuario.setText(u.getCargo().getNombre());

        m.lblCantClientes.setText(String.valueOf(cantClientes));
        m.lblCantProveedor.setText(String.valueOf(cantProveedor));
        m.lblCantProducto.setText(String.valueOf(cantProductos));
        m.lblCantUsuarios.setText(String.valueOf(cantUsuarios));
        m.lblCantVentas.setText(String.valueOf(cantVentas));
    }
}
