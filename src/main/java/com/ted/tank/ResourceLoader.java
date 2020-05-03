package com.ted.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ResourceLoader {
    public static BufferedImage goodtankL,goodtankR,goodtankU,goodtankD;
    public static BufferedImage goodtankL1,goodtankR1,goodtankU1,goodtankD1;
    public static BufferedImage badtankL,badtankR,badtankU,badtankD;
    public static BufferedImage badtankL1,badtankR1,badtankU1,badtankD1;
    public static BufferedImage bulletL,bulletR,bulletU,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];
    static {
        try {
            goodtankU = ImageIO.read(new File("images/Goodtank1.png"));
            goodtankL = ImageUtil.rotateImage(goodtankU,-90);
            goodtankR = ImageUtil.rotateImage(goodtankU,90);
            goodtankD = ImageUtil.rotateImage(goodtankU,180);
            goodtankU1 = ImageIO.read(new File("images/Goodtank2.png"));
            goodtankL1 = ImageUtil.rotateImage(goodtankU1,-90);
            goodtankR1 = ImageUtil.rotateImage(goodtankU1,90);
            goodtankD1 = ImageUtil.rotateImage(goodtankU1,180);
            badtankU = ImageIO.read(new File("images/Badtank1.png"));
            badtankL = ImageUtil.rotateImage(badtankU,-90);
            badtankR = ImageUtil.rotateImage(badtankU,90);
            badtankD = ImageUtil.rotateImage(badtankU,180);
            badtankU1 = ImageIO.read(new File("images/Badtank2.png"));
            badtankL1 = ImageUtil.rotateImage(badtankU1,-90);
            badtankR1 = ImageUtil.rotateImage(badtankU1,90);
            badtankD1 = ImageUtil.rotateImage(badtankU1,180);
            bulletU = ImageIO.read(new File("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);

            for(int i=0;i<16;i++){
                explodes[i]=ImageIO.read(new File("images/e"+(i+1)+".gif"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
