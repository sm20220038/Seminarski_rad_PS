/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.linija;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Linija;
import java.util.LinkedList;
import java.util.List;
import operacije.OpstaSistemskaOperacija;

/**
 *
 * @author stefa
 */
public class UcitajLinijeSO extends OpstaSistemskaOperacija {
    private DBBroker broker = new DBBroker();
    private List<Linija> linije = new LinkedList<>();
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        return true;
    }

    @Override
    public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        List<ApstraktniDomenskiObjekat> linijeA = broker.getAll(new Linija(), null);
        for (ApstraktniDomenskiObjekat apstraktniDomenskiObjekat : linijeA) {
            linije.add((Linija) apstraktniDomenskiObjekat);
        }
        return true;
    }
    
    public List<Linija> getLinije(){
        return linije;
    }
}
