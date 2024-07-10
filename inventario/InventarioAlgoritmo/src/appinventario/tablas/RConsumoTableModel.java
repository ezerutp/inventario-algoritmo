package appinventario.tablas;

import appinventario.models.Consumo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class RConsumoTableModel extends AbstractTableModel {
    private List<Consumo> consumos;
    private String[] columnNames = {"Producto", "Cantidad", "Unidad"};
    private int filas;
    
    public RConsumoTableModel(List<Consumo> consumos, int filas) {
        this.consumos = consumos;
        this.filas = filas;
    }
    
    public RConsumoTableModel(List<Consumo> consumos) {
        this.consumos = consumos;
        this.filas = 5;
    }

    @Override
    public int getRowCount() {
        return Math.min(filas, consumos.size());
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
        Consumo consumo = consumos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return consumo.getProducto().getNombre();
            case 1:
                return consumo.getCantidad();
            case 2:
                return consumo.getProducto().getUnidad_medida();
            default:
                return null;
        }
    }
}
