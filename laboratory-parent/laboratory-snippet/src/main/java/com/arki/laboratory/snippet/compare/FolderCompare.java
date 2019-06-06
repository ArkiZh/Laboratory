package com.arki.laboratory.snippet.compare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FolderCompare {

    private static String sourcePath;
    private static String targetPath;

    static{
        sourcePath = "C:/FolderCompare/sourceFolder";
        targetPath = "C:/FolderCompare/targetFolder";
    }

    public static void main(String[] args) throws IOException {

        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        compareFile(sourceFile, targetFile);

    }
    private static void compareFile(File a, File b) throws IOException {
        if (!sameType(a, b)) {
            System.out.println("Find difference! " + a.getCanonicalPath() + " is " + (a.isDirectory() ? "Folder" : "File")
                    + ", while " + b.getCanonicalPath() + " is " + (b.isDirectory() ? "Folder" : "File"));
        } else {
            if (a.isDirectory()) {
                File[] aChildren = a.listFiles();
                File[] bChildren = b.listFiles();
                if (aChildren.length > 0 && bChildren.length > 0) {
                    sortFileArray(aChildren);
                    sortFileArray(bChildren);
                    List<File> lackedInA = new ArrayList<>();
                    List<File> lackedInB = new ArrayList<>();
                    List<Integer> equalsInB = new ArrayList<>();
                    List<File> waitToCompare = new ArrayList<>();
                    for (int i = 0; i < aChildren.length; i++) {
                        int indexInB = searchFileInArray(aChildren[i], bChildren);
                        if (indexInB < 0) {
                            lackedInB.add(aChildren[i]);
                        } else {
                            equalsInB.add(indexInB);
                            if (aChildren[i].isDirectory()) {
                                waitToCompare.add(aChildren[i]);
                                waitToCompare.add(bChildren[indexInB]);
                            }
                        }
                    }
                    for (int i = 0; i < bChildren.length; i++) {
                        if (!equalsInB.contains(i)) {
                            lackedInA.add(bChildren[i]);
                        }
                    }
                    if (lackedInA.size() > 0)
                        System.out.println("Find difference! In Folder " + a.getCanonicalPath() + " :lacking " + printFileNamesInList(lackedInA));
                    if (lackedInB.size() > 0)
                        System.out.println("Find difference! In Folder " + b.getCanonicalPath() + " :lacking " + printFileNamesInList(lackedInB));
                    for (int i = 0; i < waitToCompare.size(); i += 2) {
                        compareFile(waitToCompare.get(i), waitToCompare.get(i + 1));
                    }
                } else if (!(aChildren.length == 0 && bChildren.length == 0)) {
                    System.out.println("Find difference! " + a.getCanonicalPath() + (aChildren.length > 0 ? " do" : " don't") + " have files inside, while "
                            + b.getCanonicalPath() + (bChildren.length > 0 ? " do" : " don't") + " have");
                }


            } else {
                if (!a.getName().equals(b.getName()))
                    System.out.println("Find difference! " + a.getCanonicalPath() + " != " + b.getCanonicalPath());
            }
        }
    }

    private static int searchFileInArray(File file, File[] fileArray) {
        for (int i = 0; i < fileArray.length; i++) {
            if (fileArray[i].getName().equals(file.getName()) && sameType(file, fileArray[i])) {
                return i;
            }
        }
        return -1;
    }

    private static String printFileNamesInList(List<File> fileList) {
        if(fileList==null || fileList.size()<=0) return "空";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < fileList.size(); i++) {
            String name = fileList.get(i).getName();
            sb.append(name).append(", ");
        }
        String substring = sb.substring(0, sb.length() - 1);
        return substring;
    }

    private static boolean sameType(File a, File b) {
        return a.isFile() == b.isFile() && a.isDirectory() == b.isDirectory();
    }

    private static void sortFileArray(File[] array) {
        Arrays.sort(array, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
