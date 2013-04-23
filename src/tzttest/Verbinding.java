/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tzttest;

import com.mysql.jdbc.CommunicationsException;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Ron
 */
public class Verbinding {

    // Database type
    private String dbType = "mysql";
    
    // Gebruikersnaam en ww
    private String userName = "root";
    private String password = "usbw";
    
    // Host, port en dbnaam
    private String host = "localhost";
    private String port = "3307";
    private String dbNaam = "tzt";
    
    // Verbindings URL
    private String url = "jdbc:" + dbType + "://" + host + ":" + port + "/" + dbNaam;
    
    // Verbindings gegevens
    private Connection verbinding = null;
    private Statement st = null;

    // Methode die de verbinding maakt
    public Connection getConnection() throws SQLException 
    {
        Connection conn = null;

        // Kijken of de benodigde jdbc mySQL driver aanwezig is (zoniet deze downloaden en plaatsen in C:\Program Files\Java\jdk1.7.0_11\jre\lib\ext)
        try 
        {
            // Verbinding maken
            conn = DriverManager.getConnection(url, userName, password);
            Class.forName("com.mysql.jdbc.Driver");
        }
        
        // Als de jdbc driver niet aanwezig is
        catch(ClassNotFoundException cnfe) 
        {
            throw new RuntimeException("Cannot find the driver in the classpath!", cnfe);
        }
        
        // Als er een andere fout optreedt
        catch(Exception e)
        {

        }
        
        // De connectie returnen
        return conn;
    }
    
    // Methode die een query uitvoert
    public ResultSet query(String query) throws SQLException
    {
        // Voer de query uit
        ResultSet rs = st.executeQuery(query);
        
        // Return de resultset
        return rs;
    }
    
    // Methode die controleert of er verbinding gemaakt is
    public boolean verbindingGemaakt() throws SQLException
    {
        boolean verbindingGemaakt = false;
        
        // De verbindingswaarde in attribuut verbinding vastleggen
        this.verbinding = getConnection();
        
        if(verbinding != null)
        {
            // Een statement attribuut aanmaken
            this.st = verbinding.createStatement();
            
            verbindingGemaakt = true;
        }
        
        return verbindingGemaakt;
    }

    /**
     * @return the verbinding
     */
    public Connection getVerbinding() {
        return verbinding;
    }

    /**
     * @return the st
     */
    public Statement getSt() {
        return st;
    }
}