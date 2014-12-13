/** SimpleDraw
 *  Nicholas Beerbower
 *  Based on Programming Assignment 5
 *  from CMPSC 221 at Penn State
 */

package SimpleDraw;

import java.awt.Color;
import java.awt.Graphics;

/**Shape
 * 
 * Defines and implements a basic shape object with:
 * *4 coordinates
 * *2 colors (main and gradient)
 * *Shape is dashed
 * *Shape has a gradient
 * *Stroke width
 * *Dash length (if dashed)
 * 
 * @author Nicholas Beerbower
 */
abstract class Shape
{
    private int x1;         // first x posistion
    private int x2;         // second x position
    private int y1;         // first y position
    private int y2;         // second y posistion
    
    private Color color1;   // main color
    private Color color2;   // gradient color
    
    boolean gradient;       // gradient is enabled
    boolean dashed;         // dashed lines are enabled
    
    float strokeWidth;      // width of stroke
    float dashLength;       // length of dashes
    
    public Shape()
    {
        // create a default shape
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
        
        color1 = Color.BLACK;
        color2 = Color.WHITE;
        
        gradient = false;
        dashed = false;
        
        strokeWidth = 5;
        dashLength = 10;
    }
    
    public Shape(int coordX1, int coordX2, int coordY1, int coordY2, Color inColor1, Color inColor2, boolean inGradient, boolean inDashed, float inStrokeW, float inDashL)
    {
        // create a custom shape via overloaded constructor
        x1 = coordX1;
        x2 = coordX2;
        y1 = coordY1;
        y2 = coordY2;
        
        color1 = inColor1;
        color2 = inColor2;
        
        gradient = inGradient;
        dashed = inDashed;
        
        strokeWidth = inStrokeW;
        dashLength = inDashL;
    }
    
    // abstract draw function to be implemented in subclasses
    abstract public void drawShape(Graphics g);
    
    // Mutators
    
    public void setX1(int inX1)
    {
        x1 = inX1;
    }
    
    public void setX2(int inX2)
    {
        x2 = inX2;
    }
    
    public void setY1(int inY1)
    {
        y1 = inY1;
    }
    
    public void setY2(int inY2)
    {
        y2 = inY2;
    }
    
    // Accessors
    
    public int getX1()
    {
        return x1;
    }
    
    public int getX2()
    {
        return x2;
    }
    
    public int getY1()
    {
        return y1;
    }
    
    public int getY2()
    {
        return y2;
    }
    
    public Color getColor1()
    {
        return color1;
    }
    
    public Color getColor2()
    {
        return color2;
    }
    
    public boolean getGradient()
    {
        return gradient;
    }
    
    public boolean getDashed()
    {
        return dashed;
    }
    
    public float getStrokeW()
    {
        return strokeWidth;
    }
    
    public float getDashL()
    {
        return dashLength;
    }
}
