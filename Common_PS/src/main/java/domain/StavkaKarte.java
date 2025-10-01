/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author stefa
 */
public class StavkaKarte {
    private long rb;
    private double pdv;
    private double popust;
    private int kolicina;
    private Date datumIzdavanja;
    private LocalTime vremeUlaska;
    private LocalTime vremeIzlaska;
    private LocalTime trajanjeVoznje;
    private String komentar;
    private double cena;
    private Karta karta;
    private Sediste sediste;
    public Karta getKarta() {
        return karta;
    }

    public void setKarta(Karta karta) {
        this.karta = karta;
    }
    public long getRb() {
        return rb;
    }

    public double getPdv() {
        return pdv;
    }

    public double getPopust() {
        return popust;
    }

    public int getKolicina() {
        return kolicina;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public LocalTime getVremeUlaska() {
        return vremeUlaska;
    }

    public LocalTime getVremeIzlaska() {
        return vremeIzlaska;
    }

    public LocalTime getTrajanjeVoznje() {
        return trajanjeVoznje;
    }

    public String getKomentar() {
        return komentar;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setId(long rb) {
        this.rb = rb;
    }

    public void setPdv(double pdv) {
        this.pdv = pdv;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public void setVremeUlaska(LocalTime vremeUlaska) {
        this.vremeUlaska = vremeUlaska;
    }

    public void setVremeIzlaska(LocalTime vremeIzlaska) {
        this.vremeIzlaska = vremeIzlaska;
    }

    public void setTrajanjeVoznje(LocalTime trajanjeVoznje) {
        this.trajanjeVoznje = trajanjeVoznje;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
    
}
