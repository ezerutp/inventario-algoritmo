package appinventario.tablemodels;

import appinventario.models.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProductoTableModel  extends AbstractTableModel {
    private List<Producto> productos;
    private String[] columnNames = {"Nombre", "Categoria", "Descripcion", "Precio", "Unidad de medida"};

    public ProductoTableModel(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto producto = productos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return producto.getNombre();
            case 1:
                return producto.getCategoria();
            case 2:
                return producto.getDescripcion();
            case 3:
                return producto.getPrecio();
            case 4:
                return producto.getUnidad_medida();
            default:
                return null;
        }
    }
}
