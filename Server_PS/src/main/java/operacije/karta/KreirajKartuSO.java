/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.karta;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Karta;
import operacije.OpstaSistemskaOperacija;

/**
 *
 * @author stefa
 */
public class KreirajKartuSO extends OpstaSistemskaOperacija {
    Karta k;
    DBBroker broker = new DBBroker();
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception{
        if(ado == null || !(ado instanceof Karta)) {
            throw new Exception("Sistem ne moze da kreira kartu");
        }
        return true;
    }

    @Override
    public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception{
        k = (Karta) ado;
        long id = broker.dodaj(k);
        k.setId(id);
        return true;
    }
    public Karta getKarta(){
        return k;
    }
}
