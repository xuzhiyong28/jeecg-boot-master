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

    public static byte[] imgAddSy(byte[] imgByte , Integer COMPID) {
        if(COMPID == null){
            COMPID = 1;
        }
        ByteArrayInputStream in = null;
        ByteArrayOutputStream bout = null;
        InputStream resourceAsStream  = null;
        try {
            bout = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(imgByte);
            BufferedImage image = ImageIO.read(in);
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            resourceAsStream = COMPID == 2 ?
                    ImageSyUtils.class.getClassLoader().getResourceAsStream("sy3.png") :
                    ImageSyUtils.class.getClassLoader().getResourceAsStream("sy2.png") ;
            BufferedImage syBufferImage = ImageIO.read(resourceAsStream);
            int syImageWidth = syBufferImage.getWidth();
            int syImageHeight = syBufferImage.getHeight();
            if(imageWidth < 1500 || imageHeight < 1500){
                syBufferImage = Thumbnails.of(syBufferImage).size(180, 180)
                        .keepAspectRatio(false).asBufferedImage();
            }

            if(imageWidth < 800 || imageHeight < 800){
                syBufferImage = Thumbnails.of(syBufferImage).size(90, 90)
                        .keepAspectRatio(false).asBufferedImage();
            }


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
