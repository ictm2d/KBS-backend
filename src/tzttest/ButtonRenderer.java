/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tzttest;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Ron
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() 
    {

    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) 
    {
        setText((o == null) ? "" : o.toString());
        return this;
    }
}
