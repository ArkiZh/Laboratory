package com.arki.laboratory.snippet.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class demo {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        Config config = new Config(properties);
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
        properties.setProperty("kaptcher.border", "yes");
        properties.setProperty("kaptcha.border.color", "255,0,0");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "600");
        properties.setProperty("kaptcha.image.height", "300");
        properties.setProperty("kaptcha.text.producer.font.size", "50");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        String text = kaptcha.createText();
        BufferedImage image = kaptcha.createImage(text);
        FileOutputStream fileOutputStream = new FileOutputStream("./image.jpg");
        ImageIO.write(image, "jpg", fileOutputStream);

    }
}
