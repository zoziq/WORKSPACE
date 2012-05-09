
package ali;

public class Status
{
        //If the player rolled doublets
        public boolean doublets;
        //If the game is in network mode (playing over the net)
        
        public boolean observer;
        public boolean clicker;
        //Whether the current player has selected a spike and the
        //possible move positions are showing.
        public boolean spike_selected;

        public Status()
        {
                doublets = false;
                observer = false;
                clicker = false;
                spike_selected = false;
        }
        
        public void newGame()
        {
                doublets = false;
                observer = false;
                clicker = false;
                spike_selected = false;
        }
}
