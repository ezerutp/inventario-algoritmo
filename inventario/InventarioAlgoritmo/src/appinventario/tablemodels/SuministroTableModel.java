package appinventario.tablemodels;

import appinventario.models.Suministro;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SuministroTableModel extends AbstractTableModel {
    private List<Suministro> suministros;
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    private String[] columnNames = {"Producto", "Proveedor", "Cantidad", "Unidad", "Usuario", "Fecha de ingreso"};

    public SuministroTableModel(List<Suministro> suministros) {
        this.suministros = suministros;
    }

    @Override
    public int getRowCount() {
        return suministros.size();
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
        Suministro suministro = suministros.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return suministro.getProducto().getNombre();
            case 1:
                return suministro.getProveedor().getNombre();
            case 2:
                return suministro.getCantidad();
            case 3:
                return suministro.getProducto().getUnidad_medida();
            case 4:
                return suministro.getUsuario().getUsuario();
            case 5:
                return formatoFecha.format(suministro.getFechaIngreso());
            default:
                return null;
        }
    }
}
