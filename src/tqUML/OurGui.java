package tqUML;

//import com.sun.glass.events.KeyEvent;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used for running the program
 *
 * @author Le Trung Thong
 */
public class OurGui extends JFrame {

    /**
     * Contain all the diagram as component in GUI
     */
    private Component[] listDiagram;
    /**
     * Contain all the diagram that does not extend in GUI
     */
    private ArrayList<Diagram> listNoExtends = new ArrayList<>();
    /**
     * Contain all the diagram that extend other diagram
     */
    private ArrayList<Diagram> listExtends = new ArrayList<>();
    /**
     * A hash map to store all diagram with key as class name
     */
    private HashMap<String, Diagram> diagramName = new HashMap<>();
    /**
     * A list contain all the connection between all diagram
     */
    private ArrayList<Connector> connectors = new ArrayList<>();

    // Generate prototype components
    private int maxWorkPanelWidth = 0;
    private int maxWorkPanelHeight = 0;
    private DragListener drag = new DragListener();
    private JLabel colorBox;
    private JComboBox<String> colorComboBox;
    private JTree directoryTree;
    private JMenuItem fileItemArrange;
    private JMenuItem fileItemClear;
    private JMenuItem fileItemFind;
    private JMenuItem fileItemOpen;
    private JMenuItem fileItemSaveAs;
    private JMenuItem fileMenuClose;
    private JPopupMenu.Separator fileMenuSeparator;
    private JButton findBtn;
    private JTextField findField;
    private JMenuItem helpItemAbout;
    private JLabel jLabel1;
    private JScrollPane leftPane;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuHelp;
    private JMenu menuTool;
    private JMenu menuView;
    private JScrollPane rightPane;
    private JButton saveBtn;
    private JSplitPane splitPane;
    private JToolBar toolBar;
    private JToolBar.Separator toolSeparatate1;
    private JCheckBoxMenuItem viewItemOutline;
    private JCheckBoxMenuItem viewItemToolbar;
    private WorkContainer workPanel;
    private JMenuItem zoomIn;
    private JButton zoomInBtn;
    private JMenuItem zoomOut;
    private JButton zoomOutBtn;

    /**
     * Creates new form OurGui
     */
    public OurGui() {
        initComponents();
    }

    private void initComponents() {
        toolBar = new JToolBar();
        zoomInBtn = new JButton();
        zoomOutBtn = new JButton();
        toolSeparatate1 = new JToolBar.Separator();
        saveBtn = new JButton();
        colorBox = new JLabel();
        colorComboBox = new JComboBox<>();
        jLabel1 = new JLabel();
        findField = new JTextField();
        findBtn = new JButton();
        splitPane = new JSplitPane();
        leftPane = new JScrollPane();
        directoryTree = new JTree();
        rightPane = new JScrollPane();
        menuBar = new JMenuBar();
        menuFile = new JMenu();
        fileItemClear = new JMenuItem();
        fileItemArrange = new JMenuItem();
        fileItemFind = new JMenuItem();
        fileMenuSeparator = new JPopupMenu.Separator();
        fileItemOpen = new JMenuItem();
        fileItemSaveAs = new JMenuItem();
        fileMenuClose = new JMenuItem();
        menuView = new JMenu();
        viewItemToolbar = new JCheckBoxMenuItem();
        viewItemOutline = new JCheckBoxMenuItem();
        menuTool = new JMenu();
        zoomIn = new JMenuItem();
        zoomOut = new JMenuItem();
        menuHelp = new JMenu();
        helpItemAbout = new JMenuItem();

        workPanel = new WorkContainer();
        workPanel.setLayout(new DragLayout());

        /**
         * Define all component of GUI
         */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("UML 1.0");
        setName("UML 1.0");
        // >> Set up Menu bar
        // >> Begin menu File
        menuFile.setIcon(new ImageIcon(getClass().getResource("/imageIcon/fileIcon.png")));
        menuFile.setMnemonic('F');
        menuFile.setText("File");
        menuFile.setToolTipText("");
        // File >> Clear
        fileItemClear.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, InputEvent.CTRL_MASK));
        fileItemClear.setIcon(new ImageIcon(getClass().getResource("/imageIcon/clearIcon.png")));
        fileItemClear.setMnemonic('r');
        fileItemClear.setText("Clear");
        fileItemClear.setToolTipText("");
        fileItemClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fileItemClearActionPerformed(evt);
            }
        });
        menuFile.add(fileItemClear);
        // File >> Rearrange
        fileItemArrange.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, InputEvent.CTRL_MASK));
        fileItemArrange.setIcon(new ImageIcon(getClass().getResource("/imageIcon/arrangeIcon.png")));
        fileItemArrange.setText("Re-arrange");
        fileItemArrange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fileItemArrangeActionPerformed(evt);
            }
        });
        menuFile.add(fileItemArrange);
        // File >> Find
        fileItemFind.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, InputEvent.CTRL_MASK));
        fileItemFind.setIcon(new ImageIcon(getClass().getResource("/imageIcon/findIcon.png")));
        fileItemFind.setMnemonic('f');
        fileItemFind.setText("Find");
        fileItemFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fileItemFindActionPerformed(evt);
            }
        });
        menuFile.add(fileItemFind);
        // A line to split menuFile
        menuFile.add(fileMenuSeparator);
        // File >> Open source
        fileItemOpen.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, InputEvent.CTRL_MASK));
        fileItemOpen.setIcon(new ImageIcon(getClass().getResource("/imageIcon/openIcon.png")));
        fileItemOpen.setText("Open Source");
        fileItemOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fileItemOpenActionPerformed(evt);
            }
        });
        menuFile.add(fileItemOpen);

        // File >> Save as image
        fileItemSaveAs.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, InputEvent.CTRL_MASK));
        fileItemSaveAs.setIcon(new ImageIcon(getClass().getResource("/imageIcon/saveIcon.png"))); // NOI18N
        fileItemSaveAs.setMnemonic('S');
        fileItemSaveAs.setText("Save Image As");
        fileItemSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fileItemSaveAsActionPerformed(evt);
            }
        });
        menuFile.add(fileItemSaveAs);
        // File >> Close
        fileMenuClose.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, InputEvent.CTRL_MASK));
        fileMenuClose.setIcon(new ImageIcon(getClass().getResource("/imageIcon/closeIcon.png")));
        fileMenuClose.setText("Close");
        fileMenuClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fileMenuCloseActionPerformed(evt);
            }
        });
        menuFile.add(fileMenuClose);
        menuBar.add(menuFile);
        // >> Finish File in menu bar

        // >> Begin View in menu bar
        menuView.setIcon(new ImageIcon(getClass().getResource("/imageIcon/viewIcon.png")));
        menuView.setMnemonic('V');
        menuView.setText("View");
        // View >> Toolbar
        viewItemToolbar.setSelected(true);
        viewItemToolbar.setText("Tool bar");
        viewItemToolbar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                viewItemToolbarActionPerformed(evt);
            }
        });
        menuView.add(viewItemToolbar);
        // View >> Outline
        viewItemOutline.setSelected(true);
        viewItemOutline.setText("Outline");
        viewItemOutline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                viewItemOutlineActionPerformed(evt);
            }
        });
        menuView.add(viewItemOutline);
        menuBar.add(menuView);
        // >> Finish View in menu bar
        // Begin Tool in menu bar
        menuTool.setIcon(new ImageIcon(getClass().getResource("/imageIcon/toolIcon.png")));
        menuTool.setMnemonic('T');
        menuTool.setText("Tool");
        menuTool.setToolTipText("");
        // Tool >> Zoom in
        zoomIn.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, InputEvent.CTRL_MASK));
        zoomIn.setIcon(new ImageIcon(getClass().getResource("/imageIcon/zoomInIcon.png")));
        zoomIn.setText("Zoom In");
        zoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                zoomInBtnActionPerformed(evt);
            }
        });
        menuTool.add(zoomIn);
        // Tool >> Zoom out
        zoomOut.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, InputEvent.CTRL_MASK));
        zoomOut.setIcon(new ImageIcon(getClass().getResource("/imageIcon/zoomOutIcon.png")));
        zoomOut.setText("Zoom Out");
        zoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                zoomOutBtnActionPerformed(evt);
            }
        });
        menuTool.add(zoomOut);
        menuBar.add(menuTool);
        // >> Finish Tool in menu bar
        // >> Begin Help in menu bar
        menuHelp.setIcon(new ImageIcon(getClass().getResource("/imageIcon/helpIcon.png")));
        menuHelp.setText("Help");
        // Help >> About
        helpItemAbout.setIcon(new ImageIcon(getClass().getResource("/imageIcon/aboutIcon.png")));
        helpItemAbout.setMnemonic('B');
        helpItemAbout.setText("About");
        helpItemAbout.setToolTipText("");
        helpItemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                helpItemAboutActionPerformed(evt);
            }
        });
        menuHelp.add(helpItemAbout);
        menuBar.add(menuHelp);
        // >> Finish Help in menu bar

        setJMenuBar(menuBar);
        // >> Finish setting up Menu Bar

        /**
         * Set up tool bar
         */
        toolBar.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        toolBar.setRollover(true);
        toolBar.setMaximumSize(new Dimension(40, 30));
        toolBar.setMinimumSize(new Dimension(40, 30));
        toolBar.setPreferredSize(new Dimension(40, 30));
        // Zoom in button setting up
        zoomInBtn.setIcon(new ImageIcon(getClass().getResource("/imageIcon/zoomInIcon.png")));
        zoomInBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        zoomInBtn.setFocusable(false);
        zoomInBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        zoomInBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        zoomInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                zoomInBtnActionPerformed(evt);
            }
        });
        toolBar.add(zoomInBtn);
        // Zoom out button setting up
        zoomOutBtn.setIcon(new ImageIcon(getClass().getResource("/imageIcon/zoomOutIcon.png")));
        zoomOutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        zoomOutBtn.setFocusable(false);
        zoomOutBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        zoomOutBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        zoomOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                zoomOutBtnActionPerformed(evt);
            }
        });
        toolBar.add(zoomOutBtn);
        // A separator between buttons
        toolBar.add(toolSeparatate1);
        // Save button setting up
        saveBtn.setIcon(new ImageIcon(getClass().getResource("/imageIcon/saveIcon.png")));
        saveBtn.setFocusable(false);
        saveBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        saveBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveBtnActionPerform(evt);
            }
        });
        toolBar.add(saveBtn);
        // Set up color box
        colorBox.setText("     Color");
        colorBox.setMaximumSize(new Dimension(70, 20));
        toolBar.add(colorBox);

        colorComboBox.setModel(new DefaultComboBoxModel<>(
                new String[]{"Default", "Pink", "Yellow", "Red", "Green", "Gray"}));
        colorComboBox.setToolTipText("");
        colorComboBox.setMaximumSize(new Dimension(90, 30));
        colorComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                colorComboBoxActionPerformed(evt);
            }
        });
        toolBar.add(colorComboBox);
        // Make a space in tool bar
        jLabel1.setText("         ");
        toolBar.add(jLabel1);
        // Find field setting up
        findField.setText("Find (Ctrl + F)");
        findField.setAction(fileItemFind.getAction());
        findField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.gray, Color.gray));
        findField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        findField.setMaximumSize(new Dimension(200, 30));
        findField.setPreferredSize(new Dimension(50, 26));
        findField.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                findFieldMousePressed(evt);
            }
        });
        findField.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                findFieldKeyPressed(evt);
            }
        });
        toolBar.add(findField);
        // Find button setting up
        findBtn.setIcon(new ImageIcon(getClass().getResource("/imageIcon/findIcon.png")));
        findBtn.setFocusable(false);
        findBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        findBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        findBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                findBtnActionPerformed(evt);
            }
        });
        toolBar.add(findBtn);
        // >> Finish setting up Toolbar

        /**
         * Set up split pane between outline and work space
         */
        splitPane.setResizeWeight(0.5);
        splitPane.setAutoscrolls(true);
        splitPane.setMinimumSize(new Dimension(3, 5));
        splitPane.setPreferredSize(new Dimension(500, 536));
        /**
         * Define default outline directory tree
         */
        directoryTree.setBorder(BorderFactory.createTitledBorder("Root Directory"));
        directoryTree.setModel(null);
        /**
         * Put directoryTree on left split pane
         */
        leftPane.setViewportView(directoryTree);
        splitPane.setLeftComponent(leftPane);

        /**
         * Configure right split pane
         * Put work space on right split pane
         */
        rightPane.setBorder(BorderFactory.createTitledBorder("Workplace"));
        rightPane.setAutoscrolls(true);
        rightPane.setMinimumSize(new Dimension(0, 0));
        rightPane.setPreferredSize(new Dimension(900, 900));
        rightPane.setViewportView(workPanel);
        splitPane.setRightComponent(rightPane);

        /**
         * Set lay out of the GUI
         * Group Layout with menu bar, tool bar,
         * outline directory tree on the left and
         * work space on the right
         */
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
                                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
        );
        /**
         * Display the GUI
         */
        pack();
    }

    /**
     * Perform action: Zoom in
     * @param evt
     */
    private void zoomInBtnActionPerformed(ActionEvent evt) {
//        workPanel.setScale(workPanel.getScale() + 0.15);
        workPanel.changeRatio(listExtends,listNoExtends,0.15);
    }
    /**
     * Perform action: Zoom out
     *
     * @param evt
     */
    private void zoomOutBtnActionPerformed(ActionEvent evt) {
//        workPanel.setScale(workPanel.getScale() - 0.15);
        workPanel.changeRatio(listExtends,listNoExtends,-0.15);
    }

    /**
     * Perform action: Save as image
     *
     * @param evt
     */
    private void saveBtnActionPerform(ActionEvent evt) {
        makePanelImage(workPanel);
    }

    /**
     * Perform action: Clear
     *
     * @param evt
     */
    private void fileItemClearActionPerformed(ActionEvent evt) {
        // confirm and clear the entire diagram
        if (JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to clear the Diagram?\nThis action can not be undone!",
                        "Clear", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            directoryTree.setModel(null);
            workPanel.removeAll();
            connectors.clear();
            maxWorkPanelWidth = 0;
            maxWorkPanelHeight = 0;
            workPanel.repaint();
        }
    }

    /**
     * Perform action: Open source
     *
     * @param evt
     */
    private void fileItemOpenActionPerformed(ActionEvent evt) {
        JFileChooser openFileChooser;
        openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("C:\\"));
        openFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
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
    }

    /**
     * Perform action: About
     *
     * @param evt
     */
    private void helpItemAboutActionPerformed(ActionEvent evt) {
        new AboutFrame();
    }

    /**
     * Perform action: view Tool bar
     *
     * @param evt
     */
    private void viewItemToolbarActionPerformed(ActionEvent evt) {
        if (!viewItemToolbar.isSelected()) {
            toolBar.setVisible(false);
        } else {
            toolBar.setVisible(true);
        }
    }

    /**
     * Perform action: View outline
     *
     * @param evt
     */
    private void viewItemOutlineActionPerformed(ActionEvent evt) {
        if (viewItemOutline.isSelected()) {
            splitPane.setLeftComponent(directoryTree);
            splitPane.setDividerLocation(200);
        } else {
            splitPane.setLeftComponent(null);
        }
    }

    /**
     * Perform action: Save as image by menu
     *
     * @param evt
     */
    private void fileItemSaveAsActionPerformed(ActionEvent evt) {
        makePanelImage(workPanel);
    }

    /**
     * Perform action: Close
     *
     * @param evt
     */
    private void fileMenuCloseActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    /**
     * Perform action: Arrange
     *
     * @param evt
     */
    private void fileItemArrangeActionPerformed(ActionEvent evt) {
        //workPanel.paintImage();
    }

    /**
     * Perform action: Find by menu
     *
     * @param evt
     */
    private void fileItemFindActionPerformed(ActionEvent evt) {

    }

    /**
     * Perform action: Find by button
     *
     * @param evt
     */
    private void findBtnActionPerformed(ActionEvent evt) {
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

    }

    /**
     * Perform action: Find field mouse pressed
     *
     * @param evt
     */
    private void findFieldMousePressed(MouseEvent evt) {
        if (findField.getText().equals("Find (Ctrl + F)")) {
            findField.setText("");
        }
    }

    /**
     * Perform action: Find field key pressed
     *
     * @param evt
     */
    private void findFieldKeyPressed(java.awt.event.KeyEvent evt) {
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
    }

    /**
     * Perform action: Choose color
     *
     * @param evt
     */
    private void colorComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
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
    }

    /**
     * Draw all the diagram in java file list
     *
     * @param javaList
     */
    private void drawAction(ArrayList<String> javaList) {
        // This list to store all diagram temporary
        ArrayList<Diagram> list = new ArrayList<>();
        ParserJava[] parser = new ParserJava[javaList.size()];
        int index = 0;
        for (String java : javaList) {
            parser[index] = new ParserJava(java);
            index++;
        }
        // Make the data completely trim
        ParserJava.replaceAppearance(parser);
        for (ParserJava pj : parser) {
            // Generate new diagram
            Diagram dia = new Diagram(pj);
            // Add diagram to hash map and list
            diagramName.put(dia.getClasName(), dia);
            list.add(dia);
        }
        // Is used to set location of diagram
        int maxHeight = 0;
        for (Diagram dia : list) {
            String extend = dia.getClasExtend().trim();
            /**
             * If diagram does not extend anything, it will be put on top of work space
             * and put in listNoExtends
             * If diagram extends, it will be put in listExtends
             */
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
            }
        }
        // Reset the location to be drawable
        maxWorkPanelHeight += maxHeight + 50;
        maxWorkPanelWidth = 0;
        /**
         * Draw diagram in listExtends at the bottom of workspace
         * Add the connect lines to connectors
         */
        for (Diagram dia : listExtends) {
            dia.setLocation(maxWorkPanelWidth + 5, maxWorkPanelHeight + 5);
            maxWorkPanelWidth += dia.getPreferredSize().getWidth() + 10;
            dia.setVisible(true);
            dia.addMouseListener(drag);
            dia.addMouseMotionListener(drag);
            connectors.add(new Connector(dia, diagramName.get(dia.getClasExtend().trim()),
                    ConnectLine.LINE_ARROW_DEST, Connector.CONNECT_LINE_TYPE_RECTANGLE, Color.blue));
            workPanel.add(dia);
        }
        // Get all components in work space to listDiagram
        listDiagram = workPanel.getComponents();
        // Set the connectors to draw lines
        workPanel.setConnectors(connectors);
        workPanel.updateUI();
    }

    /**
     * Create image from present status of work space
     *
     * @param panel
     */
    private void makePanelImage(Component panel) {
        try {
            JFileChooser saveFileChooser = new JFileChooser("C:\\");
            saveFileChooser.setDialogTitle("Save as image");
            saveFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            saveFileChooser.setApproveButtonText("Save");
            saveFileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    // Check if user enter the type of image
                    String path = f.getAbsolutePath();
                    String ext = path.substring(path.length() - 4).toLowerCase();
                    return ext.equals(".png");
                }

                @Override
                public String getDescription() {
                    return "Supported Image Formats (.PNG)";
                }
            });
            File path = null;
            String name = "";
            int returnValue = saveFileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                path = saveFileChooser.getSelectedFile();
            }
            if (path != null) {
                Dimension size = panel.getSize();
                BufferedImage image = new BufferedImage(
                        size.width, size.height
                        , BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = image.createGraphics();
                panel.paint(g2);
                if (path.getAbsolutePath().substring(path.getAbsolutePath().length() - 4).toLowerCase().equals(".png")) {
                    name = path.getAbsolutePath().substring(0, path.getAbsolutePath().length() - 4);
                }
                name = path.getAbsolutePath();
                ImageIO.write(image, "png", new File(name + ".png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new OurGui().setVisible(true);
        });
    }

}