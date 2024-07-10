package appinventario.models;

import appinventario.interfaces.DBEntity;

public class Proveedor extends Persona implements DBEntity{

    // Aqui sus atributos
    private int id; // identificador del proveedor
    private String direccion; // dirección del proveedor
    private String email; // correo electronico del proveedor
    private String tabla = "proveedor";

    public Proveedor() {
    }

    // Constructor
    public Proveedor(int id,String nombre, String telefono, String direccion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }

    // getters
    @Override
    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }
    
    //setters
    public void setId(int id){
        this.id=id;
    }

    public void setDireccion (String direccion){
        this.direccion = direccion;
    }
    
    public void setEmail (String email){
        this.email = email;
    }
    
    @Override
    public String getTabla() {
        return tabla;
    }

    @Override
    public String getColumnas(){
        return "nombre, telefono, direccion, email";
    }

    @Override
    public String values() {
        return String.format("'%s', '%s', '%s', '%s'", this.nombre, this.telefono, this.direccion, this.email);
    }

    @Override
    public String update() {
        return String.format("nombre='%s', telefono='%s', direccion='%s', email='%s'", this.nombre, this.telefono,
                this.direccion, this.email);
    }

    @Override
    public void fromString(String Data) {
        String[] data = Data.split(",");
        this.id = Integer.parseInt(data[0]);
        this.nombre = data[1];
        this.telefono = data[2];
        this.direccion = data[3];
        this.email = data[4];
    }
}
