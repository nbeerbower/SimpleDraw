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
import java.awt.geom.Ellipse2D;

/**Ellipse
 * 
 * Defines and implements a basic Ellipse object based on BoundedShape
 * 
 * @author Nicholas Beerbower
 */
public class Ellipse extends BoundedShape
{
    public Ellipse()
    {
        // default ellipse constructor
        super();
    }
    
    public Ellipse(int coordX1, int coordX2, int coordY1, int coordY2, Color inColor1, Color inColor2, boolean inGradient, boolean inDashed, boolean inFilled, float inStrokeW, float inDashL)
    {
        // overloaded ellipse constructor
        super(coordX1,coordX2,coordY1,coordY2,inColor1,inColor2,inGradient,inDashed,inFilled, inStrokeW, inDashL);
    }
    
    @Override
    public void drawShape(Graphics g)
    {
        // downcast graphics 2D
        Graphics2D g2d = (Graphics2D) g;
        
        // check if gradient selected and set appropraite paint
        if (getGradient())
        {
            g2d.setPaint(new GradientPaint(getX1(),getY1(),getColor1(),getX2(),getY2(),getColor2(),true));
        }
        else
        {
            g2d.setColor(getColor1());
        }
        
        // check if dashed selected and set appropriate stroke
        if (getDashed())
        {
            float[] dashes = {getDashL()};
            g2d.setStroke(new BasicStroke(getStrokeW(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, dashes, 0));
        }
        else
        {
            g2d.setStroke(new BasicStroke(getStrokeW()));
        }
        
        // draw filled or outlined shape
        if (getFilled())
        {
            g2d.fill(new Ellipse2D.Double(getTopLeftX(),getTopLeftY(),getWidth(),getHeight()));
        }
        else
        {
            g2d.draw(new Ellipse2D.Double(getTopLeftX(),getTopLeftY(),getWidth(),getHeight()));
        }
    }
}
