package appinventario.controllers;

import java.util.List;

import appinventario.database.DBSqlManager;
import appinventario.models.Usuario;

public class AdminController extends UsuarioController{

    private DBSqlManager<Usuario> db;

    /**
     * Constructor para AdminController.
     * Inicializa el objeto DBSqlManager para manejar administradores.
     */
    public AdminController() {
        this.db = new DBSqlManager<>(Usuario.class);
    }

    /**
     * Obtiene todos los usuarios registrados.
     * 
     * @return Una lista de todos los usuarios.
     */
    public List<Usuario> obtenerTodosUsuarios() {
        return this.db.allRecords();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * <p>
     * Este método verifica primero si el usuario ya existe en la lista de usuarios.
     * Si el usuario ya existe, el método retorna false. Si el usuario no existe,
     * se asigna un nuevo ID al usuario y se procede a registrar en el sistema.
     * </p>
     * 
     * @param user El usuario a registrar.
     * @return true si el usuario fue registrado con éxito, false en caso contrario.
     */
    public boolean registrarUsuario(Usuario user) {
        List<Usuario> listUsers = obtenerTodosUsuarios();
        // Verifica si el usuario ya está registrado en la lista de usuarios

        //algoritmo de busqueda secuencial
        for (Usuario u : listUsers) {
            if (u.getUsuario().equals(user.getUsuario())) {
                return false;
            }
        }
        //fin del algoritmo

        return db.registrar(user);
    }

    /**
     * Elimina un usuario por su ID.
     * <p>
     * Este método intenta eliminar un usuario basado en el ID proporcionado.
     * Si el usuario se elimina con éxito, retorna true; de lo contrario, retorna false.
     * </p>
     * 
     * @param id El ID del usuario a eliminar.
     * @return true si el usuario fue eliminado con éxito, false en caso contrario.
     */
    public boolean eliminarUsuario(int id) {
        return this.db.eliminarPorId(id);
    }

    /**
     * Obtiene un usuario por su nombre de usuario.
     * 
     * @param username El nombre de usuario a buscar.
     * @return El usuario correspondiente al nombre de usuario proporcionado.
     */
    public Usuario obtenerUsuarioPorNombre(String username) {

        List<Usuario> listUsers = obtenerTodosUsuarios();
        
        // Algoritmo de busqueda secuencial
        for (Usuario u : listUsers) {
            if (u.getUsuario().equals(username)) {
                return u;
            }
        }
        //
        return null;
    }

    /**
     * Cambia el estado de administrador de un usuario.
     * 
     * @param user El usuario al que se le cambiará el estado de administrador.
     * @param isAdmin El nuevo estado de administrador (true/false).
     * @return true si se cambió el estado con éxito, false en caso contrario.
     */
    public boolean cambiarEstadoAdministrador(Usuario user, boolean isAdmin) {
        user.setAdmin(isAdmin);
        return this.db.actualizarPorId(user.getId(), user);
    }

    /**
     * Cambia la contraseña de un usuario.
     * 
     * @param user El usuario al que se le cambiará la contraseña.
     * @param newPass La nueva contraseña del usuario.
     * @return true si la contraseña se cambió con éxito, false en caso contrario.
     */
    public boolean cambiarContraseñaAdministrador(Usuario user, String newPass) {
        user.setPassword(newPass);
        return this.db.actualizarPorId(user.getId(), user);
    }
}
