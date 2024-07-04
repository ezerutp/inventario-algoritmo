package appinventario.controllers;

import java.util.List;
import appinventario.database.DBSqlManager;
import appinventario.models.Consumo;

public class ConsumoController {
    
    private DBSqlManager<Consumo> db;

    /**
     * Constructor de la clase ConsumoController. Inicializa la base de datos para la clase Consumo.
     */
    public ConsumoController() {
        this.db = new DBSqlManager<>(Consumo.class);
    }

    /**
     * Registra un nuevo consumo en la base de datos.
     *
     * @param con El consumo a registrar.
     * @return true si el consumo se registró correctamente, false en caso contrario.
     */
    public boolean registrarConsumo(Consumo con) {
        return this.db.registrar(con);
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
     * Obtiene el total de un producto por su nombre en base a los suministros almacenados en la base de datos.
     *
     * @param producto El nombre del producto a buscar.
     * @return El total de unidades consumidas del producto especificado.
     */
    public int obtenerTotalProductoPorNombre(String producto) {
        return this.db.obtenerTotalProductoPorNombre(producto);
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
}
