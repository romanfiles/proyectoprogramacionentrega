/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package CapaExeption;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GuardarLicencia {
   
    


    public ResultSet obtenerLicenciasDetalladas() throws SQLException {
        
        String sql = "SELECT " +
                     "L.ID_Lic, " +
                     "P.Nombre, " +
                     "P.Apellido, " +
                     "DT.Materia, " +
                     "L.Fecha_Inicio, " +
                     "L.Fecha_Fin, " +
                     "GROUP_CONCAT(DISTINCT G.GrupoNom ORDER BY G.GrupoNom ASC SEPARATOR ', ') AS GruposAfectados " + // <-- CLAVE: Combina todos los GrupoNom
                     "FROM Licencia L " +
                     "JOIN Docente D ON L.ID_Docente = D.ID_Docente " +
                     "JOIN Persona P ON D.ID_Docente = P.ID_Persona " +
                     "JOIN LicenciaAfectaGrupo LAG ON L.ID_Lic = LAG.ID_Lic " +
                     "JOIN Grupos G ON LAG.ID_Grupo = G.ID_Grupo " +
                     "JOIN Dicta DT ON L.ID_Docente = DT.ID_Docente AND LAG.ID_Grupo = DT.ID_Grupo " +
                     "GROUP BY L.ID_Lic, P.Nombre, P.Apellido, DT.Materia, L.Fecha_Inicio, L.Fecha_Fin " +
                     "ORDER BY L.ID_Lic DESC, P.Apellido ASC";
                     
        Connection conn = conexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        return pstmt.executeQuery();
    }


    
    
    
    

    public boolean eliminarLicencia(int idLicencia) {
    
    String sql = "DELETE FROM licencia WHERE ID_Lic = ?";
    
    try (Connection conn = conexion.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        if (conn == null) {
            System.err.println("Error: No hay conexión.");
            return false;
        }
        pstmt.setInt(1, idLicencia);

        int filasAfectadas = pstmt.executeUpdate();
       
        return filasAfectadas > 0; 

    } catch (SQLException e) {
        System.err.println("Error SQL al eliminar la licencia.");
        e.printStackTrace();
        return false;
    }
}
  

   public boolean registrarLicencia(int ciDocente, Date fechaInicio, Date fechaFin) {
        
        String sql = "INSERT INTO Licencia (ID_Docente, Fecha_Inicio, Fecha_Fin) VALUES (?, ?, ?)";
        
   
        try (Connection conn = conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Error: No se pudo establecer la conexión a la base de datos.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            
            pstmt.setInt(1, ciDocente);
            pstmt.setDate(2, fechaInicio);
            pstmt.setDate(3, fechaFin);

            
            int filasAfectadas = pstmt.executeUpdate();

            return filasAfectadas > 0; 

        } catch (SQLException e) {
            System.err.println("Error SQL al registrar la licencia. Verifique que el CI del docente exista y que las fechas sean válidas.");
            e.printStackTrace();
            
            return false;
        }
   }
}

    

