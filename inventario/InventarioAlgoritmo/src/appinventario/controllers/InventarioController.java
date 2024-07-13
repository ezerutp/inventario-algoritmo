package appinventario.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;

import appinventario.database.DBSqlManager;
import appinventario.models.Inventario;
import appinventario.models.Producto;
import appinventario.utils.ProductoStock;

public class InventarioController {
    
    private DBSqlManager<Inventario> db;

    /**
     * Constructor de la clase InventarioController. Inicializa la base de datos para la clase Inventario.
     */
    public InventarioController() {
        this.db = new DBSqlManager<>(Inventario.class);
    }

    /**
     * Registra un nuevo inventario en la base de datos.
     *
     * @param inv El inventario a registrar.
     * @return true si el inventario se registró correctamente, false en caso contrario.
     */
    public boolean registrarInventario(Inventario inv) {
        Inventario p = obtenerInventario(inv.getProducto());
        if (p == null) {
            this.db.registrar(inv);
            return true;
        } else {
            int cantidadOld = p.getCantidad();
            int cantidadNew = cantidadOld + inv.getCantidad();
            System.out.println(cantidadNew);
            if (cantidadNew > 0) {
                p.setCantidad(cantidadOld + inv.getCantidad());
                actualizarInventario(p.getId(), p);
                return true;
            } else {
                eliminarInventario(p.getId());
                return true;
            }
        }
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
     * Obtiene un inventario de la base de datos por su identificador.
     *
     * @param producto El producto dentro de inventario.
     * @return El inventario correspondiente al identificador especificado, o null si no se encuentra.
     */
    public Inventario obtenerInventario(Producto producto) {

        List<Inventario> listaInventario = obtenerTodosInventarios();

        //algoritmo de busqueda secuencial
        for(Inventario i : listaInventario){
            if(i.getProducto().getId() == producto.getId()){
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
    private boolean actualizarInventario(int id, Inventario inv) {
        return this.db.actualizarPorId(id, inv);
    }

    /**
     * Obtiene una lista de productos con su respectivo stock.
     *
     * @return Una lista de objetos ProductoStock que contiene el nombre del producto y la cantidad en inventario.
     */
    public List<ProductoStock> pStock(){
        List<Inventario> inventarios = obtenerTodosInventarios();
        List<ProductoStock> lista = new ArrayList<>();
        for (Inventario inv : inventarios) {
            ProductoStock p = new ProductoStock(inv.getProducto().getNombre(), inv.getCantidad());
            lista.add(p);
        }
        return lista;
    }

    /**
     * Obtiene una lista de productos críticos basada en un predicado de cantidad.
     *
     * @param predicado El predicado que determina si un producto es crítico basado en su cantidad.
     * @return Una pila de productos críticos.
     */
    public Stack<Producto> productosCriticos(Predicate<Integer> predicado) {
        List<Inventario> lista = obtenerTodosInventarios();
        Stack<Producto> stack = new Stack<>();
        for (Inventario i : lista) {
            if (predicado.test(i.getCantidad())){
                stack.push(i.getProducto());
            }
        }
        return stack;
    }
}
