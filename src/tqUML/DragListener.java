package tqUML;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 * This class represented to an interfaces allows dragable
 * @author PC
 */

public class DragListener extends MouseInputAdapter
{
    /**
     * Location of the component
     */
    Point location;
    /**
     * Event at the pressed point
     */
    MouseEvent pressed;

    @Override
    public void mousePressed(MouseEvent me)
    {
        pressed = me;
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {
        // Get component and its location
        Diagram component = (Diagram) me.getComponent();
        location = component.getLocation(location);

        // Relocation of the component
        int x = location.x - pressed.getX() + me.getX();
        int y = location.y - pressed.getY() + me.getY();
        component.setLocation(x, y);
    }
}
