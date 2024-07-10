package appinventario.models;

import java.util.Locale;
import appinventario.interfaces.DBEntity;

public class Producto implements DBEntity{
    
    //Atributos de producto
    private int id; //identificador de producto
    private String nombre; //nombre de producto
    private String categoria;
    private String descripcion; //descripcion de producto 
    private double precio; //precio de producto
    private String unidad_medida; //unidad de medida(unidad, gramos, litros, kilogramos)
    private String tabla = "producto";
    //private Proveedor proveedor; // proveedor del producto esto lo hara la clase suministro
    
    public Producto() {
    }

    //Constructor
    public Producto (int id,String nombre,String categoria, String descripcion, double precio, String unidad_medida){
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidad_medida = unidad_medida;
    }
    
    //getters
    @Override
    public int getId (){ 
        return id;
    }   
    public String getNombre (){
        return nombre;
    }
    public String getCategoria() {
            return categoria;
    }
    public String getDescripcion (){
        return descripcion;
    }
    public double getPrecio (){
        return precio;
    }
    public String getUnidad_medida () {
        return unidad_medida;
    }

    //setters
    public void setId (int id){ 
        this.id = id;
    }

    public void setNombre (String nombre){
        this.nombre = nombre;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescripcion (String descripcion){
        this.descripcion = descripcion;
    }

    public void setPrecio (double precio){
        this.precio = precio;
    }

    public void setUnidad_medida (String medida) {
        this.unidad_medida = medida;
    }

    @Override
    public String getTabla() {
        return tabla;
    }

    @Override
    public String getColumnas() {
        return "nombre, categoria, descripcion, precio, unidad_medida";
    }

    @Override
    public String values() {
        return String.format(Locale.US,"'%s', '%s', '%s', %f, '%s'", this.nombre, this.categoria, this.descripcion, this.precio,
                this.unidad_medida);
    }
    
    @Override
    public String update() {
        return String.format(Locale.US,"nombre='%s', categoria='%s', descripcion='%s', precio=%f, unidad_medida='%s'",
                this.nombre, this.categoria, this.descripcion, this.precio, this.unidad_medida);
    }

    @Override
    public void fromString(String Data) {
        String[] data = Data.split(",");
        this.id = Integer.parseInt(data[0]);
        this.nombre = data[1];
        this.categoria = data[2];
        this.descripcion = data[3];
        this.precio = Double.parseDouble(data[4]);
        this.unidad_medida = data[5];
    }
}