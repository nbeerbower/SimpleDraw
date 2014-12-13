/** SimpleDraw
 *  Nicholas Beerbower
 *  Based on Programming Assignment 5
 *  from CMPSC 221 at Penn State
 */

package SimpleDraw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**DrawPanel
 * 
 * Defines and implements the actual "canvas" that shapes are drawn to
 * 
 * Also contains the data structure that holds shapes,
 * allowing for an undo function and
 * multiple shapes to be drawn to the screen
 * 
 * @author Nicholas Beerbower
 */
public class DrawPanel extends JPanel
{
    private LinkedList<Shape> shapeList;    // shapes drawn to panel
    
    private int initX;                      // initial x pos.
    private int initY;                      // initial y pos.
    
    private int curShapeType;               // 0 = line, 1 for rectangle, 2 for ellipse
    private Shape curShapeObj;              // current shape beign modified
    private Color curShapeCol1;             // current shape's 1st color
    private Color curShapeCol2;             // current shape's 2nd color
    private boolean curShapeFill;           // current shape is filled
    private boolean curShapeGrad;           // current shape gradient
    private boolean curShapeDash;           // current shape is dashed
    private float curShapeLW;               // current shape line width
    private float curShapeDL;               // current shape dash length
    
    JLabel mouseLabel;                      // label for mouse coords
    JTextField lwField;                     // text field to enter line width
    JTextField dlField;                     // text field for dash length
    
    public DrawPanel(JLabel inMouseLabel, JTextField inLWField, JTextField inDLField)
    {
        // drawoanel constructor
        shapeList = new LinkedList<Shape>();// make linked list to contain shapes on panel
        
        // set some default values for the current shape
        curShapeType = 0;
        curShapeObj = null;
        curShapeCol1 = Color.BLACK;
        curShapeCol2 = Color.WHITE;
        curShapeFill = false;
        curShapeGrad = false;
        curShapeDash = false;
        curShapeLW = 5;
        curShapeDL = 10;
        
        // set workable varibles to passed in pointers to gui elements
        mouseLabel = inMouseLabel;
        lwField = inLWField;
        dlField = inDLField;
        
        // setup layout for gui elements
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        add(mouseLabel,BorderLayout.SOUTH);
        
        // mouse handler
        MouseHandler h = new MouseHandler();
        addMouseListener(h);
        addMouseMotionListener(h);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        // this method draws all shapes to the panel
        super.paintComponent(g);
        
        // draw all the previous shapes
        // yes, I am aware this is a horrible way to access a LL
        for(int i=shapeList.size()-1;i >= 0;i--)
        {
            shapeList.get(i).drawShape(g);
        }
        
        // draw the current shape
        if (curShapeObj != null)
        {
            curShapeObj.drawShape(g);
        }
    }
    
    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent m)
        {
            /**
             * Make a new shape on the panel
             */
            
            // get initial clicked posistion
            initX = m.getX();
            initY = m.getY();
            
            // convert strings from fields to get dash length and line width
            curShapeLW = Float.parseFloat(lwField.getText());
            curShapeDL = Float.parseFloat(dlField.getText());
            
            // make a new shape based on the desired type
            switch (curShapeType)
            {
                case 0:         // line
                    curShapeObj = new Line(initX, initY, initX, initY, curShapeCol1, curShapeCol2, curShapeGrad, curShapeDash, curShapeLW, curShapeDL);
                    break;
                case 1:         // rectangle
                    curShapeObj = new Rectangle(initX, initY, initX, initY, curShapeCol1, curShapeCol2, curShapeGrad, curShapeDash, curShapeFill, curShapeLW, curShapeDL);
                    break;
                case 2:         // ellipse
                    curShapeObj = new Ellipse(initX, initY, initX, initY, curShapeCol1, curShapeCol2, curShapeGrad, curShapeDash, curShapeFill, curShapeLW, curShapeDL);
                    break;
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent m)
        {
            /**
             * Finish drawing the shape.
             */
            
            // finialize the coordinates
            curShapeObj.setX1(initX);
            curShapeObj.setY1(initY);
            curShapeObj.setX2(m.getX());
            curShapeObj.setY2(m.getY());
            
            // add shape to panel list
            shapeList.addFirst(curShapeObj);
            
            // make it so there is no current shape now
            curShapeObj=null;
            
            // redraw the panel
            repaint();
        }
        
        @Override
        public void mouseMoved(MouseEvent m)
        {
            // update mouse label with coordinates
            mouseLabel.setText(String.format("(%d,%d)",m.getX(),m.getY()));
        }
        
        @Override
        public void mouseDragged(MouseEvent m)
        {
            // update dragging coordinates
            curShapeObj.setX1(initX);
            curShapeObj.setY1(initY);
            curShapeObj.setX2(m.getX());
            curShapeObj.setY2(m.getY());
            // ... and redraw the screen as user moves mouse
            repaint();
        }
    }
    
    // List methods
    
    public void removeLastShape()
    {
        // if the list is not empty, undo the last shape
        if (!shapeList.isEmpty())
        {
            shapeList.removeFirst();
            repaint();
        }
    }
    
    public void clearShapes()
    {
        // delete everything in the list and redraw the panel
        shapeList.clear();
        repaint();
    }
    
    // Mutators
    
    public void setCurShapeType(int type)
    {
        curShapeType=type;
    }
    
    public void setCurShapeCol1(Color color)
    {
        curShapeCol1=color;
    }
    
    public void setCurShapeCol2(Color color)
    {
        curShapeCol2=color;
    }
    
    public void setCurShapeFill(boolean filled)
    {
        curShapeFill=filled;
    }
    
    public void setCurShapeGrad(boolean grad)
    {
        curShapeGrad=grad;
    }
    
    public void setCurShapeDash(boolean dash)
    {
        curShapeDash=dash;
    }
    
    // Accessors
    public Color getCurShapeCol1()
    {
        return curShapeCol1;
    }
    
    public Color getCurShapeCol2()
    {
        return curShapeCol2;
    }
}
