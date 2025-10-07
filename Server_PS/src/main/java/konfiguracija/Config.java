/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;



/**
 *
 * @author stefa
 */
public class Config {
    private static Config instance;
    private final Properties properties;
    
    private Config() {
        properties = new Properties();
        try (InputStream fis = Config.class.getClassLoader().getResourceAsStream("konfiguracija/Config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Greška prilikom učitavanja konfiguracije baze: " + e.getMessage());
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
    
    public int getPort(){
        return Integer.parseInt(properties.getProperty("server.port"));
    }
    
    public String getUrl() {
        return properties.getProperty("db.url");
    }

    public String getUsername() {
        return properties.getProperty("db.username");
    }

    public String getPassword() {
        return properties.getProperty("db.password");
    }

    public boolean isAutoCommit() {
        return Boolean.parseBoolean(properties.getProperty("db.autoCommit", "false"));
    }
    
    public String getTableName(){
        return properties.getProperty("db.name");
    }
    
    public void setUrl(String url){
        properties.setProperty("db.url", url);
    }
    public void setUsername(String username){
        properties.setProperty("db.username", username);
    }
    public void setPassword(String password){
        properties.setProperty("db.password", password);
    }
    public void save() throws IOException{
        try (OutputStream output = new FileOutputStream("src/main/resources/konfiguracija/Config.properties")){
            properties.store(output, "Azurirana konfiguracija baze.");
        }
    }
}
