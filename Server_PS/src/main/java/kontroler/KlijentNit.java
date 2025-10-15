/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import communication.Odgovor;
import communication.Posaljilac;
import communication.Primalac;
import communication.Zahtev;
import domain.Karta;
import domain.Kvalifikacija;
import domain.Linija;
import domain.Operater;
import domain.Putnik;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

/**
 *
 * @author stefa
 */
public class KlijentNit extends Thread {
    
    private final Socket soket;
    private final Posaljilac posaljilac;
    private final Primalac primalac;
    private boolean aktivna = true;
    private Kontroler kontroler;
    private ServerKontroler sk;
    public KlijentNit(Socket soket, ServerKontroler sk) {
        this.sk = sk;
        kontroler = Kontroler.getInstance();
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
                
                switch(zahtev.getOperacija()){
                    case PRIJAVI_OPERATER:
                        Operater o = (Operater) zahtev.getArgument();
                        o = kontroler.login(o);
                        odgovor.setRezultat(o);
                        break;
                    case KREIRAJ_PUTNIK:
                        Putnik p = (Putnik) zahtev.getArgument();
                        p = kontroler.kreirajPutnik(p);
                        odgovor.setRezultat(p);
                        break;
                    case UCITAJ_SVE_PUTNIKE:
                        List<Putnik> putnici = kontroler.ucitajSvePutnike();
                        odgovor.setRezultat(putnici);
                        break;
                    case UCITAJ_SVE_OPERATERE:
                        List<Operater> operateri = kontroler.ucitajOperatere();
                        odgovor.setRezultat(operateri);
                        break;
                    case UCITAJ_SVE_LINIJE:
                        List<Linija> linije = kontroler.ucitajLinije();
                        odgovor.setRezultat(linije);
                        break;
                    case OBRISI_PUTNIK:
                        Putnik op = (Putnik) zahtev.getArgument();
                        kontroler.obrisiPutnik(op);
                        odgovor.setRezultat("Obrisan putnik");
                        break;
                    case PRETRAZI_PUTNIK:
                        Putnik pp = (Putnik) zahtev.getArgument();
                        List<Putnik> listaPutnika = kontroler.pretraziPutnik(pp);
                        odgovor.setRezultat(listaPutnika);
                        break;
                    case PROMENI_PUTNIK:
                        Putnik prp = (Putnik) zahtev.getArgument();
                        kontroler.promeniPutnik(prp);
                        odgovor.setRezultat("Promenjen putnik");
                        break;
                    case UBACI_KVALIFIKACIJA:
                        Kvalifikacija k = (Kvalifikacija) zahtev.getArgument();
                        k = kontroler.ubaciKvalifikacija(k);
                        odgovor.setRezultat(k);                        
                        break;
                    case KREIRAJ_KARTA:
                        Karta karta = (Karta) zahtev.getArgument();
                        karta = kontroler.kreirajKarta(karta);
                        odgovor.setRezultat(karta);
                        break;
                    case UCITAJ_SVE_KARTE:
                        List<Karta> karte = kontroler.ucitajSveKarte();
                        odgovor.setRezultat(karte);
                        break;
                    case PRETRAZI_KARTA:
                        List<Karta> listaKarti = kontroler.pretraziKarta();
                        odgovor.setRezultat(listaKarti);
                        break;
                    case PROMENI_KARTA:
                        Karta kar = (Karta) zahtev.getArgument();
                        kontroler.promeniKartu(kar);
                        odgovor.setRezultat("Promenjena karta");
                        break;
                }
                posaljilac.posalji(odgovor);
            } catch (SocketException e) {
                System.err.println("Veza sa ovim klijentom je zatvorena: " + soket.getInetAddress());
                aktivna = false;
                break;
            } catch (IOException e){
                System.err.println("Ulaz-izlaz greska: " + e.getMessage());
                break;
            } catch (Exception e){
                System.err.println("Nepoznata greska: " + e.getMessage());
                break;
            }
        }
    }

    void zaustaviNit() {
        aktivna = false;
        interrupt();
        try {
            if (soket != null && !soket.isClosed()) {
                soket.close();
            }
        } catch (IOException e) {
            System.err.println("Greska pri zatvaranju soketa klijenta: " + e.getMessage());
        } finally {
            sk.ukloniKlijenta(this);
            System.out.println("Klijent nit je uspesno zaustavljena.");
    }
}
    
}
