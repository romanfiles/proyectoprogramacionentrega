package CapaLogica;

import CapaPersistencia.PersistenciaPersona;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Fachada que coordina la lógica relacionada a los administrativos.
 * Pertenece a la capa lógica.
 */
public class FachadaPersona {

    private final PersistenciaPersona persistencia;

    public FachadaPersona() {
        this.persistencia = new PersistenciaPersona();
    }


    /**
     * Valida las credenciales de acceso contra la base de datos.
     *
     * @param usuario nombre de usuario ingresado.
     * @param contrasena contraseña ingresada.
     * @return {@code true} si las credenciales son válidas.
     */
    public boolean iniciarSesion(String usuario, String contrasena) {
        if (estaVacio(usuario) || estaVacio(contrasena)) {
            System.err.println("Usuario y contraseña son obligatorios.");
            return false;
        }

        try {
            Administrador admin = persistencia.obtenerPorUsuario(usuario);
            if (admin == null || !Objects.equals(admin.getContrasena(), contrasena)) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.err.println("No se pudo validar las credenciales: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera el administrativo asociado a un usuario.
     *
     * @param usuario nombre de usuario buscado.
     * @return administrativo encontrado o {@code null} si no existe.
     */
    public Administrador obtenerAdministradorPorUsuario(String usuario) {
        if (estaVacio(usuario)) {
            return null;
        }
        try {
            return persistencia.obtenerPorUsuario(usuario);
        } catch (SQLException e) {
            System.err.println("No se pudo obtener la información del administrativo: " + e.getMessage());
            return null;
        }
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.isBlank();
    }
}
