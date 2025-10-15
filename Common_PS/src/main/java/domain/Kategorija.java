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
public class Kategorija implements ApstraktniDomenskiObjekat{
    private long id;
    private VrstaKategorije vrstaKategorije;
    private String opis;

    public Kategorija(long id, VrstaKategorije vrstaKategorije, String opis) {
        this.id = id;
        this.vrstaKategorije = vrstaKategorije;
        this.opis = opis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VrstaKategorije getVrstaKategorije() {
        return vrstaKategorije;
    }

    public void setVrstaKategorije(VrstaKategorije vrstaKategorije) {
        this.vrstaKategorije = vrstaKategorije;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
    public VrstaKategorije getVrstaById(long id){
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
    
    public long getIdByVrsta(VrstaKategorije vrstaKategorije){
        if(vrstaKategorije.equals(VrstaKategorije.EKONOMICNA)){
            return 2;
        } else if(vrstaKategorije.equals(VrstaKategorije.BIZNIS)){
            return 1;
        } else if(vrstaKategorije.equals(VrstaKategorije.PRVI_RAZRED)){
            return 3;
        }
        return -1;
    }
    @Override
    public String getNazivTabele() {
        return "kategorija";
    }

    @Override
    public String getKoloneZaInsert() {
        return "vrstaKategorije, opis";
    }

    @Override
    public String getVrednostiZaInsert() {
        return  "'" + vrstaKategorije.toString() + "', '" + opis + "'";
    }

    @Override
    public String getWhereUslov() {
        return "kategorija.id="+id;
    }

    @Override
    public String getPrimarniKljuc() {
        return "id="+id;
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "vrstaKategorije= '" + vrstaKategorije.toString() + "', opis='" + opis +"'";
    }

    @Override
    public String getJoinKlauzulu() {
        return "";
    }

    @Override
    public String getNazivKolonePoIndex(int index) {
        String[] kolone = {"id","vrstaKategorije","opis"};
        return kolone[index];
    }

    @Override
    public ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException {
        return new Kategorija(
            rs.getLong(1),
            VrstaKategorije.valueOf(rs.getString(2)),
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
