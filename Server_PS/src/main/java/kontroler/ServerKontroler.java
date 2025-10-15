/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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
    
    public synchronized void pokreniServer() throws IOException{
        if(aktivan) {
            System.err.println("Server vec radi!");
            return;
        }
        try{
            ss = new ServerSocket(Config.getInstance().getPort());
            aktivan = true;
            serverNit = new Thread(this);
            serverNit.start();
            System.out.println("Server je pokrenut na portu: " + Config.getInstance().getPort());
        } catch (IOException e){
            System.err.println("Greska pri pokretanju servera: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        while(aktivan){
            try {
                System.out.println("Cekaju se klijenti...");
                Socket soket = ss.accept();
                System.out.println("Klijent koji je povezan: " + soket.getInetAddress());
                KlijentNit klijent = new KlijentNit(soket, this);
                synchronized (klijenti) {
                    klijenti.add(klijent);
                }
                klijent.start();
            } catch (SocketException se) {  
                if (aktivan) {
                    System.err.println("Greska u komunikaciji: " + se.getMessage());
                } else {
                    System.err.println("Server socket zatvoren.");
                }
            } catch (IOException e) {
                if(aktivan){
                    System.out.println("Greska pri prihvatanju klijenta: " + e.getMessage());
                }
            }
        }
    }
    
     public synchronized void zaustaviServer() {
        if (!aktivan) {
            System.err.println("Server vec nije aktivan.");
            return;
        }

        System.err.println("Zaustavljanje servera...");
        aktivan = false;

        if (serverNit != null && serverNit.isAlive()) {
            serverNit.interrupt();
        }
        zaustaviSveKlijente();
        zatvoriServerSocket();

         System.err.println("Server uspesno zaustavljen.");
    }

    
    private void zaustaviSveKlijente() {
        synchronized (klijenti) {
            for (KlijentNit klijent : new LinkedList<>(klijenti)) {
                try {
                    klijent.zaustaviNit();
                } catch (Exception e) {
                    System.err.println("️Greska pri zaustavljanju klijenta: " + e.getMessage());
                }
            }
            klijenti.clear();
        }
    }
    public void ukloniKlijenta(KlijentNit klijent){
        klijenti.remove(klijent);
        System.out.println("Klijent je uklonjen. Broj aktivnih klijenata: " + klijenti.size());
    }
    private void zatvoriServerSocket() {
        try {
            if (ss != null && !ss.isClosed()) {
                ss.close();
            }
        } catch (IOException e) {
            System.err.println("Greska pri zatvaranju server soketa: " + e.getMessage());
        }
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public int getBrojKlijenata() {
        synchronized (klijenti) {
            return klijenti.size();
        }
    }
    
}
