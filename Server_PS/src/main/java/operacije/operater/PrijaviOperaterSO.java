/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.operater;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Operater;
import java.util.List;
import operacije.OpstaSistemskaOperacija;

/**
 *
 * @author stefa
 */
public class PrijaviOperaterSO extends OpstaSistemskaOperacija {
    private DBBroker broker = new DBBroker();
    private Operater operater;
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if (ado == null || !(ado instanceof Operater)) {
            throw new Exception("Korisnicko ime i sifra nisu ispravni");
        }
        Operater o = (Operater) ado;
        if(o.getKorisnickoIme() == null || o.getKorisnickoIme().trim().isEmpty()
                || o.getLozinka() == null || o.getLozinka().trim().isEmpty()){
            throw new Exception("Korisnicko ime i sifra nisu ispravni");
        }
        return true;
    }

    @Override
    public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        Operater o = (Operater) ado;
        try {
            Operater pronadjen = pronadjiOperatera(o);
            if(pronadjen == null){
                throw new Exception("Korisnicko ime i sifra nisu ispravni");
            }
            this.operater = pronadjen;
        } catch (Exception e) {
            throw new Exception("Korisnicko ime i sifra nisu ispravni");
        }
        return true;
    }
    
    public Operater pronadjiOperatera(Operater o) throws Exception{
        String uslov = " WHERE ";

        uslov += "korisnickoIme='" + o.getKorisnickoIme() + "' AND ";
        uslov += "lozinka='" + o.getLozinka() + "'";
        try {
            List<ApstraktniDomenskiObjekat> operateri= broker.getAll(o, uslov);
            if(!operateri.isEmpty()){
                return (Operater) operateri.get(0);
            }
        } catch (Exception e) {
            throw new Exception("Korisnicko ime i sifra nisu ispravni");
        }
        return null;
    }
    
    public Operater getOperater(){
        return operater;
    }
}
