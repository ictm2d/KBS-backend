/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tzttest;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Ron
 */
public class Table {
    
    // Een nieuwe verbinding aanmaken
    private Verbinding verbinding = new Verbinding();
    private String query;
    private int handmatigeKolommen;
    private int aantalDBRijen;
    
    private String[] kolomNamen;
    private Object[][] kolomData;

    public Table(String query) throws SQLException
    {
        this.query = query;
        this.handmatigeKolommen = handmatigeKolommen;
        tabelgegevensOphalen();
    }
    
    public void tabelgegevensOphalen() throws SQLException
    {
        // Alleen queries uitvoeren als het maken van een verbinding gelukt is
        if(verbinding.verbindingGemaakt())
        {
            // De query die alle entries nagaat
            ResultSet rs = verbinding.query(query);    
            
            // Metadata vd select query ophalen
            ResultSetMetaData rsmd = rs.getMetaData();
            
            // Het aantal kolommen in de DB vaststellen
            int aantalDBKolommen = rsmd.getColumnCount();
            
            // Het aantal kolommen is de hoeveelheid DB kolommen + de hoeveelheid handmatige kolommen
            int totaalAantalKolommen = aantalDBKolommen + handmatigeKolommen;
            
            // Het aantal DB rijen vaststellen
            aantalDBRijen = 0;
            
            while(rs.next())
            {
                aantalDBRijen++;
            }
            
            // De resultset weer op zijn beginwaarde zetten, omdat we de waardes vd resultset later opnieuw nodig hebben
            rs.beforeFirst();
            
            // Invulling geven aan de tabel waardes
            kolomNamen = new String[totaalAantalKolommen];
            kolomData = new Object[aantalDBRijen][totaalAantalKolommen];
            int beginRij = 0;
            
            // Alle kolomnamen in de gelijknamige array zetten
            for(int i = 1; i <= aantalDBKolommen; i++)
            {
                kolomNamen[i-1] = (rsmd.getColumnName(i));
            }
           
            // Alle gegevens uit de database in de kolomdata array zetten
            while(rs.next())
            {
                for(int i = 1; i <= aantalDBKolommen; i++)
                {
                    kolomData[beginRij][i-1] = rs.getString(rsmd.getColumnName(i));
                }
                
                beginRij++;
            }
        }        
    }

    /**
     * @return the kolomNamen
     */
    public String[] getKolomNamen() {
        return kolomNamen;
    }

    /**
     * @return the kolomData
     */
    public Object[][] getKolomData() {
        return kolomData;
    }
}
