package tzttest;

import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BeheerderTable {

	public BeheerderTable(){
		System.out.println("Dit wordt uitgevoerd");
		try{
			tekenTabel();
		}
		catch(Exception e){
			System.out.println("Iets ging fout");
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

}
