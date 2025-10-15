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
public class Putnik implements ApstraktniDomenskiObjekat{
    private long id;
    private String ime;
    private String prezime;
    private String email;
    private Kategorija kategorija;
    
    public Putnik(Long id, String ime, String prezime, String email, Kategorija kategorija){
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.kategorija = kategorija;
    }

    public Putnik(long id) {
        this.id = id;
        ime="";
        prezime="";
        email="";
        kategorija=null;
    }

    public Putnik(long idPutnik, String imePutnik, String prezimePutnik) {
        id = idPutnik;
        ime = imePutnik;
        prezime = prezimePutnik;
        email = "";
        kategorija = null;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
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
        final Putnik other = (Putnik) obj;
        return this.id == other.id;
    }
    
    public Putnik() {
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public String getNazivTabele() {
        return "putnik";
    }

    @Override
    public String getKoloneZaInsert() {
        return "ime, prezime, email, idKategorija";
    }

    @Override
    public String getVrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', " + kategorija.getId();
    }

    @Override
    public String getPrimarniKljuc() {
        return "id =" + id;
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "ime= '" + ime + "', prezime='" + prezime +"', email='"+email+"', idKategorija="+kategorija.getId();
    }

    @Override
    public String getWhereUslov() {
        return "putnik.id="+id;
    }

    @Override
    public String getJoinKlauzulu() {
        return "";
    }
    
    @Override
    public String getNazivKolonePoIndex(int index) {
        String[] kolone = {"id","ime","prezime","email","idKategorija"};
        return kolone[index];
    }

    @Override
    public ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException {
        return new Putnik(
            rs.getLong(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            new Kategorija(rs.getLong(5),getVrstaById(rs.getLong(5)),"")
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

    private VrstaKategorije getVrstaById(long id){
        VrstaKategorije vk;
        switch ((int)id) {
            case 1:
                vk = VrstaKategorije.BIZNIS;
                break;
            case 2:
                vk = VrstaKategorije.EKONOMICNA;
                break;
            case 3:
                vk = VrstaKategorije.PRVI_RAZRED;
                break;
            default:
                vk = null; 
        }
        return vk;
    }
  
}
