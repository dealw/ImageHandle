package com.deal.image.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 图像处理工具类
 *
 * @Author Deal
 * @Date 2022/1/1 23:10
 */
public class ImageUtil {
    /**
     * 图像灰度化处理
     *
     * @return
     * @Author Deal
     * @Date 2022/1/1 23:11
     */
    public static BufferedImage RGB2Gray(BufferedImage bufferedImage, double grayR, double grayG, double grayB) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage grayBufferedImage = new BufferedImage(width, height, bufferedImage.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufferedImage.getRGB(i, j);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                int newPixel = (int) (grayR * r + grayG * g + grayB * b);
                int rgbPixel = color2RGB(255, newPixel, newPixel, newPixel);
                grayBufferedImage.setRGB(i, j, rgbPixel);
            }
        }
        return grayBufferedImage;
    }

    /**
     * 将颜色转为rgb颜色
     *
     * @return
     * @Author Deal
     * @Date 2022/1/1 23:43
     */
    private static int color2RGB(int alpha, int red, int green, int blue) {
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;
    }

    /**
     * 图片二值化
     *
     * @Author Deal
     * @Date 2022/1/1 23:54
     */
    public static BufferedImage binaryImage(BufferedImage bufferedImage, int threshold) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int black = new Color(0, 0, 0).getRGB();
        int white = new Color(255, 255, 255).getRGB();
        BufferedImage binaryBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufferedImage.getRGB(i, j);
                int gray = rgb & 0xff;
                //如果大于阈值就是黑色 反之是白色
                if (gray > threshold) {
                    binaryBufferedImage.setRGB(i, j, black);
                } else {
                    binaryBufferedImage.setRGB(i, j, white);
                }
            }
        }
        return binaryBufferedImage;
    }
}
