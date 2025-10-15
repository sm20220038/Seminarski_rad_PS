/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

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
    private Time vremeUlaska;
    private Time vremeIzlaska;
    private Time trajanjeVoznje;
    private String valuta;
    private Linija linija;

    public StavkaKarte() {
    }

    public StavkaKarte(long rb, Karta karta, int kolicina, double cenaVoznje, Date datumIzdavanja, Time vremeUlaska, Time vremeIzlaska, String valuta, Linija linija) {
        this.rb = rb;
        this.karta = karta;
        this.kolicina = kolicina;
        this.cenaVoznje = cenaVoznje;
        this.cenaStavke = cenaVoznje*kolicina;
        this.datumIzdavanja = datumIzdavanja;
        this.vremeUlaska = vremeUlaska;
        this.vremeIzlaska = vremeIzlaska;
        
        this.trajanjeVoznje = izracunajTrajanje(vremeUlaska, vremeIzlaska);
        this.valuta = valuta;
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

    public Time getVremeUlaska() {
        return vremeUlaska;
    }

    public void setVremeUlaska(Time vremeUlaska) {
        this.vremeUlaska = vremeUlaska;
    }

    public Time getVremeIzlaska() {
        return vremeIzlaska;
    }

    public void setVremeIzlaska(Time vremeIzlaska) {
        this.vremeIzlaska = vremeIzlaska;
    }

    public Time getTrajanjeVoznje() {
        return trajanjeVoznje;
    }

    public void setTrajanjeVoznje(Time trajanjeVoznje) {
        this.trajanjeVoznje = trajanjeVoznje;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public Linija getLinija() {
        return linija;
    }

    public void setLinija(Linija linija) {
        this.linija = linija;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final StavkaKarte other = (StavkaKarte) obj;
        if (!Objects.equals(this.linija, other.linija)) {
            return false;
        }
        if (this.rb != other.rb) {
            return false;
        }
    
        if (this.karta != null && other.karta != null) {
            if (this.karta.getId() != other.karta.getId()) {
                return false;
            }
        } else if (this.karta != other.karta) { 
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = (int) (83 * hash + this.rb);
        if (this.karta != null) {
            hash = (int) (83 * hash + this.karta.getId());
        }
        return hash;
    }

    @Override
    public String getNazivTabele() {
        return "stavkakarte";
    }

    @Override
    public String getKoloneZaInsert() {
        return "idKarta, kolicina, cenaVoznje, cenaStavke, datumIzdavanja, vremeUlaska, vremeIzlaska, trajanjeVoznje, valuta, idLinija";
    }

    @Override
    public String getVrednostiZaInsert() {
        return karta.getId() + ", " + kolicina + ", " + cenaVoznje 
                + ", " + cenaStavke + ", '" + datumIzdavanja 
                + "', '" + vremeUlaska + "', '" + vremeIzlaska 
                + "', '" + trajanjeVoznje + "', '" + valuta 
                + "', " + linija.getId();
    }

    @Override
    public String getWhereUslov() {
        return "stavkakarte.rb = " + rb + " AND stavkakarte.idKarta = "+karta.getId();
    }

    @Override
    public String getPrimarniKljuc() {
        return "rb =" + rb + " AND idKarta = "+karta.getId();
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "idKarta="+karta.getId() + ", kolicina=" + kolicina + ", cenaVoznje=" + cenaVoznje 
                + ", cenaStavke=" + cenaStavke + ", datumIzdavanja='" + datumIzdavanja 
                + "', vremeUlaska='" + vremeUlaska + "', vremeIzlaska='" + vremeIzlaska 
                + "', trajanjeVoznje='" + trajanjeVoznje + "', valuta=" + valuta 
                + ", idLinija=" + linija.getId();
    }

    @Override
    public String getJoinKlauzulu() {
        inicijalizacija();
        return " JOIN " + 
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
        String[] kolone = {"rb","idKarta","kolicina","cenaVoznje","cenaStavke","datumIzdavanja","vremeUlaska","vremeIzlaska","trajanjeVoznje","valuta","idLinija"};
        return kolone[index];
    }
    
    public void inicijalizacija(){
        if(this.karta == null) karta = new Karta();
        if(linija == null) linija = new Linija();
    }
    
    @Override
    public ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException {

        long idKarta = rs.getLong("stavkakarte.idKarta"); 
        Karta karta = new Karta(idKarta); 

        long rb = rs.getLong("stavkakarte.rb");
        int kolicina = rs.getInt("stavkakarte.kolicina");
        double cenaVoznje = rs.getDouble("stavkakarte.cenaVoznje");
    
        Date datumIzdavanja = rs.getDate("stavkakarte.datumIzdavanja");

        Time vremeUlaska = rs.getTime("stavkakarte.vremeUlaska");
        Time vremeIzlaska = rs.getTime("stavkakarte.vremeIzlaska");
    
        String valuta = rs.getString("stavkakarte.valuta");

        long idLinija = rs.getLong("linija.id");
        String pocetnaStanica = rs.getString("linija.pocetnaStanica");
        String krajnjaStanica = rs.getString("linija.krajnjaStanica");

        Linija linija = new Linija(idLinija, pocetnaStanica, krajnjaStanica);

        StavkaKarte stavka = new StavkaKarte(
            rb, 
            karta, 
            kolicina, 
            cenaVoznje,       
            datumIzdavanja, 
            vremeUlaska, 
            vremeIzlaska, 
            valuta, 
            linija
        );
    
        return stavka;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getListuIzSeta(ResultSet rs) throws SQLException {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            lista.add(getNoviObjekat(rs));
        }
        return lista;
    }

    private Time izracunajTrajanje(Time pocetak, Time kraj) {
        long diffMillis = kraj.getTime() - pocetak.getTime();
        if (diffMillis < 0) {
            // Ako voznja prelazi ponoc
            diffMillis += 24 * 60 * 60 * 1000;
        }
        long totalSeconds = diffMillis / 1000;
        int hours = (int) (totalSeconds / 3600);
        int minutes = (int) ((totalSeconds % 3600) / 60);
        int seconds = (int) (totalSeconds % 60);

        LocalTime trajanje = LocalTime.of(hours, minutes, seconds);
        return Time.valueOf(trajanje);
    }
    
    
}
