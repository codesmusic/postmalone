package tq.s.uml;

import java.awt.Font;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author Le Trung Thong
 */
public class Diagram extends JPanel {

    private String clasName = "";
    private String clasExtend = "";
    private Pair connectPoints;
    private Point locate;

    public Point getLocate() {
        return locate;
    }

    public void setLocate(Point p) {
        locate = p;
    }

    public String getClasExtend() {
        return clasExtend;
    }

    public void setClasExtend(String clasExtend) {
        this.clasExtend = clasExtend;
    }

    public String getClasName() {
        return clasName;
    }

    public void setClasName(String clasName) {
        this.clasName = clasName;
    }

    public Pair getConnectPoints() {
        return connectPoints;
    }

    public void setConnectPoints(Pair connectPoints) {
        this.connectPoints = connectPoints;
    }

    public void setLocation(double x, double y) {
        this.setLocation((int) x, (int) y);
        locate = new Point((int) x, (int) y);
    }

    public Diagram() {
        initComponents();
    }

    /**
     * Creates new form Diagram
     *
     * @param parser
     */
    public Diagram(ParserJava parser) {
        initComponents();

        String diaName = parser.getClasses();
        String diaAtt = parser.getAttributes();
        String diaMethod = parser.getMethods();
        this.setClasExtend(parser.getExtendRelationship());
        this.setClasName(diaName);
        JLabel textClas = new JLabel(diaName);
        textClas.setOpaque(true);
        textClas.setFont(new Font("Tahoma", 1, 18));
        this.add(textClas);
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        Scanner inMethod = new Scanner(diaMethod);
        Scanner inAtt = new Scanner(diaAtt);
        while (inAtt.hasNextLine()) {
            JLabel label = new JLabel(inAtt.nextLine());
            this.add(label);
        }
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        while (inMethod.hasNextLine()) {
            JLabel label = new JLabel(inMethod.nextLine());
            this.add(label);
        }
        inAtt.close();
        inMethod.close();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLayout(new java.awt.GridLayout(0, 1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    protected ArrayList<Line2D> drawRelationship(Point located, Diagram parent) {
        ArrayList lines = new ArrayList();
        Pair pair = getPointRelation(located, parent);
        Line2D line = new Line2D.Double(pair.getChild().getX(), pair.getChild().getY(), pair.getParent().getX(), pair.getParent().getY());
        lines.add(line);
        line = new Line2D.Double(pair.getParent().getX(), pair.getParent().getY(), pair.getParent().getX() + 20, pair.getParent().getY() + 5);
        lines.add(line);
        line = new Line2D.Double(pair.getParent().getX(), pair.getParent().getY(), pair.getParent().getX() - 20, pair.getParent().getY() + 5);
        lines.add(line);
        return lines;
    }

    private Pair getPointRelation(Point located, Diagram parent){
        int startX = (int) (getPreferredSize().getWidth() / 2 + located.getX());
        int startY = (int) located.getY();
        int endX = (int) (parent.getPreferredSize().getWidth() / 2 + parent.getLocation().getX());
        int endY = (int) (parent.getLocation().getY() + parent.getPreferredSize().getHeight());
        Point start = new Point(startX, startY);
        Point end = new Point(endX, endY);
        return new Pair(start, end);
    }

}
