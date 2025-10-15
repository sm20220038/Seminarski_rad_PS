/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domain.Operater;
import forme.LoginForma;
import komunikacija.Komunikacija;

/**
 *
 * @author stefa
 */
public class KontrolerLogin {
    private final LoginForma lf;
    
    public KontrolerLogin (LoginForma lf){
        this.lf = lf;
        lf.setTitle("Ulogujte se");
        lf.setResizable(false);
    }
    
    public Operater login(String korisnickoIme, String lozinka) throws Exception{
        Komunikacija instance = Komunikacija.getInstance();
        instance.konekcija();
       
        Operater o = instance.login(korisnickoIme, lozinka);
        return o;
    }
}
