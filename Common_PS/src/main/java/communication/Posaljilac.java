/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author stefa
 */
public class Posaljilac {
    private final Socket soket;

    public Posaljilac(Socket soket) {
        this.soket = soket;
    }
    public void posalji(Object objekat) throws Exception{
        try {
            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(objekat);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska: " + e.getMessage());
        }
    }
}
