package appinventario.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import appinventario.interfaces.DBEntity;

public class Usuario extends Persona implements DBEntity {
    // Atributos del modelo Usuario
    private int id;
    private boolean admin;
    private String usuario;
    private String password;
    private String cargo;
    private boolean forcePass;
    private String tabla = "usuario";

    // Constructores
    public Usuario() {
    }

    public Usuario(int id, boolean admin, String nombre, String apellido, String telefono, String usuario, String password, String cargo, boolean forcePass) {
        this.id = id;
        this.admin = admin;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.usuario = usuario;
        this.password = hashPassword(password);
        this.cargo = cargo;
        this.forcePass = forcePass;
    }

    // Getters y Setters
    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean Admin) {
        this.admin = Admin;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean getforcePass() {
        return this.forcePass;
    }

    public void setforcePass(boolean forcePass) {
        this.forcePass = forcePass;
    }

    private int forceState() {
        if (this.forcePass) { return 1; }
        return 0;
    }

    private int adminState() {
        if (this.admin) { return 1; }
        return 0;
    }

    @Override
    public String getTabla() {
        return tabla;
    }

    @Override
    public String getColumnas(){
        return "admin, nombre, apellido, telefono, usuario, password, cargo, forcePass";
    }

    @Override
    public String values() {
        return String.format("'%d', '%s', '%s', '%s', '%s', '%s', '%s', '%d'", adminState(), this.nombre, this.apellido,
                this.telefono, this.usuario, this.password, this.cargo, forceState());
    }

    @Override
    public String update() {
        return String.format(
                "admin='%d', nombre='%s', apellido='%s', telefono='%s', usuario='%s', password='%s', cargo='%s', forcePass='%d'",
                adminState(), this.nombre, this.apellido, this.telefono, this.usuario, this.password, this.cargo, forceState());
    }

    @Override
    public void fromString(String Data) {
        String[] data = Data.split(",");
        this.id = Integer.parseInt(data[0]);
        this.admin = data[1].equals("1");
        this.nombre = data[2];
        this.apellido = data[3];
        this.telefono = data[4];
        this.usuario = data[5];
        this.password = data[6];
        this.cargo = data[7];
        this.forcePass = data[8].equals("1");
    }

    // Genera el hash SHA-256 de la contraseña proporcionada.
    public static String hashPassword(String password) {
        try {
            // Algoritmo SHA-256 para generar el hash del password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convertir los bytes a una representación hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}