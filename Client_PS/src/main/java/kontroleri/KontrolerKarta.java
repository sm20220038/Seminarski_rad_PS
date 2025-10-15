/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domain.Karta;
import domain.Linija;
import domain.Operater;
import domain.Putnik;
import domain.StavkaKarte;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Komunikacija;

/**
 *
 * @author stefa
 */
public class KontrolerKarta {
    private static KontrolerKarta instance;
    
    private KontrolerKarta(){
        
    }
    
    public static KontrolerKarta getInstance(){
        if(instance == null){
            instance = new KontrolerKarta();
        }
        return instance;
    }
    
    public List<Operater> ucitajOperatere(){
        try {
            return Komunikacija.getInstance().ucitajOperatere();
        } catch (Exception e) {
            Logger.getLogger(KontrolerKarta.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public List<Linija> ucitajLinije(){
        try {
            return Komunikacija.getInstance().ucitajLinije();
        } catch (Exception e) {
            Logger.getLogger(KontrolerKarta.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Karta kreirajKartu(Karta k) {
        try {
            Karta karta = Komunikacija.getInstance().kreirajKarta(k);
            return karta;
        } catch (Exception e) {
            Logger.getLogger(KontrolerKarta.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public void sacuvajKartu(Karta karta) {
        try {
            Komunikacija.getInstance().sacuvajKartu(karta);
        } catch (Exception e) {
            Logger.getLogger(KontrolerKarta.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Karta> ucitajKarte() {
        try {
            return Komunikacija.getInstance().ucitajSveKarte();
        } catch (Exception e) {
            Logger.getLogger(KontrolerKarta.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public List<Karta> pretraziKarte(Karta k) {
        try {
            return Komunikacija.getInstance().pretraziKarte(k);
        } catch (Exception e) {
            Logger.getLogger(KontrolerKarta.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
