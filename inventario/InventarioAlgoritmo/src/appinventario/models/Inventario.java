package appinventario.models;

import appinventario.controllers.ProductoController;
import appinventario.interfaces.DBEntity;

public class Inventario implements DBEntity{
    private int id;
    private Producto producto;
    private int cantidad;
    private String tabla = "inventario";

    public Inventario() {
    }

    public Inventario(int id, Producto producto, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String getTabla() {
        return tabla;
    }

    @Override
    public String getColumnas() {
        return "producto_id, cantidad";
    }

    @Override
    public String values() {
        return String.format("'%d', '%d'", this.producto.getId(), this.cantidad);
    }

    @Override
    public String update() {
        return String.format("producto_id='%d', cantidad='%d'", this.producto.getId(), this.cantidad);
    }

    @Override
    public void fromString(String Data) {
        String[] data = Data.split(",");
        this.id = Integer.parseInt(data[0]);

        // Creando Objeto Producto
        ProductoController controlproducto = new ProductoController();
        int productoId = Integer.parseInt(data[1]);
        Producto productoObjeto = controlproducto.obtenerProducto(productoId);
        if (productoObjeto != null) {
            this.producto = productoObjeto;
        } else {
            this.producto = null;
        }

        this.cantidad = Integer.parseInt(data[2]);
    }
}
