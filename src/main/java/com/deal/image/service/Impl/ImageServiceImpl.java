package com.deal.image.service.Impl;

import com.deal.image.service.ImageService;
import com.deal.image.utils.ImageUtil;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author Deal
 * Date 2021/12/29 22:04
 **/
@Service
public class ImageServiceImpl implements ImageService {
    /**
     * 图像灰度化处理
     *
     * @Author Deal
     * @Date 2022/1/1 23:08
     */
    @Override
    public String img2Gray(MultipartFile file, double grayR, double grayG, double grayB, String resultPath) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        BufferedImage grayImage = ImageUtil.color2Gray(bufferedImage, grayR, grayG, grayB);
        FileOutputStream fos = new FileOutputStream(resultPath + "gray_" + file.getOriginalFilename());
        ImageIO.write(grayImage, "jpg", fos);
        fos.close();
        return "OK!";
    }

    /**
     * 图片二值化处理
     *
     * @Author Deal
     * @Date 2022/1/2 0:07
     */
    @Override
    public String img2Binary(MultipartFile file, double grayR, double grayG, double grayB, int threshold, String resultPath) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        BufferedImage grayImage = ImageUtil.color2Gray(bufferedImage, grayR, grayG, grayB);
        BufferedImage binaryImage = ImageUtil.binaryImage(grayImage, threshold);
        FileOutputStream fos = new FileOutputStream(resultPath + "binary_" + file.getOriginalFilename());
        ImageIO.write(binaryImage, "jpg", fos);
        fos.close();
        return "OK!";
    }

    /**
     * 图片识别
     *
     * @Date 2022/1/2 12:48
     * @Author Deal
     */
    @Override
    public String imgocr(MultipartFile file) throws IOException, TesseractException {
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        /*//灰度化
        BufferedImage grayImage = ImageUtil.RGB2Gray(bufferedImage,0.3,0.59,0.11);
        //二值化
        BufferedImage binaryImage = ImageUtil.binaryImage(grayImage, 150);*/
        //获取ocr数据
        return ImageUtil.getOcr(bufferedImage);
    }
}
