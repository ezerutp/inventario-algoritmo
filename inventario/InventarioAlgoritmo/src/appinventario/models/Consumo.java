package appinventario.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import appinventario.controllers.ProductoController;
import appinventario.controllers.UsuarioController;
import appinventario.interfaces.DBEntity;

public class Consumo implements DBEntity {

    private int id;
    private Producto producto;
    private Usuario usuario;
    private int cantidad;
    private Date fechaSalida;
    private String tabla = "consumo";
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    public Consumo() {
    }

    public Consumo(int id, Producto producto, Usuario usuario, int cantidad, Date fechaSalida, String tabla) {
        this.id = id;
        this.producto = producto;
        this.usuario = usuario;
        this.cantidad = 0 - cantidad;
        this.fechaSalida = fechaSalida;
        this.tabla = tabla;
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

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = 0 - cantidad;
    }

    public Date getFechaSalida() {
        return this.fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public String getTabla() {
        return tabla;
    }

    @Override
    public String getColumnas() {
        return "producto_id, usuario_id, cantidad, fecha_salida";
    }

    @Override
    public String values() {
        return String.format("'%d', '%d', '%d', '%s'", this.producto.getId(), this.usuario.getId(), this.cantidad,
                formatoFecha.format(fechaSalida));
    }

    @Override
    public String update() {
        return String.format("producto_id='%d', usuario_id='%d', cantidad='%d', fecha_salida='%s'",
                this.producto.getId(), this.usuario.getId(), this.cantidad, formatoFecha.format(fechaSalida));
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

        // Creando Objeto Proveedor
        UsuarioController controlusuario = new UsuarioController();
        int usuarioId = Integer.parseInt(data[2]); // Índice actualizado
        Usuario usuarioObjeto = controlusuario.obtenerUsuario(usuarioId);
        if (usuarioObjeto != null) {
            this.usuario = usuarioObjeto;
        } else {
            this.usuario = null;
        }

        this.cantidad = Integer.parseInt(data[3]);

        try {
            this.fechaSalida = formatoFecha.parse(data[4]);
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + data[4]); 
            e.printStackTrace();
        }
    }
}
