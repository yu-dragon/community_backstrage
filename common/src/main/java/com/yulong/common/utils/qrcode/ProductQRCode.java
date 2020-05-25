package com.yulong.common.utils.qrcode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * created by 赵玉龙
 * on 2020/5/24-23:50
 */
public class ProductQRCode {
    //生成二维码的类库
    private static MultiFormatWriter mutiWriter = new MultiFormatWriter();

    /**
     * 生成一个彩色二维码
     * @param content 二维码内容
     * @param width 宽度
     * @param height 高度
     * @param path 二维码生成路径
     * @return
     */
    public static String encode(String fileName, String content, int width, int height,String path, Color color) {
        try {
//            String fileName = UUID.randomUUID()+".png";
            File file = new File(path+"/"+fileName);
            ImageIO.write(genBarcode(content, width, height, color),"png", file);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成一个二维码
     * @param content 内容
     * @param width 宽度
     * @param height 高度
     * @return
     * @throws WriterException
     * @throws IOException
     */
    private static BufferedImage genBarcode(String content, int width, int height, Color color) throws WriterException, IOException {
        Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
        hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 生成二维码
        BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,width, height, hint);
        int w = matrix.getWidth();
        int h = matrix.getHeight();
        int[] data = new int[w * h];
        boolean flag1=true;
        int stopx=0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if(matrix.get(x, y)){
                    if(flag1){
                        flag1=false;
                    }
                }else{
                    if(!flag1){
                        stopx =x;
                        break;
                    }
                }
            }
            if(!flag1) {
                break;
            }
        }

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if(matrix.get(x, y)){
                    if((x<stopx)&&(y<stopx)){
//                        Color color = new Color(231, 144, 56);
                        int colorInt = color.getRGB();
                        data[y * width + x] =colorInt;
                    }else{
                        int num1 = (int) (50 - (50.0 - 13.0)/ matrix.getHeight()* (y + 1));
                        int num2 = (int) (165 - (165.0 - 72.0) / matrix.getHeight()* (y + 1));
                        int num3 = (int) (162 - (162.0 - 107.0)/ matrix.getHeight() * (y + 1));
//                        Color color = new Color(num1, num2, num3);
//                        Color color = new Color(231, 144, 56);
                        int colorInt = color.getRGB();
                        data[y * w + x] = colorInt;
                    }
                }else{
                    //白色
                    data[y * w + x] = -1;
                }
            }
        }
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        image.getRaster().setDataElements(0, 0, width, height, data);
        return image;
    }
}
