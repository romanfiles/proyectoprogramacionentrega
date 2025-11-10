package CapaPersistencia;

/**
 * Clase encargada de gestionar las conexiones con la base de datos MySQL.
 * Pertenece a la capa de persistencia.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/inasistencias_docentes";
    private static final String USUARIO = "root";
    private static final String CONTRASENIA = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private Conexion() {
        // Utility class
    }

    /**
     * Obtiene una nueva conexión JDBC utilizando el driver de MySQL.
     *
     * @return instancia funcional de {@link Connection}.
     * @throws SQLException si ocurre un problema al abrir la conexión.
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC de MySQL no encontrado.");
            throw new IllegalStateException("Driver JDBC de MySQL no encontrado", e);
        }

        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
            System.out.println("Conexión a la base de datos establecida correctamente.");
            return conexion;
        } catch (SQLException e) {
            System.err.println("No se pudo establecer conexión con la base de datos: " + e.getMessage());
            throw e;
        }
    }
}
