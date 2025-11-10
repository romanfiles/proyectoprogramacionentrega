package CapaLogica;

import CapaPersistencia.ClaseBaseDatosRegistro;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Fachada principal para la gestión de inasistencias de docentes.
 * Pertenece a la capa lógica.
 */
public class FachadaLogica {

    private final ClaseBaseDatosRegistro persistencia;

    /**
     * Crea la fachada inicializando la dependencia de persistencia.
     */
    public FachadaLogica() {
        this.persistencia = new ClaseBaseDatosRegistro();
    }

    /**
     * Valida y registra una nueva inasistencia.
     *
     * @param inasistencia datos a almacenar.
     * @return {@code true} si la operación fue exitosa.
     */
    public boolean registrarInasistencia(Inasistencia_docente inasistencia) {
        if (!validarInasistencia(inasistencia)) {
            return false;
        }

        try {
            return persistencia.insertar(inasistencia);
        } catch (SQLException e) {
            System.err.println("No se pudo registrar la inasistencia: " + e.getMessage());
            return false;
        }
    }

    
    /**
     * Elimina una inasistencia según su identificador.
     *
     * @param id identificador del registro.
     * @return {@code true} si se eliminó correctamente.
     */
    public boolean eliminarInasistencia(int id) {
        if (id <= 0) {
            System.err.println("Debe indicar un identificador válido para eliminar.");
            return false;
        }

        try {
            return persistencia.eliminar(id);
        } catch (SQLException e) {
            System.err.println("No se pudo eliminar la inasistencia: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todas las inasistencias registradas.
     *
     * @return lista de inasistencias o vacía ante error.
     */
    public List<Inasistencia_docente> obtenerTodas() {
        try {
            return persistencia.obtenerTodas();
        } catch (SQLException e) {
            System.err.println("No se pudieron obtener las inasistencias: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Obtiene las inasistencias vigentes en la fecha actual.
     *
     * @return lista filtrada o vacía ante error.
     */
    public List<Inasistencia_docente> obtenerDeHoy() {
        try {
            return persistencia.obtenerDeHoy();
        } catch (SQLException e) {
            System.err.println("No se pudieron obtener las inasistencias de hoy: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private boolean validarInasistencia(Inasistencia_docente inasistencia) {
        if (inasistencia == null) {
            System.err.println("No se proporcionaron datos de la inasistencia.");
            return false;
        }
        if (estaVacio(inasistencia.getTipo()) || estaVacio(inasistencia.getMotivo())
                || estaVacio(inasistencia.getCiDocente()) || estaVacio(inasistencia.getCiAdministrativo())) {
            System.err.println("Tipo, motivo y los CI del docente y administrativo son obligatorios.");
            return false;
        }
        LocalDate inicio = inasistencia.getFechaInicio();
        LocalDate fin = inasistencia.getFechaFin();
        if (inicio == null || fin == null) {
            System.err.println("Las fechas de inicio y fin son obligatorias.");
            return false;
        }
        if (fin.isBefore(inicio)) {
            System.err.println("La fecha de fin no puede ser anterior a la fecha de inicio.");
            return false;
        }
        return true;
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.isBlank();
    }
}
