/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domain.ApstraktniDomenskiObjekat;
import domain.Karta;
import domain.StavkaKarte;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import konfiguracija.Config;

/**
 *
 * @author stefa
 */
public class DBBroker {
    private final String dbName;
    private Connection conn;
    private static DBBroker instance;
    private final Config dbconfig = Config.getInstance();
    
    private DBBroker(){
        dbName = dbconfig.getTableName();
    }
    public static DBBroker getInstance(){
        if(instance == null){
            instance = new DBBroker();
        }
        return instance;
    }
    
    
    public boolean createConnection() {
        try {
            if(conn != null && !conn.isClosed()) return true;
            
            conn = DriverManager.getConnection(dbconfig.getUrl(), dbconfig.getUsername(),dbconfig.getPassword());
            conn.setAutoCommit(dbconfig.isAutoCommit());
        } catch (SQLException e) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
            return false;
        }
        return true;
    }

    
    public boolean createZapis(ApstraktniDomenskiObjekat ado) {
        String upit = "INSERT INTO `" + dbName + "`.`" + ado.getNazivTabele() + "` (" +
                ado.getKoloneZaInsert() + ") VALUES (" + ado.getVrednostiZaInsert()+ ")";
        return executeUpdate(upit);
    }

    
    public boolean updateZapis(ApstraktniDomenskiObjekat stariAdo, ApstraktniDomenskiObjekat noviAdo) {
        String upit = "UPDATE `" + dbName + "`.`" + noviAdo.getNazivTabele() + 
                "` SET " + noviAdo.getVrednostiZaUpdate() + " WHERE " + stariAdo.getWhereUslov();
        return executeUpdate(upit);
    }
    
    public boolean updateZapis(ApstraktniDomenskiObjekat ado){
        String upit = "UPDATE `" + dbName + "`.`" + ado.getNazivTabele() + "` SET " 
                + ado.getVrednostiZaUpdate() + " WHERE " + ado.getWhereUslov();
        return executeUpdate(upit);
    }
    
    public boolean deleteZapis(ApstraktniDomenskiObjekat ado) {
        String upit = "DELETE FROM `" + dbName + "`.`" + ado.getNazivTabele() + "` WHERE " 
                + ado.getWhereUslov();
        return executeUpdate(upit);
    }

    
    public boolean deleteZapisi(ApstraktniDomenskiObjekat ado, String where) {
        String upit = "DELETE FROM `" + dbName + "`.`" + ado.getNazivTabele() +
                "` WHERE " + where;
        return executeUpdate(upit);
    }

    
    public ApstraktniDomenskiObjekat nadjiZapis(ApstraktniDomenskiObjekat ado) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT * FROM `" + dbName + "`.`" + ado.getNazivTabele() +
                "` " + ado.getJoinKlauzulu() + " WHERE " + ado.getWhereUslov();
        
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(upit);
            if(rs.next()){
                ado = ado.getNoviObjekat(rs);
            } else{
                ado = null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String)null,e);
        } finally {
            zatvoriResurse(null, statement, rs);
        }
        return ado;
    }

    
    public List<ApstraktniDomenskiObjekat> nadjiZapise(ApstraktniDomenskiObjekat ado, String where) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT * FROM `" + dbName + "`.`" + ado.getNazivTabele() +
                "` " + ado.getJoinKlauzulu() + " WHERE " + ado.getWhereUslov();
        List<ApstraktniDomenskiObjekat> lista = new LinkedList<>();
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(upit);
            while(rs.next()){
                lista.add(ado.getNoviObjekat(rs));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String)null,e);
        } finally {
            zatvoriResurse(null, statement, rs);
        }
        return lista;
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
        zatvoriResurse(conn, null, null);
    }

    
    public ApstraktniDomenskiObjekat getZapis(ApstraktniDomenskiObjekat ado, int index) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT * FROM `" + dbName + "`.`" + ado.getNazivTabele()
                + "` ORDER BY " + ado.getNazivKolonePoIndex(0) + 
                " ASC LIMIT " + index + ",1";
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(upit);
            if(rs.next()){
                ado = ado.getNoviObjekat(rs);
            } else {
                ado = null;
            }
        } catch (Exception e) {
            ado = null;
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
        } finally {
            zatvoriResurse(null, statement, rs);
        }
        return ado;
    }

    
    public int getBrojZapisa(ApstraktniDomenskiObjekat ado) {
        ResultSet rs = null;
        Statement statement = null;
        int brojRedova = 0;
        String upit = "SELECT * FROM `" + dbName + "`.`" + ado.getNazivTabele()+ "`;";
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(upit);
            if(rs.last()){
                brojRedova = rs.getRow();
            }
        } catch (Exception e) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
        } finally {
            zatvoriResurse(null, statement, rs);
        }
        return brojRedova;
    }

    
    public int getPozicijuZapisa(ApstraktniDomenskiObjekat ado) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT COUNT(*) AS position FROM `" + dbName + "`.`" + ado.getNazivTabele()+
            "` WHERE " + ado.getNazivKolonePoIndex(0) + " < " + ado.getPrimarniKljuc();
        
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(upit);
            if(rs.next()){
                return rs.getInt("position");
            }
        } catch (Exception e) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
        } finally {
            zatvoriResurse(null, statement, rs);
        }
        return -1;
    }

    public boolean sacuvajKartuSaStavkama(Karta karta){
        try {
            boolean uspesno = createZapis(karta);
            if(!uspesno){
                rollbackTransaction();
                return false;
            }
            
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID() AS id");
            if(rs.next()){
                karta.setId(rs.getLong("id"));
            }
            zatvoriResurse(null, statement, rs);
            
            long rb = 1;
            for(StavkaKarte stavka : karta.getStavkeKarte()){
                stavka.setKarta(karta);
                stavka.setRb(rb++);
                boolean stavkaUspesno = createZapis(stavka);
                if(!stavkaUspesno){
                    rollbackTransaction();
                    return false;
                }
            }
            
            commitTransaction();
            return true;
        } catch (Exception e) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
            rollbackTransaction();
            return false;
        }
    }
    
    public Karta ucitajKartuSaStavkama(Karta karta){
        try {
            
            Karta pronadjenaKarta = (Karta) nadjiZapis(karta);
            if(pronadjenaKarta == null){
                return null;
            }
            
            StavkaKarte stavka = new StavkaKarte();
            stavka.setKarta(pronadjenaKarta);
            String where = "stavkakarte.idKarta = " + pronadjenaKarta.getId();
            List<ApstraktniDomenskiObjekat> stavke = nadjiZapise(stavka, where);
            
            List<StavkaKarte> listaStavki = new LinkedList<>();
            for(ApstraktniDomenskiObjekat ado : stavke){
                listaStavki.add((StavkaKarte) ado);
            }
            
            pronadjenaKarta.setStavkeKarte(listaStavki);
            return pronadjenaKarta;
        } catch (Exception e) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
            return null;
        }
    }
    
    public boolean isDBConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            System.getLogger(DBBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
            return false;
        }
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
