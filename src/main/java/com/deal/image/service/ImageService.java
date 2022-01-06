package com.deal.image.service;


import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    /**
     * 图像灰度化处理
     *
     * @Author Deal
     * @Date 2022/1/1 23:08
     */
    String img2Gray(MultipartFile file, double grayR, double grayG, double grayB, String resultPath) throws IOException;

    /**
     * 图片二值化处理
     *
     * @Author Deal
     * @Date 2022/1/2 0:07
     */
    String img2Binary(MultipartFile file, double grayR, double grayG, double grayB, int threshold, String resultPath) throws IOException;

    /**
     * 图像识别
     *
     * @Date 2022/1/2 12:48
     * @Author Deal
     */
    String imgocr(MultipartFile file) throws IOException, TesseractException;

    /**
     * 抠图
     *
     * @Author Deal
     * @Date 2022/1/6 21:48
     */
    String circleImage(MultipartFile originalImage, MultipartFile binaryImage, String resultPath) throws IOException;
}
