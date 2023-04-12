package com.emdata.common.util;


import cn.hutool.core.io.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

/**
 * 图片压缩工具
 */
public class ImageCompressUtil {

    /**
     * 流方式输入输出图片压缩
     *
     * @param src     文件输入流（单个）
     * @param target  压缩后文件输出流（单个）
     * @param quality 压缩质量
     * @throws IOException
     */
    public static void compress(InputStream src, OutputStream target, float quality) throws IOException {
        Thumbnails.of(src).scale(1)
                .outputQuality(quality).toOutputStream(target);
    }


    /**
     * 流方式输入输出图片压缩
     *
     * @param src     文件输入流（单个）
     * @param target  压缩后文件输出流（单个）
     * @param scale   压缩后规模
     * @param quality 压缩质量
     * @throws IOException
     */
    public static void compress(InputStream src, OutputStream target, float scale, float quality) throws IOException {
        Thumbnails.of(src).scale(scale)
                .outputQuality(quality).toOutputStream(target);
    }


    /**
     * 流方式输入输出图片压缩
     *
     * @param src     文件输入流（单个）
     * @param target  压缩后文件输出流（单个）
     * @param quality 压缩质量
     * @throws IOException
     */
    public static void compress(InputStream src, OutputStream target, float quality, String format) throws IOException {
        Thumbnails.of(src).scale(1)
                //输出格式
                .outputFormat(format)
                .outputQuality(quality).toOutputStream(target);
    }

    /**
     * 压缩并重置大小(流式)
     *
     * @param src
     * @param target
     * @param quality
     * @param width
     * @param height
     * @throws IOException
     */
    public static void compressAndsize(InputStream src, OutputStream target, float quality, int width, int height) throws IOException {
        Thumbnails.of(src).size(width, height).keepAspectRatio(false).outputQuality(quality).toOutputStream(target);
    }

    /**
     * @param src
     * @param target
     * @param quality
     * @param width
     * @param height
     * @param format
     * @throws IOException
     */
    public static void compressAndsize(InputStream src, OutputStream target, float quality, int width, int height, String format) throws IOException {
        Thumbnails.of(src).size(width, height).keepAspectRatio(false)
                .outputFormat(format)//输出格式
                .outputQuality(quality).toOutputStream(target);
    }

    /**
     * 根据宽比例压缩(流式)
     *
     * @param src
     * @param target
     * @param quality
     * @param width
     * @throws IOException
     */
    public static void compressAndsizeWidth(InputStream src, OutputStream target, float quality, int width) throws IOException {
        Thumbnails.of(src).width(width).keepAspectRatio(true).outputQuality(quality).toOutputStream(target);
    }

    /**
     * 根据宽比例压缩(流式)
     *
     * @param src
     * @param target
     * @param quality
     * @param width
     * @throws IOException
     */
    public static void compressAndsizeWidth(InputStream src, OutputStream target, float quality, int width, String format) throws IOException {
        Thumbnails.of(src).width(width).keepAspectRatio(true)
                .outputFormat(format)//输出格式
                .outputQuality(quality).toOutputStream(target);
    }


    /**
     * 根据宽比例压缩(流式)
     *
     * @param src
     * @param target
     * @param quality
     * @param height
     * @throws IOException
     */
    public static void compressAndsizeHeight(InputStream src, OutputStream target, float quality, int height) throws IOException {
        Thumbnails.of(src).height(height).keepAspectRatio(true).outputQuality(quality).toOutputStream(target);
    }

    /**
     * 根据宽比例压缩(流式)
     *
     * @param src
     * @param target
     * @param quality
     * @param height
     * @throws IOException
     */
    public static void compressAndsizeHeight(InputStream src, OutputStream target, float quality, int height, String format) throws IOException {
        Thumbnails.of(src).height(height).keepAspectRatio(true)
                .outputFormat(format)//输出格式
                .outputQuality(quality).toOutputStream(target);
    }

    /**
     * 压缩并添加图片水印(流式)
     *
     * @param src      原图
     * @param target   输出图
     * @param mark     水印图片
     * @param position 水印位置
     * @param opacity  水印透明度 0.0f-1.0f
     * @param quality  图片质量 0-1
     * @throws IOException
     */
    public static void compressAndWatermark(InputStream src, OutputStream target, InputStream mark, Positions position, float opacity, float quality) throws IOException {
        Thumbnails.of(src)
                .scale(1)
                .watermark(position, ImageIO.read(mark), opacity)
                .outputQuality(quality)
                .toOutputStream(target);
    }

    /**
     * 压缩并添加图片水印(流式)
     *
     * @param src      原图
     * @param target   输出图
     * @param mark     水印图片
     * @param position 水印位置
     * @param opacity  水印透明度 0.0f-1.0f
     * @param quality  图片质量 0-1
     * @throws IOException
     */
    public static void compressAndWatermark(InputStream src, OutputStream target, InputStream mark, Positions position, float opacity, float quality, String format) throws IOException {
        Thumbnails.of(src).scale(1)
                .watermark(position, ImageIO.read(mark), opacity)
                .outputFormat(format)//输出格式
                .outputQuality(quality).toOutputStream(target);
    }

    /*public static void main(String[] args) {
        try {
            compress("D:\\workplace\\IMG_20181024_105224.jpg", "D:\\workplace\\IMG_20181024_105224-zp.jpg", 0.8f);
//            compressAndFormat("D:\\workplace\\IMG_20181024_105224.jpg", "D:\\workplace\\IMG_20181024_105224-zp.png", "png", 0.8f);
//            compressAndsize("D:\\workplace\\IMG_20181024_105224.jpg", "D:\\workplace\\IMG_20181024_105224-zp-size.jpg", 0.8f, 1200, 400);
            FileInputStream src = new FileInputStream(new File("D:\\test.jpg"));

            InputStream icon = new FileInputStream(new File("d:\\icon.png"));

            OutputStream target = new FileOutputStream(new File("D:\\test-size.jpg"));

            compressAndsize(src, target, 0.9f, 484, 300, "jpg");

            compressAndWatermark(src, target, icon, Positions.BOTTOM_RIGHT, 1, 0.8f);
            src.close();
            icon.close();
            target.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * url下载压缩图
     * jdk1.8可以执行
     * jdk1.4无法执行
     *
     * @param file
     * @param urlstr
     * @return
     */
    public static boolean downloadCompressedPicture(File file, String urlstr) {
        URL url = null;
        try {
            url = new URL(urlstr);
            //1.获取url的输入流 dataInputStream
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            //2.加一层BufferedInputStream
            BufferedInputStream bufferedInputStream = new BufferedInputStream(dataInputStream);
            //3.构造原始图片流 preImage
            BufferedImage preImage = ImageIO.read(bufferedInputStream);
            //4.获得原始图片的长宽 width/height
            int width = preImage.getWidth();
            int height = preImage.getHeight();
            //5.构造压缩后的图片流 image 长宽各为原来的1/2
            BufferedImage image = new BufferedImage(width / 2, height / 2, BufferedImage.TYPE_INT_RGB);
            //6.给image创建Graphic ,在Graphic上绘制压缩后的图片
            Graphics graphic = image.createGraphics();
            graphic.drawImage(preImage, 0, 0, width / 2, height / 2, null);
            //7.为file生成对应的文件输出流
            //将image传给输出流
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            //8.将image写入到file中
            ImageIO.write(image, "bmp", bufferedOutputStream);
            //9.关闭输入输出流
            bufferedInputStream.close();
            bufferedOutputStream.close();

            return true;
        } catch (IOException e) {
            System.out.println(e);
        }

        return false;
    }

    public static void main(String[] args) {
        try {


//            String str = HttpClientUtil.downloadToBase64("http://10.28.98.10:8084/650049905909301257/2019/08/12/08/130602000000014514/08390663829867902.jpg?fid=541415-4BE0A00036-36AD17A-7DC1B", true, 500);

            String str = HttpClientUtil.downloadToBase64("http://10.29.00.11/ivhs/violation//violation/bdheihe/f6fd66b3-8d5f-4eca-b73c-7be1dd1da03f_2.jpg", true, 500);

            cn.hutool.core.codec.Base64.decodeToFile(str, FileUtil.newFile("1.jpg"));
/*

            URL url = new URL("http://seopic.699pic.com/photo/40163/5826.jpg_wh1200.jpg");

            //打开链接HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            conn.connect();

            int contentLength = conn.getContentLength();

            System.out.println((contentLength / 1024));

            // 创建输出流
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            compress(conn.getInputStream(),output,0.7f);

//            Thumbnails.of(conn.getInputStream()).scale(1).outputQuality(0.6f).toOutputStream(output);

            String str = Base64.encodeBase64String(output.toByteArray());

            System.out.println(str);

            cn.hutool.core.codec.Base64.decodeToFile(str,FileUtil.newFile("1.jpg"));*/

         /*   InputStream stream = URLUtil.getStream(URLUtil.url("http://seopic.699pic.com/photo/40163/5826.jpg_wh1200.jpg"));

            int available = stream.available();

            String s = FileUtil.readableFileSize(available);

            System.out.println(s);

            // 创建输出流
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            Thumbnails.of(stream).scale(1).outputQuality(0.6f).toOutputStream(output);

            String str = Base64.encodeBase64String(output.toByteArray());

            System.out.println(str);

            cn.hutool.core.codec.Base64.decodeToFile(str,FileUtil.newFile("1.jpg"));*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

