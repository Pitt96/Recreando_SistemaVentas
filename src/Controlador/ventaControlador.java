package Controlador;

import Modelo.*;
import ModeloDAO.*;
import Reportes.Pdf;
import Vista.Menu;
import Vista.vistaClientes;
import Vista.vistaVender;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ventaControlador implements ActionListener {

    Producto producto = new Producto();
    ProductoDAO proDAO = new ProductoDAO();
    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();

    vistaVender vista = new vistaVender();
    Venta modelo = new Venta();
    VentaDAO dao = new VentaDAO();
    DefaultTableModel modeloTabla = new DefaultTableModel();
    List<LocalStorage> listaCarrito = new ArrayList<>();

    MouseListener oyenteRaton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = vista.jtVenta.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe selecionar una fila");
            } else {
                String[] options = {"Eliminar", "Editar", "Cancelar"};
                MyIconPersonalizado icon = new MyIconPersonalizado("../Imagenes/opciones.png");
                int seleccion = JOptionPane.showOptionDialog(null, "Opciones a Realizar", "Opciones",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
                if (seleccion == 0) {
                    MyIconPersonalizado icon2 = new MyIconPersonalizado("../Imagenes/eliminar_carrito.png");
                    int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, icon2);
                    if (resp == 0) {
                        String codigo = vista.jtVenta.getValueAt(fila, 0).toString();
                        int posicion = getPosicionLista(codigo);
                        eliminarElementoCarrito(posicion);
                        JOptionPane.showMessageDialog(null, "PRODUCTO ELIMINADO DEL CARRITO");
                        limpiarTabla();
                        mostrarCarrito();
                    }
                } else if (seleccion == 1) {

                } else if (seleccion == 2) {

                }
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

    public ventaControlador(vistaVender vista, Venta modelo, VentaDAO dao) {
        this.vista = vista;
        this.modelo = modelo;
        this.dao = dao;

        this.vista.lblCodigoVenta.setText(getCodigoVentas());
        this.vista.jfFecha.setText(getFecha());

        this.vista.btnAgregarCarrito.addActionListener(this);
        this.vista.btnBuscarCliente.addActionListener(this);
        this.vista.btnBuscarProducto.addActionListener(this);

        this.vista.btnAgregarCliente.addActionListener(this);
        this.vista.btnGenerarCompra.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.jtVenta.addMouseListener(oyenteRaton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscarCliente) {
            getCliente();
        } else if (e.getSource() == vista.btnBuscarProducto) {
            getProducto();
        } else if (e.getSource() == vista.btnAgregarCarrito) {
            agregarCarrito();
            limpiarCampos();
            limpiarTabla();
            mostrarCarrito();
        } else if (e.getSource() == vista.btnGenerarCompra) {
            generarCompra();
            
            limpiarTabla();
            limpiarCamposAll();
            
        } else if (e.getSource() == vista.btnCancelar) {
            vaciarCarrito();
            vista.dispose();
        } else if (e.getSource() == vista.btnAgregarCliente) {
            Cliente cliente = new Cliente();
            ClienteDAO cdao = new ClienteDAO();
            vistaClientes vista = new Vista.vistaClientes();
            clienteControlador control = new clienteControlador(vista, cliente, cdao);
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
        }
    }

    void getCliente() {
        if (vista.txtDniCliente.equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO ESTA VACIO");
        } else {
            String dni = vista.txtDniCliente.getText();
            cliente = clienteDAO.read(dni);
            if (cliente.getDni() == null) {
                JOptionPane.showMessageDialog(null, "EL CLIENTE NO EXISTE");
            } else {
                vista.txtNombreCliente.setText(cliente.getNombre());
            }
        }
    }

    void getProducto() {
        if (vista.txtCodigoProducto.equals("")) {
            JOptionPane.showMessageDialog(null, "EL CAMPO ESTA VACIO");
        } else {
            String codigo = vista.txtCodigoProducto.getText();
            producto = proDAO.read(codigo);
            if (producto.getCodigo() == null) {
                JOptionPane.showMessageDialog(null, "EL PRODUCTO NO EXISTE");
            } else {
                vista.txtNombreProducto.setText(producto.getNombre());
                vista.txtPrecioProducto.setText(String.valueOf(producto.getPrecio()));
                vista.txtStockProducto.setText(String.valueOf(producto.getStock()));
            }
        }
    }

    void agregarCarrito() {

        if (vista.txtDniCliente.equals("") || vista.txtCodigoProducto.equals("")
                || vista.txtNombreProducto.equals("") || vista.txtPrecioProducto.equals("")
                || vista.txtStockProducto.equals("") || vista.txtNombreCliente.equals("")) {
            JOptionPane.showMessageDialog(null, "LOS CAMPOS ESTAN VACIO PARA AGREGAR AL CARRITO");
        } else {
            int stock = Integer.parseInt(vista.txtStockProducto.getText());
            if (stock == 0) {
                JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS DISPONIBLES EN LA TIENDA");
            } else {
                int cantidadP = Integer.parseInt(vista.jsCantProducto.getText());
                if (cantidadP <= stock) {
                    double precioP = Double.parseDouble(vista.txtPrecioProducto.getText());
                    String codigo = vista.txtCodigoProducto.getText();
                    String nombreP = vista.txtNombreProducto.getText();
                    double total = precioXcantidad(cantidadP, precioP);
                    producto = proDAO.read(codigo);
                    LocalStorage localStorage = new LocalStorage(cantidadP, precioP, producto, total);
                    listaCarrito.add(localStorage);
                } else {
                    JOptionPane.showMessageDialog(null, "LA CANTIDAD SOBREPASA EL STOCK DISPINIBLE EN LA TIENDA");
                }
            }

        }
    }

    double precioXcantidad(int cant, double precio) {
        return precio * cant;
    }

    void mostrarCarrito() {
        double suma = 0;
        modeloTabla = (DefaultTableModel) vista.jtVenta.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < listaCarrito.size(); i++) {
            ob[0] = (i + 1);
            ob[1] = listaCarrito.get(i).getProducto().getCodigo();
            ob[2] = listaCarrito.get(i).getProducto().getNombre();
            ob[3] = listaCarrito.get(i).getCant();
            ob[4] = listaCarrito.get(i).getPrecio();
            ob[5] = listaCarrito.get(i).getTotal();
            suma += (double) ob[5];
            modeloTabla.addRow(ob);
        }
        vista.lblSumaTotal.setText(String.valueOf(suma));
        vista.jtVenta.setModel(modeloTabla);
    }

    void generarCompra() {
        if (vista.txtDniCliente.equals("") || vista.txtNombreCliente.equals("") || vista.lblSumaTotal.equals("")) {
            JOptionPane.showMessageDialog(null, "NO HAY NADA EN EL CARRITO");
        } else {
            String idVenta = vista.lblCodigoVenta.getText();
            crearVenta(idVenta);
            Pdf pdf = new Pdf();
            pdf.generarPdf(idVenta);
            vista.lblCodigoVenta.setText(getCodigoVentas());
            vista.jfFecha.setText(getFecha());
        }
    }

    String getCodigoVentas() {
        ServicioCantidad serC = new ServicioCantidad();
        generarCodigos gen = new generarCodigos();
        int cant = serC.getCantVentas();
        if (cant == 0) {
            return "V00001";
        } else {
            return gen.getNum("select * from venta");
        }
    }

    String getFecha() {
        obtenerFechaHora fecH = new obtenerFechaHora();
        return fecH.getFec();
    }

    void crearVenta(String idVenta) {
        modelo.setCodigo(idVenta);
        modelo.setTotal(Double.parseDouble(vista.lblSumaTotal.getText()));
        modelo.setFecha(vista.jfFecha.getText());
        String dni = vista.txtDniCliente.getText();
        cliente = clienteDAO.read(dni);
        modelo.setCliente(cliente);
        modelo.setUsuario(vista.u);
        dao.create(modelo);
        for (int i = 0; i < listaCarrito.size(); i++) {
            int cantidad = listaCarrito.get(i).getCant();
            double precioUni = listaCarrito.get(i).getPrecio();
            double total = listaCarrito.get(i).getTotal();
            Producto pt = new Producto();
            pt = listaCarrito.get(i).getProducto();

            Venta vt = new Venta();
            VentaDAO vtDao = new VentaDAO();
            vt = vtDao.read(idVenta);

            Detalle_Venta dt = new Detalle_Venta();
            dt.setCantidad(cantidad);
            dt.setPrecioUnitario(precioUni);
            dt.setTotal(total);
            dt.setProducto(pt);
            dt.setVenta(vt);
            Detalle_VentaDAO dtdao = new Detalle_VentaDAO();
            dtdao.create(dt);

            actualizarSotck(pt.getId(), cantidad);

        }

        vaciarCarrito();
    }

    void vaciarCarrito() {
        listaCarrito.clear();
    }

    void actualizarSotck(int id, int cant) {
        ProductoDAO pdao = new ProductoDAO();
        Producto promodel = new Producto();
        promodel = pdao.read(id);
        pdao.actualizarStock(promodel, cant);
    }

    void limpiarTabla() {
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }

    void limpiarCampos() {
        vista.txtNombreProducto.setText("");
        vista.txtPrecioProducto.setText("");
        vista.txtStockProducto.setText("");
        vista.jsCantProducto.setText("");
    }

    void limpiarCamposAll() {
        vista.txtDniCliente.setText("");
        vista.txtNombreCliente.setText("");
        vista.txtCodigoProducto.setText("");
        vista.txtNombreProducto.setText("");
        vista.txtPrecioProducto.setText("");
        vista.txtStockProducto.setText("");
        vista.jsCantProducto.setText("");
        vista.jfFecha.setText("");
        vista.lblSumaTotal.setText("");
        vista.txtDniCliente.requestFocus();
    }

    void eliminarElementoCarrito(int posicion) {
        listaCarrito.remove(posicion);
    }

    int getPosicionLista(String codigo) {
        int posicion = 0;
        for (int i = 0; i < listaCarrito.size(); i++) {
            if (listaCarrito.get(i).getProducto().getCodigo().equals(codigo)) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

}
