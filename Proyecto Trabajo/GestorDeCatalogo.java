import java.io.IOException;
import java.util.Scanner;

public class GestorDeCatalogo {
    private Catalogo catalogo;
    private Scanner scanner;

    public GestorDeCatalogo() {
        catalogo = new Catalogo();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("Gestor de Catálogo");
            System.out.println("1. Alta de producto");
            System.out.println("2. Baja de producto");
            System.out.println("3. Modificación de producto");
            System.out.println("4. Listar productos");
            System.out.println("5. Guardar productos en archivo");
            System.out.println("6. Cargar productos desde archivo");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    altaProducto();
                    break;
                case 2:
                    bajaProducto();
                    break;
                case 3:
                    modificarProducto();
                    break;
                case 4:
                    catalogo.listarProductos();
                    break;
                case 5:
                    guardarProductos();
                    break;
                case 6:
                    cargarProductos();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void altaProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Cantidad del producto: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        Producto producto = new Producto(nombre, precio, cantidad);
        catalogo.agregarProducto(producto);
        System.out.println("Producto añadido.");
    }

    private void bajaProducto() {
        System.out.print("Nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();
        catalogo.eliminarProducto(nombre);
        System.out.println("Producto eliminado si existía en el catálogo.");
    }

    private void modificarProducto() {
        System.out.print("Nombre del producto a modificar: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo precio del producto: ");
        double nuevoPrecio = scanner.nextDouble();
        System.out.print("Nueva cantidad del producto: ");
        int nuevaCantidad = scanner.nextInt();
        scanner.nextLine();

        catalogo.modificarProducto(nombre, nuevoPrecio, nuevaCantidad);
        System.out.println("Producto modificado si existía en el catálogo.");
    }

    private void guardarProductos() {
        try {
            catalogo.guardarProductosEnArchivo("productos.txt");
            System.out.println("Productos guardados en archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar productos en archivo.");
        }
    }

    private void cargarProductos() {
        try {
            catalogo.cargarProductosDesdeArchivo("productos.txt");
            System.out.println("Productos cargados desde archivo.");
        } catch (IOException e) {
            System.out.println("Error al cargar productos desde archivo.");
        }
    }

    public static void main(String[] args) {
        GestorDeCatalogo gestor = new GestorDeCatalogo();
        gestor.iniciar();
    }
}
