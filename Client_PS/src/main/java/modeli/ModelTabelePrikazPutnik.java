/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.Kategorija;
import domain.Putnik;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author stefa
 */
public class ModelTabelePrikazPutnik extends AbstractTableModel {
    private List<Putnik> putnici = new LinkedList<>();
    private List<Putnik> filtriraniPutnici = new LinkedList<>();
    private String[] kolone = {"id", "ime", "prezime", "email", "kategorija"};
    
    public ModelTabelePrikazPutnik(List<Putnik> putnici){
        this.putnici = putnici;
    }
    
    @Override
    public int getRowCount() {
        return putnici.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Putnik p = putnici.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return p.getId();
            case 1:
                return p.getIme();
            case 2:
                return p.getPrezime();
            case 3:
                return p.getEmail();
            case 4:
                return p.getKategorija().getVrstaKategorije().toString();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Putnik> getPutnici() {
        return putnici;
    }

    public void setPutnici(List<Putnik> putnici) {
        this.putnici = putnici;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public List<Putnik> pretrazi(String ime, String prezime, String email, Kategorija kategorija){
        List<Putnik> filtriraniPutnici = (List<Putnik>) putnici.stream()
                .filter(p -> (ime == null || ime.isBlank() || p.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(p -> (prezime == null || prezime.isBlank() || p.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(p -> (email == null || email.isBlank() || p.getEmail().toLowerCase().contains(email.toLowerCase())))
                .filter(p -> (kategorija == null || p.getKategorija().equals(kategorija))).collect(Collectors.toList());
        this.filtriraniPutnici = filtriraniPutnici;
        fireTableDataChanged();
        return this.filtriraniPutnici;
    }
    
    public void resetujFiltriranje() {
        this.filtriraniPutnici = new LinkedList<>(putnici);
        fireTableDataChanged();
    }
    
    public void osveziTabelu(){
        fireTableDataChanged();
    }
}
