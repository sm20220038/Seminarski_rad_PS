/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domain.ApstraktniDomenskiObjekat;
import domain.Karta;
import domain.Kvalifikacija;
import domain.Linija;
import domain.Operater;
import domain.Putnik;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import operacije.karta.KreirajKartuSO;
import operacije.karta.PretraziKartaSO;
import operacije.karta.PromeniKartaSO;
import operacije.karta.UcitajKarteSO;
import operacije.kvalifikacija.UbaciKvalifikacijaSO;
import operacije.linija.UcitajLinijeSO;
import operacije.operater.PrijaviOperaterSO;
import operacije.operater.UcitajOperatereSO;
import operacije.putnik.KreirajPutnikSO;
import operacije.putnik.ObrisiPutnikSO;
import operacije.putnik.PretraziPutnikSO;
import operacije.putnik.PromeniPutnikSO;
import operacije.putnik.UcitajPutnikSO;

/**
 *
 * @author stefa
 */
public class Kontroler {
    private static Kontroler instance;
    
    private Kontroler(){
        
    }
    
    public static Kontroler getInstance(){
        if(instance == null){
            instance = new Kontroler();
        }
        return instance;
    }
    
    public Operater login(Operater o) throws Exception{
        PrijaviOperaterSO operacija = new PrijaviOperaterSO();
        operacija.izvrsi(o);
        return operacija.getOperater();
    }

    public Putnik kreirajPutnik(Putnik p) throws Exception{
        KreirajPutnikSO operacija = new KreirajPutnikSO();
        operacija.izvrsi(p);
        return operacija.getPutnik();
    }

    public void obrisiPutnik(Putnik op) throws Exception{
        ObrisiPutnikSO operacija = new ObrisiPutnikSO();
        operacija.izvrsi(op);
    }
    
    public List<Putnik> pretraziPutnik(Putnik p) throws Exception {
        PretraziPutnikSO operacija = new PretraziPutnikSO();
        operacija.izvrsi(p);

        List<Putnik> putnici = new LinkedList<>();
        for (ApstraktniDomenskiObjekat ado : operacija.getRezultatPretrage()) {
            putnici.add((Putnik) ado);
        }
        return putnici;
    }

    public void promeniPutnik(Putnik prp) throws Exception{
        PromeniPutnikSO operacija = new PromeniPutnikSO();
        operacija.izvrsi(prp);
    }

    public Kvalifikacija ubaciKvalifikacija(Kvalifikacija k) throws Exception{
        UbaciKvalifikacijaSO operacija = new UbaciKvalifikacijaSO();
        operacija.izvrsi(k);
        return operacija.getKvalifikacija();
    }

    public Karta kreirajKarta(Karta karta) throws Exception{
        KreirajKartuSO operacija = new KreirajKartuSO();
        operacija.izvrsi(karta);
        return operacija.getKarta();
    }

    public List<Karta> pretraziKarta() throws Exception{
        PretraziKartaSO operacija = new PretraziKartaSO();
        operacija.izvrsi(null);
        return operacija.getListaKarti();
    }

    public void promeniKartu(Karta kar) throws Exception{
        PromeniKartaSO operacija = new PromeniKartaSO();
        operacija.izvrsi(kar);
    }

    public List<Putnik> ucitajSvePutnike() throws Exception{
        UcitajPutnikSO operacija = new UcitajPutnikSO();
        operacija.izvrsi(null);
        return operacija.getPutnici();
    }

    public List<Operater> ucitajOperatere() throws Exception{
        UcitajOperatereSO operacija = new UcitajOperatereSO();
        operacija.izvrsi(null);
        return operacija.getOperateri();
    }

    public List<Linija> ucitajLinije() throws SQLException {
        UcitajLinijeSO operacija = new UcitajLinijeSO();
        operacija.izvrsi(null);
        return operacija.getLinije();
    }

    public List<Karta> ucitajSveKarte() throws Exception{
        UcitajKarteSO operacija = new UcitajKarteSO();
        operacija.izvrsi(null);
        return operacija.getListaKarti();
    }
}
