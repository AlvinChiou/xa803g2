package com.utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Alvin on 2014/4/23.
 */
public class DecodeImageFromBase64Main {
    public static void main(String[] args) throws IOException{
        DecodeImageFromBase64 decode = new DecodeImageFromBase64();
        BufferedImage newImg;
        String encodedStr = "Put encoded string here.";
        newImg = decode.decodeToImage(encodedStr);
        ImageIO.write(newImg, "png", new File("files/img/CopyOfTestImage.png"));
    }
}
