package Controlador;

import Modelo.Categoria_Producto;
import Modelo.Producto;
import Modelo.Proveedor;
import ModeloDAO.CategoriaProductoDAO;
import ModeloDAO.ProductoDAO;
import ModeloDAO.ProveedorDAO;
import ModeloDAO.ServicioCantidad;
import Vista.Menu;
import Vista.vistaProductos;
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
public class productoControlador implements ActionListener {

    int idProducto;
    vistaProductos vista = new vistaProductos();
    Producto modelo = new Producto();
    ProductoDAO dao = new ProductoDAO();

    DefaultTableModel modeloTabla = new DefaultTableModel();
    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.jtProducto.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                idProducto = Integer.valueOf(vista.jtProducto.getValueAt(fila, 0).toString());
                String codigo = vista.jtProducto.getValueAt(fila, 1).toString();
                String nombre = vista.jtProducto.getValueAt(fila, 2).toString();
                String categoria = vista.jtProducto.getValueAt(fila, 3).toString();
                String proveedor = vista.jtProducto.getValueAt(fila, 4).toString();
                String precio = vista.jtProducto.getValueAt(fila, 5).toString();
                String stock = vista.jtProducto.getValueAt(fila, 6).toString();

                vista.txtId.setText(String.valueOf(idProducto));
                vista.txtCodigo.setText(codigo);
                vista.txtNombre.setText(nombre);
                vista.txtPrecio.setText(precio);
                vista.txtStock.setText(stock);
                vista.jcCategoria.setSelectedItem(categoria);
                vista.jcProveedor.setSelectedItem(proveedor);
                

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

    public productoControlador(vistaProductos vista, Producto modelo, ProductoDAO dao) {
        this.vista = vista;
        this.modelo = modelo;
        this.dao = dao;
        llenarComboBoxCategoria();
        llenarComboBoxProveedor();
        listar();
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
        this.vista.jtProducto.addMouseListener(oyenteRaton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            agregar();
            limpiarTabla();
            listar();
            limpiarCampos();
        } else if (e.getSource() == vista.btnActualizar) {
            actualizar();
            limpiarTabla();
            listar();
            limpiarCampos();
        } else if (e.getSource() == vista.btnEliminar) {
            eliminar();
            limpiarTabla();
            listar();
            limpiarCampos();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }else if(e.getSource() == vista.btnBuscar){
            limpiarTabla();
            buscar();
        }else if(e.getSource() == vista.btnListar){
            limpiarTabla();
            listar();
        }
    }

    void listar() {
        List<Producto> lista = dao.read();
        modeloTabla = (DefaultTableModel) vista.jtProducto.getModel();
        Object[] ob = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getCodigo();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getCategoria().getNombre();
            ob[4] = lista.get(i).getProveedor().getNombres();
            ob[5] = lista.get(i).getPrecio();
            ob[6] = lista.get(i).getStock();
            modeloTabla.addRow(ob);
        }
        vista.jtProducto.setModel(modeloTabla);
    }

    void agregar() {
        if (vista.txtNombre.equals("") || vista.txtCodigo.equals("") || vista.txtPrecio.equals("") || vista.txtStock.equals("")
                || vista.jcCategoria.getSelectedItem().toString().equals("Seleccione Cargo...") || vista.jcCategoria.getSelectedItem() == null
                || vista.jcProveedor.getSelectedItem().toString().equals("Seleccione Cargo...") || vista.jcProveedor.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
        } else {
            String codigo = vista.txtCodigo.getText();
            String nombre = vista.txtNombre.getText();
            double precio = Double.parseDouble(vista.txtPrecio.getText());
            int stock = Integer.parseInt(vista.txtStock.getText());
            
            Categoria_Producto categoria = new Categoria_Producto();
            CategoriaProductoDAO catDao = new CategoriaProductoDAO();
            String cat=vista.jcCategoria.getSelectedItem().toString();
            categoria = catDao.read(cat);
            
            Proveedor proveedor = new Proveedor();
            ProveedorDAO provDao = new ProveedorDAO();
            String prov=vista.jcProveedor.getSelectedItem().toString();
            proveedor = provDao.read(prov);
            
            System.out.println("Codigo : "+codigo);
            System.out.println("Nombre : "+nombre);
            System.out.println("Precio : "+precio);
            System.out.println("Stock : "+stock);
            System.out.println("Categoria : "+cat);
            System.out.println("Proveedor : "+prov);
            Producto prod = new Producto(codigo, nombre, precio, stock, categoria, proveedor);
            
            dao.create(prod);
            
           
        }
    }

    void actualizar() {
        int fila = vista.jtProducto.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            if (vista.txtNombre.equals("") || vista.txtCodigo.equals("") || vista.txtPrecio.equals("") || vista.txtStock.equals("")
                    || vista.jcCategoria.getSelectedItem().toString().equals("Seleccione Categoria...") || vista.jcCategoria.getSelectedItem() == null
                    || vista.jcProveedor.getSelectedItem().toString().equals("Seleccione Proveedor...") || vista.jcProveedor.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "LLENAR TODOS LOS CAMPOS");
            } else {
                String codigo = vista.txtCodigo.getText();
                String nombre = vista.txtNombre.getText();
                double precio = Double.parseDouble(vista.txtPrecio.getText());
                int stock = Integer.parseInt(vista.txtStock.getText());
                Categoria_Producto categoria = new Categoria_Producto();
                CategoriaProductoDAO catDao = new CategoriaProductoDAO();
                categoria = catDao.read(vista.jcCategoria.getSelectedItem().toString());
                Proveedor proveedor = new Proveedor();
                ProveedorDAO provDao = new ProveedorDAO();
                proveedor = provDao.read(vista.jcProveedor.getSelectedItem().toString());
                Producto prod = new Producto(idProducto, codigo, nombre, precio, stock, categoria, proveedor);
                System.out.println("ID PRODUCTO: "+idProducto);
                dao.update(prod);
            }
        }
    }

    void eliminar() {
        int fila = vista.jtProducto.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        } else {
            dao.delete(idProducto);
           
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }

    void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
        vista.txtStock.setText("");
        vista.jcCategoria.setSelectedIndex(0);
        vista.jcProveedor.setSelectedIndex(0);
        vista.txtCodigo.requestFocus();
    }
    
    void buscar(){
        String palabra=vista.txtDato.getText().trim();
        List<Producto> lista = dao.buscarProductos(palabra);
        modeloTabla = (DefaultTableModel) vista.jtProducto.getModel();
        Object[] ob = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getCodigo();
            ob[2] = lista.get(i).getNombre();
            ob[3] = lista.get(i).getCategoria().getNombre();
            ob[4] = lista.get(i).getProveedor().getNombres();
            ob[5] = lista.get(i).getPrecio();
            ob[6] = lista.get(i).getStock();
            modeloTabla.addRow(ob);
        }
        vista.jtProducto.setModel(modeloTabla);
    }
    void llenarComboBoxCategoria() {
        CategoriaProductoDAO daoCat = new CategoriaProductoDAO();
        List<Categoria_Producto> lista = daoCat.read();
        vista.jcCategoria.removeAllItems();
        vista.jcCategoria.addItem("Seleccione Categoria...");
        for (int i = 0; i < lista.size(); i++) {
            vista.jcCategoria.addItem(lista.get(i).getNombre());
        }
    }

    void llenarComboBoxProveedor() {
        ProveedorDAO daoProveedor = new ProveedorDAO();
        List<Proveedor> lista = daoProveedor.read();
        vista.jcProveedor.removeAllItems();
        vista.jcProveedor.addItem("Seleccione Proveedor...");
        for (int i = 0; i < lista.size(); i++) {
            vista.jcProveedor.addItem(lista.get(i).getNombres());
        }
    }
    
    
}
