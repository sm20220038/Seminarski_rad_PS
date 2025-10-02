/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author stefa
 */
public class StavkaKarte implements ApstraktniDomenskiObjekat{
    private long rb;
    private int kolicina;
    private Date datumIzdavanja;
    private LocalTime vremeUlaska;
    private LocalTime vremeIzlaska;
    private double popust;
    private double pdv;
    private double cenaPrePDVA;
    private LocalTime trajanjeVoznje;
    private double cenaStavke;
    private String komentar;
    private Karta karta;
    private Sediste sediste;
    private String valuta;

    public long getRb() {
        return rb;
    }

    public void setRb(long rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public LocalTime getVremeUlaska() {
        return vremeUlaska;
    }

    public void setVremeUlaska(LocalTime vremeUlaska) {
        this.vremeUlaska = vremeUlaska;
    }

    public LocalTime getVremeIzlaska() {
        return vremeIzlaska;
    }

    public void setVremeIzlaska(LocalTime vremeIzlaska) {
        this.vremeIzlaska = vremeIzlaska;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public double getPdv() {
        return pdv;
    }

    public void setPdv(double pdv) {
        this.pdv = pdv;
    }

    public double getCenaPrePDVA() {
        return cenaPrePDVA;
    }

    public void setCenaPrePDVA(double cenaPrePDVA) {
        this.cenaPrePDVA = cenaPrePDVA;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Karta getKarta() {
        return karta;
    }

    public void setKarta(Karta karta) {
        this.karta = karta;
    }

    public Sediste getSediste() {
        return sediste;
    }

    public void setSediste(Sediste sediste) {
        this.sediste = sediste;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public LocalTime getTrajanjeVoznje() {
        return trajanjeVoznje;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public StavkaKarte() {
    }

    public StavkaKarte(long rb, int kolicina, Date datumIzdavanja, LocalTime vremeUlaska, LocalTime vremeIzlaska, double popust, double pdv, double cenaPrePDVA, LocalTime trajanjeVoznje, double cenaStavke, String komentar, Karta karta, Sediste sediste, String valuta) {
        this.rb = rb;
        this.kolicina = kolicina;
        this.datumIzdavanja = datumIzdavanja;
        this.vremeUlaska = vremeUlaska;
        this.vremeIzlaska = vremeIzlaska;
        this.popust = popust;
        this.pdv = pdv;
        this.cenaPrePDVA = cenaPrePDVA;
        this.trajanjeVoznje = trajanjeVoznje;
        this.cenaStavke = cenaStavke;
        this.komentar = komentar;
        this.karta = karta;
        this.sediste = sediste;
        this.valuta = valuta;
    }

    @Override
    public String getNazivTabele() {
        return "stavkakarte";
    }

    @Override
    public String getKoloneZaInsert() {
        return "kolicina, datumIzdavanja, vremeUlaska, vremeIzlaska, popust, pdv, cenaPrePDVA, komentar, idKarta, idSediste, valuta";
    }

    @Override
    public String getVrednostiZaInsert() {
        return kolicina + ", '" + datumIzdavanja + "', '" + vremeUlaska + "', '" + vremeIzlaska + "', " + popust + ", " + pdv + ", " + cenaPrePDVA + ", '" + komentar + "', " + karta.getId() + ", " + sediste.getId() + ", '" + valuta + "'";
    }

    @Override
    public String getWhereUslov() {
        return "stavkakarte.rb = " + rb + "AND stavkakarte.idKarta = "+karta.getId();
    }

    @Override
    public String getPrimarniKljuc() {
        return "rb =" + rb + "AND idKarta = "+karta.getId();
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "kolicina=" + kolicina + ", datumIzdavanja='" + datumIzdavanja + "', vremeUlaska='" + vremeUlaska + "', vremeIzlaska='" + vremeIzlaska + 
                "', popust=" + popust + ", pdv=" + pdv + ", cenaPrePDVA=" + cenaPrePDVA + ", komentar='" + komentar + "', idKarta=" + karta.getId() + 
                ", idSediste=" + sediste.getId() + ", valuta='" + valuta + "'";
    }

    @Override
    public String getJoinKlauzulu() {
        inicijalizacija();
        return "JOIN " + 
                karta.getNazivTabele() + 
                " ON " + 
                karta.getNazivTabele() +
                ".id = " + 
                this.getNazivTabele() +
                ".idKarta JOIN " +
                sediste.getNazivTabele() +
                " ON " +
                sediste.getNazivTabele() +
                ".id=" +
                this.getNazivTabele() +
                ".idSediste";
    }

    @Override
    public String getNazivKolonePoIndex(int index) {
        String[] kolone = {"rb","kolicina","datumIzdavanja", "vremeUlaska", "vremeIzlaska", "popust", "pdv", "cenaPrePDVA", "trajanjeVoznje", "cenaStavke", "komentar","idKarta","idSediste","valuta"};
        return kolone[index];
    }
    
    public void inicijalizacija(){
        if(this.karta == null) karta = new Karta();
        if(sediste == null) sediste = new Sediste();
    }
    
    @Override
    public ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException {
        return new StavkaKarte(
                rs.getLong(this.getNazivTabele() + ".rb"),
                rs.getInt(this.getNazivTabele() + ".kolicina"),
                rs.getDate(this.getNazivTabele() + ".datumIzdavanja"),
                rs.getTime("stavkakarte.vremeUlaska").toLocalTime(),
                rs.getTime("stavkakarte.vremeIzlaska").toLocalTime(),
                rs.getDouble(this.getNazivTabele() + ".popust"),
                rs.getDouble(this.getNazivTabele() + ".pdv"),
                rs.getDouble(this.getNazivTabele() + ".cenaPrePDVA"),
                (rs.getTime("stavkakarte.trajanjeVoznje")).toLocalTime(),
                rs.getDouble(this.getNazivTabele() + ".cenaStavke"),
                rs.getString(this.getNazivTabele() + ".komentar"),
                new Karta(rs.getLong(this.getNazivTabele() + ".idKarta")),
                new Sediste(rs.getLong(this.getNazivTabele() + ".idSediste")),
                rs.getString(this.getNazivTabele() + ".valuta"));
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
