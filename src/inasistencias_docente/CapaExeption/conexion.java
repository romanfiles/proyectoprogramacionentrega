/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package CapaExeption;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/inasistencias_docentes?zeroDateTimeBehavior=CONVERT_TO_NULL"; 
    private static final String USUARIO = "root";          
    private static final String CONTRASENIA = ""; 

    public static Connection getConnection() {
        Connection conn = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
        } catch (ClassNotFoundException e) {
            System.err.println("Error de Driver (JAR no encontrado): " + e.getMessage());
        } catch (SQLException e) {
          
            System.err.println("Error al conectar a la BD.");
            e.printStackTrace();
        }
        return conn;
    }
}