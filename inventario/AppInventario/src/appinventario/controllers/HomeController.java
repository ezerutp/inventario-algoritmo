package appinventario.controllers;

import appinventario.tablas.ConsumoTableModel;
import appinventario.tablas.ProductoTableModel;
import appinventario.tablas.ProveedorTableModel;
import appinventario.tablas.RConsumoTableModel;
import appinventario.tablas.RSuministroTableModel;
import appinventario.tablas.UsuarioTableModel;

public class HomeController {
    
    //Definimos los controladores a utilizar
    private AdminController admcontrol;
    private ConsumoController conscontrol;
    private SuministroController sumicontrol;
    private ProductoController prodcontrol;
    private ProveedorController provcontrol;
    
    public HomeController() {
        this.admcontrol = new AdminController();
        this.conscontrol = new ConsumoController();
        this.sumicontrol = new SuministroController();
        this.prodcontrol = new ProductoController();
        this.provcontrol = new ProveedorController();
    }

    public int cantidadUsers() {
        return admcontrol.obtenerTodosUsuarios().size();
    }

    public int cantidadProductos() {
        return prodcontrol.obtenerTodosProductos().size();
    }

    public int cantidadConsumos() {
        return conscontrol.obtenerTodosConsumos().size();
    }

    public int cantidadSuministros() {
        return sumicontrol.obtenerTodosSuministros().size();
    }

    public int cantidadProveedores() {
        return provcontrol.obtenerTodosProveedores().size();
    }

    //Modelo de las tablas
    public UsuarioTableModel listaUsuarios(){
        return new UsuarioTableModel(admcontrol.obtenerTodosUsuarios());
    }
    
    public RConsumoTableModel listaConsumos(){
        return new RConsumoTableModel(conscontrol.obtenerTodosConsumos());
    }

    public RSuministroTableModel listaSuministros(){
        return new RSuministroTableModel(sumicontrol.obtenerTodosSuministrosOrdenadosDES());
    }

    public ProductoTableModel listaProductos(){
        return new ProductoTableModel(prodcontrol.obtenerTodosProductos());
    }

    public ProveedorTableModel listaProveedores(){
        return new ProveedorTableModel(provcontrol.obtenerTodosProveedores());
    }
}
