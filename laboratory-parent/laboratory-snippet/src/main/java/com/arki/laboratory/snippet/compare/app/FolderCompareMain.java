package com.arki.laboratory.snippet.compare.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;
import java.util.List;

public class FolderCompareMain {

    private static final int RECORD_DIFFERENCE_ORIGIN_REDUNDANT = 1;
    private static final int RECORD_DIFFERENCE_BACKUP_REDUNDANT = 2;
    private static final int RECORD_DIFFERENCE_ORIGIN_SIZE = 3;
    private static final int RECORD_DIFFERENCE_BACKUP_SIZE = 4;
    private static final int RECORD_DIFFERENCE_ORIGIN_MD5 = 5;
    private static final int RECORD_DIFFERENCE_BACKUP_MD5 = 6;

    private FolderCompareGUIAbsolute frame = new FolderCompareGUIAbsolute();



    public static void main(String[] args) {
        FolderCompareMain folderCompareMain = new FolderCompareMain();
        folderCompareMain.init();
    }

    public void init() {

        JButton scanButton = frame.getScanButton();
        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // init start options.
                initScanOptions();

                // start compare.
                JTextField originDirText = frame.getOriginDirText();
                String originPath = originDirText.getText();
                JTextField backupDirText = frame.getBackupDirText();
                String backupPath = backupDirText.getText();
                boolean useFileSizeFlag = frame.getFileSizeCheckbox().isSelected();
                boolean useFileMD5Flag = frame.getFileMd5Checkbox().isSelected();

                startCompare(originPath, backupPath,useFileSizeFlag,useFileMD5Flag);


                // reset start options.
                resetScanOptions();


            }
        });
        frame.setVisible(true);
    }

    public void initScanOptions() {
        frame.getOriginDirText().setEnabled(false);
        frame.getOriginDirChooserButton().setEnabled(false);
        frame.getBackupDirText().setEnabled(false);
        frame.getBackupDirChooserButton().setEnabled(false);
        frame.getFileSizeCheckbox().setEnabled(false);
        frame.getFileMd5Checkbox().setEnabled(false);
        frame.getScanButton().setEnabled(false);
        frame.getWarnInfoLabel().setText("");
        /*frame.getOriginResultList().setCellRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                this.setBackground(Color.orange);
                return this;
            }
        });
        frame.getOriginResultList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = frame.getOriginResultList().locationToIndex(e.getPoint());
                frame.getOriginResultList().setSelectedIndex(index);
                frame.getOriginResultList().setForeground(Color.BLUE);
            }
        });*/
        frame.getOriginResultList().setListData(new Difference[]{});
        frame.getBackupResultList().setListData(new Difference[]{});
    }
    public void resetScanOptions() {
        frame.getOriginDirText().setEnabled(true);
        frame.getOriginDirChooserButton().setEnabled(true);
        frame.getBackupDirText().setEnabled(true);
        frame.getBackupDirChooserButton().setEnabled(true);
        frame.getFileSizeCheckbox().setEnabled(true);
        frame.getFileMd5Checkbox().setEnabled(true);
        frame.getScanButton().setEnabled(true);
    }

    public void startCompare(String originPath, String backupPath,boolean useFileSizeFlag,boolean useFileMD5Flag) {
        JLabel warnInfoLabel = frame.getWarnInfoLabel();
        warnInfoLabel.setText("");
        String warnInfo = "";
        // Ensure dir not empty.
        if ("".equals(originPath.trim())) {
            warnInfo = "Please choose directory for: Origin directory";
        }
        if ("".equals(backupPath.trim())) {
            warnInfo = "".equals(warnInfo) ? "Please choose directory for: Backup directory" : warnInfo + " | Backup directory";
        }
        if (!"".equals(warnInfo)) {
            warnInfoLabel.setText(warnInfo);
            return;
        }


        File originFile = new File(originPath);
        File backupFile = new File(backupPath);

        // Ensure directory for exist.
        if (!originFile.exists()) {
            warnInfo = "Warning: File doesn't exist: " + originPath;
        }
        if (!backupFile.exists()) {
            warnInfo = "".equals(warnInfo) ? "Warning: File doesn't exist: " + backupPath : warnInfo + " | " + backupPath;
        }
        if (!"".equals(warnInfo)) {
            warnInfoLabel.setText(warnInfo);
            return;
        }

        // Start to compare:
        FileInfo originFileInfo = new FileInfo(originFile, useFileSizeFlag, useFileMD5Flag);
        FileInfo backupFileInfo = new FileInfo(backupFile, useFileSizeFlag, useFileMD5Flag);
        compareFileInfo(originFileInfo, backupFileInfo, useFileSizeFlag, useFileMD5Flag);
    }

    /**
     * 第一次调用时候origin backup的文件名可不一样，之后递归调用时两者名字需确保一样。
     * 原因：第一次调用时可能只是为了比较两个不同名的文件md5是否一致，或者比较两个不同名字的文件夹中内容是否一样。
     * @param origin
     * @param backup
     * @param useSize
     * @param useMD5
     */
    public void compareFileInfo(FileInfo origin, FileInfo backup, boolean useSize, boolean useMD5) {
        String warnInfo;
        JLabel warnInfoLabel = frame.getWarnInfoLabel();
        if (!origin.sameType(backup)) {
            // origin and backup are not the same type.
            warnInfo = "dir".equals(origin.getType())
                    ? "Warning: The origin is a directory while the backup is a file"
                    : "Warning: The origin is a file while the backup is a directory";
            warnInfoLabel.setText(warnInfo);
            return;
        }else{
            if ("file".equals(origin.getType())) {
                // Compare files
                if (useSize) {
                    if (origin.getSize() != backup.getSize()) {
                        // Find different size.
                        recordDifferenceToPane(new Difference(origin, Difference.CAMP_ORIGIN, Difference.DIFF_SIZE));
                        recordDifferenceToPane(new Difference(backup, Difference.CAMP_BACKUP, Difference.DIFF_SIZE));
                        return;
                    }
                }
                if (useMD5) {
                    if (!origin.getMd5().equals(backup.getMd5())) {
                        // Find different size.
                        recordDifferenceToPane(new Difference(origin, Difference.CAMP_ORIGIN, Difference.DIFF_MD5));
                        recordDifferenceToPane(new Difference(backup, Difference.CAMP_BACKUP, Difference.DIFF_MD5));
                        return;
                    }
                }
            } else if ("dir".equals(origin.getType())) {
                // Compare directories
                // Step 1: Separate each dir's children into dirs and files.
                ArrayList<String> originChildrenDir = new ArrayList<>();
                ArrayList<String> originChildrenFile = new ArrayList<>();
                separateFileAndDir(new File(origin.getCanonicalPath()), originChildrenDir, originChildrenFile);

                ArrayList<String> backupChildrenDir = new ArrayList<>();
                ArrayList<String> backupChildrenFile = new ArrayList<>();
                separateFileAndDir(new File(backup.getCanonicalPath()), backupChildrenDir, backupChildrenFile);

                // Step 2: Compare the same type of children.
                HashMap<ArrayList<String>, ArrayList<String>> compareMap = new HashMap<>();
                compareMap.put(originChildrenDir, backupChildrenDir);
                compareMap.put(originChildrenFile, backupChildrenFile);
                for (Map.Entry<ArrayList<String>, ArrayList<String>> entry : compareMap.entrySet()) {
                    ArrayList<String> originChildren = entry.getKey();
                    ArrayList<String> backupChildren = entry.getValue();
                    // Iterate children of origin.
                    for (int i = 0; i < originChildren.size(); i++) {
                        String originChildName = originChildren.get(i);
                        // Since this origin child may don't have the corresponding backup child, don't calculate md5 now. It takes much time to calculate.
                        FileInfo originChild = new FileInfo(new File(origin.getCanonicalPath(), originChildName), origin, useSize, false, false);
                        // Judge whether children of backup contains this origin child.
                        int hitIndex = backupChildren.indexOf(originChildName);
                        if (hitIndex >= 0) {
                            // Calculate md5 and set it into md5 field.
                            if (useMD5) {
                                originChild.calculateMd5();
                            }
                            // Compare the same named files.
                            FileInfo backupChild = new FileInfo(new File(backup.getCanonicalPath(), originChildName), backup, useSize, useMD5, false);
                            compareFileInfo(originChild, backupChild, useSize, useMD5);
                            // Remove the file of backup since it has been compared.
                            backupChildren.remove(hitIndex);
                        } else {
                            // Only the origin has this file. Record it.
                            recordDifferenceToPane(new Difference(originChild, Difference.CAMP_ORIGIN, Difference.DIFF_REDUNDANT));
                        }
                    }
                    // After the compare according to name and remove, only the backup has these files.
                    for (int i = 0; i < backupChildren.size(); i++) {
                        FileInfo backupChild = new FileInfo(new File(backup.getCanonicalPath(), backupChildren.get(i)), backup, useSize, false, false);
                        recordDifferenceToPane(new Difference(backupChild, Difference.CAMP_BACKUP, Difference.DIFF_REDUNDANT));
                    }
                }
            } else {
                throw new RuntimeException("Unexpected file type.");
            }
        }
    }


    public void recordDifferenceToPane(Difference difference) {
        FolderCompareGUIAbsolute.DifferenceJList jList;
        int camp = difference.getCamp();
        if (camp == Difference.CAMP_ORIGIN) {
            jList = frame.getOriginResultList();
        } else if (camp == Difference.CAMP_BACKUP) {
            jList = frame.getBackupResultList();
        } else {
            throw new RuntimeException("Unexpected camp!");
        }
        ListModel<Difference> model = jList.getModel();
        Difference[] content = new Difference[model.getSize() + 1];
        for (int i = 0; i < model.getSize(); i++) {
            content[i] = model.getElementAt(i);
        }
        content[model.getSize()] = difference;
        jList.setListData(content);
    }

    public void separateFileAndDir(File dirAsParent, List<String> dirList, List<String> fileList) {
        String[] nameArray = dirAsParent.list();
        for (int i = 0; i < nameArray.length; i++) {
            String s = nameArray[i];
            if (new File(dirAsParent, s).isDirectory()) {
                dirList.add(s);
            } else {
                fileList.add(s);
            }
        }
    }

}
