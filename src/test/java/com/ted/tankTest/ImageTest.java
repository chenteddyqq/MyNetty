package com.ted.tankTest;

import com.ted.tank.Dir;
import com.ted.tank.Tank;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageTest {


    @Test
    public void test() throws IOException {
        BufferedImage image = ImageIO.read(new File("images/bulletU.gif"));
        Assert.assertNotNull(image);
    }
}
