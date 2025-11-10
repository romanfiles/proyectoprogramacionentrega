/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

/**
 *
 * @author Román Rodriguez, Matias Santillan, Fabricio Alvez.
 */
import java.time.LocalDate;
import java.util.List;

public class Licencia {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<String> clasesAfectadas;
    private Profesor profesorConLicencia;
    private String causa;
    
    
    public boolean estaActiva() {
    LocalDate hoy = LocalDate.now();
    return !hoy.isBefore(fechaInicio) && !hoy.isAfter(fechaFin);
}

    public Licencia() {
    }

    public Licencia(LocalDate fechaInicio, LocalDate fechaFin, List<String> clasesAfectadas, Profesor profesorConLicencia, String causa) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.clasesAfectadas = clasesAfectadas;
        this.profesorConLicencia = profesorConLicencia;
        this.causa = causa;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public List<String> getClasesAfectadas() {
        return clasesAfectadas;
    }

    public Profesor getProfesorConLicencia() {
        return profesorConLicencia;
    }

    public String getCausa() {
        return causa;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setClasesAfectadas(List<String> clasesAfectadas) {
        this.clasesAfectadas = clasesAfectadas;
    }

    public void setProfesorConLicencia(Profesor profesorConLicencia) {
        this.profesorConLicencia = profesorConLicencia;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }
    
}
