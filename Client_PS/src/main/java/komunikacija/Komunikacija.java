/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import communication.Odgovor;
import communication.Operacija;
import communication.Posaljilac;
import communication.Primalac;
import communication.Zahtev;
import domain.Karta;
import domain.Kvalifikacija;
import domain.Linija;
import domain.Operater;
import domain.Putnik;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author stefa
 */
public class Komunikacija {
    private Socket soket;
    private Primalac primalac;
    private Posaljilac posaljilac;
    private static Komunikacija instance;
    

    private Komunikacija() {
    }
    
    public static Komunikacija getInstance(){
        if(instance == null){
            instance = new Komunikacija();
        }
        return instance;
    }
    
    public void konekcija() throws IOException{
        soket = new Socket("localhost", 9000);
        primalac = new Primalac(soket);
        posaljilac = new Posaljilac(soket);
    }
    
    public Operater login(String korisnickoIme, String lozinka) throws Exception{
        Operater o = new Operater();
        o.setKorisnickoIme(korisnickoIme);
        o.setLozinka(lozinka);
        
        Zahtev zahtev = new Zahtev(Operacija.PRIJAVI_OPERATER, o);
        posaljilac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        o = (Operater) odgovor.getRezultat();
        return o;
    }
    
    public Putnik kreirajPutnik(Putnik p) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_PUTNIK, p);
        posaljilac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        Putnik p1 = (Putnik) odgovor.getRezultat();
        return p1;
    }

    public boolean promeniPutnik(Putnik putnik) throws Exception{
        try {
            Zahtev zahtev = new Zahtev(Operacija.PROMENI_PUTNIK, putnik);
            posaljilac.posalji(zahtev);
            Odgovor odgovor = (Odgovor) primalac.primi();
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    public void obrisiPutnik(Putnik putnik) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_PUTNIK, putnik);
        posaljilac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        return;
    }

    public List<Putnik> vratiSvePutnike(Putnik p) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_PUTNIK, p);
        posaljilac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Putnik> putnici = (List<Putnik>) odgovor.getRezultat();
        return putnici;
    }

    public List<Putnik> ucitajSvePutnike() throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SVE_PUTNIKE, null);
        posaljilac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Putnik> putnici = (List<Putnik>) odgovor.getRezultat();
        return putnici;
    }

    public Kvalifikacija sacuvajKvalifikaciju(Kvalifikacija kvalifikacija) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_KVALIFIKACIJA, kvalifikacija);
        posaljilac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        return (Kvalifikacija) odgovor.getRezultat();
    }

    public List<Operater> ucitajOperatere() throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SVE_OPERATERE, null);
        posaljilac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Operater> operateri = (List<Operater>) odgovor.getRezultat();
        return operateri;
    }

    public List<Linija> ucitajLinije() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SVE_LINIJE, null);
        posaljilac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Linija> linije = (List<Linija>) odgovor.getRezultat();
        return linije;
    }

    public Karta kreirajKarta(Karta k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_KARTA, k);
        posaljilac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        Karta karta = (Karta) odgovor.getRezultat();
        return karta;
    }

    public void sacuvajKartu(Karta karta) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_KARTA, karta);
        posaljilac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        return;
    }

    public List<Karta> ucitajSveKarte() throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SVE_KARTE, null);
        posaljilac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Karta> karte = (List<Karta>) odgovor.getRezultat();
        return karte;
    }

    public List<Karta> pretraziKarte(Karta k) throws Exception{
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_KARTA, k);
        posaljilac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Karta> karte = (List<Karta>) odgovor.getRezultat();
        return karte;
    }
}
