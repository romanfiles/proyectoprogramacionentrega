package CapaLogica;

import java.time.LocalDate;

/**
 * Modelo que describe una inasistencia registrada para un docente.
 * Pertenece a la capa lógica.
 */
public class Inasistencia_docente {
    private final Integer id;
    private final String tipo;
    private final String motivo;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final String ciDocente;
    private final String ciAdministrativo;
    private final Docente docente;
    private final Administrador administrativo;

    public Inasistencia_docente(Integer id,
                                String tipo,
                                String motivo,
                                LocalDate fechaInicio,
                                LocalDate fechaFin,
                                String ciDocente,
                                String ciAdministrativo) {
        this(id, tipo, motivo, fechaInicio, fechaFin, ciDocente, ciAdministrativo, null, null);
    }

    public Inasistencia_docente(Integer id,
                                String tipo,
                                String motivo,
                                LocalDate fechaInicio,
                                LocalDate fechaFin,
                                String ciDocente,
                                String ciAdministrativo,
                                Docente docente,
                                Administrador administrativo) {
        this.id = id;
        this.tipo = tipo;
        this.motivo = motivo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ciDocente = ciDocente;
        this.ciAdministrativo = ciAdministrativo;
        this.docente = docente;
        this.administrativo = administrativo;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getCiDocente() {
        return ciDocente;
    }

    public String getCiAdministrativo() {
        return ciAdministrativo;
    }

    public Docente getDocente() {
        return docente;
    }

    public Administrador getAdministrador() {
        return administrativo;
    }
}
