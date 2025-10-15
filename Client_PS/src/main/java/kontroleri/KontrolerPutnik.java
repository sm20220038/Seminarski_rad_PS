/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domain.Kategorija;
import domain.Putnik;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Komunikacija;

/**
 *
 * @author stefa
 */
public class KontrolerPutnik {
    private static KontrolerPutnik instance;
    private Putnik putnik;
    
    private KontrolerPutnik(){
        
    }
    
    public static KontrolerPutnik getInstance(){
        if(instance == null){
            instance = new KontrolerPutnik();
        }
        return instance;
    }
    
    public Putnik kreirajPutnik(Kategorija kategorija){
        try {
            putnik = new Putnik(Long.MIN_VALUE, null, null, null, kategorija);
            putnik = Komunikacija.getInstance().kreirajPutnik(putnik);
            System.out.println(putnik);
            return putnik;
        } catch (Exception e) {
            return null;
        }
    }
    
    public long getIdPutnik(){
        return putnik.getId();
    }

    public boolean promeniPutnik(long id, String ime, String prezime, String email, Kategorija kategorija){
        try {
            putnik = new Putnik(id, ime, prezime, email, kategorija);
            boolean odgovor = Komunikacija.getInstance().promeniPutnik(putnik);
            return odgovor;
        } catch (Exception ex) {
            Logger.getLogger(KontrolerPutnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void obrisiPutnika(Putnik putnik) throws Exception {
        Komunikacija.getInstance().obrisiPutnik(putnik);
    }

    public List<Putnik> vratiSvePutnike(Putnik p) {
        try {
            return Komunikacija.getInstance().vratiSvePutnike(p);
        } catch (Exception ex) {
            Logger.getLogger(KontrolerPutnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Putnik> ucitajSvePutnike() {
        try {
            return Komunikacija.getInstance().ucitajSvePutnike();
        } catch (Exception e) {
            Logger.getLogger(KontrolerPutnik.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
