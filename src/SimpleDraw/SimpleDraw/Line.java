/** SimpleDraw
 *  Nicholas Beerbower
 *  Based on Programming Assignment 5
 *  from CMPSC 221 at Penn State
 */

package SimpleDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**Line
 * 
 * Defines and implements a line object based on the shape superclass
 * 
 * @author Nicholas Beerbower
 */
public class Line extends Shape
{
    public Line()
    {
        // default line constructor
        super();
    }
    
    public Line(int coordX1, int coordX2, int coordY1, int coordY2, Color inColor1, Color inColor2, boolean inGradient, boolean inDashed, float inStrokeW, float inDashL)
    {
        // overloaded line constructor
        super(coordX1,coordX2,coordY1,coordY2,inColor1,inColor2,inGradient,inDashed,inStrokeW,inDashL);
    }
    
    @Override
    public void drawShape(Graphics g)
    {
        // downcast graphics 2d
        Graphics2D g2d = (Graphics2D) g;
        
        // check if gradient selected
        if (getGradient())
        {
            g2d.setPaint(new GradientPaint(getX1(),getY1(),getColor1(),getX2(),getY2(),getColor2(),true));
        }
        else
        {
            g2d.setPaint(getColor1());
        }
        
        // check if dashed selected
        if (getDashed())
        {
            float[] dashes = {getDashL()};
            g2d.setStroke(new BasicStroke(getStrokeW(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, dashes, 0));
        }
        else
        {
            g2d.setStroke(new BasicStroke(getStrokeW()));
        }
        
        // draw the actual line
        g2d.draw(new Line2D.Double(getX1(),getY1(),getX2(),getY2()));
    }
}
