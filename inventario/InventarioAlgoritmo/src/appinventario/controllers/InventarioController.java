package appinventario.controllers;

import java.util.List;

import appinventario.database.DBSqlManager;
import appinventario.models.Inventario;
import appinventario.models.Suministro;
import appinventario.utils.ProductoStock;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class InventarioController {
    
    private DBSqlManager<Inventario> db;
    private SuministroController sucontrol;
    private ConsumoController cocontrol;

    /**
     * Constructor de la clase InventarioController. Inicializa la base de datos para la clase Inventario.
     */
    public InventarioController() {
        this.db = new DBSqlManager<>(Inventario.class);
        this.sucontrol = new SuministroController();
        this.cocontrol = new ConsumoController();
    }

    /**
     * Registra un nuevo inventario en la base de datos.
     *
     * @param inv El inventario a registrar.
     * @return true si el inventario se registró correctamente, false en caso contrario.
     */
    public boolean registrarInventario(Inventario inv) {
        return this.db.registrar(inv);
    }

    /**
     * Obtiene un inventario de la base de datos por su identificador.
     *
     * @param id El identificador del inventario a obtener.
     * @return El inventario correspondiente al identificador especificado, o null si no se encuentra.
     */
    public Inventario obtenerInventario(int id) {

        List<Inventario> listaInventario = obtenerTodosInventarios();

        //algoritmo de busqueda secuencial
        for(Inventario i : listaInventario){
            if(i.getId() == id){
                return i;
            }
        }
        return null;
    }

    /**
     * Obtiene todos los inventarios almacenados en la base de datos.
     *
     * @return Una lista de todos los inventarios almacenados.
     */
    public List<Inventario> obtenerTodosInventarios() {
        return this.db.allRecords();
    }

    /**
     * Elimina un inventario de la base de datos por su identificador.
     *
     * @param id El identificador del inventario a eliminar.
     * @return true si el inventario se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarInventario(int id) {
        return this.db.eliminarPorId(id);
    }

    /**
     * Actualiza un inventario en la base de datos por su identificador.
     *
     * @param id El identificador del inventario a actualizar.
     * @param inv El nuevo inventario con los datos actualizados.
     * @return true si el inventario se actualizó correctamente, false en caso contrario.
     */
    public boolean actualizarInventario(int id, Inventario inv) {
        return this.db.actualizarPorId(id, inv);
    }

    /**
     * Calcula el total de un producto en inventario restando la cantidad de suministros y consumos.
     *
     * @param producto El nombre del producto a buscar en inventario.
     * @return La cantidad total del producto en inventario.
     */
    public int totalProductoInventario(String producto) {
        int suministro= sucontrol.obtenerTotalProductoPorNombre(producto);
        int consumo = cocontrol.obtenerTotalProductoPorNombre(producto);
        int total = suministro - consumo;
        return total;
    }
    
    /**
     * lista de productos con stock en inventario.
     *
     * @return Una lista de productos con stock en inventario.
     */
    public List<ProductoStock> listaProductosConStock() {
        
        List<ProductoStock> lista = new ArrayList<>();
        //Uso de HashSet para evitar que se repitan los items de la lista productos ingresados
        Set<String> nombresProductos = new HashSet<>();
        List<Suministro> sum = sucontrol.obtenerTodosSuministros();

        //Algoritmo de busqueda secuencial
        for (Suministro s : sum) {
            if (totalProductoInventario(s.getProducto().getNombre()) > 0) {
                nombresProductos.add(s.getProducto().getNombre());
            }
        }

        //segundo algoritmo de busqueda
        for (String s : nombresProductos) {
            int cantidad = totalProductoInventario(s);
            ProductoStock pstock = new ProductoStock(s, cantidad);
            lista.add(pstock);
        }
        return lista;
    }
}
