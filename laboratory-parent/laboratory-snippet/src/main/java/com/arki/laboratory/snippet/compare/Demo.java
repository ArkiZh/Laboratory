package com.arki.laboratory.snippet.compare;

public class Demo {
    public static void main(String[] args) {

    }

    public static void compareFileInfo(FileInfo base, FileInfo backup, boolean useSize, boolean useMD5) {
        if (!base.sameType(backup)){
            recordDifference(base,backup,"Different type.");
        }else {
            if ("dir".equals(base.getType())) {

            } else if ("file".equals(base.getType())) {
                if (useSize) {
                    if (base.getSize() != backup.getSize()) {

                    }
                }
                if (useMD5) {

                }
                if (base.getName().equals(backup.getName())) {

                }
            }
        }
    }

    public static void recordDifference(FileInfo base, FileInfo backup, String msg) {
        System.out.println("Find diffenence: " + msg + " || Base: " + base.getCanonicalPath() + " || Backup: " + backup.getCanonicalPath());
    }

}
