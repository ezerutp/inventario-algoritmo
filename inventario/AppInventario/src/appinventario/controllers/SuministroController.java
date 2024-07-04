package appinventario.controllers;

import appinventario.database.DBSqlManager;
import appinventario.models.Suministro;
import java.util.List;

public class SuministroController {

    private DBSqlManager<Suministro> db;

    /**
     * Constructor de la clase SuministroController. Inicializa la base de datos para la clase Suministro.
     */
    public SuministroController() {
        this.db = new DBSqlManager<>(Suministro.class);
    }

    /**
     * Registra un nuevo suministro en la base de datos.
     *
     * @param suministro El suministro a registrar.
     * @return true si el suministro se registró correctamente, false en caso contrario.
     */
    public boolean registrarSuministro(Suministro suministro){
        return this.db.registrar(suministro);
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
     * Obtiene el total de un producto por su nombre en base a los suministros almacenados en la base de datos.
     *
     * @param producto El nombre del producto a buscar.
     * @return El total de unidades consumidas del producto especificado.
     */
    public int obtenerTotalProductoPorNombre(String producto) {
        return this.db.obtenerTotalProductoPorNombre(producto);
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
     * @return Lista de suministros
     */
    public List<Suministro> obtenerTodosSuministrosOrdenados(){

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

    
}
