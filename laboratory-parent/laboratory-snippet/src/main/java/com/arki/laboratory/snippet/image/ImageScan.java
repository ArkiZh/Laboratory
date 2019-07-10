package com.arki.laboratory.snippet.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class ImageScan {

    public static void main(String[] args) throws ImageProcessingException, IOException {
        //String image_path = "D:\\POCKET\\MINE\\100D7100\\DSC_2659.JPG";
        String image_path = "C:\\Users\\mi\\Desktop\\2012-07-12_15-22-04_768.jpg";
        Metadata metadata = ImageMetadataReader.readMetadata(new File(image_path));
        Iterable<Directory> directories = metadata.getDirectories();
        for (Directory d : directories) {
            System.out.println("===============");
            Collection<Tag> tags = d.getTags();
            for (Tag t : tags) {
                System.out.println(t);
            }
        }
    }

}
