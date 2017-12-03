package tq.s.uml;


import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author PC
 */
public class DragListener extends MouseInputAdapter
{
    Point location;
    MouseEvent pressed;
 
    @Override
    public void mousePressed(MouseEvent me)
    {
        pressed = me;
    }
 
    @Override
    public void mouseDragged(MouseEvent me)
    {
        Diagram component = (Diagram) me.getComponent();
        location = component.getLocation(location);
        int x = location.x - pressed.getX() + me.getX();
        int y = location.y - pressed.getY() + me.getY();
        component.setLocation(x, y);
        
        
    }
}