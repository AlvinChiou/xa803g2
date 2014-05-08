package com.utilities;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JPGCompression {

     // ���Y�Ϥ���k
     // @param oldFile    �n���Y���Ϥ����|
     // @param newFile    ���Y��K�[�����W(�b�X�i�W�e�K�[,���|���ܮ榡)
     // @param width      ���Y�e
     // @param height     ���Y��
     // @param percentage �O�_��������Y,true�h�e����۰ʽվ�
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
                // ���������Y�p���X���e��
                double rate1 = ((double) src.getWidth(null))
                        / (double) widthdist + 0.1;
                double rate2 = ((double) src.getHeight(null))
                        / (double) heightdist + 0.1;
                double rate = rate1 > rate2 ? rate1 : rate2;
                int new_w = (int) (((double) src.getWidth(null)) / rate);
                int new_h = (int) (((double) src.getHeight(null)) / rate);
                // �]�w�e��
                BufferedImage tag = new BufferedImage(new_w, new_h,
                        BufferedImage.TYPE_INT_RGB);
                // �]�w����X�i�W
                // String filePrex = oldFile
                // .substring(0, oldFile.lastIndexOf('.'));
                // newFile = filePrex + "SCALE_AREA_AVERAGING"
                // + oldFile.substring(filePrex.length());
                // �ͦ��Ϥ�
                // ��ؤ�k,�ĪG�P��q���ۦP,�Ĳv�t���h
                //tag.getGraphics().drawImage(src.getScaledInstance(widthdist,heightdist,Image.SCALE_SMOOTH), 0, 0, null);
                tag.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_AREA_AVERAGING), 0, 0, null);
                FileOutputStream out = new FileOutputStream(oldFile);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(tag);
                out.close();
            } else {
                // �]�w�e��
                BufferedImage tag = new BufferedImage(widthdist, heightdist,
                        BufferedImage.TYPE_INT_RGB);
                // �]�w����X�i�W
                // String filePrex = oldFile
                // .substring(0, oldFile.lastIndexOf('.'));
                // newFile = filePrex + "SCALE_AREA_AVERAGING" + newFile
                // + oldFile.substring(filePrex.length());
                // �ͦ��Ϥ�
                // ��ؤ�k,�ĪG�P��q���ۦP,�ĤG�خĲv��Ĥ@�ذ�,���@��
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
