package appinventario.tablas;

import appinventario.models.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel {
    private List<Usuario> usuarios;
    private String[] columnNames = {"ID", "Admin", "Nombre", "Apellido", "Teléfono", "Usuario", "Cargo"};

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
                return usuario.getId();
            case 1:
                return usuario.isAdmin();
            case 2:
                return usuario.getNombre();
            case 3:
                return usuario.getApellido();
            case 4:
                return usuario.getTelefono();
            case 5:
                return usuario.getUsuario();
            case 6:
                return usuario.getCargo();
            default:
                return null;
        }
    }
}
