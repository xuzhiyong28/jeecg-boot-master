package org.jeecg.modules.demo.test.util;

import cn.hutool.core.io.resource.ClassPathResource;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author xuzhiyong
 * @createDate 2020-04-12-20:06
 */
public class ImageSyUtils {

    // 不透明度
    private static float opacity = 0.25f;

    public static byte[] imgAddSy(byte[] imgByte) {
        ByteArrayInputStream in = null;
        ByteArrayOutputStream bout = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource("sy.png");
            bout = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(imgByte);
            BufferedImage image = ImageIO.read(in);
            Thumbnails.of(image).size(image.getWidth(), image.getHeight())
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(classPathResource.getFile()), opacity).outputFormat("png").toOutputStream(bout);
            byte[] bytes = bout.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imgByte;
    }


}
