/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Date;

/**
 *
 * @author stefa
 */
public class OperaterKvalifikacija {
    private Operater operater;
    private Kvalifikacija kvalfikacija;
    private Date datumIzdavanja;

    public Operater getOperater() {
        return operater;
    }

    public void setOperater(Operater operater) {
        this.operater = operater;
    }

    public Kvalifikacija getKvalfikacija() {
        return kvalfikacija;
    }

    public void setKvalfikacija(Kvalifikacija kvalfikacija) {
        this.kvalfikacija = kvalfikacija;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }
    
    
}
