package tqUML;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

/**
 * This class is the container that contain lines and diagram
 *
 * @author Le Trung Thong
 */

public class WorkContainer extends JPanel implements MouseWheelListener{
    /**
     * All the lines in work space
     */
    private ArrayList<Connector> connectors;
    /**
     * The scale is used for zooming
     */
    private double scale = 1.0;

    //protected BufferedImage image = new BufferedImage( (int) Math.round(getWidth()*scale), (int) (getHeight()*scale),BufferedImage.TYPE_INT_RGB);
    /**
     * Getter of scale ratio
     *
     * @return
     */
    public double getScale() {
        return scale;
    }

    /**
     * Setter of scale ratio
     *
     * @param scale
     */
    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * Setter the array of connective lines
     *
     * @param connectors
     */
    public void setConnectors(ArrayList<Connector> connectors) {
        this.connectors = connectors;
    }

    /**
     * Gettet the array of connective line
     *
     * @return
     */
    public ArrayList<Connector> getConnectors() {
        return connectors;
    }

    /**
     * Default constructor
     */
    public WorkContainer() {

    }

    /**
     * Constructor with params
     *
     * @param connectors
     */
    public WorkContainer(ArrayList<Connector> connectors) {
        this.connectors = connectors;
    }

    /**
     * Override paint method to paint all the lines
     *
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);
        if (connectors != null) {
            for (int i = 0; i < connectors.size(); i++) {
                if (connectors.get(i) != null) {
                    connectors.get(i).paint(g  );
                }
            }
        }
        repaint();
    }



    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    public void changeRatio(ArrayList<Diagram> listExtends, ArrayList<Diagram> listNoExtends, double addRate) {
        scale+= addRate;

        for (Diagram list: listNoExtends){
            //changeTextRatio(list,addRate);
            list.setBounds((int) Math.round(list.getX()*scale),(int) Math.round(list.getY()*scale),
                    (int) Math.round(list.getWidth()*scale),(int) Math.round(list.getHeight()*scale));

        }

        for (Diagram list: listExtends){
            //changeTextRatio(list,addRate);
            list.setBounds((int) Math.round(list.getX()*scale),(int) Math.round(list.getY()*scale),
                    (int) Math.round(list.getWidth()*scale),(int) Math.round(list.getHeight()*scale));
        }



    }

    private void changeTextRatio(Diagram list, double addRate) {
        JPanel panel = (JPanel) list;
        Component[] com = panel.getComponents();
        for(Component c: com){
            if (c instanceof JLabel){
                int size = c.getFont().getSize();
                int style = c.getFont().getStyle();
                c.setFont(new Font("Tahoma",style,(int) Math.round(size*(scale))));
            }
        }
    }
}
