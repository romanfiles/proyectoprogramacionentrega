package CapaLogica;

/**
 * Modelo de dominio que representa a un docente registrado.
 * Pertenece a la capa lógica.
 */
public class Docente {
    private final String ci;
    private final String nombre;
    private final String apellido;
    private final String grupo;
    private final String turno;

    public Docente(String ci, String nombre, String apellido, String grupo, String turno) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.grupo = grupo;
        this.turno = turno;
    }


    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getTurno() {
        return turno;
    }

    /**
     * Devuelve el nombre y apellido combinados del docente.
     *
     * @return nombre completo sin espacios innecesarios.
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
