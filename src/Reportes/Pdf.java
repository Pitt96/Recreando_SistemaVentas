package Reportes;

import Modelo.Tienda;
import Modelo.Venta;
import ModeloDAO.Detalle_VentaDAO;
import ModeloDAO.TiendaDAO;
import ModeloDAO.VentaDAO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Pdf {

    public void generarPdf(String id) {
        VentaDAO dao = new VentaDAO();
        Detalle_VentaDAO vdDA = new Detalle_VentaDAO();
        Venta venta = dao.read(id);
        venta.setDetalles(vdDA.buscarVenta(id));
        TiendaDAO daoTienda = new TiendaDAO();
        Tienda tienda = daoTienda.leer();
        try {
            FileOutputStream archivo;
            File file = new File("src/reportegenerados/venta_" + venta.getCodigo() + ".pdf");
            if (file.exists() == false) {
                archivo = new FileOutputStream(file);

                Document doc = new Document();
                PdfWriter.getInstance(doc, archivo);

                doc.open();
                Image img = Image.getInstance("src/imagenes/logo_producto.png");

                //Encabezado
                Paragraph fecha = new Paragraph();
                Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
                fecha.add(Chunk.NEWLINE);
                fecha.add("Boleta: " + venta.getCodigo() + "\nFecha : " + venta.getFecha() + "\n\n");

                PdfPTable Encabezado = new PdfPTable(4);
                Encabezado.setWidthPercentage(100);
                Encabezado.getDefaultCell().setBorder(0);
                float[] ColumnaEncabezado = new float[]{45f, 10f, 70f, 40f};
                Encabezado.setWidths(ColumnaEncabezado);
                Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

                Encabezado.addCell(img);
                Encabezado.addCell("");
                Encabezado.addCell("Ruc:       " + tienda.getRuc()
                        + "\nNombre:    " + tienda.getNombre()
                        + "\nTelefono:  " + tienda.getTelefono()
                        + "\nDireccion: " + tienda.getDireccion()
                        + "\nRazon:     " + tienda.getNombre() + "SAC");
                Encabezado.addCell(fecha);

                doc.add(Encabezado);
                //Clientes:
                Paragraph cli = new Paragraph();
                cli.add(Chunk.NEWLINE);
                cli.add("Datos del Cliente:\n\n");
                doc.add(cli);

                PdfPTable tablacli = new PdfPTable(4);
                tablacli.setWidthPercentage(100);
                tablacli.getDefaultCell().setBorder(0);
                float[] Columnacli = new float[]{20f, 50f, 0f, 40f};
                tablacli.setWidths(Columnacli);
                tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell cl1 = new PdfPCell(new Phrase("Dni", negrita));
                PdfPCell cl2 = new PdfPCell(new Phrase("Nombre", negrita));
                PdfPCell cl3 = new PdfPCell(new Phrase("Telefono", negrita));
                PdfPCell cl4 = new PdfPCell(new Phrase("Direccion", negrita));
                cl1.setBorder(0);
                cl2.setBorder(0);
                cl3.setBorder(0);
                cl4.setBorder(0);

                tablacli.addCell(cl1);
                tablacli.addCell(cl2);
                tablacli.addCell(cl3);
                tablacli.addCell(cl4);
                tablacli.addCell(venta.getCliente().getDni());
                tablacli.addCell(venta.getCliente().getNombre());
                tablacli.addCell(venta.getCliente().getCelular());
                tablacli.addCell(venta.getCliente().getDireccion());

                doc.add(tablacli);

                //AGREGAR OTRO 
                Paragraph salto = new Paragraph();
                salto.add(Chunk.NEWLINE);
                salto.add("Detalle de la venta:\n\n");
                doc.add(salto);

                Paragraph info = new Paragraph();
                info.add("Total a Pagar :");
                info.setAlignment(Element.ALIGN_CENTER);

                //Productos
                PdfPTable tablapro = new PdfPTable(4);

                tablapro.setWidthPercentage(100);
                tablapro.getDefaultCell().setBorder(0);
                float[] Columnapro = new float[]{50f, 10f, 20f, 20f};
                tablapro.setWidths(Columnapro);
                tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell pro1 = new PdfPCell(new Phrase("PRODUCTO", negrita));
                PdfPCell pro2 = new PdfPCell(new Phrase("CANT", negrita));
                PdfPCell pro3 = new PdfPCell(new Phrase("PRECIO", negrita));
                PdfPCell pro4 = new PdfPCell(new Phrase("TOTAL", negrita));
                pro1.setBorder(0);
                pro2.setBorder(0);
                pro3.setBorder(0);
                pro4.setBorder(0);

                pro1.setBackgroundColor(BaseColor.YELLOW);
                pro2.setBackgroundColor(BaseColor.YELLOW);
                pro3.setBackgroundColor(BaseColor.YELLOW);
                pro4.setBackgroundColor(BaseColor.YELLOW);
                tablapro.addCell(pro1);
                tablapro.addCell(pro2);
                tablapro.addCell(pro3);
                tablapro.addCell(pro4);

                for (int i = 0; i < venta.getDetalles().size(); i++) {
                    tablapro.addCell(venta.getDetalles().get(i).getProducto().getNombre());
                    tablapro.addCell(String.valueOf(venta.getDetalles().get(i).getCantidad()));
                    tablapro.addCell(String.valueOf(venta.getDetalles().get(i).getPrecioUnitario()));
                    tablapro.addCell(String.valueOf(venta.getDetalles().get(i).getTotal()));

                }

                tablapro.addCell("");
                tablapro.addCell("");
                tablapro.addCell(info);
                tablapro.addCell(String.valueOf(venta.getTotal()));
                doc.add(tablapro);

                Paragraph saludo = new Paragraph();
                saludo.add(Chunk.NEWLINE);
                saludo.add(tienda.getMensaje());
                saludo.setAlignment(Element.ALIGN_CENTER);
                doc.add(saludo);

                doc.close();
                archivo.close();
                
                Desktop.getDesktop().open(file);
            }else{
                Desktop.getDesktop().open(file);
            }

            

        } catch (DocumentException | IOException e) {
            System.out.println("Error   " + e.getMessage());
        }
    }
}
