package appinventario.utils;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class FillTable {
    
    public static void setearTabla(JTable tabla, AbstractTableModel modelo) {
        tabla.setModel(modelo);
    }
}
