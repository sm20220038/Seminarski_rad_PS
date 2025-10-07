/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package operacije;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import java.sql.SQLException;

/**
 *
 * @author stefa
 */
public abstract class OpstaSistemskaOperacija {
    protected DBBroker dbBroker;
    //Template Method pattern definisemo metodu izvrsi, a validiraj i izvrsiOperaciju implementiramo u samim klasama.
    synchronized public boolean izvrsi(ApstraktniDomenskiObjekat ado) throws SQLException{
        boolean signal = false;
        try {
            dbBroker = new DBBroker();
            if(validiraj(ado)){
                signal = izvrsiOperaciju(ado);
                if(signal){
                    dbBroker.commitTransaction();
                } else {
                    dbBroker.closeConnection();
                }
            }
        } catch (Exception e) {
            System.out.println("Rollback: " + e.getMessage());
            dbBroker.rollbackTransaction();
        }
        return signal;
    }
    public abstract boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception;
    public abstract boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception;
    
}
