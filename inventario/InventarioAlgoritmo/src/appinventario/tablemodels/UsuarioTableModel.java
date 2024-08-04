package appinventario.tablemodels;

import appinventario.models.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel {
    private List<Usuario> usuarios;
    private String[] columnNames = {"Admin", "Nombre", "Apellido", "Teléfono", "Usuario", "Cargo"};

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
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
        Usuario usuario = usuarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return usuario.isAdmin();
            case 1:
                return usuario.getNombre();
            case 2:
                return usuario.getApellido();
            case 3:
                return usuario.getTelefono();
            case 4:
                return usuario.getUsuario();
            case 5:
                return usuario.getCargo();
            default:
                return null;
        }
    }
}
