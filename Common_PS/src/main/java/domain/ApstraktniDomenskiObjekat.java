/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author stefa
 */
public interface ApstraktniDomenskiObjekat extends Serializable{
    String getNazivTabele();
    String getKoloneZaInsert();
    String getVrednostiZaInsert();
    String getWhereUslov();
    String getPrimarniKljuc();
    String getVrednostiZaUpdate();
    String getJoinKlauzulu();
    String getNazivKolonePoIndex(int index);
    ApstraktniDomenskiObjekat getNoviObjekat(ResultSet rs) throws SQLException;
    List<ApstraktniDomenskiObjekat> getListuIzSeta(ResultSet rs) throws SQLException;
}
