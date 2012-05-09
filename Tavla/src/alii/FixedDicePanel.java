/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alii;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Dogru
 */
class FixedDicePanel extends JPanel{
        Container content;
        JBackgammon parent;

        public FixedDicePanel(Container c, JBackgammon p)
        {
                content = c;
                parent = p;
                content.setLayout(null);
                content.add(this);
        }
    
}
