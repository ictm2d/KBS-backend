/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tzttest;

import java.sql.SQLException;

/**
 *
 * @author Ron
 */
public class TZTTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
//        SplashScreen scherm = new SplashScreen();
        Login login = new Login();
        Backend backend = new Backend(login);
        backend.setVisible(true);
    }
}
