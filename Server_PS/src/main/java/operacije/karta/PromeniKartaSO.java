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
public class PromeniKartaSO extends OpstaSistemskaOperacija {
    private List<StavkaKarte> listaStavki = new LinkedList<>();
    private Karta k;
    DBBroker broker = new DBBroker();
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if(ado == null || !(ado instanceof Karta)){
            throw new Exception("Sistem ne moze da nadje kartu");
        }
        return true;
    }

    @Override
public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
    k = (Karta) ado;
    listaStavki = k.getStavkeKarte();

    
    String uslov = "JOIN linija ON stavkakarte.idLinija = linija.id WHERE stavkakarte.idKarta = " + k.getId();
    List<ApstraktniDomenskiObjekat> listaUBaziA = broker.getAll(new StavkaKarte(), uslov);
    List<StavkaKarte> listaUBazi = new LinkedList<>();
    for (ApstraktniDomenskiObjekat a : listaUBaziA) {
        listaUBazi.add((StavkaKarte) a);
    }

    
    broker.promeni(k, null);

    
    List<StavkaKarte> listaStavkiZaDodavanje = new LinkedList<>();
    List<StavkaKarte> listaStavkiZaMenjanje = new LinkedList<>();
    List<StavkaKarte> listaStavkiZaBrisanje = new LinkedList<>();

    
    for (StavkaKarte trenutna : listaStavki) {
        boolean postojiUBazi = false;
        for (StavkaKarte stavkaUBazi : listaUBazi) {
            if (stavkaUBazi.equals(trenutna)) {
                postojiUBazi = true;
                if (stavkaUBazi.getKolicina() != trenutna.getKolicina()) {
                    listaStavkiZaMenjanje.add(trenutna);
                }
                break;
            }
        }
        if (!postojiUBazi) {
            listaStavkiZaDodavanje.add(trenutna);
        }
    }

    
    for (StavkaKarte stavkaUBazi : listaUBazi) {
        boolean postoji = false;
        for (StavkaKarte trenutna : listaStavki) {
            if (stavkaUBazi.equals(trenutna) && stavkaUBazi.getKolicina() == trenutna.getKolicina()) {
                postoji = true;
                break;
            }
        }
        if (!postoji) {
            listaStavkiZaBrisanje.add(stavkaUBazi);
        }
    }

    
    for (StavkaKarte sk : listaStavkiZaDodavanje) {
        broker.dodaj(sk);
    }

    
    for (StavkaKarte sk : listaStavkiZaBrisanje) {
        sk.setKarta(k);
        String deleteUslov = "WHERE rb = " + sk.getRb() + " AND idKarta = " + sk.getKarta().getId();
        broker.obrisi(sk, deleteUslov);
    }

    
    for (StavkaKarte sk : listaStavkiZaMenjanje) {
        broker.promeni(sk, null);
    }

    return true;
}
    
}
