package com.salesmanager.admin.components.content.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageUtils {

    public static Dimension getImageSize(String path){

        BufferedImage readImage = null;
        Dimension dim = new Dimension();
        try {
            readImage = ImageIO.read(new File(path));
            dim.height = readImage.getHeight();
            dim.width = readImage.getWidth();
        } catch (Exception e) {
            readImage = null;
        }
        return dim;
    }
}
