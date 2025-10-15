/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.putnik;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Putnik;
import java.util.LinkedList;
import java.util.List;
import operacije.OpstaSistemskaOperacija;

/**
 *
 * @author stefa
 */
public class UcitajPutnikSO extends OpstaSistemskaOperacija {
    private DBBroker broker = new DBBroker();
    List<Putnik> putnici = new LinkedList<>();
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        return true;
    }

    @Override
    public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        String uslov = " JOIN kategorija ON putnik.idKategorija = kategorija.idKategorije";
        List<ApstraktniDomenskiObjekat> putniciA = broker.getAll(new Putnik(), uslov);
        for (ApstraktniDomenskiObjekat apstraktniDomenskiObjekat : putniciA) {
            putnici.add((Putnik) apstraktniDomenskiObjekat);
        }
        return true;
    }
    public List<Putnik> getPutnici(){
        return putnici;
    }
    
}
