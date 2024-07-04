package appinventario.utils;

import appinventario.models.*;

public class LoginResult {
    private boolean success;
    private Usuario user;

    public LoginResult(boolean success, Usuario user) {
        this.success = success;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public Usuario getUser() {
        return user;
    }
}