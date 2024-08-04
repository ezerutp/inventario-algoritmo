package appinventario.controllers;

import java.util.List;
import appinventario.database.DBSqlManager;
import appinventario.models.Consumo;
import appinventario.models.Inventario;
import appinventario.tablemodels.ConsumoTableModel;
import appinventario.utils.Ordenamiento;

import java.util.function.BiPredicate;

public class ConsumoController {
    
    private DBSqlManager<Consumo> db;
    private InventarioController inv;

    /**
     * Constructor de la clase ConsumoController. Inicializa la base de datos para la clase Consumo.
     */
    public ConsumoController() {
        this.db = new DBSqlManager<>(Consumo.class);
        this.inv = new InventarioController();
    }

    /**
     * Registra un nuevo consumo en la base de datos.
     *
     * @param con El consumo a registrar.
     * @return true si el consumo se registró correctamente, false en caso contrario.
     */
    public boolean registrarConsumo(Consumo con) {
        boolean r = this.db.registrar(con);
        if (r) {
            Inventario inventario = new Inventario(0, con.getProducto(), con.getCantidad());
            return inv.registrarInventario(inventario);
        }
        return false;
    }

    /**
     * Obtiene un consumo de la base de datos por su identificador.
     *
     * @param id El identificador del consumo a obtener.
     * @return El consumo correspondiente al identificador especificado, o null si no se encuentra.
     */
    public Consumo obtenerConsumo(int id) {

        List<Consumo> listaConsumo = obtenerTodosConsumos();//ALLRECORDS (Nos devuelve toda la tabla)
        
        //Algoritmo de busqueda secuencial
        for(Consumo c : listaConsumo){
            if(c.getId() == id){
                return c;
            }
        }
        //fin del algoritmo
        return null;
    }

    /**
     * Obtiene todos los consumos almacenados en la base de datos.
     *
     * @return Una lista de todos los consumos almacenados.
     */
    public List<Consumo> obtenerTodosConsumos() {
        return this.db.allRecords();
    }
    
       /**
     * Obtiene todos los Consumos almacenados pero ordenados de menor a mayor
     * @param predicado criterio para ordenar
     * @return Lista de consumos
     */
    public List<Consumo> obtenerTodosConsumosOrdenados(BiPredicate<Consumo, Consumo> predicado){
        List<Consumo> lista = obtenerTodosConsumos();
        return Ordenamiento.burbuja(lista, predicado);
    }

    /**
     * Elimina un consumo de la base de datos por su identificador.
     *
     * @param id El identificador del consumo a eliminar.
     * @return true si el consumo se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarConsumo(int id) {
        return this.db.eliminarPorId(id);
    }

    /**
     * Actualiza un consumo en la base de datos por su identificador.
     *
     * @param id  El identificador del consumo a actualizar.
     * @param con El nuevo consumo con los datos actualizados.
     * @return true si el consumo se actualizó correctamente, false en caso contrario.
     */
    public boolean actualizarConsumo(int id, Consumo con) {
        return this.db.actualizarPorId(id, con);
    }

    /**
     * Retorna un modelo de tabla para los consumos.
     * 
     * @return Un objeto ConsumoTableModel que contiene la lista de todos los consumos.
     */
    public ConsumoTableModel modeloConsumo(){
        return new ConsumoTableModel(obtenerTodosConsumos());
    }
}
