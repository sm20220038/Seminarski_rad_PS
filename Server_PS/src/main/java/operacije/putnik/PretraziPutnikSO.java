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
public class PretraziPutnikSO extends OpstaSistemskaOperacija {
    private DBBroker broker = new DBBroker();
    List<ApstraktniDomenskiObjekat> rezultat = new LinkedList<>();
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        if(ado == null || !(ado instanceof Putnik)){
            throw new Exception("Sistem ne moze da nadje putnika");
        }
        return true;
    }

    @Override
    public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            Putnik p = (Putnik) ado;
            String whereUslov = kreirajKriterijum(p);
            rezultat = broker.getAll(p, whereUslov);
            return true;
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da pronadje putnike po zadatim kriterijumima");
        }
        
    }
    public List<ApstraktniDomenskiObjekat> getRezultatPretrage(){
        return rezultat;
    }
    private String kreirajKriterijum(Putnik p) {
        StringBuilder sb = new StringBuilder("1=1"); 

        if (p.getId() > 0) {
            sb.append(" AND id = ").append(p.getId());
        }
        if (p.getIme() != null && !p.getIme().trim().isEmpty()) {
            sb.append(" AND ime LIKE '%").append(p.getIme().trim()).append("%'");
        }
        if (p.getPrezime() != null && !p.getPrezime().trim().isEmpty()) {
            sb.append(" AND prezime LIKE '%").append(p.getPrezime().trim()).append("%'");
        }
        if (p.getEmail() != null && !p.getEmail().trim().isEmpty()) {
            sb.append(" AND email LIKE '%").append(p.getEmail().trim()).append("%'");
        }

        return sb.toString();
    }
}
