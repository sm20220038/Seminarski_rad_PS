/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.ObjectInputStream;
import java.net.Socket;



/**
 *
 * @author stefa
 */
public class Primalac {
   private final Socket soket;
   
   public Primalac(Socket soket){
       this.soket = soket;
   }
   
   public Object primi() throws Exception {
       ObjectInputStream in;
       try {
           in = new ObjectInputStream(soket.getInputStream());
           return in.readObject();
       } catch (Exception e) {
           e.printStackTrace();
           throw new Exception("Greska kod citanja objekta: " + e.getMessage());
       }
   }
}
