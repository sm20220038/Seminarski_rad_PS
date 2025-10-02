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
public class Sediste implements ApstraktniDomenskiObjekat{
    private long id;
    private int broj;
    private boolean doProzora;
    private int red;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public boolean isDoProzora() {
        return doProzora;
    }

    public void setDoProzora(boolean doProzora) {
        this.doProzora = doProzora;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public Sediste(long id, int broj, boolean doProzora, int red) {
        this.id = id;
        this.broj = broj;
        this.doProzora = doProzora;
        this.red = red;
    }

    public Sediste(long id) {
        this.id=id;
        broj = 0;
        doProzora = false;
        red = 0;
    }

    public Sediste() {
    }
    

    @Override
    public String getNazivTabele() {
        return "sediste";
    }

    @Override
    public String getKoloneZaInsert() {
        return "broj, doProzora, red";
    }

    @Override
    public String getVrednostiZaInsert() {
        int doProzoraInt = doProzora ? 1:0;
        return broj + ", " + doProzoraInt + ", " + red;
    }

    @Override
    public String getWhereUslov() {
        return "sediste.id=" + id;
    }

    @Override
    public String getPrimarniKljuc() {
        return "id="+id;
    }

    @Override
    public String getVrednostiZaUpdate() {
        int doProzoraInt = doProzora ? 1:0;
        return "broj=" + broj + ", doProzora=" + doProzoraInt + ", red=" + red;
    }

    @Override
    public String getJoinKlauzulu() {
        return "";
    }

    @Override
    public String getNazivKolonePoIndex(int index) {
        String[] kolone = {"id", "broj", "doProzora", "red"};
        return kolone[index];
    }

    @Override
    public ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException {
        return new Sediste(
            rs.getLong(1),
            rs.getInt(2),
            rs.getBoolean(3),
            rs.getInt(4)
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
