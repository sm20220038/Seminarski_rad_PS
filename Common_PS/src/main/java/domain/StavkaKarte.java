/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
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
    private Karta karta;
    private int kolicina;
    private double cenaVoznje;
    private double cenaStavke;
    private Date datumIzdavanja;
    private LocalTime vremeUlaska;
    private LocalTime vremeIzlaska;
    private LocalTime trajanjeVoznje;
    private String valuta;
    private String komentar;
    private Linija linija;

    public StavkaKarte() {
    }

    public StavkaKarte(long rb, Karta karta, int kolicina, double cenaVoznje, double cenaStavke, Date datumIzdavanja, LocalTime vremeUlaska, LocalTime vremeIzlaska, LocalTime trajanjeVoznje, String valuta, String komentar, Linija linija) {
        this.rb = rb;
        this.karta = karta;
        this.kolicina = kolicina;
        this.cenaVoznje = cenaVoznje;
        this.cenaStavke = cenaStavke;
        this.datumIzdavanja = datumIzdavanja;
        this.vremeUlaska = vremeUlaska;
        this.vremeIzlaska = vremeIzlaska;
        this.trajanjeVoznje = trajanjeVoznje;
        this.valuta = valuta;
        this.komentar = komentar;
        this.linija = linija;
    }

    public StavkaKarte(long rb, Karta karta, int kolicina, double cenaVoznje, Date datumIzdavanja, LocalTime vremeUlaska, LocalTime vremeIzlaska, String valuta, String komentar, Linija linija) {
        this.rb = rb;
        this.karta = karta;
        this.kolicina = kolicina;
        this.cenaVoznje = cenaVoznje;
        this.cenaStavke = cenaVoznje*kolicina;
        this.datumIzdavanja = datumIzdavanja;
        this.vremeUlaska = vremeUlaska;
        this.vremeIzlaska = vremeIzlaska;
        long seconds = Duration.between(vremeUlaska, vremeIzlaska).getSeconds();
        this.trajanjeVoznje = LocalTime.ofSecondOfDay(seconds);
        this.valuta = valuta;
        this.komentar = komentar;
        this.linija = linija;
    }

    
    public long getRb() {
        return rb;
    }

    public void setRb(long rb) {
        this.rb = rb;
    }

    public Karta getKarta() {
        return karta;
    }

    public void setKarta(Karta karta) {
        this.karta = karta;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCenaVoznje() {
        return cenaVoznje;
    }

    public void setCenaVoznje(double cenaVoznje) {
        this.cenaVoznje = cenaVoznje;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
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

    public LocalTime getTrajanjeVoznje() {
        return trajanjeVoznje;
    }

    public void setTrajanjeVoznje(LocalTime trajanjeVoznje) {
        this.trajanjeVoznje = trajanjeVoznje;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Linija getLinija() {
        return linija;
    }

    public void setLinija(Linija linija) {
        this.linija = linija;
    }
    
    @Override
    public String getNazivTabele() {
        return "stavkakarte";
    }

    @Override
    public String getKoloneZaInsert() {
        return "idKarta, kolicina, cenaVoznje, cenaStavke, datumIzdavanja, vremeUlaska, vremeIzlaska, trajanjeVoznje, valuta, komentar, idLinija";
    }

    @Override
    public String getVrednostiZaInsert() {
        return karta.getId() + ", " + kolicina + ", " + cenaVoznje 
                + ", " + cenaStavke + ", '" + datumIzdavanja 
                + "', '" + vremeUlaska + "', '" + vremeIzlaska 
                + "', '" + trajanjeVoznje + "', " + valuta 
                + ", '" + komentar + "', " + linija.getId();
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
        return "idKarta="+karta.getId() + ", kolicina=" + kolicina + ", cenaVoznje=" + cenaVoznje 
                + ", cenaStavke=" + cenaStavke + ", datumIzdavanja='" + datumIzdavanja 
                + "', vremeUlaska='" + vremeUlaska + "', vremeIzlaska='" + vremeIzlaska 
                + "', trajanjeVoznje='" + trajanjeVoznje + "', valuta=" + valuta 
                + ", komentar='" + komentar + "', idLinija=" + linija.getId();
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
                linija.getNazivTabele() +
                " ON " +
                linija.getNazivTabele() +
                ".id=" +
                this.getNazivTabele() +
                ".idLinija";
    }

    @Override
    public String getNazivKolonePoIndex(int index) {
        String[] kolone = {"rb","idKarta","kolicina","cenaVoznje","cenaStavke","datumIzdavanja","vremeUlaska","vremeIzlaska","trajanjeVoznje","valuta","komentar","idLinija"};
        return kolone[index];
    }
    
    public void inicijalizacija(){
        if(this.karta == null) karta = new Karta();
        if(linija == null) linija = new Linija();
    }
    
    @Override
    public ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException {
        return new StavkaKarte(
                rs.getLong(this.getNazivTabele() + ".rb"),
                new Karta(rs.getLong(this.getNazivTabele() + ".idKarta")),
                rs.getInt(this.getNazivTabele() + ".kolicina"),
                rs.getDouble(this.getNazivTabele() + ".cenaVoznje"),
                rs.getDate(this.getNazivTabele() + ".datumIzdavanja"),
                rs.getTime("stavkakarte.vremeUlaska").toLocalTime(),
                rs.getTime("stavkakarte.vremeIzlaska").toLocalTime(),
                rs.getString(this.getNazivTabele() + ".valuta"),
                rs.getString(this.getNazivTabele() + ".komentar"),
                new Linija(rs.getLong(this.getNazivTabele() + ".idLinija"))
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
