package com.arki.laboratory.snippet.compare.app;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FolderCompareGUI extends JFrame{


    private final JFrame frame = this;
    private final GridBagLayout layout = new GridBagLayout();
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final JLabel originDirLabel = new JLabel("Origin directory:");
    private final JTextField originDirText = new JTextField(25);
    private final JFileChooser originDirChooser = new JFileChooser();
    private final JButton originDirChooserButton = new JButton("Choose");
    private final JLabel backupDirLabel = new JLabel("Backup directory:");
    private final JTextField backupDirText = new JTextField(25);
    private final JFileChooser backupDirChooser = new JFileChooser();
    private final JButton backupDirChooserButton = new JButton("Choose");
    private final JLabel optionLabel = new JLabel("Options:");
    private final JCheckBox fileNameCheckbox = new JCheckBox("Use file name check.");
    private final JCheckBox fileSizeCheckbox = new JCheckBox("Use file size check.");
    private final JCheckBox fileMd5Checkbox = new JCheckBox("Use MD5 check.");
    private final JLabel tipLabel = new JLabel("Tip: todo");
    private final JButton scanButton = new JButton("Scan");
    private final JButton cancelButton = new JButton("Cancel");
    private final JLabel warnInfoLabel = new JLabel();
    private final JLabel resultAreaLabel = new JLabel("Scan result:");
    private final JSeparator resultAreaSeparator = new JSeparator();
    // Origin directory contains these files, while backup directory doesn't contain:
    private final JLabel originResultInfoLabel = new JLabel("Backup lacking:");
    // Backup directory contains these files, while origin directory doesn't contain:
    private final JLabel backupResultInfoLabel = new JLabel("Backup redundant:");
    private final DifferenceJList originResultList = new DifferenceJList();
    private final JScrollPane originResultScrollPane = new JScrollPane();
    private final DifferenceJList backupResultList = new DifferenceJList();
    private final JScrollPane backupResultScrollPane = new JScrollPane();

    public FolderCompareGUI() {

        frame.setTitle("Folder Compare");
        frame.setBounds(300, 300, 1000, 618);
        frame.setBackground(new Color(200, 200, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(layout);


        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.insets = new Insets(0, 10, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;


        //------------------------ Row start------------------------------
        // Origin directory chooser.
        constraints.gridwidth = 1;
        layout.setConstraints(originDirLabel, constraints);
        frame.add(originDirLabel);


        constraints.gridwidth = 2;
        constraints.gridx = GridBagConstraints.RELATIVE;
        layout.setConstraints(originDirText, constraints);
        frame.add(originDirText);

        originDirChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        originDirChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int state = originDirChooser.showOpenDialog(null);
                if (state == JFileChooser.APPROVE_OPTION) {
                    originDirText.setText(originDirChooser.getSelectedFile().toString());
                }
            }
        });
        constraints.gridwidth = 1;
        layout.setConstraints(originDirChooserButton, constraints);
        frame.add(originDirChooserButton);


        // Backup directory chooser.
        constraints.gridwidth = 1;
        layout.setConstraints(backupDirLabel, constraints);
        frame.add(backupDirLabel);

        layout.setConstraints(backupDirText, constraints);
        constraints.gridwidth = 2;
        frame.add(backupDirText);

        backupDirChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        backupDirChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int state = backupDirChooser.showOpenDialog(null);
                if (state == JFileChooser.APPROVE_OPTION) {
                    backupDirText.setText(backupDirChooser.getSelectedFile().toString());
                }
            }
        });
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(backupDirChooserButton, constraints);
        frame.add(backupDirChooserButton);
        //------------------------ Row end  ------------------------------


        //------------------------ Row start------------------------------
        constraints.gridwidth = 1;
        layout.setConstraints(optionLabel, constraints);
        frame.add(optionLabel);

        fileNameCheckbox.setSelected(true);
        fileNameCheckbox.setEnabled(false);
        layout.setConstraints(fileNameCheckbox, constraints);
        frame.add(fileNameCheckbox);

        layout.setConstraints(fileSizeCheckbox, constraints);
        frame.add(fileSizeCheckbox);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(fileMd5Checkbox, constraints);
        frame.add(fileMd5Checkbox);
        //------------------------ Row end  ------------------------------


        //------------------------ Row start------------------------------
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridwidth = 2;
        layout.setConstraints(tipLabel, constraints);
        frame.add(tipLabel);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        layout.setConstraints(scanButton, constraints);
        frame.add(scanButton);

        constraints.gridx = 6;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        frame.add(cancelButton);
        //------------------------ Row end  ------------------------------

        //------------------------ Row start------------------------------
        warnInfoLabel.setForeground(Color.RED);
        constraints.gridx = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(warnInfoLabel, constraints);
        frame.add(warnInfoLabel);
        //------------------------ Row end  ------------------------------


        //------------------------ Row start------------------------------
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        layout.setConstraints(resultAreaLabel, constraints);
        frame.add(resultAreaLabel);

        constraints.gridx = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(resultAreaSeparator, constraints);
        frame.add(resultAreaSeparator);
        //------------------------ Row end  ------------------------------

        //------------------------ Row start------------------------------
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        layout.setConstraints(originResultInfoLabel, constraints);
        frame.add(originResultInfoLabel);

        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(backupResultInfoLabel, constraints);
        frame.add(backupResultInfoLabel);

        //------------------------ Row end  ------------------------------

        //------------------------ Row start------------------------------
        // Result area. Origin
        originResultList.setVisibleRowCount(20);
        /*String[] items = new String[]{"0123456789", "0123456789", "0123456789", "0123456789"
                , "0123456789", "0123456789", "0123456789"
                , "0123456789", "0123456789", "0123456789"
                , "0123456789", "0123456789", "0123456789"
                , "0123456789", "0123456789", "0123456789"};
        originResultList.setListData(items);*/

        originResultScrollPane.setViewportView(originResultList);
        originResultScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        originResultScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        constraints.gridx = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(originResultScrollPane, constraints);
        frame.add(originResultScrollPane);

        // Result area. Backup
        //backupResultList.setListData(items);
        backupResultList.setVisibleRowCount(20);

        backupResultScrollPane.setViewportView(backupResultList);
        backupResultScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        backupResultScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        constraints.gridx = 4;
        constraints.gridheight = 1;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(backupResultScrollPane, constraints);
        frame.add(backupResultScrollPane);
        //------------------------ Row end  ------------------------------

        //frame.setVisible(true);
    }

    @Override
    public GridBagLayout getLayout() {
        return layout;
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }

    public JLabel getOriginDirLabel() {
        return originDirLabel;
    }

    public JTextField getOriginDirText() {
        return originDirText;
    }

    public JFileChooser getOriginDirChooser() {
        return originDirChooser;
    }

    public JButton getOriginDirChooserButton() {
        return originDirChooserButton;
    }

    public JLabel getBackupDirLabel() {
        return backupDirLabel;
    }

    public JTextField getBackupDirText() {
        return backupDirText;
    }

    public JFileChooser getBackupDirChooser() {
        return backupDirChooser;
    }

    public JButton getBackupDirChooserButton() {
        return backupDirChooserButton;
    }

    public JLabel getOptionLabel() {
        return optionLabel;
    }

    public JCheckBox getFileNameCheckbox() {
        return fileNameCheckbox;
    }

    public JCheckBox getFileSizeCheckbox() {
        return fileSizeCheckbox;
    }

    public JCheckBox getFileMd5Checkbox() {
        return fileMd5Checkbox;
    }

    public JLabel getTipLabel() {
        return tipLabel;
    }

    public JButton getScanButton() {
        return scanButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JLabel getWarnInfoLabel() {
        return warnInfoLabel;
    }

    public JLabel getResultAreaLabel() {
        return resultAreaLabel;
    }

    public JSeparator getResultAreaSeparator() {
        return resultAreaSeparator;
    }

    public JLabel getOriginResultInfoLabel() {
        return originResultInfoLabel;
    }

    public JLabel getBackupResultInfoLabel() {
        return backupResultInfoLabel;
    }

    public DifferenceJList getOriginResultList() {
        return originResultList;
    }

    public JScrollPane getOriginResultScrollPane() {
        return originResultScrollPane;
    }

    public DifferenceJList getBackupResultList() {
        return backupResultList;
    }

    public JScrollPane getBackupResultScrollPane() {
        return backupResultScrollPane;
    }

    static class DifferenceJList extends JList<Difference> {
        public DifferenceJList() {
           super();
           initCellRender();
           initListener();
        }

        private void initListener() {
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem copyPathButton = new JMenuItem("Copy canonical path");
            JMenuItem synchorizeFilesButton = new JMenuItem("Synchronize selected files to the other side.");
            popupMenu.add(copyPathButton);
            popupMenu.add(synchorizeFilesButton);
            this.setComponentPopupMenu(popupMenu);
            /* This also work:
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            });*/
        }

        private void initCellRender() {
            //this.setCellRenderer(new MyCellRenderer());

            this.setCellRenderer(new ListCellRenderer<Difference>() {
                @Override
                public Component getListCellRendererComponent(JList<? extends Difference> list, Difference difference, int index, boolean isSelected, boolean cellHasFocus) {

                    JLabel jLabel = new JLabel(difference.getFileInfo().getCanonicalPath());
                    // Set item color according to selected, diff-type. Focus has no effect here.
                    if (isSelected){
                        jLabel.setBackground(Color.LIGHT_GRAY);
                    } else {
                        if (difference.getCode() == Difference.DIFF_SIZE) {
                            jLabel.setBackground(Color.PINK);
                        } else if (difference.getCode() == Difference.DIFF_MD5) {
                            jLabel.setBackground(Color.RED);
                        } else if ("dir".equals(difference.getFileInfo().getType())) {
                            jLabel.setBackground(Color.orange);
                        } else {
                            jLabel.setBackground(Color.WHITE);
                        }
                    }

                    // Opaque must be true, the default is false. Otherwise, you can't see any effects on it.
                    jLabel.setOpaque(true);
                    return jLabel;
                }
            });
        }
    }
}
