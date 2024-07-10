package appinventario.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import appinventario.controllers.ProductoController;
import appinventario.controllers.ProveedorController;
import appinventario.controllers.UsuarioController;
import appinventario.interfaces.DBEntity;

public class Suministro implements DBEntity{
    
    // Atributos del modelo suministro
    private int id;
    private Producto producto;
    private Proveedor proveedor;
    private Usuario usuario;
    private int cantidad;
    private Date fechaIngreso;
    private String tabla = "suministro";
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    public Suministro() {
    }

    public Suministro(int id, Producto producto, Proveedor proveedor, Usuario usuario, int cantidad, Date fechaIngreso) {
        this.id = id;
        this.producto = producto;
        this.proveedor = proveedor;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.fechaIngreso = fechaIngreso;
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

    public Proveedor getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
        this.cantidad = cantidad;
    }

    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String getTabla() {
        return tabla;
    }

    @Override
    public String getColumnas() {
        return "producto_id, proveedor_id, usuario_id, cantidad, fecha_ingreso";
    }

    @Override
    public String values() {
        return String.format("'%d', '%d', '%d', '%d', '%s'", this.producto.getId(), this.proveedor.getId(),
                this.usuario.getId(), this.cantidad, formatoFecha.format(fechaIngreso));
    }

    @Override
    public String update() {
        return String.format("producto_id='%d', proveedor_id='%d', usuario_id='%d', cantidad='%d', fecha_ingreso='%s'",
                this.producto.getId(), this.proveedor.getId(), this.usuario.getId(), this.cantidad,
                formatoFecha.format(fechaIngreso));
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
        ProveedorController controlproveedor = new ProveedorController();
        int proveedorId = Integer.parseInt(data[2]); // Índice actualizado
        Proveedor proveedorObjeto = controlproveedor.obtenerProveedor(proveedorId);
        if (proveedorObjeto != null) {
            this.proveedor = proveedorObjeto;
        } else {
            this.proveedor = null;
        }

        // Creando Objeto Proveedor
        UsuarioController controluser = new UsuarioController();
        int usuarioId = Integer.parseInt(data[3]); // Índice actualizado
        Usuario usuarioObjeto = controluser.obtenerUsuario(usuarioId);
        if (usuarioObjeto != null) {
            this.usuario = usuarioObjeto;
        } else {
            this.usuario = null;
        }

        this.cantidad = Integer.parseInt(data[4]);

        try {
            this.fechaIngreso = formatoFecha.parse(data[5]); // Actualización según instrucciones de followup
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + data[5]); // Actualización según instrucciones de followup
            e.printStackTrace();
        }
    }
}
