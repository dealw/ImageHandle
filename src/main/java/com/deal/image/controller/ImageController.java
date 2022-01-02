package com.deal.image.controller;

import com.deal.image.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@Api(tags = "图片处理类")
@RequestMapping("/image")
public class ImageController {
    @Resource
    private ImageService imageService;

    @ApiOperation("图片灰度化")
    @PostMapping("img2gray")
    public String img2Gray(@ApiParam("图片") MultipartFile file,
                           @ApiParam("R倍数") @RequestParam(defaultValue = "0.3") double grayR,
                           @ApiParam("G倍数") @RequestParam(defaultValue = "0.59") double grayG,
                           @ApiParam("B倍数") @RequestParam(defaultValue = "0.01") double grayB,
                           @ApiParam("灰度化结果保存路径,以\\结尾")@RequestParam String resultPath) throws IOException {
        return imageService.img2Gray(file, grayR, grayG, grayB,resultPath);
    }

    @ApiOperation("图片二值化")
    @PostMapping("img2binary")
    public String img2Binary(@ApiParam("图片") MultipartFile file,
                             @ApiParam("R倍数") @RequestParam(defaultValue = "0.3") double grayR,
                             @ApiParam("G倍数") @RequestParam(defaultValue = "0.59") double grayG,
                             @ApiParam("B倍数") @RequestParam(defaultValue = "0.01") double grayB,
                             @ApiParam("阈值") @RequestParam(defaultValue = "125") int threshold,
                             @ApiParam("二值化结果保存路径,以\\结尾")@RequestParam String resultPath) throws IOException {
        return imageService.img2Binary(file, grayR, grayG, grayB,threshold,resultPath);
    }

    @ApiOperation("图像识别")
    @PostMapping("imgocr")
    public String imgocr(@ApiParam("图片") MultipartFile file) throws TesseractException, IOException {
        return imageService.imgocr(file);
    }
}
