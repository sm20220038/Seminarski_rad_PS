/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.operater;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Operater;
import java.util.LinkedList;
import java.util.List;
import operacije.OpstaSistemskaOperacija;

/**
 *
 * @author stefa
 */
public class UcitajOperatereSO extends OpstaSistemskaOperacija {
    private DBBroker broker = new DBBroker();
    private List<Operater> operateri = new LinkedList<>();
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        return true;
    }

    @Override
    public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {
        List<ApstraktniDomenskiObjekat> operateriA = broker.getAll(new Operater(), null);
        for (ApstraktniDomenskiObjekat apstraktniDomenskiObjekat : operateriA) {
            operateri.add((Operater) apstraktniDomenskiObjekat);
        }
        return true;
    }
    
    public List<Operater> getOperateri(){
        return operateri;
    }
}
