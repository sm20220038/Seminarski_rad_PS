/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.putnik;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Putnik;
import operacije.OpstaSistemskaOperacija;

/**
 *
 * @author stefa
 */
public class KreirajPutnikSO extends OpstaSistemskaOperacija {
    private Putnik putnik;
    private DBBroker broker = new DBBroker();
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if(ado == null || !(ado instanceof Putnik)){
            throw new Exception("Sistem ne moze da kreira osobu");
        }
        return true;
    }

    @Override
    public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        putnik = (Putnik) ado;
        long id = -1;
        try{
            id = broker.dodaj(ado);
        } catch(Exception e){
            throw new Exception("Sistem ne moze da zapamti putnika");
        }
        putnik.setId(id);
        return true;
    }
    public Putnik getPutnik(){
        return putnik;
    }
}
