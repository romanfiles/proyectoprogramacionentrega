package CapaPersistencia;

/**
 * Clase de acceso a datos para los administrativos del sistema.
 * Pertenece a la capa de persistencia.
 */

import CapaLogica.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersistenciaPersona {

    /**
     * Inserta un nuevo administrativo en la tabla correspondiente.
     *
     * @param admin administrativo a registrar.
     * @return {@code true} si se insertó correctamente.
     * @throws SQLException si ocurre un error durante la inserción.
     */
    public boolean insertarAdministrador(Administrador admin) throws SQLException {
        String sql = "INSERT INTO administrativo (CI, nombre, apellido, usuario, contrasena) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                throw new SQLException("No se pudo obtener una conexión a la base de datos.");
            }

            stmt.setString(1, admin.getCi());
            stmt.setString(2, admin.getNombre());
            stmt.setString(3, admin.getApellido());
            stmt.setString(4, admin.getUsuario());
            stmt.setString(5, admin.getContrasena());

            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Recupera un administrativo según su usuario de acceso.
     *
     * @param usuario nombre de usuario buscado.
     * @return instancia de {@link Administrador} o {@code null} si no existe.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    public Administrador obtenerPorUsuario(String usuario) throws SQLException {
        String sql = "SELECT CI, nombre, apellido, usuario, contrasena FROM administrativo WHERE usuario = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                throw new SQLException("No se pudo obtener una conexión a la base de datos.");
            }

            stmt.setString(1, usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Administrador(
                            rs.getString("CI"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("usuario"),
                            rs.getString("contrasena")
                    );
                }
                return null;
            }
        }
    }
}
