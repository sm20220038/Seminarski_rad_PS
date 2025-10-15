/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domain.Kvalifikacija;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Komunikacija;

/**
 *
 * @author stefa
 */
public class KontrolerKvalifikacija {
    private static KontrolerKvalifikacija instance;
    private Kvalifikacija kvalifikacija;
    
    
    private KontrolerKvalifikacija(){
        
    }
    
    public static KontrolerKvalifikacija getInstance(){
        if(instance == null){
            instance = new KontrolerKvalifikacija();
        }
        return instance;
    }

    public Kvalifikacija sacuvajKvalifikaciju(String naziv, String institucija) {
        try {
            kvalifikacija = new Kvalifikacija();
            kvalifikacija.setNaziv(naziv);
            kvalifikacija.setInstitucija(institucija);
            kvalifikacija = Komunikacija.getInstance().sacuvajKvalifikaciju(kvalifikacija);
            return kvalifikacija;
        } catch (Exception ex) {
            Logger.getLogger(KontrolerKvalifikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
