/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import konfiguracija.Config;

/**
 *
 * @author stefa
 */
public class ServerKontroler implements Runnable{
    
    private ServerSocket ss;
    private boolean aktivan = false;
    private final List<KlijentNit> klijenti = new LinkedList<>();
    private Thread serverNit;
    
    public void pokreniServer() throws IOException{
        if(aktivan) {
            System.out.println("Server vec radi!");
            return;
        }
//        if(ss.isBound()){
//            System.out.println("Soket je boundovan!");
//        }
//        if(ss.isClosed()){
//            System.out.println("Soket je zatvoren!");
//        }
        ss = new ServerSocket(Config.getInstance().getPort());
        aktivan = true;
        serverNit = new Thread(this);
        serverNit.start();
        System.out.println("Server je pokrenut na portu: " + Config.getInstance().getPort());
    }

    @Override
    public void run() {
        while(aktivan){
            try {
                System.out.println("Cekaju se klijenti...");
                Socket soket = ss.accept();
                System.out.println("Klijent koji je povezan: " + soket.getInetAddress());
                KlijentNit klijent = new KlijentNit(soket);
                klijenti.add(klijent);
                klijent.start();
            } catch (Exception e) {
                if(aktivan){
                    System.out.println("Greska pri prihvatanju klijenta: " + e.getMessage());
                }
            
            }
        }
    }
    public void ukloniKlijenta(KlijentNit klijent){
        klijenti.remove(klijent);
        System.out.println("Klijent je uklonjen. Broj aktivnih klijenata: " + klijenti.size());
    }
    public void zaustaviServer(){
        try {
            aktivan = false;
            for(KlijentNit klijent : klijenti){
                klijent.zaustaviNit();
            }
            klijenti.clear();
            if(ss != null && !ss.isClosed()){
                ss.close();
            }
            System.out.println("Server je zaustavljen.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
