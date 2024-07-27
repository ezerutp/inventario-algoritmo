package appinventario.tablas;

import appinventario.models.Inventario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class InventarioTableModel extends AbstractTableModel {
    private List<Inventario> inventarios;
    private String[] columnNames = {"Producto", "Cantidad"};

    public InventarioTableModel(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    @Override
    public int getRowCount() {
        return inventarios.size();
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
        Inventario inventario = inventarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return inventario.getProducto().getNombre();
            case 1:
                return inventario.getCantidad();
            default:
                return null;
        }
    }
}
