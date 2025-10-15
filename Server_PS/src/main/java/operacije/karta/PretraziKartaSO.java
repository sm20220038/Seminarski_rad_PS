/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.karta;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Karta;
import domain.StavkaKarte;
import java.util.LinkedList;
import java.util.List;
import operacije.OpstaSistemskaOperacija;

/**
 *
 * @author stefa
 */
public class PretraziKartaSO extends OpstaSistemskaOperacija {
    DBBroker broker = new DBBroker();
    private List<Karta> listaKarti = new LinkedList<>();
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        
        return true;
    }

    @Override
    public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        String upit = " JOIN operater ON operater.id = karta.idOperater "
                + "JOIN putnik ON putnik.id = karta.idPutnik JOIN "
                + "kategorija ON kategorija.id = putnik.idKategorija";
        List<ApstraktniDomenskiObjekat> listaKartiA = broker.getAll(new Karta(), upit);
        for (ApstraktniDomenskiObjekat a : listaKartiA) {
            listaKarti.add((Karta) a);
        }
        
        for (Karta k : listaKarti) {
            String uslov = " JOIN linija ON linija.id = stavkakarte.idLinija "
                    + "WHERE stavkakarte.idKarta = " + k.getId();
            List<ApstraktniDomenskiObjekat> stavkeA = broker.getAll(new StavkaKarte(), uslov);
            List<StavkaKarte> stavke = new LinkedList<>();
            for (ApstraktniDomenskiObjekat a : stavkeA) {
                stavke.add((StavkaKarte) a);
            }
            for (StavkaKarte stavkaKarte : stavke) {
                System.out.println(stavkaKarte.getLinija().getPocetnaStanica() + " " 
                        + stavkaKarte.getLinija().getKrajnjaStanica() + " " + stavkaKarte.getCenaStavke());
            }
            k.setStavkeKarte(stavke);
        }
        return true;
    }
    public List<Karta> getListaKarti(){
        return listaKarti;
    }
    
}
