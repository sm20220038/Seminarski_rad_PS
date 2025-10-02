/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stefa
 */
public class Kvalifikacija implements ApstraktniDomenskiObjekat{
    private long id;
    private String naziv;
    private String institucija;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    public Kvalifikacija() {
    }
    public Kvalifikacija(long id){
        this.id = id;
        naziv = "";
        institucija = "";
    }
    public Kvalifikacija(long id, String naziv, String institucija) {
        this.id = id;
        this.naziv = naziv;
        this.institucija = institucija;
    }

    @Override
    public String getNazivTabele() {
        return "kvalifikacija";
    }

    @Override
    public String getKoloneZaInsert() {
        return "naziv, institucija";
    }

    @Override
    public String getVrednostiZaInsert() {
        return "'" + naziv + "', '" + institucija + "'";
    }

    @Override
    public String getWhereUslov() {
        return "kvalifikacija.id="+id;
    }

    @Override
    public String getPrimarniKljuc() {
        return "id="+id;
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "naziv= '" + naziv + "', institucija='" + institucija + "'";
    }

    @Override
    public String getJoinKlauzulu() {
        return "";
    }

    @Override
    public String getNazivKolonePoIndex(int index) {
        String[] kolone = {"id","naziv","institucija"};
        return kolone[index];
    }

    @Override
    public ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException {
        return new Kvalifikacija(
            rs.getLong(1),
            rs.getString(2),
            rs.getString(3)
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
