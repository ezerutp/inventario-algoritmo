package appinventario.controllers;

import appinventario.database.DBSqlManager;
import appinventario.models.Producto;
import java.util.List;

public class ProductoController {

    private DBSqlManager<Producto> db;

    /**
     * Constructor para ProdcutoController.
     * Inicializa el objeto DBSqlManager para manejar productos.
     */
    public ProductoController() {
        this.db = new DBSqlManager<>(Producto.class);
    }
    
    /**
     * Registra un nuevo producto en el sistema.
     * <p>
     * Este método asigna un nuevo ID al producto y luego intenta registrar el producto
     * en el sistema utilizando el objeto DBSqlManager. Si el producto se registra con éxito,
     * retorna true; de lo contrario, retorna false.
     * </p>
     * 
     * @param producto El producto a registrar.
     * @return true si el producto fue registrado con éxito, false en caso contrario.
     */
    public boolean registrarProducto(Producto producto){
        return this.db.registrar(producto);
    }
    
    /**
     * Obtiene un producto por su ID.
     * <p>
     * Este método busca en el sistema el producto que coincida con el ID proporcionado.
     * Si encuentra un producto con el ID correspondiente, lo retorna; de lo contrario, retorna null.
     * </p>
     * 
     * @param id El ID del producto a buscar.
     * @return El producto encontrado o null si no se encuentra.
     */
    public Producto obtenerProducto(int id){

        List<Producto> listaProducto = obtenerTodosProductos();

        //algoritmo de busqueda secuencial
        for(Producto p : listaProducto){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    /**
     * Obtiene un producto por su nombre.
     * 
     * @param nombre El nombre de producto a buscar.
     * @return El producto correspondiente al nombre de producto proporcionado.
     */
    public Producto obtenerProductoPorNombre(String nombre) {
        List<Producto> listUsers =  obtenerTodosProductos();

        //Algoritmo de busqueda secuencial
        for (Producto u : listUsers) {
            if (u.getNombre().equals(nombre)) {
                return u;
            }
        }
        return null;
    }
    
    /**
     * Obtiene todos los productos registrados en el sistema.
     * <p>
     * Este método lee todos los productos registrados en el sistema
     * y los retorna en forma de una lista.
     * </p>
     * 
     * @return La lista de productos registrados.
     */
    public List<Producto> obtenerTodosProductos(){
        return this.db.allRecords();
    }
    
       /**
     * Obtiene todos los Productos almacenados pero ordenados por su precio de menor a mayor
     * @return Lista de Productos
     */
    public List<Producto> obtenerTodosProductosOrdenados(){
        List<Producto> lista = obtenerTodosProductos();
        int tamaño = lista.size();
        
        //Algoritmo de ordenamiento
        for(int i = 0; i < tamaño - 1; i++){
            for(int j = 0; j < tamaño - i - 1; j++){
                if(lista.get(j).getPrecio()> lista.get(j+1).getPrecio()){
                    //Si es verdadero intercambiamos
                    Producto tmp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, tmp);
                }
            }
        }
        
        return lista;
    }

    
    /**
     * Elimina un producto por su ID.
     * <p>
     * Este método intenta eliminar un producto basado en el ID proporcionado.
     * Si el producto se elimina con éxito, retorna true; de lo contrario, retorna false.
     * </p>
     * 
     * @param id El ID del producto a eliminar.
     * @return true si el producto fue eliminado con éxito, false en caso contrario.
     */
    public boolean eliminarProducto(int id){
        return db.eliminarPorId(id);
    }
    
    /**
     * Actualiza la información de un producto por su ID.
     * <p>
     * Este método intenta actualizar la información de un producto basado en el ID proporcionado.
     * Si el producto se actualiza con éxito, retorna true; de lo contrario, retorna false.
     * </p>
     * 
     * @param id El ID del producto a actualizar.
     * @param producto El producto con la información actualizada.
     * @return true si el producto fue actualizado con éxito, false en caso contrario.
     */
    public boolean actualizarProducto(int id, Producto producto){
        return db.actualizarPorId(id, producto);
    }
    
}
