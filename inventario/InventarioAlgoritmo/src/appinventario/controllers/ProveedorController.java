package appinventario.controllers;
import appinventario.database.DBSqlManager;
import appinventario.models.Proveedor;
import appinventario.utils.Ordenamiento;

import java.util.List;
import java.util.function.BiPredicate;

public class ProveedorController {

    private DBSqlManager<Proveedor> db;

    /**
     * Constructor para ProveedorController.
     * Inicializa el objeto DBSqlManager para manejar proveedores.
     */
    public ProveedorController() {
        this.db = new DBSqlManager<>(Proveedor.class);
    }

    /**
     * Registra un nuevo proveedor en el sistema.
     * <p>
     * Este método asigna un nuevo ID al proveedor y luego intenta registrar el proveedor
     * en el sistema utilizando el objeto DBSqlManager. Si el proveedor se registra con éxito,
     * retorna true; de lo contrario, retorna false.
     * </p>
     * 
     * @param proveedor El proveedor a registrar.
     * @return true si el proveedor fue registrado con éxito, false en caso contrario.
     */
    public boolean registrarProveedor(Proveedor proveedor){
        return this.db.registrar(proveedor);
    }

    /**
     * Obtiene un proveedor por su ID.
     * <p>
     * Este método busca en el sistema el proveedor que coincida con el ID proporcionado.
     * Si encuentra un proveedor con el ID correspondiente, lo retorna; de lo contrario, retorna null.
     * </p>
     * 
     * @param id El ID del proveedor a buscar.
     * @return El proveedor encontrado o null si no se encuentra.
     */
    public Proveedor obtenerProveedor(int id){
        
        List<Proveedor> listaProveedor = obtenerTodosProveedores();

        //algoritmo de busqueda secuencial
        for(Proveedor p : listaProveedor){
            if(p.getId() == id){
                return p;
            }
        }

        return null;
    }

    /**
     * Obtiene un proveedor por su nombre.
     * 
     * @param nombre El nombre de proveedor a buscar.
     * @return El proveedor correspondiente al nombre de proveedor proporcionado.
     */
    public Proveedor obtenerProveedorPorNombre(String nombre) {
        List<Proveedor> listUsers =  obtenerTodosProveedores();
        //algoritmo de busqueda secuencial
        for (Proveedor u : listUsers) {
            if (u.getNombre().equals(nombre)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Obtiene todos los proveedores registrados en el sistema.
     * <p>
     * Este método lee todos los proveedores registrados en el sistema
     * y los retorna en forma de una lista.
     * </p>
     * 
     * @return La lista de proveedores registrados.
     */
    public List<Proveedor> obtenerTodosProveedores(){
        return this.db.allRecords();
    }

    /**
     * Obtiene todos los proveedores almacenados pero ordenados por un criterio definido.
     * <p>
     * Este método lee todos los proveedores registrados en el sistema
     * y los ordena de acuerdo al criterio proporcionado.
     * </p>
     * 
     * @param predicado El criterio de ordenamiento.
     * @return La lista de proveedores ordenados.
     */
    public List<Proveedor> obtenerTodosProveedoresOrdenados(BiPredicate<Proveedor, Proveedor> predicado){

        List<Proveedor> lista = obtenerTodosProveedores();
        return Ordenamiento.burbuja(lista, predicado);
    }

    /**
     * Elimina un proveedor por su ID.
     * <p>
     * Este método intenta eliminar un proveedor basado en el ID proporcionado.
     * Si el proveedor se elimina con éxito, retorna true; de lo contrario, retorna false.
     * </p>
     * 
     * @param id El ID del proveedor a eliminar.
     * @return true si el proveedor fue eliminado con éxito, false en caso contrario.
     */
    public boolean eliminarProveedor(int id){
        return this.db.eliminarPorId(id);
    }

    /**
     * Actualiza la información de un proveedor por su ID.
     * <p>
     * Este método intenta actualizar la información de un proveedor basado en el ID proporcionado.
     * Si el proveedor se actualiza con éxito, retorna true; de lo contrario, retorna false.
     * </p>
     * 
     * @param id El ID del proveedor a actualizar.
     * @param proveedor El proveedor con la información actualizada.
     * @return true si el proveedor fue actualizado con éxito, false en caso contrario.
     */
    public boolean actualizarProveedor(int id, Proveedor provedor){
        return this.db.actualizarPorId(id, provedor);
    }
}
