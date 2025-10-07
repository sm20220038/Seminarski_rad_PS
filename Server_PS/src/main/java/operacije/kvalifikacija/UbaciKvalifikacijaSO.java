/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kvalifikacija;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Kvalifikacija;
import operacije.OpstaSistemskaOperacija;

/**
 *
 * @author stefa
 */
public class UbaciKvalifikacijaSO extends OpstaSistemskaOperacija {
    private DBBroker broker = new DBBroker();
    private Kvalifikacija kvalifikacija;
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof Kvalifikacija)) {
            throw new Exception("Sistem ne moze da zapamti kvalifikaciju");
        }
        Kvalifikacija k = (Kvalifikacija) ado;
        if(k.getNaziv() == null || k.getNaziv().trim().isEmpty()
                || k.getInstitucija() == null || k.getInstitucija().trim().isEmpty()){
            throw new Exception("Sistem ne moze da zapamti kvalifikaciju");
        }
        return true;
    }

    @Override
    public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        kvalifikacija = (Kvalifikacija) ado;
        try {
            long id = broker.dodaj(kvalifikacija);
            kvalifikacija.setId(id);
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da zapamti kvalifikaciju");
        }
        return true;
    }
    
    public Kvalifikacija getKvalifikacija(){
        return kvalifikacija;
    }
}
