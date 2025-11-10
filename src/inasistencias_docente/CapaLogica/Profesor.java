/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

/**
 *
 * @author Terra, Vazquez, Cordero
 */

import CapaLogica.Licencia;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Profesor extends Usuario {

    private String nombreCompleto;
    private List<String> materias;
    private List<Licencia> licencias;

    
    public Profesor(String nombreCompleto, List<String> materias) {
        setNombreCompleto(nombreCompleto);
        setMaterias(materias);
        this.licencias = new ArrayList<>();
    }

    public Profesor() {
        this.licencias = new ArrayList<>();
        this.materias = new ArrayList<>();
    }

    public Profesor(String nombreCompleto, List<String> materias, List<Licencia> licencias) {
        setNombreCompleto(nombreCompleto);
        setMaterias(materias);
        setLicencias(licencias);
    }

   
    public List<Licencia> getLicenciasActivas() {
        List<Licencia> activas = new ArrayList<>();
        for (Licencia licencia : licencias) {
            if (licencia.estaActiva()) {
                activas.add(licencia);
            }
        }
        return activas;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public List<String> getMaterias() {
        return new ArrayList<>(materias); // devuelve copia para evitar modificaciones externas
    }

    public List<Licencia> getLicencias() {
        return new ArrayList<>(licencias);
    }

    @Override
    public boolean autenticar(String usuario, String contraseña) {
        return this.nombreUsuario.equals(usuario) && this.contraseña.equals(contraseña);
    }

    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombreCompleto = nombreCompleto.trim();
    }

    public void setMaterias(List<String> materias) {
        if (materias == null || materias.isEmpty()) {
            throw new IllegalArgumentException("El profesor debe tener al menos una materia.");
        }
        for (String m : materias) {
            if (m == null || m.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre de una materia no puede estar vacío.");
            }
        }
        this.materias = new ArrayList<>(materias);
    }

    public void setLicencias(List<Licencia> licencias) {
        if (licencias == null) {
            throw new IllegalArgumentException("La lista de licencias no puede ser nula.");
        }
        this.licencias = new ArrayList<>(licencias);
    }
}
