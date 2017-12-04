package tqUML;

import javax.swing.*;

/**
 * This class represent to Help >> About 's frame
 * @author Le Trung Thong
 */
public class AboutFrame extends JFrame {

    /**
     * Creates new form AboutFrame
     */
    public AboutFrame() {
        initComponents();
        setVisible(true);

    }

    /**
     * Generate default components
     */
    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About");
        setName("About");

        jLabel1.setText("GUI Developer: Le Trung Thong");

        jLabel2.setText("Parse Developer: Nguyen Duy Quang");

        jLabel3.setText("UML Version: 1.0");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addContainerGap(134, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * All the labels in frame
     */

    private JLabel jLabel1; // GUI developer name
    private JLabel jLabel2; // Parser developer name
    private JLabel jLabel3; // Version

}
