
package JBackgammon11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;

public class FixedButton  extends JButton
{
        Container content;
        JBackgammon parent;

        public FixedButton(Container c, JBackgammon p)
        {
                content = c;
                parent = p;
                content.setLayout(null);
                content.add(this);
        }

        public void drawOnSpike(int spike)
        {
                Insets in = parent.getInsets();

                if (spike > 12)
                        setBounds(parent.findX(spike) - in.left, parent.findY(spike) - in.top, 28, 10);
                else
                        setBounds(parent.findX(spike) - in.left, parent.findY(spike) - 10 - in.top, 28, 10);

                setVisible(true);
                parent.repaint();
        }
}
