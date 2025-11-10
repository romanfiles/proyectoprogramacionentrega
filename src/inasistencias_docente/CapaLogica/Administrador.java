package CapaLogica;

import java.util.Objects;

/**
 * Modelo de dominio que representa a un administrativo del sistema.
 * Pertenece a la capa lógica.
 */
public class Administrador {
    private final String ci;
    private final String nombre;
    private final String apellido;
    private final String usuario;
    private final String contrasena;

    public Administrador(String ci,
                         String nombre,
                         String apellido,
                         String usuario,
                         String contrasena) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Administrador(String ci, String nombre, String apellido) {
        this(ci, nombre, apellido, null, null);
    }

    public String getCi() {
        return ci;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    /**
     * Devuelve el nombre y apellido formateados del administrativo.
     *
     * @return nombre completo o cadena vacía si no hay datos.
     */
    public String getNombreCompleto() {
        StringBuilder builder = new StringBuilder();
        if (nombre != null && !nombre.isBlank()) {
            builder.append(nombre.trim());
        }
        if (apellido != null && !apellido.isBlank()) {
            if (builder.length() > 0) {
                builder.append(' ');
            }
            builder.append(apellido.trim());
        }
        return builder.toString();
    }

  
}
