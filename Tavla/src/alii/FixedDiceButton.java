
package alii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;

public class FixedDiceButton  extends JButton
{
        Container content;
        JBackgammon parent;
        int row;
        int col;
        int diceID;
        public FixedDiceButton(Container c, JBackgammon p)
        {
                content = c;
                parent = p;
                content.setLayout(null);
                content.add(this);
        }
}
