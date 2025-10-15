/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.Karta;
import domain.Linija;
import domain.Operater;
import domain.Putnik;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author stefa
 */
public class ModelTabelePrikazKarta extends AbstractTableModel {
    private List<Karta> karte = new LinkedList<>();
    private List<Karta> filtrirane = new LinkedList<>();
    private String[] kolone = {"Ime operatera", "Prezime operatera", "Datum", "Ime putnika", "Prezime putnika", "Ukupna cena", "Valuta"};
    private final DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
    
    public ModelTabelePrikazKarta(List<Karta> karte) {
        this.karte = karte;
    }
    
    @Override
    public int getRowCount() {
        return karte.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Karta k = karte.get(rowIndex);
        switch (columnIndex){
            case 0:
                return k.getOperater().getIme();
            case 1:
                return k.getOperater().getPrezime();
            case 2:
                if(k.getDatumIzdavanja() != null){
                    return formater.format(k.getDatumIzdavanja());
                }
                return "";
            case 3:
                return k.getPutnik().getIme();
            case 4:
                return k.getPutnik().getPrezime();
            case 5:
                return k.getUkupnaCena();
            case 6:
                return k.getValuta();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Karta> getKarte() {
        return karte;
    }

    public void setKarte(List<Karta> karte) {
        this.karte = karte;
    }
    
    public List<Karta> pretrazi(Date datumIzdavanja, Operater operater, Putnik putnik, Linija linija, double iznos){
        List<Karta> filtrirane = karte.stream()
                .filter(k -> datumIzdavanja == null || k.getDatumIzdavanja().after(datumIzdavanja))
                .filter(k -> operater == null || k.getOperater() != null && k.getOperater().getId() == operater.getId())
                .filter(k -> putnik == null || k.getPutnik() != null && k.getPutnik().getId() == putnik.getId())
                .filter(k -> linija == null || (k.getStavkeKarte() != null && 
                    k.getStavkeKarte().stream().anyMatch(s -> s.getLinija() != null && s.getLinija().getId() == linija.getId())))
                .filter(k -> iznos <= 0 || k.getUkupnaCena() >= iznos)
                .collect(Collectors.toList());
        this.filtrirane = filtrirane;
        fireTableDataChanged();
        return this.filtrirane;
    }
    public void resetujFiltriranje() {
        this.filtrirane = new LinkedList<>(karte);
        fireTableDataChanged();
    }

}
