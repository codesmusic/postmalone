/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tq.s.uml;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author Le Trung Thong
 */
public class OurGui extends javax.swing.JFrame {

    private final JFileChooser openFileChooser;
    private Component[] listDiagram;
    private ArrayList<Diagram> listNoExtends = new ArrayList<>();
    private ArrayList<Diagram> listExtends = new ArrayList<>();
    private HashMap<String, Diagram> diagramName = new HashMap<>();
    private HashMap<String, Diagram> childDiagram = new HashMap<>();
    private ArrayList<Line2D> lines = new ArrayList<>();

    /**
     * Creates new form OurGui
     */
    public OurGui() {
        initComponents();
        openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("C:\\"));
        openFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        zoomInBtn = new javax.swing.JButton();
        zoomOutBtn = new javax.swing.JButton();
        toolSeparatate1 = new javax.swing.JToolBar.Separator();
        saveBtn = new javax.swing.JButton();
        colorBox = new javax.swing.JLabel();
        colorComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        findField = new javax.swing.JTextField();
        findBtn = new javax.swing.JButton();
        splitPane = new javax.swing.JSplitPane();
        leftPane = new javax.swing.JScrollPane();
        directoryTree = new javax.swing.JTree();
        rightPane = new javax.swing.JScrollPane();
        workPanel = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                if (lines.size() > 0){
                    Graphics2D g2 = (Graphics2D) g;
                    for (Line2D line : lines) {
                        g2.draw(line);
                    }
                    lines.clear();
                }
                System.out.println("end");
            }

        };
        workPanel.setLayout(new DragLayout());
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        fileItemClear = new javax.swing.JMenuItem();
        fileItemArrange = new javax.swing.JMenuItem();
        fileItemFind = new javax.swing.JMenuItem();
        fileMenuSeparator = new javax.swing.JPopupMenu.Separator();
        fileItemOpen = new javax.swing.JMenuItem();
        fileItemSaveAs = new javax.swing.JMenuItem();
        fileMenuClose = new javax.swing.JMenuItem();
        menuView = new javax.swing.JMenu();
        viewItemToolbar = new javax.swing.JCheckBoxMenuItem();
        viewItemOutline = new javax.swing.JCheckBoxMenuItem();
        menuTool = new javax.swing.JMenu();
        zoomIn = new javax.swing.JMenuItem();
        zoomOut = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        helpItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UML 1.0");
        setName("UML 1.0"); // NOI18N

        toolBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        toolBar.setRollover(true);
        toolBar.setMaximumSize(new java.awt.Dimension(40, 30));
        toolBar.setMinimumSize(new java.awt.Dimension(40, 29));
        toolBar.setPreferredSize(new java.awt.Dimension(40, 29));
        toolBar.setRequestFocusEnabled(false);

        zoomInBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/zoomInIcon.png"))); // NOI18N
        zoomInBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        zoomInBtn.setFocusable(false);
        zoomInBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        zoomInBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(zoomInBtn);

        zoomOutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/zoomOutIcon.png"))); // NOI18N
        zoomOutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        zoomOutBtn.setFocusable(false);
        zoomOutBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        zoomOutBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(zoomOutBtn);
        toolBar.add(toolSeparatate1);

        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/saveIcon.png"))); // NOI18N
        saveBtn.setFocusable(false);
        saveBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(saveBtn);

        colorBox.setText("     Color");
        colorBox.setMaximumSize(new java.awt.Dimension(70, 20));
        toolBar.add(colorBox);

        colorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Pink", "Yellow", "Red", "Green", "Gray" }));
        colorComboBox.setToolTipText("");
        colorComboBox.setMaximumSize(new java.awt.Dimension(90, 30));
        colorComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorComboBoxActionPerformed(evt);
            }
        });
        toolBar.add(colorComboBox);

        jLabel1.setText("         ");
        toolBar.add(jLabel1);

        findField.setText("Find (Ctrl + F)");
        findField.setAction(fileItemFind.getAction());
        findField.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray));
        findField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        findField.setMaximumSize(new java.awt.Dimension(200, 30));
        findField.setPreferredSize(new java.awt.Dimension(50, 26));
        findField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                findFieldMousePressed(evt);
            }
        });
        findField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                findFieldKeyPressed(evt);
            }
        });
        toolBar.add(findField);

        findBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/findIcon.png"))); // NOI18N
        findBtn.setFocusable(false);
        findBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        findBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        findBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findBtnActionPerformed(evt);
            }
        });
        toolBar.add(findBtn);

        splitPane.setResizeWeight(0.5);
        splitPane.setAutoscrolls(true);
        splitPane.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        splitPane.setMinimumSize(new java.awt.Dimension(3, 5));
        splitPane.setPreferredSize(new java.awt.Dimension(500, 536));

        directoryTree.setBorder(javax.swing.BorderFactory.createTitledBorder("Root Directory"));
        directoryTree.setModel(null);
        leftPane.setViewportView(directoryTree);

        splitPane.setLeftComponent(leftPane);

        rightPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Workplace"));
        rightPane.setAutoscrolls(true);
        rightPane.setMinimumSize(new java.awt.Dimension(0, 0));
        rightPane.setName(""); // NOI18N
        rightPane.setPreferredSize(new java.awt.Dimension(900, 900));
        rightPane.setRequestFocusEnabled(false);
        rightPane.setViewportView(workPanel);

        splitPane.setRightComponent(rightPane);

        menuFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/fileIcon.png"))); // NOI18N
        menuFile.setMnemonic('F');
        menuFile.setText("File");
        menuFile.setToolTipText("");

        fileItemClear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        fileItemClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/clearIcon.png"))); // NOI18N
        fileItemClear.setMnemonic('r');
        fileItemClear.setText("Clear");
        fileItemClear.setToolTipText("");
        fileItemClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileItemClearActionPerformed(evt);
            }
        });
        menuFile.add(fileItemClear);

        fileItemArrange.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        fileItemArrange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/arrangeIcon.png"))); // NOI18N
        fileItemArrange.setText("Re-arrange");
        fileItemArrange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileItemArrangeActionPerformed(evt);
            }
        });
        menuFile.add(fileItemArrange);

        fileItemFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        fileItemFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/findIcon.png"))); // NOI18N
        fileItemFind.setMnemonic('f');
        fileItemFind.setText("Find");
        fileItemFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileItemFindActionPerformed(evt);
            }
        });
        menuFile.add(fileItemFind);
        menuFile.add(fileMenuSeparator);

        fileItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        fileItemOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/openIcon.png"))); // NOI18N
        fileItemOpen.setText("Open Source");
        fileItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileItemOpenActionPerformed(evt);
            }
        });
        menuFile.add(fileItemOpen);

        fileItemSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        fileItemSaveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/saveIcon.png"))); // NOI18N
        fileItemSaveAs.setMnemonic('S');
        fileItemSaveAs.setText("Save Image As");
        fileItemSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileItemSaveAsActionPerformed(evt);
            }
        });
        menuFile.add(fileItemSaveAs);

        fileMenuClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        fileMenuClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/closeIcon.png"))); // NOI18N
        fileMenuClose.setText("Close");
        fileMenuClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuCloseActionPerformed(evt);
            }
        });
        menuFile.add(fileMenuClose);

        menuBar.add(menuFile);

        menuView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/viewIcon.png"))); // NOI18N
        menuView.setMnemonic('V');
        menuView.setText("View");

        viewItemToolbar.setSelected(true);
        viewItemToolbar.setText("Tool bar");
        viewItemToolbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewItemToolbarActionPerformed(evt);
            }
        });
        menuView.add(viewItemToolbar);

        viewItemOutline.setSelected(true);
        viewItemOutline.setText("Outline");
        viewItemOutline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewItemOutlineActionPerformed(evt);
            }
        });
        menuView.add(viewItemOutline);

        menuBar.add(menuView);

        menuTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/toolIcon.png"))); // NOI18N
        menuTool.setMnemonic('T');
        menuTool.setText("Tool");
        menuTool.setToolTipText("");

        zoomIn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        zoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/zoomInIcon.png"))); // NOI18N
        zoomIn.setText("Zoom In");
        menuTool.add(zoomIn);

        zoomOut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        zoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/zoomOutIcon.png"))); // NOI18N
        zoomOut.setText("Zoom Out");
        menuTool.add(zoomOut);

        menuBar.add(menuTool);

        menuHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/helpIcon.png"))); // NOI18N
        menuHelp.setMnemonic('H');
        menuHelp.setText("Help");

        helpItemAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageIcon/aboutIcon.png"))); // NOI18N
        helpItemAbout.setMnemonic('B');
        helpItemAbout.setText("About");
        helpItemAbout.setToolTipText("");
        helpItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpItemAboutActionPerformed(evt);
            }
        });
        menuHelp.add(helpItemAbout);

        menuBar.add(menuHelp);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileItemClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileItemClearActionPerformed
        directoryTree.setModel(null);
        workPanel.removeAll();
        lines.clear();
        maxWorkPanelWidth = 0;
        maxWorkPanelHeight = 0;
        workPanel.repaint();

    }//GEN-LAST:event_fileItemClearActionPerformed

    private void fileItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileItemOpenActionPerformed
        int returnValue = openFileChooser.showOpenDialog(this);
        String path = null;
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                path = openFileChooser.getSelectedFile().getAbsolutePath();
                directoryTree.setModel(new FileSystemModel(new File(path)));
            } catch (Exception ioe) {
            }
            FileFinder fileFinder = new FileFinder(path);
            ArrayList<String> javaList = fileFinder.getFileList();
            drawAction(javaList);
        }
    }//GEN-LAST:event_fileItemOpenActionPerformed

    private void helpItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpItemAboutActionPerformed
        new AboutFrame();
    }//GEN-LAST:event_helpItemAboutActionPerformed

    private void viewItemToolbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewItemToolbarActionPerformed
        if (!viewItemToolbar.isSelected()) {
            toolBar.setVisible(false);
        } else {
            toolBar.setVisible(true);
        }
    }//GEN-LAST:event_viewItemToolbarActionPerformed

    private void viewItemOutlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewItemOutlineActionPerformed
        if (viewItemOutline.isSelected()) {
            splitPane.setLeftComponent(directoryTree);
            splitPane.setDividerLocation(200);
        } else {
            splitPane.setLeftComponent(null);
        }

    }//GEN-LAST:event_viewItemOutlineActionPerformed

    private void fileItemSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileItemSaveAsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileItemSaveAsActionPerformed

    private void fileMenuCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_fileMenuCloseActionPerformed

    private void fileItemArrangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileItemArrangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileItemArrangeActionPerformed

    private void fileItemFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileItemFindActionPerformed

    }//GEN-LAST:event_fileItemFindActionPerformed

    private void findBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findBtnActionPerformed
        if (!"".equals(findField.getText())) {
            boolean found = false;
            String sourceFind = findField.getText();
            Component[] listLabel = null;
            for (Component listCom : listDiagram) {
                if (listCom instanceof JPanel) {
                    listLabel = ((JPanel) listCom).getComponents();
                }
                for (Component label : listLabel) {
                    if (label instanceof JLabel) {
                        if (((JLabel) label).getText().toLowerCase().contains(sourceFind.toLowerCase())) {
                            label.setForeground(Color.red);
                            //label.setBackground(Color.green);
                            found = true;
                        }
                    }
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "0 found!!!");
            }
        }

    }//GEN-LAST:event_findBtnActionPerformed

    private void findFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_findFieldMousePressed
        if (findField.getText().equals("Find (Ctrl + F)")) {
            findField.setText("");
        }
    }//GEN-LAST:event_findFieldMousePressed

    private void findFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_findFieldKeyPressed
        boolean found = false;
        Component[] listLabel = null;
        if (findField.getText().equals("")) {
            for (Component listCom : listDiagram) {
                if (listCom instanceof JPanel) {
                    listLabel = ((JPanel) listCom).getComponents();
                }
                for (Component label : listLabel) {
                    if (label instanceof JLabel) {
                        label.setForeground(Color.BLACK);
                    }
                }
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER && !"".equals(findField.getText())) {
            String sourceFind = findField.getText();
            for (Component listCom : listDiagram) {
                if (listCom instanceof JPanel) {
                    listLabel = ((JPanel) listCom).getComponents();
                }
                for (Component label : listLabel) {
                    if (label instanceof JLabel) {
                        if (((JLabel) label).getText().toLowerCase().contains(sourceFind.toLowerCase())) {
                            label.setForeground(Color.red);
                            //label.setBackground(Color.green);
                            found = true;
                        }
                    }
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "0 found!!!");
            }
        }
    }//GEN-LAST:event_findFieldKeyPressed

    private void colorComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorComboBoxActionPerformed
        String color = (String) ((JComboBox) evt.getSource()).getSelectedItem();
        switch (color.toLowerCase()) {
            case "red":
                workPanel.setBackground(Color.red);
                break;
            case "green":
                workPanel.setBackground(Color.green);
                break;
            case "yellow":
                workPanel.setBackground(Color.yellow);
                break;
            case "pink":
                workPanel.setBackground(Color.pink);
                break;
            case "gray":
                workPanel.setBackground(Color.gray);
                break;
            default:
                workPanel.setBackground(Color.white);
                break;

        }
    }//GEN-LAST:event_colorComboBoxActionPerformed

    private int maxWorkPanelWidth = 0;
    private int maxWorkPanelHeight = 0;
    DragListener drag = new DragListener();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel colorBox;
    private javax.swing.JComboBox<String> colorComboBox;
    private javax.swing.JTree directoryTree;
    private javax.swing.JMenuItem fileItemArrange;
    private javax.swing.JMenuItem fileItemClear;
    private javax.swing.JMenuItem fileItemFind;
    private javax.swing.JMenuItem fileItemOpen;
    private javax.swing.JMenuItem fileItemSaveAs;
    private javax.swing.JMenuItem fileMenuClose;
    private javax.swing.JPopupMenu.Separator fileMenuSeparator;
    private javax.swing.JButton findBtn;
    private javax.swing.JTextField findField;
    private javax.swing.JMenuItem helpItemAbout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane leftPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuTool;
    private javax.swing.JMenu menuView;
    private javax.swing.JScrollPane rightPane;
    private javax.swing.JButton saveBtn;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JToolBar.Separator toolSeparatate1;
    private javax.swing.JCheckBoxMenuItem viewItemOutline;
    private javax.swing.JCheckBoxMenuItem viewItemToolbar;
    private javax.swing.JPanel workPanel;
    private javax.swing.JMenuItem zoomIn;
    private javax.swing.JButton zoomInBtn;
    private javax.swing.JMenuItem zoomOut;
    private javax.swing.JButton zoomOutBtn;
    // End of variables declaration//GEN-END:variables

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new OurGui().setVisible(true);
        });
    }

    private void drawAction(ArrayList<String> javaList) {
        ParserJava[] parser = new ParserJava[javaList.size()];
        int index = 0;
        int maxHeight = 0;
        ArrayList<Diagram> list = new ArrayList<>();
        for (String java : javaList) {
            parser[index] = new ParserJava(java);
            index++;
        }

        for (ParserJava pj : parser) {
            ParserJava.replaceAppearance(parser);
            Diagram dia = new Diagram(pj);
            diagramName.put(dia.getClasName(), dia);
            list.add(dia);
        }

        for (Diagram dia : list) {
            String extend = dia.getClasExtend().trim();

            if (extend.equals("")) {
                listNoExtends.add(dia);
                maxHeight = (int) Math.max(dia.getPreferredSize().getHeight(), maxHeight);
                dia.setLocation(maxWorkPanelWidth + 5, maxWorkPanelHeight + 5);

                maxWorkPanelWidth += dia.getPreferredSize().getWidth() + 10;
                dia.setVisible(true);
                dia.addMouseListener(drag);
                dia.addMouseMotionListener(drag);
                workPanel.add(dia);
            } else {
                listExtends.add(dia);
                childDiagram.put(dia.getClasName(), dia);
            }
        }
        maxWorkPanelHeight += maxHeight + 50;
        maxWorkPanelWidth = 0;

        for (Diagram dia : listExtends) {
            dia.setLocation(maxWorkPanelWidth + 5, maxWorkPanelHeight + 5);
            maxWorkPanelWidth += dia.getPreferredSize().getWidth() + 10;
            dia.setVisible(true);
            dia.addMouseListener(drag);
            dia.addMouseMotionListener(drag);
            lines.addAll(dia.drawRelationship(dia.getLocation(),diagramName.get(dia.getClasExtend().trim())));
            workPanel.add(dia);
        }
        listDiagram = workPanel.getComponents();
        workPanel.updateUI();
    }
}
