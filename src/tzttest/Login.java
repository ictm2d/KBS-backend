/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tzttest;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Ron
 */
public class Login extends JDialog implements ActionListener {

    // Een nieuwe verbinding aanmaken
    private Verbinding verbinding = new Verbinding();
    
    // Scherm items aanmaken
    private String gebruikersnaam;
    private JLabel labelGebruikersnaam;
    private JLabel labelWachtwoord;
    private JTextField textGebruikersnaam;
    private JPasswordField passWachtwoord;
    private JButton buttonLogin;
    
    public Login()
    {
        // Scherm eigenschappen
        setTitle("Login");
        setSize(250, 130);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 2, 2));
        
        // Scherm items definiëren
        labelGebruikersnaam = new JLabel("Gebruikersnaam:");
        labelWachtwoord = new JLabel("Wachtwoord:");
        textGebruikersnaam = new JTextField(10);
        passWachtwoord = new JPasswordField(10);
        buttonLogin = new JButton("Login");
        
        // Scherm items tonen
        add(labelGebruikersnaam);
        add(textGebruikersnaam);
        add(labelWachtwoord);
        add(passWachtwoord);
        add(new JLabel("")); // 1 kolom overslaan
        add(buttonLogin);
        
        // Action Listeners toevoegen
        buttonLogin.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        // Als er op de inloggen knop gedrukt is wordt gekeken of de inlog gegevens kloppen
        if(ae.getSource() == buttonLogin)
        {
            try 
            {
                controleerLogin();
            } 
            
            catch (SQLException sqle) 
            {
                
            }
        }
    }
    
    // Functie die controleert of de ingetypte login gegevens overeenkomen met de bekende username/password
    public void controleerLogin() throws SQLException
    {
        // Alleen queries uitvoeren als het maken van een verbinding gelukt is
        if(verbinding.verbindingGemaakt())
        {
            // Query in een try zetten, voor het geval een niet kloppende query wordt uitgevoerd
            try
            {
                // De query die alle entries nagaat
                ResultSet rs = verbinding.query("SELECT Gebruikersnaam, Wachtwoord FROM beheerder");
                boolean succesvolleLogin = false;
                
                // Controleren of de ingetypte gebruikersnaam en wachtwoord bekend zijn in de DB
                while(rs.next())
                {
                    // Als de inlog gegevens bekend zijn, wordt de backend geopend
                    if(rs.getString("Gebruikersnaam").equals(textGebruikersnaam.getText()) && rs.getString("Wachtwoord").equals(passWachtwoord.getText()))
                    {
                        succesvolleLogin = true;
                        break;
                    }
                }
                
                // Als er succesvol ingelogd is
                if(succesvolleLogin)
                {
                    this.gebruikersnaam = rs.getString("Gebruikersnaam");
                    Backend backendSysteem = new Backend(this);
                    backendSysteem.setVisible(true);
                    this.setVisible(false);
                }
                
                // Als er niet succesvol ingelogd wordt
                else
                {
                    JOptionPane.showMessageDialog(null, "Uw combinatie van gebruikersnaam en wachtwoord klopt niet.", "Inloggen mislukt", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            catch(Exception e)
            {
                System.out.println("SQL Error");
            }
            
            // De verbindingen sluiten wanneer de query klaar is
            finally
            {
                verbinding.getSt().close();
                verbinding.getVerbinding().close();
            }
        }
        
        // Als er geen verbinding met de DB gemaakt kan worden
        else
        {
            JOptionPane.showMessageDialog(null, "Er is geen verbinding met de database.", "Geen verbinding", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @return the gebruikersnaam
     */
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }
}
