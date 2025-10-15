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
public class ModelTabelePrikazStavkiKarte extends AbstractTableModel {
    private List<StavkaKarte> lista = new LinkedList<>();
    private String[] kolone = {"Naziv","Kolicina","Cena","Iznos","Valuta","Vreme ulaska","Vreme izlaska","Trajanje voznje"};

    public ModelTabelePrikazStavkiKarte(List<StavkaKarte> lista) {
        this.lista = lista;
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
                return sk.getLinija().toString();
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

    public List<StavkaKarte> getLista() {
        return lista;
    }

    public void setLista(List<StavkaKarte> lista) {
        this.lista = lista;
    }
    
    
    
    
}
