/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tzttest;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;
import javax.swing.*;

/**
 *
 * @author Ron
 */
public class SplashScreenPanel extends JPanel {
    
    private BufferedImage logo;
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        // Lees het logo in
        try
        {
            logo = ImageIO.read(new File("logo.png"));
        }
        
        catch(IOException ioe)
        {
            
        }

        // Teken het logo
        g.drawImage(logo, 35, 130, this);
        g.setFont(new Font("sansserif", Font.BOLD, 32));
        g.drawString("Beheersysteem", 200, 370);
    }
}