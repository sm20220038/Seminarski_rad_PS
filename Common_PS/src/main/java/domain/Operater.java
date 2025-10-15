/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author stefa
 */
public class Operater implements ApstraktniDomenskiObjekat{
    private long id;
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;
    private String email;

    public Operater() {
    }

    Operater(long idOperater, String imeOperater, String prezimeOperater) {
        this.id = idOperater;
        this.ime = imeOperater;
        this.prezime = prezimeOperater;
        korisnickoIme = "";
        email = "";
        lozinka = "";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public Operater(long id, String korisnickoIme, String ime, String prezime, String email) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }
    public Operater(long id){
        this.id = id;
        korisnickoIme="";
        lozinka="";
        ime="";
        prezime="";
        email="";
    }

    public Operater(String korisnickoIme, String ime, String prezime, String email) {
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
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
        final Operater other = (Operater) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.lozinka, other.lozinka);
    }

    @Override
    public String toString() {
        return korisnickoIme;
    }
    

    public Operater(String korisnickoIme, String lozinka, String ime, String prezime, String email) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }
    
    public Operater(long id, String korisnickoIme, String lozinka, String ime, String prezime, String email) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
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

    @Override
    public String getNazivTabele() {
        return "operater";
    }

    @Override
    public String getKoloneZaInsert() {
        return "korisnickoIme, lozinka, ime, prezime, email";
    }

    @Override
    public String getVrednostiZaInsert() {
        return  "'" + korisnickoIme + "', '" + lozinka + "', '" + ime + "', '" + prezime + "', '" + email + "'";
    }

    @Override
    public String getPrimarniKljuc() {
        return "id = " + id;
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "korisnickoIme= '" + korisnickoIme + "', lozinka='" + lozinka + "', ime='" + ime +"', prezime='" + prezime + "', email='"+ email + "'";
    }

    @Override
    public ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException {
        return new Operater(
                rs.getLong("id"),
                rs.getString("korisnickoIme"),
                rs.getString("lozinka"),
                rs.getString("ime"),
                rs.getString("prezime"),
                rs.getString("email")
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

    @Override
    public String getWhereUslov() {
        return "operater.id=" +id;
    }

    @Override
    public String getJoinKlauzulu() {
        return "";
    }

    @Override
    public String getNazivKolonePoIndex(int index) {
        String[] kolone = {"id","korisnickoIme","lozinka", "ime","prezime","email"};
        return kolone[index];
    }
    
}
