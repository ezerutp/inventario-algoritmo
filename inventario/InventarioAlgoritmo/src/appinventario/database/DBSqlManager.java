package appinventario.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import appinventario.interfaces.DBEntity;

public class DBSqlManager <T extends DBEntity>{
    
    // Variables
    private Class<T> clase;
    T model;
    private static final String DELIMITER = ",";

    // Constructor
    @SuppressWarnings("deprecation")
    public DBSqlManager(Class<T> clase) {
        this.clase = clase;
        try {
            this.model = clase.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private ResultSet ejecutarConsulta(String query) {
        Connection conexion = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conexion = DBSql.getInstancia().getConexion();
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private boolean ejecutarActualizacion(String query) {
        Connection conexion = null;
        Statement stmt = null;
        int filasAfectadas = 0;
        try {
            conexion = DBSql.getInstancia().getConexion();
            stmt = conexion.createStatement();
            filasAfectadas = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (filasAfectadas == 0) { return false; }
        return true;
    }

    @SuppressWarnings("deprecation")
    public List<T> allRecords() {
        try {
            List<T> objetos = new ArrayList<>();
            String query = String.format("SELECT * FROM %s", model.getTabla());
            ResultSet rs = ejecutarConsulta(query);
            if (rs == null) { return null; }
            int numColumnas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                T objeto = this.clase.newInstance();
                String[] fila = new String[numColumnas];
                for (int i = 0; i < numColumnas; i++) {
                    fila[i] = rs.getString(i + 1);
                }
                objeto.fromString(String.join(DELIMITER, fila));
                objetos.add(objeto);
            }
            return objetos;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registrar(T objeto) {
        try {
            String columnas = model.getColumnas();
            String valores = objeto.values();
            String query = String.format("INSERT INTO %s (%s) VALUES (%s)", model.getTabla(), columnas, valores);
            boolean rs = ejecutarActualizacion(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarPorId(int id){
        try {
            String query = String.format("DELETE FROM %s WHERE id=%d", model.getTabla(), id);
            boolean rs = ejecutarActualizacion(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return false;
    }

    public boolean actualizarPorId(int id, T objeto) {
        try {
            String setValues = objeto.update();
            String query = String.format("UPDATE %s SET %s WHERE id=%d", model.getTabla(), setValues, id);
            boolean rs = ejecutarActualizacion(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
