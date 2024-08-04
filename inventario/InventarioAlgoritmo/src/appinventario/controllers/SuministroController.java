package appinventario.controllers;

import appinventario.database.DBSqlManager;
import appinventario.models.Inventario;
import appinventario.models.Suministro;
import appinventario.tablemodels.SuministroTableModel;
import appinventario.utils.*;
import java.util.List;
import java.util.function.BiPredicate;

public class SuministroController {

    private DBSqlManager<Suministro> db;
    private InventarioController inv;

    /**
     * Constructor de la clase SuministroController. Inicializa la base de datos para la clase Suministro.
     */
    public SuministroController() {
        this.db = new DBSqlManager<>(Suministro.class);
        this.inv = new InventarioController();
    }

    /**
     * Registra un nuevo suministro en la base de datos.
     *
     * @param suministro El suministro a registrar.
     * @return true si el suministro se registró correctamente, false en caso contrario.
     */
    public boolean registrarSuministro(Suministro suministro){
        boolean r = this.db.registrar(suministro);
        if (r) {
            Inventario inventario = new Inventario(0, suministro.getProducto(), suministro.getCantidad());
            return inv.registrarInventario(inventario);
        }
        return false;
    }

    /**
     * Obtiene un suministro de la base de datos por su identificador.
     *
     * @param id El identificador del suministro a obtener.
     * @return El suministro correspondiente al identificador especificado, o null si no se encuentra.
     */
    public Suministro obtenerSuministro(int id){
        
        List<Suministro> listaSuministro = obtenerTodosSuministros();

        //algoritmo de busqueda secuencial
        for(Suministro s : listaSuministro){
            if(s.getId() == id){
                return s;
            }
        }

        return null;
    }

    /**
     * Obtiene todos los suministros almacenados en la base de datos.
     *
     * @return Una lista de todos los suministros almacenados.
     */
    public List<Suministro> obtenerTodosSuministros(){
        return this.db.allRecords();
    }

    /**
     * Obtiene todos los suministros almacenados pero ordenados de menor a mayor
     * @param predicado criterio de evaluacion
     * @return Lista de suministros
     */
    public List<Suministro> obtenerTodosSuministrosOrdenados(BiPredicate<Suministro, Suministro> predicado){
        
        List<Suministro> lista = obtenerTodosSuministros();
        return Ordenamiento.burbuja(lista, predicado);
    }

    /**
     * Elimina un suministro de la base de datos por su identificador.
     *
     * @param id El identificador del suministro a eliminar.
     * @return true si el suministro se eliminó correctamente, false en caso contrario.
     */
    public boolean eliminarSuministro(int id){
        return this.db.eliminarPorId(id);
    }
    
    /**
     * Actualiza un suministro en la base de datos por su identificador.
     *
     * @param id El identificador del suministro a actualizar.
     * @param suministro El nuevo suministro con los datos actualizados.
     * @return true si el suministro se actualizó correctamente, false en caso contrario.
     */
    public boolean actualizarSuministro(int id, Suministro suministro){
        return this.db.actualizarPorId(id, suministro);
    }

    /**
     * Retorna un modelo de tabla para los suministros.
     * 
     * @return Un objeto SuministroTableModel que contiene la lista de todos los suministros.
     */
    public SuministroTableModel modeloSuministros(){
        return new SuministroTableModel(obtenerTodosSuministros());
    }
    
}
