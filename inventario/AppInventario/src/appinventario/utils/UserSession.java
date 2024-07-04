package appinventario.utils;

import appinventario.models.Usuario;

public class UserSession {
    
    private static UserSession instancia;
    private Usuario user;

    public UserSession() {
    }

    public static synchronized UserSession getInstancia() {
        if (instancia == null ) {
            instancia = new UserSession();
            return instancia;
        } else {
            return instancia;
        }
    }

    public Usuario getUser() {
        return user;
    }
    
    public void setUser(Usuario user) {
        this.user = user;
    }
    
    public String getSaludo() {
        return "BIENVENIDO " + user.getNombre().toUpperCase() + "!";
    }

}
