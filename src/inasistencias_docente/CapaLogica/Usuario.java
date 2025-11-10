/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

/**
 *
 * @author Terra, Vazquez, Cordero
 */
  public abstract class Usuario {

    protected String nombreUsuario;
    protected String contraseña;

    public void setNombreUsuario(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombreUsuario = nombre.trim();
    }

    public void setContraseña(String contraseña) {
        if (contraseña == null || contraseña.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        if (contraseña.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }
        this.contraseña = contraseña; 
        
        if (!contraseña.matches(".*[A-Za-z].*") || !contraseña.matches(".*[0-9].*")) {
    throw new IllegalArgumentException("La contraseña debe contener letras y números.");
}
        
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public abstract boolean autenticar(String usuario, String contraseña);
}
