/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

/**
 *
 * @author stefa
 */
public class ServerKontroler {
    private static ServerKontroler instance;
    
    private ServerKontroler(){
        
    }
    
    public static ServerKontroler getInstance(){
        if(instance == null){
            instance = new ServerKontroler();
        }
        return instance;
    }
}
