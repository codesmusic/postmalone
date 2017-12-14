package tqUML;

import java.awt.Font;
import java.util.Scanner;
import javax.swing.*;

/**
 * This class represent to a structure for a class
 * I am misunderstood meaning of the structure
 * so I set name to "Diagram".
 * Sorry for my mistake, I will fix it later
 *
 * @author Le Trung Thong
 */
public class Diagram extends JPanel {
    /**
     * Name of class in diagram
     */
    private String clasName = "";
    /**
     * Name of class extend in diagram
     */
    private String clasExtend = "";

    /**
     * Getter of class extend name
     * @return
     */
    public String getClasExtend() {
        return clasExtend;
    }

    /**
     * Setter of class extend name
     * @param clasExtend
     */
    public void setClasExtend(String clasExtend) {
        this.clasExtend = clasExtend;
    }

    /**
     * Getter of class name
     * @return
     */
    public String getClasName() {
        return clasName;
    }

    /**
     * Setter of clas name
     * @param clasName
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
        /**
         * Diagram 's name
         */
        String diaName = parser.getClasses();
        /**
         * All attribute of diagram
         */
        String diaAtt = parser.getAttributes();
        /**
         * All method of diagram
         */
        String diaMethod = parser.getMethods();

        /**
         * Set the class extend of this diagram
         */
        this.setClasExtend(parser.getExtendRelationship());
        /**
         * Set the class name of this diagram
         */
        this.setClasName(diaName);
        /**
         * Add class name to diagram with special font
         */
        JLabel textClas = new JLabel(diaName);
        textClas.setOpaque(true);
        textClas.setFont(new Font("Tahoma", 1, 18));
        this.add(textClas);
        /**
         * Add a separator for beautiful view
         */
        this.add(new JSeparator(SwingConstants.HORIZONTAL));

        /**
         * Two scanner stored strings to add per line to diagram
         */
        Scanner inMethod = new Scanner(diaMethod);
        Scanner inAtt = new Scanner(diaAtt);
        /**
         * Add all attribute to diagram
         */
        while (inAtt.hasNextLine()) {
            JLabel label = new JLabel(inAtt.nextLine());
            this.add(label);
        }
        /**
         * Add a separator for beautiful view
         */
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        /**
         * Add all method to diagram
         */
        while (inMethod.hasNextLine()) {
            JLabel label = new JLabel(inMethod.nextLine());
            this.add(label);
        }
        /**
         * Close two scanner
         */
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
