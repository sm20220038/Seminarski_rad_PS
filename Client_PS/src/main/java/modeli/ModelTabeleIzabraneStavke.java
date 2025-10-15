/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.StavkaKarte;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author stefa
 */
public class ModelTabeleIzabraneStavke extends AbstractTableModel {
    private List<StavkaKarte> lista = new LinkedList<>();
    private String[] kolone = {"Naziv", "Kolicina", "Cena", "Iznos", "Valuta", "Vreme ulaska","Vreme izlaska","Trajanje voznje"};

    public ModelTabeleIzabraneStavke(List<StavkaKarte> karte) {
        this.lista = karte;
    }

    public ModelTabeleIzabraneStavke() {
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaKarte sk = lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return "" + sk.getLinija().getPocetnaStanica() + "-" + sk.getLinija().getKrajnjaStanica();
            case 1:
                return sk.getKolicina();
            case 2:
                return sk.getCenaVoznje();
            case 3:
                return sk.getCenaStavke();
            case 4:
                return sk.getValuta();
            case 5:
                return sk.getVremeUlaska();
            case 6:
                return sk.getVremeIzlaska();
            case 7:
                return sk.getTrajanjeVoznje();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<StavkaKarte> getOriginalnaLista() {
        return lista;
    }

    public void setOriginalnaLista(List<StavkaKarte> lista) {
        this.lista = lista;
    }
    
    public void dodajStavku(StavkaKarte sk){
        lista.add(sk);
        fireTableDataChanged();
    }
    
    public StavkaKarte vratiStavku(int red){
        if(red >= 0 && red < lista.size()){
            return lista.get(red);
        }
        return null;
    }
    
    public void izbaciStavku(StavkaKarte sk){
        lista.remove(sk);
        fireTableDataChanged();
    }
}
