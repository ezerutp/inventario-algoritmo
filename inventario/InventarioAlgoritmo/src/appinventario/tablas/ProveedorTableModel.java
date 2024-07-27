package appinventario.tablas;

import appinventario.models.Proveedor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProveedorTableModel extends AbstractTableModel {
    private List<Proveedor> proveedores;
    private String[] columnNames = {"Nombre", "Teléfono", "Dirección", "Email"};

    public ProveedorTableModel(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    @Override
    public int getRowCount() {
        return proveedores.size();
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
        Proveedor proveedor = proveedores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return proveedor.getNombre();
            case 1:
                return proveedor.getTelefono();
            case 2:
                return proveedor.getDireccion();
            case 3:
                return proveedor.getEmail();
            default:
                return null;
        }
    }
}
