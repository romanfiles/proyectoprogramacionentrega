 package CapaPersistencia;

/**
 * Clase que gestiona el CRUD de inasistencias docentes contra la base MySQL.
 * Pertenece a la capa de persistencia.
 */

import CapaLogica.Administrador;
import CapaLogica.Docente;
import CapaLogica.Inasistencia_docente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClaseBaseDatosRegistro {

    /**
     * Inserta un nuevo registro de inasistencia en la tabla registro
     *
     * @param inasistencia datos a persistir
     * @return {@code true} si se inserto al menos una fila
     * @throws SQLException si ocurre un error durante la insercion
     */
    public boolean insertar(Inasistencia_docente inasistencia) throws SQLException {
        String sql = "INSERT INTO registro (tipo, motivo, fecha_inicio, fecha_fin, CI_docente, CI_administrativo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                throw new SQLException("No se pudo obtener una conexión a la base de datos.");
            }

            stmt.setString(1, inasistencia.getTipo());
            stmt.setString(2, inasistencia.getMotivo());
            stmt.setDate(3, Date.valueOf(inasistencia.getFechaInicio()));
            stmt.setDate(4, Date.valueOf(inasistencia.getFechaFin()));
            stmt.setString(5, inasistencia.getCiDocente());
            stmt.setString(6, inasistencia.getCiAdministrativo());

            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Actualiza un registro de inasistencia existente.
     *
     * @param id identificador del registro.
     * @param inasistencia nuevos valores a guardar.
     * @return {@code true} si se modificó una fila.
     * @throws SQLException si ocurre un error durante la actualización.
     */
    public boolean actualizar(int id, Inasistencia_docente inasistencia) throws SQLException {
        String sql = "UPDATE registro SET tipo = ?, motivo = ?, fecha_inicio = ?, fecha_fin = ?, CI_docente = ?, CI_administrativo = ? WHERE id = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                throw new SQLException("No se pudo obtener una conexión a la base de datos.");
            }

            stmt.setString(1, inasistencia.getTipo());
            stmt.setString(2, inasistencia.getMotivo());
            stmt.setDate(3, Date.valueOf(inasistencia.getFechaInicio()));
            stmt.setDate(4, Date.valueOf(inasistencia.getFechaFin()));
            stmt.setString(5, inasistencia.getCiDocente());
            stmt.setString(6, inasistencia.getCiAdministrativo());
            stmt.setInt(7, id);

            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un registro de inasistencia por su identificador.
     *
     * @param id identificador del registro.
     * @return {@code true} si se eliminó una fila.
     * @throws SQLException si ocurre un error durante la eliminación.
     */
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM registro WHERE id = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                throw new SQLException("No se pudo obtener una conexión a la base de datos.");
            }

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Recupera todas las inasistencias almacenadas.
     *
     * @return lista completa de registros.
     * @throws SQLException si falla la consulta.
     */
    public List<Inasistencia_docente> obtenerTodas() throws SQLException {
        String sql = "SELECT r.id, r.tipo, r.motivo, r.fecha_inicio, r.fecha_fin, r.CI_docente, r.CI_administrativo, "
                + "d.nombre AS nombre_docente, d.apellido AS apellido_docente, d.grupo, d.turno, "
                + "a.nombre AS nombre_admin, a.apellido AS apellido_admin "
                + "FROM registro r "
                + "LEFT JOIN docente d ON r.CI_docente = d.CI "
                + "LEFT JOIN administrativo a ON r.CI_administrativo = a.CI "
                + "ORDER BY r.fecha_inicio DESC, r.id DESC";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (conn == null) {
                throw new SQLException("No se pudo obtener una conexión a la base de datos.");
            }

            List<Inasistencia_docente> registros = new ArrayList<>();
            while (rs.next()) {
                registros.add(mapearRegistro(rs));
            }
            return registros;
        }
    }

    /**
     * Recupera las inasistencias activas en la fecha actual.
     *
     * @return lista filtrada por la fecha de hoy.
     * @throws SQLException si falla la consulta.
     */
    public List<Inasistencia_docente> obtenerDeHoy() throws SQLException {
        String sql = "SELECT r.id, r.tipo, r.motivo, r.fecha_inicio, r.fecha_fin, r.CI_docente, r.CI_administrativo, "
                + "d.nombre AS nombre_docente, d.apellido AS apellido_docente, d.grupo, d.turno, "
                + "a.nombre AS nombre_admin, a.apellido AS apellido_admin "
                + "FROM registro r "
                + "LEFT JOIN docente d ON r.CI_docente = d.CI "
                + "LEFT JOIN administrativo a ON r.CI_administrativo = a.CI "
                + "WHERE r.fecha_inicio <= CURDATE() AND r.fecha_fin >= CURDATE() "
                + "ORDER BY r.fecha_inicio DESC, r.id DESC";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (conn == null) {
                throw new SQLException("No se pudo obtener una conexión a la base de datos.");
            }

            List<Inasistencia_docente> registros = new ArrayList<>();
            while (rs.next()) {
                registros.add(mapearRegistro(rs));
            }
            return registros;
        }
    }

    /**
     * Construye un objeto de dominio a partir del resultado JDBC.
     */
    private Inasistencia_docente mapearRegistro(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String tipo = rs.getString("tipo");
        String motivo = rs.getString("motivo");
        LocalDate inicio = obtenerFecha(rs.getDate("fecha_inicio"));
        LocalDate fin = obtenerFecha(rs.getDate("fecha_fin"));
        String ciDocente = rs.getString("CI_docente");
        String ciAdministrativo = rs.getString("CI_administrativo");

        Docente docente = new Docente(
                ciDocente,
                rs.getString("nombre_docente"),
                rs.getString("apellido_docente"),
                rs.getString("grupo"),
                rs.getString("turno")
        );

        Administrador administrativo = null;
        String nombreAdmin = rs.getString("nombre_admin");
        String apellidoAdmin = rs.getString("apellido_admin");
        if (nombreAdmin != null || apellidoAdmin != null || ciAdministrativo != null) {
            administrativo = new Administrador(ciAdministrativo, nombreAdmin, apellidoAdmin);
        }

        return new Inasistencia_docente(id, tipo, motivo, inicio, fin, ciDocente, ciAdministrativo, docente, administrativo);
    }

    /**
     * Convierte una fecha SQL a {@link LocalDate} manejando valores nulos.
     */
    private LocalDate obtenerFecha(Date date) {
        return date != null ? date.toLocalDate() : null;
    }
}
