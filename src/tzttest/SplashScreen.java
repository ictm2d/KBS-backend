/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tzttest;

import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author Ron
 */
public class SplashScreen extends JDialog {
    private SplashScreenPanel logoPanel = new SplashScreenPanel();
    
    public SplashScreen()
    {
        // Scherm eigenschappen
        setSize(640, 500);        
        setUndecorated(true); // Geen titelbalk
        this.setBackground(Color.yellow);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Voeg het panel met het splashscreen toe
        add(logoPanel);
        
        // Toon het splashscreen
        setVisible(true);
        
        // Toon het login scherm
        toonLoginScherm();
    }
    
    public boolean wachtTweeSeconden()
    {
        boolean tweeSecondenGewacht = false;
        
        long huidigeTijd = System.currentTimeMillis();
        long tweeSecondenLater = huidigeTijd + 2000;
        
        // De huidige tijd wordt net zo lang verhoogd, tot er 2 seconden voorbij zijn
        while(huidigeTijd < tweeSecondenLater)
        {
            huidigeTijd = System.currentTimeMillis();
        }
        
        // Als de 2 seconden voorbij zijn wordt het splashscreen gesloten en het loginscherm getoond
        if(huidigeTijd >= tweeSecondenLater)
        {
            tweeSecondenGewacht = true;
            dispose();
        }
        
        return tweeSecondenGewacht;
    }
    
    public void toonLoginScherm()
    {
        // Toon na 2 seconden het splashscherm het login scherm
        if(wachtTweeSeconden())
        {
            Login loginScherm = new Login();
            loginScherm.setVisible(true);            
        }
    }
}
