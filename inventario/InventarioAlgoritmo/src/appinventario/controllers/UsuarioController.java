package appinventario.controllers;

import java.util.List;

import appinventario.database.DBSqlManager;
import appinventario.models.Usuario;
import appinventario.utils.*;

public class UsuarioController {

    private DBSqlManager<Usuario> db;

    /**
     * Constructor para UsuarioController.
     * Inicializa el objeto DBSqlManager para manejar usuarios.
     */
    public UsuarioController() {
        this.db = new DBSqlManager<>(Usuario.class);
    }

    /**
     * Valida las credenciales de un usuario.
     * 
     * @param usuario El nombre de usuario a validar.
     * @param password La contraseña del usuario a validar.
     * @return El resultado de la validación de las credenciales.
     */
    public LoginResult validarUsuario(String usuario, String password) {
        List<Usuario> listUsers = obtenerTodosUsuarios();

        //algoritmo de busqueda secuencial
        for (Usuario u : listUsers) {
            if (u.getUsuario().equals(usuario) && u.getPassword().equals(Usuario.hashPassword(password))) {
                return new LoginResult(true, u);
            }
        }
        return new LoginResult(false, null);
    }

    /**
     * Obtiene todos los usuarios registrados.
     * 
     * @return Una lista de todos los usuarios.
     */
    private List<Usuario> obtenerTodosUsuarios() {
        return this.db.allRecords();
    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param id El ID del usuario a obtener.
     * @return El usuario correspondiente al ID proporcionado.
     */
    public Usuario obtenerUsuario(int id) {
        
        List<Usuario> listaUsuario = obtenerTodosUsuarios();

        //algoritmo de busqueda secuencial
        for(Usuario u : listaUsuario){
            if(u.getId() == id){
                return u;
            }
        }

        return null;
    }

    /**
     * Cambia la contraseña de un usuario si es el propio usuario quien la cambia.
     * 
     * @param user El usuario que desea cambiar su contraseña.
     * @param oldPass La contraseña actual del usuario.
     * @param newPass La nueva contraseña del usuario.
     * @return true si la contraseña se cambió con éxito, false en caso contrario.
     */
    public boolean cambiarContraseñaUsuario(Usuario user, String oldPass, String newPass) {
        if (verificarPassword(oldPass, user)) {
            user.setPassword(newPass);
            return this.db.actualizarPorId(user.getId(), user);
        }
        return false;
    }

    /**
     * Verifica si la contraseña proporcionada coincide con la del usuario.
     * 
     * @param password La contraseña a verificar.
     * @param user El usuario cuya contraseña se verificará.
     * @return true si la contraseña coincide, false en caso contrario.
     */
    private boolean verificarPassword(String password, Usuario user) {
        String hashedPassword = Usuario.hashPassword(password);
        return user.getPassword().equals(hashedPassword);
    }

    /**
     * Actualiza la información de un usuario por su ID.
     * 
     * @param id El ID del usuario a actualizar.
     * @param user El usuario con la información actualizada.
     * @return true si el usuario fue actualizado con éxito, false en caso contrario.
     */
    public boolean actualizarUsuario(int id, Usuario user) {
        return this.db.actualizarPorId(id, user);
    }
}
