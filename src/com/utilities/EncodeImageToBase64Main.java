package com.utilities;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Alvin on 2014/4/23.
 */
public class EncodeImageToBase64Main {
    public static void main(String[] args) throws IOException {
        BufferedImage img = ImageIO.read(new File("H:\\1.jpg"));
        EncodeImageToBase64 encode = new EncodeImageToBase64();
        String imgstr = encode.encodeToString(img, "jpg"); //can change png to jpg
        /**
         * Can write SQL INSERT INTO command...
         * */
        System.out.println("<img alt=\"\" src=\"data:image/jpeg;base64,"+imgstr+"\" />");
    }
}
