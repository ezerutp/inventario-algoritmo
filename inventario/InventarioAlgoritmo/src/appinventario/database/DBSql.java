package appinventario.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSql {
    private static DBSql instancia;
    private Connection connection;
    private static final String hostLocal = "jdbc:sqlserver://sql.bsite.net;instanceName=MSSQL2016;encrypt=false;databaseName=ezerutp_";
    private static final String userLocal = "ezerutp_";
    private static final String passLocal = "123";

    private DBSql() {
        try {
            // Controlador de SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Establecer la conexión :)
            connection = DriverManager.getConnection(hostLocal, userLocal, passLocal);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DBSql getInstancia() {
        if (instancia == null) {
            instancia = new DBSql();
        }
        return instancia;
    }

    public Connection getConexion() {
        return connection;
    }
}