/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.putnik;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Putnik;
import operacije.OpstaSistemskaOperacija;

/**
 *
 * @author stefa
 */
public class PromeniPutnikSO extends OpstaSistemskaOperacija {
    private DBBroker broker = new DBBroker();
    private Putnik putnik;
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if(ado == null || !(ado instanceof Putnik)){
            throw new Exception("Sistem ne moze da nadje putnika");
        }
        return true;
    }

    @Override
    public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        putnik = (Putnik) ado;
        try {
            broker.promeni((Putnik) ado, null);
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da zapamti putnika.");
        }
        
        return true;
    }
    public Putnik getPutnik(){
        return putnik;
    }
}
