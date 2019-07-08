package com.arki.laboratory.snippet.file;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FileCopy {
    public static void main(String[] args) {
        File origin = new File("C:\\Users\\k\\Desktop\\temp\\scikit-learn-docs.pdf");
        File target = new File("C:\\Users\\k\\Desktop\\temp\\scikit-learn-docs1.pdf");
        File target1 = new File("C:\\Users\\k\\Desktop\\temp\\scikit-learn-docs2.pdf");
        long l = System.currentTimeMillis();
        copyByBufferedStream(origin, target);
        System.out.println(System.currentTimeMillis()-l);
        l = System.currentTimeMillis();
        copyByFileChannel(origin,target);
        System.out.println(System.currentTimeMillis()-l);
        l = System.currentTimeMillis();
        copyByFiles(origin,target1);
        System.out.println(System.currentTimeMillis()-l);
        copyAttributes(origin,target);
    }

    public static void copyAttributes(File origin,File target) {
        try {
            BasicFileAttributes originAttributes = Files.readAttributes(Paths.get(origin.getAbsolutePath()), BasicFileAttributes.class);
            FileTime creationTime = originAttributes.creationTime();
            FileTime lastModifiedTime = originAttributes.lastModifiedTime();
            FileTime lastAccessTime = originAttributes.lastAccessTime();
            Path targetPath = Paths.get(target.getAbsolutePath());
            /* Same as targetAttributeView.setTimes
            Files.setAttribute(targetPath, "creationTime", creationTime);
            Files.setAttribute(targetPath, "lastModifiedTime", lastModifiedTime);
            Files.setAttribute(targetPath, "lastAccessTime", lastAccessTime);*/
            BasicFileAttributeView targetAttributeView = Files.getFileAttributeView(targetPath, BasicFileAttributeView.class);
            targetAttributeView.setTimes(lastModifiedTime,lastAccessTime,creationTime);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Use Files.copy
     * @param origin
     * @param target
     */
    public static void copyByFiles(File origin, File target) {
        Path originPath = Paths.get(origin.getAbsolutePath());
        Path targetPath = Paths.get(target.getAbsolutePath());
        try {
            Files.copy(originPath, targetPath,StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy by FileChannel
     * @param origin
     * @param target
     */
    public static void copyByFileChannel(File origin, File target) {
        FileInputStream fis = null;
        FileChannel inChannel = null;
        FileOutputStream fos = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream(origin);
            inChannel = fis.getChannel();
            fos = new FileOutputStream(target);
            outChannel = fos.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            try {
                if (inChannel != null) {
                    inChannel.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (outChannel != null) {
                    outChannel.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Use BufferedStream to copy.
     * @param origin
     * @param target
     */
    public static void copyByBufferedStream(File origin, File target) {
        FileInputStream fis = null;
        BufferedInputStream in = null;
        FileOutputStream fos = null;
        BufferedOutputStream out = null;

        try {
            fis = new FileInputStream(origin);
            in = new BufferedInputStream(fis);
            fos = new FileOutputStream(target);
            out = new BufferedOutputStream(fos);
            int s = 8*1024*1024; // 1MB
            byte[] buf = new byte[s];
            int i;
            while ((i = in.read(buf)) != -1) {
                out.write(buf, 0, i);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            try {
                if (in != null) {
                    in.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                if (out != null) {
                    out.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
