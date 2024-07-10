package appinventario.utils;

public class ProductoStock {

    private String nombre;
    private int cantidad;

    public ProductoStock(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    @Override
    public String toString(){
        return "producto : " + this.nombre + " - cantidad : " + this.cantidad;
    }
}
