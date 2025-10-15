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
public class Linija implements ApstraktniDomenskiObjekat{
    private long id;
    private String pocetnaStanica, krajnjaStanica;
    private double cenaVoznje;

    public Linija() {
    }
    public Linija(long id){
        this.id = id;
        pocetnaStanica = "";
        krajnjaStanica = "";
        cenaVoznje = 0;
    }
    public Linija(long id, String pocetnaStanica, String krajnjaStanica, double cenaVoznje) {
        this.id = id;
        this.pocetnaStanica = pocetnaStanica;
        this.krajnjaStanica = krajnjaStanica;
        this.cenaVoznje = cenaVoznje;
    }

    Linija(long idLinija, String pocetnaStanica, String krajnjaStanica) {
        id = idLinija;
        this.pocetnaStanica = pocetnaStanica;
        this.krajnjaStanica = krajnjaStanica;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPocetnaStanica() {
        return pocetnaStanica;
    }

    public void setPocetnaStanica(String pocetnaStanica) {
        this.pocetnaStanica = pocetnaStanica;
    }

    public String getKrajnjaStanica() {
        return krajnjaStanica;
    }

    public void setKrajnjaStanica(String krajnjaStanica) {
        this.krajnjaStanica = krajnjaStanica;
    }

    public double getCenaVoznje() {
        return cenaVoznje;
    }

    public void setCenaVoznje(double cenaVoznje) {
        this.cenaVoznje = cenaVoznje;
    }

    @Override
    public String getNazivTabele() {
        return "linija";
    }

    @Override
    public String getKoloneZaInsert() {
        return "pocetnaStanica, krajnjaStanica, cenaVoznje";
    }

    @Override
    public String getVrednostiZaInsert() {
        return "'" + pocetnaStanica + "', '" + krajnjaStanica + "', " + cenaVoznje;
    }

    @Override
    public String getWhereUslov() {
        return "linija.id=" + id;
    }

    @Override
    public String getPrimarniKljuc() {
        return "id="+id;
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "pocetnaStanica='" + pocetnaStanica + "', krajnjaStanica='" + krajnjaStanica + "', cenaVoznje" + cenaVoznje;
    }

    @Override
    public String getJoinKlauzulu() {
        return "";
    }

    @Override
    public String toString() {
        return "" + pocetnaStanica + "-" + krajnjaStanica;
    }

    @Override
    public String getNazivKolonePoIndex(int index) {
        String[] kolone = {"id", "pocetnaStanica", "krajnjaStanica", "cenaVoznje"};
        return kolone[index];
    }

    @Override
    public ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException {
        return new Linija(
            rs.getLong(1),
            rs.getString(2),
            rs.getString(3),
            rs.getDouble(4)
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
