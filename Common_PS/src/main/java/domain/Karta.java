/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author stefa
 */
public class Karta implements ApstraktniDomenskiObjekat{
    private long id;
    private double ukupnaCena;
    private String valuta;
    private Operater operater;
    private Putnik putnik;
    private Date datumIzdavanja;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public Operater getOperater() {
        return operater;
    }

    public void setOperater(Operater operater) {
        this.operater = operater;
    }

    public Putnik getPutnik() {
        return putnik;
    }

    public void setPutnik(Putnik putnik) {
        this.putnik = putnik;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Karta() {
    }
    public Karta(long id){
        this.id = id;
        ukupnaCena = 0;
        valuta = "";
        operater = null;
        putnik = null;
        datumIzdavanja = null;
    }
    public Karta(long id, double ukupnaCena, String valuta, Operater operater, Putnik putnik, Date datumIzdavanja) {
        this.id = id;
        this.ukupnaCena = ukupnaCena;
        this.valuta = valuta;
        this.operater = operater;
        this.putnik = putnik;
        this.datumIzdavanja = datumIzdavanja;
    }
    
    

    @Override
    public String getNazivTabele() {
        return "karta";
    }

    @Override
    public String getKoloneZaInsert() {
        return "ukupnaCena,valuta,idOperater,idPutnik,datumIzdavanja";
    }

    @Override
    public String getVrednostiZaInsert() {
        return ukupnaCena + ", '" + valuta + "', " + operater.getId() + ", " + putnik.getId() + ", '" + datumIzdavanja + "'";
    }

    @Override
    public String getWhereUslov() {
        return "karta.id="+id;
    }

    @Override
    public String getPrimarniKljuc() {
        return "id=" + id;
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "ukupnaCena=" + ukupnaCena + ", valuta='"+valuta
                +"', idOperater=" + operater.getId()+", idPutnik="+putnik.getId() +", datumIzdavanja='"+datumIzdavanja+"'";
    }
    public void inicijalizacija(){
        if(operater == null) operater = new Operater();
        if(putnik == null) putnik = new Putnik();
    }
    @Override
    public String getJoinKlauzulu() {
        inicijalizacija();
        return "JOIN " + 
                operater.getNazivTabele() + 
                " ON " + 
                operater.getNazivTabele() +
                ".id = " + 
                this.getNazivTabele() +
                ".idOperater JOIN " +
                putnik.getNazivTabele() +
                " ON " +
                putnik.getNazivTabele() +
                ".id=" +
                this.getNazivTabele() +
                ".idPutnik";
    }

    @Override
    public String getNazivKolonePoIndex(int index) {
        String[] kolone = {"id", "ukupnaCena", "valuta","idOperater","idPutnik","datumIzdavanja"};
        return kolone[index];
    }

    @Override
    public ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException {
        return new Karta(
            rs.getLong(this.getNazivTabele()+".id"),
            rs.getDouble(this.getNazivTabele() + ".ukupnaCena"),
            rs.getString(this.getNazivTabele() + ".valuta"),
            new Operater(rs.getLong(this.getNazivTabele() + ".idOperater")),
            new Putnik(rs.getLong(this.getNazivTabele() + ".idPutnik")),
            rs.getDate(this.getNazivTabele()+".datumIzdavanja")
        );
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getListuIzSeta(ResultSet rs) throws SQLException {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            lista.add(getNoviObjekat(rs));
        }
        return lista;
    }

    
    
    
}
