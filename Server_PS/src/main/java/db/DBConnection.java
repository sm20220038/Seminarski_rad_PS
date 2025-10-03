/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author stefa
 */
public class DBConnection {
    private static Connection conn;
    private DBConnection(){
        
    }
    
    public static Connection getConnection() throws SQLException{
        if(conn == null){
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prodajakarata", "root", "");
        }
        return conn;
    }
}
