package com.arki.laboratory.snippet.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GuiTest {

    public static void main(String[] args) {
        //chooseFile();
        layout();
    }

    public static void layout() {
        JFrame frame = new JFrame();
        frame.setTitle("Folder Compare");
        frame.setBounds(300, 300, 1000, 618);
        frame.setBackground(new Color(200, 200, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        GridBagLayout layout = new GridBagLayout();

        frame.setLayout(layout);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.insets = new Insets(0, 10, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;


        //------------------------ Row start------------------------------
        // Origin directory chooser.
        JLabel originDirLabel = new JLabel();
        originDirLabel.setText("Origin directory:");
        constraints.gridwidth = 1;
        layout.setConstraints(originDirLabel, constraints);
        frame.add(originDirLabel);

        JTextField originDirText = new JTextField(25);
        //JPanel jPanel = new JPanel();
        //jPanel.add(originDirText);
        constraints.gridwidth = 2;
        constraints.gridx = GridBagConstraints.RELATIVE;
        layout.setConstraints(originDirText, constraints);
        frame.add(originDirText);

        JFileChooser originDirChooser = new JFileChooser();
        originDirChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        JButton originDirChooserButton = new JButton();
        originDirChooserButton.setText("Choose");
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
        JLabel backupDirLabel = new JLabel();
        backupDirLabel.setText("Backup directory:");
        constraints.gridwidth = 1;
        layout.setConstraints(backupDirLabel, constraints);
        frame.add(backupDirLabel);

        JTextField backupDirText = new JTextField(25);
        layout.setConstraints(backupDirText, constraints);
        constraints.gridwidth = 2;
        frame.add(backupDirText);

        JFileChooser backupDirChooser = new JFileChooser();
        backupDirChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        JButton backupDirChooserButton = new JButton();
        backupDirChooserButton.setText("Choose");
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
        JLabel optionLabel = new JLabel();
        optionLabel.setText("Options:");
        constraints.gridwidth = 1;
        layout.setConstraints(optionLabel, constraints);
        frame.add(optionLabel);

        JCheckBox fileNameCheckbox = new JCheckBox();
        fileNameCheckbox.setText("Use file name check.");
        fileNameCheckbox.setSelected(true);
        fileNameCheckbox.setEnabled(false);
        layout.setConstraints(fileNameCheckbox, constraints);
        frame.add(fileNameCheckbox);

        JCheckBox fileSizeCheckbox = new JCheckBox();
        fileSizeCheckbox.setText("Use file size check.");
        layout.setConstraints(fileSizeCheckbox, constraints);
        frame.add(fileSizeCheckbox);

        JCheckBox fileMd5Checkbox = new JCheckBox();
        fileMd5Checkbox.setText("Use md5 check.");
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(fileMd5Checkbox, constraints);
        frame.add(fileMd5Checkbox);
        //------------------------ Row end  ------------------------------


        //------------------------ Row start------------------------------
        JLabel tipLabel = new JLabel();
        tipLabel.setText("Tip: todo");
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridwidth = 2;
        layout.setConstraints(tipLabel, constraints);
        frame.add(tipLabel);

        JButton scanButton = new JButton();
        scanButton.setText("Scan");
        constraints.gridx = 4;
        constraints.gridwidth = 1;
        layout.setConstraints(scanButton, constraints);
        frame.add(scanButton);

        JButton cancelButton = new JButton();
        cancelButton.setText("Cancel");
        constraints.gridx = 6;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        frame.add(cancelButton);
        //------------------------ Row end  ------------------------------

        //------------------------ Row start------------------------------
        JLabel warnInfoLabel = new JLabel("Warn info.");
        warnInfoLabel.setForeground(Color.RED);
        constraints.gridx = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(warnInfoLabel, constraints);
        frame.add(warnInfoLabel);
        //------------------------ Row end  ------------------------------

        //------------------------ Row start------------------------------
        JLabel label1 = new JLabel();
        label1.setText("Scan result:");
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        layout.setConstraints(label1, constraints);
        frame.add(label1);

        JSeparator jSeparator = new JSeparator();
        constraints.gridx = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(jSeparator, constraints);
        frame.add(jSeparator);
        //------------------------ Row end  ------------------------------

        //------------------------ Row start------------------------------
        JLabel label2 = new JLabel("Origin directory contains these files, while backup directory doesn't contain:");
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        layout.setConstraints(label2, constraints);
        frame.add(label2);

        JLabel label3 = new JLabel("Backup directory contains these files, while origin directory doesn't contain:");
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(label3, constraints);
        frame.add(label3);

        //------------------------ Row end  ------------------------------

        //------------------------ Row start------------------------------
        // Result area. Origin
        JList<String> originResultList = new JList<>();
        originResultList.setVisibleRowCount(20);
        String[] items = new String[]{"0123456789", "0123456789", "0123456789", "0123456789"
                , "0123456789", "0123456789", "0123456789"
                , "0123456789", "0123456789", "0123456789"
                , "0123456789", "0123456789", "0123456789"
                , "0123456789", "0123456789", "0123456789"};
        originResultList.setListData(items);

        JScrollPane originResultPane = new JScrollPane();
        originResultPane.setViewportView(originResultList);
        originResultPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        originResultPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        constraints.gridx = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(originResultPane, constraints);
        frame.add(originResultPane);

        // Result area. Backup
        JList<String> backupResultList = new JList<>();
        backupResultList.setListData(items);
        backupResultList.setVisibleRowCount(20);

        JScrollPane backupResultPane = new JScrollPane();
        backupResultPane.setViewportView(backupResultList);
        backupResultPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        backupResultPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        constraints.gridx = 4;
        constraints.gridheight = 1;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(backupResultPane, constraints);
        frame.add(backupResultPane);
        //------------------------ Row end  ------------------------------

        frame.setVisible(true);
    }

    public static void chooseFile() {
        // 窗口框架
        JFrame jFrame = new JFrame();
        jFrame.setTitle("jFrameTitle");
        jFrame.setBounds(600, 300, 500, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 面板1
        JPanel panel = new JPanel();
        jFrame.getContentPane().add(panel, BorderLayout.NORTH);

        // 可滚动面板
        JScrollPane scrollPane = new JScrollPane();
        jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        JTextArea textArea = new JTextArea();
        scrollPane.setViewportView(textArea);

        JButton button = new JButton("选择文件");
        // 监听button的选择路径
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示打开的文件对话框
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(jFrame);
                try {
                    // 使用文件类获取选择器选择的文件
                    File file = fileChooser.getSelectedFile();//
                    //这里是我的业务需求，各位不必照抄
                    String content = file.getAbsolutePath();
                    textArea.setText(content);
                } catch (Exception e2) {
                    JPanel panel3 = new JPanel();
                    JOptionPane.showMessageDialog(panel3, "没有选中任何文件", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panel.add(button);

        jFrame.setVisible(true);
    }

}
