/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaExeption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioAds {

    // CAMBIO: Ahora devuelve la contraseña del Adscripto encontrado (o null)
    public String obtenerContraseniaAdscripto(String ciAdscripto) {
        // Asumiendo que el campo de usuario es el CI en la BD
        String sql = "SELECT Contrasenia FROM Adscripto WHERE CI_Adscripto = ?";
        
        try (Connection conn = conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Asumimos que CI_Adscripto es INT en la BD, ajusta si es STRING
            int ci = Integer.parseInt(ciAdscripto); 
            pstmt.setInt(1, ci); 

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Contrasenia"); // Devuelve la contraseña de la BD
                }
            }

        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error al obtener credenciales: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // Si no lo encuentra o hay error
    }
}