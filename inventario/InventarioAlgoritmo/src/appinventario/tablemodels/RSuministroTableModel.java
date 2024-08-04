package appinventario.tablemodels;

import appinventario.models.Suministro;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class RSuministroTableModel extends AbstractTableModel {
    private List<Suministro> suministros;
    private int filas;
    private String[] columnNames = {"Producto", "Cantidad", "Unidad"};
    
    public RSuministroTableModel(List<Suministro> suministro, int filas){
        this.suministros = suministro;
        this.filas = filas;
    }

    public RSuministroTableModel(List<Suministro> suministros) {
        this.suministros = suministros;
        this.filas = 5;
    }

    @Override
    public int getRowCount() {
        return Math.min(this.filas, suministros.size());
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
                return suministro.getCantidad();
            case 2:
                return suministro.getProducto().getUnidad_medida();
            default:
                return null;
        }
    }
}
