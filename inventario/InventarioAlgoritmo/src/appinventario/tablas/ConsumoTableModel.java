package appinventario.tablas;

import appinventario.models.Consumo;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ConsumoTableModel extends AbstractTableModel {
    private List<Consumo> consumos;
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    private String[] columnNames = {"Producto", "Cantidad", "Unidad", "Usuario", "Fecha de salida"};

    public ConsumoTableModel(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    @Override
    public int getRowCount() {
        return consumos.size();
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
            case 3:
                return consumo.getUsuario().getUsuario();
            case 4:
                return formatoFecha.format(consumo.getFechaSalida());
            default:
                return null;
        }
    }
}
