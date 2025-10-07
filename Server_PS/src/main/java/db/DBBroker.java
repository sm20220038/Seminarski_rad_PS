/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domain.ApstraktniDomenskiObjekat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefa
 */
public class DBBroker {
    Connection conn;

    public DBBroker() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat ado, String uslov) throws Exception{
        List<ApstraktniDomenskiObjekat> lista = new LinkedList<>();
        String upit = "SELECT * FROM " + ado.getNazivTabele() + " ";
        
        if(uslov != null && !uslov.isBlank()){
            upit += uslov;
        }
        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = ado.getListuIzSeta(rs);
        
        st.close();
        rs.close();
        
        return lista;
    }
    
    public long dodaj(ApstraktniDomenskiObjekat ado) throws Exception{
        String upit = "INSERT INTO " + ado.getNazivTabele() + " (" + ado.getKoloneZaInsert()
                + ") VALUES (" + ado.getVrednostiZaInsert() + ")";
        
        PreparedStatement ps = conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        
        long id = -1;
        if(rs.next()){
            id = rs.getLong(1);
        }
        
        ps.close();
        rs.close();
        
        return id;
    }
    
    public void promeni(ApstraktniDomenskiObjekat noviAdo, ApstraktniDomenskiObjekat stariAdo) throws Exception{
        
        String upit = "UPDATE " + noviAdo.getNazivTabele() + " SET "
                + noviAdo.getVrednostiZaUpdate() + " WHERE ";
        if(stariAdo == null){
            upit += noviAdo.getWhereUslov();
        } else{
            upit += stariAdo.getWhereUslov();
        }
        Statement st = conn.createStatement();
        st.executeUpdate(upit);
        st.close();
    }
    
    public int obrisi(ApstraktniDomenskiObjekat ado) throws Exception{
        String upit = "DELETE FROM " + ado.getNazivTabele() +
                " WHERE " + ado.getWhereUslov();
        
        Statement st = conn.createStatement();
        int promenjeniRedovi = st.executeUpdate(upit);
        st.close();
        return promenjeniRedovi;
    }
    
     public boolean commitTransaction() {
        try {
            conn.commit();
            return true;
        } catch (Exception e) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
            return false;
        }
    }

    
    public boolean rollbackTransaction() {
        try {
            conn.rollback();
            return true;
        } catch (Exception e) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
            return false;
        }
    }

    
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
