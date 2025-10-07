/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import communication.Odgovor;
import communication.Posaljilac;
import communication.Primalac;
import communication.Zahtev;
import java.net.Socket;

/**
 *
 * @author stefa
 */
public class KlijentNit extends Thread {
    
    private final Socket soket;
    private final Posaljilac posaljilac;
    private final Primalac primalac;
    private boolean aktivna = true;

    public KlijentNit(Socket soket) {
        this.soket = soket;
        this.posaljilac = new Posaljilac(soket);
        this.primalac = new Primalac(soket);
    }
    
    @Override
    public void run() {
        while(aktivna){
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                System.out.println("Primljen zahtev: " + zahtev.getOperacija());
                
                Odgovor odgovor = new Odgovor();
                
                if(zahtev == null){
                    System.out.println("Klijent se odjavio.");
                    aktivna = false;
                }
                
            } catch (Exception e) {
                System.out.println("Klijent se odjavio: " + soket.getInetAddress());
                aktivna = false;
            }
        }
    }

    

    void zaustaviNit() {
        aktivna = false;
        try {
            soket.close();
        } catch (Exception e) {
            System.out.println("Greska pri zatvaranju soketa klijenta.");
        }
    }
    
}
