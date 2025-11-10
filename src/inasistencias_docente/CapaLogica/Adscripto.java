/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

/**
 *
 * @author Román Rodriguez, Matias santillan, Fabricio Alvez
 */
public class Adscripto extends Usuario {

  
    public Adscripto(String nombreUsuario, String contraseña) {
        setNombreUsuario(nombreUsuario);  // valida y asigna desde la superclase
        setContraseña(contraseña);
    }

   
    @Override
    public boolean autenticar(String usuario, String contraseña) {
        return usuario != null && contraseña != null &&
               this.nombreUsuario != null && this.contraseña != null &&
               this.nombreUsuario.equals(usuario) && this.contraseña.equals(contraseña);
    }
}
