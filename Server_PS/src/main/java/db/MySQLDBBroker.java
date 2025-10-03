/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domain.ApstraktniDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author stefa
 */
public class MySQLDBBroker implements DBBroker {
    private final String dbName;
    private Connection conn;
    private static MySQLDBBroker instance;
    
    private MySQLDBBroker(){
        dbName = "prodajakarata";
    }
    public static MySQLDBBroker getInstance(){
        if(instance == null){
            instance = new MySQLDBBroker();
        }
        return instance;
    }
    
    @Override
    public boolean createConnection() {
        try {
            if(conn != null && !conn.isClosed()) return true;
            String url = "jdbc:mysql://localhost:3306/" + dbName;
            conn = DriverManager.getConnection(url, "root","");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean createZapis(ApstraktniDomenskiObjekat ado) {
        String upit = "INSERT INTO '" + dbName + "'.'" + ado.getNazivTabele() + "' (" +
                ado.getKoloneZaInsert() + ") VALUES (" + ado.getVrednostiZaInsert()+ ")";
        return executeUpdate(upit);
    }

    @Override
    public boolean updateZapis(ApstraktniDomenskiObjekat stariAdo, ApstraktniDomenskiObjekat noviAdo) {
        String upit = "UPDATE '" + dbName + "'.'" + noviAdo.getNazivTabele() + 
                "' SET " + noviAdo.getVrednostiZaUpdate() + " WHERE " + stariAdo.getWhereUslov();
        return executeUpdate(upit);
    }

    @Override
    public boolean deleteZapis(ApstraktniDomenskiObjekat ado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteZapisi(ApstraktniDomenskiObjekat ado, String where) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ApstraktniDomenskiObjekat nadjiZapis(ApstraktniDomenskiObjekat ado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> nadjiZapise(ApstraktniDomenskiObjekat ado, String where) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean commitTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean rollbackTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ApstraktniDomenskiObjekat getZapis(ApstraktniDomenskiObjekat ado, int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getBrojZapisa(ApstraktniDomenskiObjekat ado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getPozicijuZapisa(ApstraktniDomenskiObjekat ado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isDBConnected() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private boolean executeUpdate(String query) {
        Statement statement = null;
        boolean uspeh = false;
        try {
            statement = conn.createStatement();
            int promenjeniRedovi = statement.executeUpdate(query);
            uspeh = promenjeniRedovi > 0;
        } catch (SQLException e) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
        } finally {
            zatvoriResurse(null, statement, null);
        }
        return uspeh;
    }

    private void zatvoriResurse(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try { 
                rs.close(); 
            } catch (SQLException e){
                System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e); 
            }
        }
        if (stmt != null) {
            try { 
                stmt.close();
            } catch (SQLException e){
                System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e); 
            }
        }
        if (conn != null) {
            try { 
                conn.close(); 
            } catch (SQLException e){
                System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e); 
            }
        }
    }
}
