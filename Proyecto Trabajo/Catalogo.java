import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private List<Producto> productos;

    public Catalogo() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(String nombre) {
        productos.removeIf(producto -> producto.getNombre().equals(nombre));
    }

    public void modificarProducto(String nombre, double nuevoPrecio, int nuevaCantidad) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                producto.setPrecio(nuevoPrecio);
                producto.setCantidad(nuevaCantidad);
                break;
            }
        }
    }

    public void listarProductos() {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public void guardarProductosEnArchivo(String nombreArchivo) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
        for (Producto producto : productos) {
            writer.write(producto.toString());
            writer.newLine();
        }
        writer.close();
    }

    public void cargarProductosDesdeArchivo(String nombreArchivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length == 3) {
                String nombre = partes[0];
                double precio = Double.parseDouble(partes[1]);
                int cantidad = Integer.parseInt(partes[2]);
                agregarProducto(new Producto(nombre, precio, cantidad));
            }
        }
        reader.close();
    }
}
