/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tzttest;
//dit is een testje
//dit is nog een testje
//dit is een test van Bert
//dit is een test van Wietse 2
//dit is een test van Gert
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Ron
 */
public class Backend extends JFrame implements ActionListener {
    
    private Login loginGegevens = new Login();
    private JPanel backendNorth;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private JButton gekozenButton, buttonBeheerders, buttonTreinkoeriers, buttonKoeriers, buttonOpdrachten, buttonUitloggen;
    
    public Backend(Login login) throws SQLException
    {
        this.loginGegevens = login;
        
        // Scherm eigenschappen
        setTitle("TZT Beheersysteem (ingelogd als " + loginGegevens.getGebruikersnaam() + ")");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
        // Maak het bovenste deel van de backend (met de buttons) aan
        backendNorth();
        
        // Teken de tabel
        tekenTabel();
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        // Verander de button kleur, wanneer er op een button gedrukt wordt
        for(JButton a : buttons)
        {
            if(ae.getSource() == a)
            {
                veranderButtonKleur(a);
            }
        }
        
        if(ae.getSource() == buttonUitloggen)
        {
            uitloggen();
        }
    }
    
    public void tekenTabel() throws SQLException
    {
        // Haal de gegevens vd query op uit de database
        Table tabelgegevens = new Table("SELECT * FROM beheerder");
        String[] kolomNamen = tabelgegevens.getKolomNamen();
        Object[][] kolomData = tabelgegevens.getKolomData();
        int aantalKolommen = (kolomNamen.length - 1); // Min 1 ivm array begint bij 0
        
//        kolomNamen[aantalKolommen] = "Wijzig";
//                
//        for(int i = 0; i <= aantalKolommen; i++)
//        {
//            kolomData[1][aantalKolommen] = "Wijzig";
//        }
        
        // Een tabel aanmaken en deze vullen met de gegevens uit bovenstaande query
        JTable tabel = new JTable(kolomData, kolomNamen);
        
        // De data onder het kopje Wijzig wordt verandert in een button
//        tabel.getColumn("Wijzig").setCellRenderer(new ButtonRenderer());
        
        // De data kan niet dmv een dubbelklik gewijzigd worden
        tabel.setEnabled(false);
        
        // Maak het scroll gedeelte en voeg deze aan de tabel toe
        JScrollPane scroll = new JScrollPane(tabel);
 
        // Toon de tabel
        add(scroll);        
    }
    
    public void maakButtons()
    {
        // De buttons definiëren
        buttonBeheerders = new JButton("Beheerders");
        buttonTreinkoeriers = new JButton("Treinkoeriers");
        buttonKoeriers = new JButton("Koeriers");
        buttonOpdrachten = new JButton("Opdrachten");
        buttonUitloggen = new JButton("Uitloggen");
        
        // Omdat er altijd begonnen wordt op het beheerders scherm, is die knop in eerste instantie altijd ingekleurd
        buttonBeheerders.setBackground(Color.yellow);
        gekozenButton = buttonBeheerders;
        
        // De buttons aan ArrayList toevoegen
        buttons.add(buttonBeheerders);
        buttons.add(buttonTreinkoeriers);
        buttons.add(buttonKoeriers);
        buttons.add(buttonOpdrachten);
        buttons.add(buttonUitloggen);
        
        for(JButton a : buttons)
        {
            a.setPreferredSize(new Dimension(150, 50));
            
            // Verwijder het bordertje om de gekozen button
            a.setFocusPainted(false);
            
            // Maak voor alle buttons een actionlistener aan
            a.addActionListener(this);
        }
    }
    
    public void veranderButtonKleur(JButton gekozenButton)
    {
        // De gekozen button wordt opgeslagen, zodat wanneer er op uitloggen, maar vervolgens op
        // annuleren gedrukt wordt, de button van het openstaande scherm weer gekleurd wordt
        if(gekozenButton != buttonUitloggen)
        {
            this.gekozenButton = gekozenButton;            
        }
        
        for(JButton a : buttons)
        {
            // De kleur vd gekozen button verandert
            if(gekozenButton == a)
            {
                a.setBackground(Color.yellow);
            }
            
            // De rest blijft normaal
            else
            {
                a.setBackground(null);                
            }
        }
    }
    
    // Deze functie maakt een nieuw jpanel aan, die ik gebruik om de knoppen bovenin het scherm te tonen
    public void backendNorth()
    {
        backendNorth = new JPanel();
        backendNorth.setLayout(new FlowLayout());
        
        // Maak de buttons
        maakButtons();
        
        // Voeg de buttons toe aan backendNorth
        for(JButton a : buttons)
        {
            backendNorth.add(a);
        }
        
        add(backendNorth, BorderLayout.NORTH);
    }
    
    public void uitloggen()
    {
        int antwoord = JOptionPane.showConfirmDialog(null, "Weet u zeker dat u wilt uitloggen?", "Uitloggen", JOptionPane.YES_NO_OPTION);
        
        // Als er op ja gedrukt wordt, wordt er daadwerkelijk uitgelogd
        if(antwoord == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
        
        // Bij nee wordt de knop van het openstaande scherm weer ingekleurd en gebeurt er verder niks
        else
        {
            veranderButtonKleur(gekozenButton);
        }
    }
}
