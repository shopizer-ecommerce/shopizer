package com.salesmanager.admin.components.content.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.terracotta.modules.ehcache.store.TerracottaClusteredInstanceFactory.LOGGER;

public class ImageUtils {

    private ImageUtils() {}

    public static Dimension getImageSize(String path){

        BufferedImage readImage;
        Dimension dim = new Dimension();
        try {
            readImage = ImageIO.read(new File(path));
            dim.height = readImage.getHeight();
            dim.width = readImage.getWidth();
        } catch (Exception e) {
            LOGGER.error("get image size ", e);
        }
        return dim;
    }
}
