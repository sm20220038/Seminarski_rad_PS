/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author stefa
 */
public class OperaterKvalifikacija implements ApstraktniDomenskiObjekat{
    private Operater operater;
    private Kvalifikacija kvalifikacija;
    private Date datumIzdavanja;

    public OperaterKvalifikacija(Operater operater, Kvalifikacija kvalifikacija, Date datumIzdavanja) {
        this.operater = operater;
        this.kvalifikacija = kvalifikacija;
        this.datumIzdavanja = datumIzdavanja;
    }
    
    public Operater getOperater() {
        return operater;
    }

    public void setOperater(Operater operater) {
        this.operater = operater;
    }

    public Kvalifikacija getKvalfikacija() {
        return kvalifikacija;
    }

    public void setKvalfikacija(Kvalifikacija kvalifikacija) {
        this.kvalifikacija = kvalifikacija;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public OperaterKvalifikacija() {
    }

    

    @Override
    public String getNazivTabele() {
        return "operaterkvalifikacija";
    }

    @Override
    public String getKoloneZaInsert() {
        return "idOperater,idKvalifikacija,datumIzdavanja";
    }

    @Override
    public String getVrednostiZaInsert() {
        return operater.getId() + ", " + kvalifikacija.getId() + ", '" + datumIzdavanja + "'";
    }

    @Override
    public String getWhereUslov() {
         return "operaterkvalifikacija.idOperater = " + operater.getId() + "AND operaterkvalifikacija.idKvalifikacija = "+kvalifikacija.getId();
    }

    @Override
    public String getPrimarniKljuc() {
        return "idOperater =" + operater.getId() + "AND idKvalifikacija = "+ kvalifikacija.getId();
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "idOperater=" + operater.getId() + ", idKvalifikacija=" + kvalifikacija.getId() + ", dautmIzdavanja='" + datumIzdavanja + "'";
    }
    public void inicijalizacija(){
        if(operater == null) operater = new Operater();
        if(kvalifikacija == null) kvalifikacija = new Kvalifikacija();
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
                kvalifikacija.getNazivTabele() +
                " ON " +
                kvalifikacija.getNazivTabele() +
                ".id=" +
                this.getNazivTabele() +
                ".idKvalifikacija";
    }

    @Override
    public String getNazivKolonePoIndex(int index) {
        String[] kolone = {"idOperater", "idKvalifikacija", "datumIzdavanja"};
        return kolone[index];
    }

    @Override
    public ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException {
        return new OperaterKvalifikacija(
                new Operater(rs.getLong(this.getNazivTabele()+".idOperater")),
                new Kvalifikacija(rs.getLong(this.getNazivTabele()+".idKvalifikacija")),
                rs.getDate(this.getNazivTabele()+".datumIzdavanja"));
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
