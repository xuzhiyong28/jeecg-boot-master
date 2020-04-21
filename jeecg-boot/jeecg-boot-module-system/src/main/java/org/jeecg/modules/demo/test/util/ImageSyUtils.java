package org.jeecg.modules.demo.test.util;

import cn.hutool.core.io.resource.ClassPathResource;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author xuzhiyong
 * @createDate 2020-04-12-20:06
 */
@Slf4j
public class ImageSyUtils {

    // 不透明度
    private static float opacity = 0.6f;

    public static byte[] imgAddSy(byte[] imgByte) {
        ByteArrayInputStream in = null;
        ByteArrayOutputStream bout = null;
        InputStream resourceAsStream  = null;
        try {
            bout = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(imgByte);
            BufferedImage image = ImageIO.read(in);
            resourceAsStream = ImageSyUtils.class.getClassLoader().getResourceAsStream("sy2.png");
            BufferedImage syBufferImage = ImageIO.read(resourceAsStream);
            Thumbnails.of(image)
                    //.size(image.getWidth(), image.getHeight())
                    .scale(0.7f)
                    .outputQuality(0.7f)
                    //.watermark(Positions.BOTTOM_RIGHT, syBufferImage , opacity)
                    .watermark((var1, var2, var3, var4, var5, var6, var7, var8) -> {
                        int var9 = var1 - var3 - var6 - 100;
                        int var10 = var2 - var4 - var8 - 100;
                        return new Point(var9, var10);
                    },syBufferImage , opacity)
                    .outputFormat("jpg")
                    .toOutputStream(bout);
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
            if(resourceAsStream != null){
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imgByte;
    }


}
