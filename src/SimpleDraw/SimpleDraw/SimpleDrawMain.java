/** SimpleDraw
 *  Nicholas Beerbower
 *  Based on Programming Assignment 5
 *  from CMPSC 221 at Penn State
 */

package SimpleDraw;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

public class SimpleDrawMain
{
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        // start a window for the program!
        DrawFrame mainFrame = new DrawFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(650,500);
        mainFrame.setVisible(true);
    }
    
}
