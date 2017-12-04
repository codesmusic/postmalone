package tqUML;


import java.awt.Font;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

/**
 * @author Le Trung Thong
 */
public class Diagram extends JPanel {

    // Name of class
    private String clasName = "";
    // Name of extend class
    private String clasExtend = "";

    /**
     * Getter of extend class name
     * @return String class extend name
     */
    public String getClasExtend() {
        return clasExtend;
    }

    /**
     * Setter of extend class name
     * @param clasExtend String extend class name
     */
    public void setClasExtend(String clasExtend) {
        this.clasExtend = clasExtend;
    }

    /**
     * Class name getter
     * @return Name of class
     */
    public String getClasName() {
        return clasName;
    }

    /**
     * Class name setter
     * @param clasName Stirng class name
     */
    public void setClasName(String clasName) {
        this.clasName = clasName;
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

    /**
     * Generate default components
     */
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLayout(new java.awt.GridLayout(0, 1));
    }
}
