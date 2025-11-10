/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaExeption.InicioAds;

public class ServicioAutenticacion {
    
    public boolean autenticarAdscripto(String ciAdscripto, String contraseniaIngresada) {
        
        InicioAds dao = new InicioAds();
        String contraseniaBD = dao.obtenerContraseniaAdscripto(ciAdscripto);
        
        if (contraseniaBD == null) {
            return false;
        }

        try {
            Adscripto adscripto = new Adscripto(ciAdscripto, contraseniaBD);

            return adscripto.autenticar(ciAdscripto, contraseniaIngresada);
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación del Adscripto: " + e.getMessage());
            return false;
        }
    }
}
