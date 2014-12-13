/** SimpleDraw
 *  Nicholas Beerbower
 *  Based on Programming Assignment 5
 *  from CMPSC 221 at Penn State
 */

package SimpleDraw;

import java.awt.Color;
import java.awt.Graphics;

/**BoundedShape
 * 
 * Extends Shape to allow for the following extra features:
 * *Filled Shapes
 * *Drawing in the other direction
 * 
 * @author Nicholas Beerbower
 */
abstract class BoundedShape extends Shape
{
    boolean filled;
    
    public BoundedShape()
    {
        // default constructor
        super();
        filled = false;
    }
    
    public BoundedShape(int coordX1, int coordX2, int coordY1, int coordY2, Color inColor1, Color inColor2, boolean inGradient, boolean inDashed, boolean inFilled, float inStrokeW, float inDashL)
    {
        // overloaded constructor
        super(coordX1, coordX2, coordY1, coordY2, inColor1, inColor2, inGradient, inDashed, inStrokeW, inDashL);
        filled = inFilled;
    }
    
    // abstract class to draw th shape (implemented in subclasses)
    @Override
    abstract public void drawShape(Graphics g);
    
    // Accessors
    
    public int getTopLeftX()
    {
        return Math.min(getX1(),getX2());
    }
    
    public int getTopLeftY()
    {
        return Math.min(getY1(),getY2());
    }
    
    public int getWidth()
    {
        return Math.abs(getX1()-getX2());
    }

    public int getHeight()
    {
        return Math.abs(getY1()-getY2());
    }
    
    public boolean getFilled()
    {
        return filled;
    }
}
