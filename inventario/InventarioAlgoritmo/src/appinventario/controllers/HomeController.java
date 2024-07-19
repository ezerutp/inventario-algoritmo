package appinventario.controllers;

import appinventario.models.Consumo;
import appinventario.models.Suministro;
import appinventario.tablas.*;

import java.util.function.BiPredicate;

public class HomeController {
    
    //Definimos los controladores a utilizar
    private final AdminController admcontrol;
    private final InventarioController invcontrol;
    private final ConsumoController conscontrol;
    private final SuministroController sumicontrol;
    private final ProductoController prodcontrol;
    private final ProveedorController provcontrol;
    
    public HomeController() {
        this.admcontrol = new AdminController();
        this.invcontrol = new InventarioController();
        this.conscontrol = new ConsumoController();
        this.sumicontrol = new SuministroController();
        this.prodcontrol = new ProductoController();
        this.provcontrol = new ProveedorController();
    }

    public int cantidadUsers() {
        return admcontrol.obtenerTodosUsuarios().size();
    }

    public int cantidadInventario() {
        return invcontrol.obtenerTodosInventarios().size();
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
    
    public RConsumoTableModel listaConsumos(int filas){
        BiPredicate<Consumo, Consumo> predicado = (c1, c2) -> c1.getId() < c2.getId();
        return new RConsumoTableModel(conscontrol.obtenerTodosConsumosOrdenados(predicado), filas);
    }

    public RSuministroTableModel listaSuministros(int filas){
        //criterio de ordenamiento (ordenar y mostrar los ultimos 5 ingresos)
        BiPredicate<Suministro, Suministro> predicado = (s1, s2) -> s1.getId() < s2.getId();
        return new RSuministroTableModel(sumicontrol.obtenerTodosSuministrosOrdenados(predicado), filas);
    }

    public ProductoTableModel listaProductos(){
        return new ProductoTableModel(prodcontrol.obtenerTodosProductos());
    }

    public ProveedorTableModel listaProveedores(){
        return new ProveedorTableModel(provcontrol.obtenerTodosProveedores());
    }
}
