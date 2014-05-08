package com.utilities;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JPGCompression {

     // 壓縮圖片方法
     // @param oldFile    要壓縮的圖片路徑
     // @param newFile    壓縮後添加的後綴名(在擴展名前添加,不會改變格式)
     // @param width      壓縮寬
     // @param height     壓縮高
     // @param percentage 是否等比例壓縮,true則寬高比自動調整
     // @return
     // @throws Exception

    public static void reduceImg(String oldFile, int widthdist,
                                 int heightdist, boolean percentage) {
        try {
            File srcfile = new File(oldFile);
            if (!srcfile.exists()) {
                return;
            }
            Image src = javax.imageio.ImageIO.read(srcfile);
            if (percentage) {
                // 為等比壓縮計算輸出的寬高
                double rate1 = ((double) src.getWidth(null))
                        / (double) widthdist + 0.1;
                double rate2 = ((double) src.getHeight(null))
                        / (double) heightdist + 0.1;
                double rate = rate1 > rate2 ? rate1 : rate2;
                int new_w = (int) (((double) src.getWidth(null)) / rate);
                int new_h = (int) (((double) src.getHeight(null)) / rate);
                // 設定寬高
                BufferedImage tag = new BufferedImage(new_w, new_h,
                        BufferedImage.TYPE_INT_RGB);
                // 設定文件擴展名
                // String filePrex = oldFile
                // .substring(0, oldFile.lastIndexOf('.'));
                // newFile = filePrex + "SCALE_AREA_AVERAGING"
                // + oldFile.substring(filePrex.length());
                // 生成圖片
                // 兩種方法,效果與質量都相同,效率差不多
                //tag.getGraphics().drawImage(src.getScaledInstance(widthdist,heightdist,Image.SCALE_SMOOTH), 0, 0, null);
                tag.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_AREA_AVERAGING), 0, 0, null);
                FileOutputStream out = new FileOutputStream(oldFile);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(tag);
                out.close();
            } else {
                // 設定寬高
                BufferedImage tag = new BufferedImage(widthdist, heightdist,
                        BufferedImage.TYPE_INT_RGB);
                // 設定文件擴展名
                // String filePrex = oldFile
                // .substring(0, oldFile.lastIndexOf('.'));
                // newFile = filePrex + "SCALE_AREA_AVERAGING" + newFile
                // + oldFile.substring(filePrex.length());
                // 生成圖片
                // 兩種方法,效果與質量都相同,第二種效率比第一種高,約一倍
                // tag.getGraphics().drawImage(src.getScaledInstance(widthdist,heightdist, Image.SCALE_SMOOTH), 0, 0, null);
                tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_AREA_AVERAGING), 0, 0, null);
                FileOutputStream out = new FileOutputStream(oldFile);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(tag);
                out.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
