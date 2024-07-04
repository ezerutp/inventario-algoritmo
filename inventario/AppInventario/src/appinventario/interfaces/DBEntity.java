package appinventario.interfaces;

public interface DBEntity {
    String getTabla(); // Nombre de la tabla en la base de datos
    String getColumnas();
    String values();
    String update();
    void fromString(String csvData); // Nos permite ingresr los datos del csv para crear objetos
    int getId();
}
