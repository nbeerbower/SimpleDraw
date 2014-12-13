/** SimpleDraw
 *  Nicholas Beerbower
 *  Based on Programming Assignment 5
 *  from CMPSC 221 at Penn State
 */

package SimpleDraw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**DrawFrame
 * 
 * Defines and implements the main GUI for the application.
 * 
 * @author Nicholas Beerbower
 */
public class DrawFrame extends JFrame
{
    // define GUI elements
    private DrawPanel panel;            // panel for the shapes/drawings
    
    private JButton undoBut;            // undo button
    private JButton clearBut;           // clear button
    private JButton col1But;            // 1st color button
    private JButton col2But;            // 2nd color button
    Color tempColor = Color.BLACK;      // temporary color
    
    private JComboBox shapeSelector;    // combobox to choose shapes
    private final String shapeOptions[] = {"Line","Rectangle","Ellipse"};   // options for combo box
    
    private JCheckBox fillCheck;        // check box for fill
    private JCheckBox gradCheck;        // check box for gradient
    private JCheckBox dashCheck;        // check box for dashes
    
    private JTextField lineWidthField;  // text field for line width
    private JTextField dashLengthField; // text field for dash length
    
    private JLabel lwLabel;             // label for line width
    private JLabel dlLabel;             // label for dash length
    
    private JPanel optionPanel;         // panel to contain the gui elements
    private JPanel padPanel;            // panel to pad the option panel
    
    private JLabel mouseStatus;         // label for the mouse coords
    
    public DrawFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        // set window title
        super("SimpleDraw");
        
        // set the swing theme to look like the user's OS
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        // initialize mouse label
        mouseStatus = new JLabel("(0,0)");
        
        // text fields
        lwLabel = new JLabel("Line Width:");
        dlLabel = new JLabel("Dash Length:");
        lineWidthField = new JTextField("5",2);
        dashLengthField = new JTextField("10",2);
        
        panel = new DrawPanel(mouseStatus,lineWidthField,dashLengthField);
        
        // buttons
        undoBut = new JButton("Undo");
        clearBut = new JButton("Clear");
        // color selector buttons
        col1But = new JButton("1st Color...");
        col2But = new JButton("2nd Color...");
        
        // shape selector
        shapeSelector = new JComboBox(shapeOptions);
        
        // checkboxes
        fillCheck = new JCheckBox("Filled");
        gradCheck = new JCheckBox("Gradient");
        dashCheck = new JCheckBox("Dashed");
        
        // panels
        optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(2,7,5,5));
        padPanel = new JPanel();
        padPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5));
        
        // add GUI elements to option panel
        optionPanel.add(undoBut);
        optionPanel.add(clearBut);
        optionPanel.add(shapeSelector);
        optionPanel.add(col1But);
        optionPanel.add(col2But);
        optionPanel.add(fillCheck);
        optionPanel.add(gradCheck);
        optionPanel.add(dashCheck);
        optionPanel.add(lwLabel);
        optionPanel.add(lineWidthField);
        optionPanel.add(dlLabel);
        optionPanel.add(dashLengthField);
        padPanel.add(optionPanel);
        
        add(optionPanel,BorderLayout.NORTH);
        add(panel,BorderLayout.CENTER);
        
        // button handlers
        ButtonHandler butH = new ButtonHandler();
        undoBut.addActionListener(butH);
        clearBut.addActionListener(butH);
        col1But.addActionListener(butH);
        col2But.addActionListener(butH);
        
        // handler for checkboxes and combolist
        ItemListenerHandler itemH = new ItemListenerHandler();
        shapeSelector.addItemListener(itemH);
        fillCheck.addItemListener(itemH);
        gradCheck.addItemListener(itemH);
        dashCheck.addItemListener(itemH);
    }
    
    private class ButtonHandler implements ActionListener
    {
        // handle buttons
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            switch (event.getActionCommand())
            {
                case "Undo":
                    panel.removeLastShape();
                    break;
                case "Clear":
                    panel.clearShapes();
                    break;
                    
                case "1st Color...":
                    tempColor = JColorChooser.showDialog(DrawFrame.this, "Choose a color...", panel.getCurShapeCol1());
                    if (tempColor != null)
                    {
                        panel.setCurShapeCol1(tempColor);
                    }
                    break;
                    
                case "2nd Color...":
                    tempColor = JColorChooser.showDialog(DrawFrame.this, "Choose a color...", panel.getCurShapeCol2());
                    if (tempColor != null)
                    {
                        panel.setCurShapeCol2(tempColor);
                    }
                    break;
            }
             
        }
    }
    
    private class ItemListenerHandler implements ItemListener
    {
        @Override
        public void itemStateChanged( ItemEvent event )
        {
            // checkbox checks (fill, gradient, dash)
            if (event.getSource() == fillCheck)
            {
                panel.setCurShapeFill(fillCheck.isSelected());
            }
            else if (event.getSource() == gradCheck)
            {
                panel.setCurShapeGrad(gradCheck.isSelected());
            }
            else if (event.getSource() == dashCheck)
            {
                panel.setCurShapeDash(dashCheck.isSelected());
            }
            
            // combobox check (to select different shapes)
            if ( event.getStateChange() == ItemEvent.SELECTED )
            {
                if (event.getSource() == shapeSelector)
                {
                    panel.setCurShapeType(shapeSelector.getSelectedIndex());
                }
            }
            
        }
    }
    
}
