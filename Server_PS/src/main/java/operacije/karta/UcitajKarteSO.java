/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.karta;

import db.DBBroker;
import domain.ApstraktniDomenskiObjekat;
import domain.Karta;
import domain.Linija;
import domain.StavkaKarte;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import operacije.OpstaSistemskaOperacija;

/**
 *
 * @author stefa
 */
public class UcitajKarteSO extends OpstaSistemskaOperacija {
    private DBBroker broker = new DBBroker();
    private List<Karta> listaKarti = new LinkedList<>();
    @Override
    public boolean validiraj(ApstraktniDomenskiObjekat ado) throws Exception {
        return true;
    }

    @Override
    public boolean izvrsiOperaciju(ApstraktniDomenskiObjekat ado) throws Exception {

        String kartaJoin = " JOIN operater ON operater.id = karta.idOperater " +
                           "JOIN putnik ON putnik.id = karta.idPutnik";

        List<ApstraktniDomenskiObjekat> karteA = broker.getAll(new Karta(), kartaJoin);

        Map<Long, Karta> mapaKarti = new LinkedHashMap<>();
        for (ApstraktniDomenskiObjekat adoKarta : karteA) {
            Karta k = (Karta) adoKarta;
            k.setStavkeKarte(new LinkedList<>());
            mapaKarti.put(k.getId(), k);
        }

        if (mapaKarti.isEmpty()) {
            listaKarti = new LinkedList<>();
            return true;
        }

        StringBuilder uslovStavke = new StringBuilder();
        uslovStavke.append(" JOIN linija ON linija.id = stavkakarte.idLinija WHERE stavkakarte.idKarta IN (");

        StringJoiner sj = new StringJoiner(",");
        for (Long idKarta : mapaKarti.keySet()) {
            sj.add(String.valueOf(idKarta));
        }
        uslovStavke.append(sj.toString());
        uslovStavke.append(")");

        List<ApstraktniDomenskiObjekat> stavkeA = broker.getAll(new StavkaKarte(), uslovStavke.toString());

        for (ApstraktniDomenskiObjekat adoStavka : stavkeA) {
            StavkaKarte sk = (StavkaKarte) adoStavka;
            Karta k = mapaKarti.get(sk.getKarta().getId()); 
            if (k != null) {
                k.getStavkeKarte().add(sk);
            }
        }

        listaKarti = new LinkedList<>(mapaKarti.values());
        return true;
    }

    public List<Karta> getListaKarti(){
        return listaKarti;
    }
}
