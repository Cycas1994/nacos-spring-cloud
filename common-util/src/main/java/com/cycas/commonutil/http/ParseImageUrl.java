package com.cycas.commonutil.http;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class ParseImageUrl {

    private static final Logger logger = LoggerFactory.getLogger(ParseImageUrl.class);

    public static void main(String[] args) {

        HttpURLConnection conn = null;
        try {
            String pictureUrl = "https://probe.bjmantis.net/hcFile/2021/05/3401/png/下载_1620891889046.png";
            String originalFilename = pictureUrl.substring(pictureUrl.lastIndexOf("/") + 1);
            logger.info("originalFilename:{}", originalFilename);
            String fileName = originalFilename.substring(0,originalFilename.lastIndexOf("."))+"_"+System.currentTimeMillis();
            String suffix=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            if (StringUtils.isBlank(suffix)){
                throw new RuntimeException("该文件后缀异常!");
            }
            if (StringUtils.isBlank(suffix)) {
                throw new RuntimeException("图片格式不正确!");
            }
            URL url = new URL(pictureUrl);
            // 打开链接
            conn = (HttpURLConnection) url.openConnection();
            // 设置请求方式
            conn.setRequestMethod("GET");
            // 响应超时时间
            conn.setConnectTimeout(5 * 1000);
            // 获取输入流
            InputStream inputStream = conn.getInputStream();
            // 大小校验
            int fileLength = inputStream.available();
            logger.info("fileLength:{}", fileLength);
            if (fileLength > 5 * 1024 * 2014) {
                throw new RuntimeException("文件大小超过5M!");
            }
            // 图片字节数据
            byte[] buffer = readAndCloseInputStream(inputStream);
            // 保存
            String filePath = "/Users/admin/Documents/";
            File imageFile = new File(filePath + fileName + "." + suffix);
            FileUtils.writeByteArrayToFile(imageFile, buffer);
            logger.info("文件下载完成:{}", pictureUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    private static byte[] readAndCloseInputStream(InputStream inputStream) {

        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new BufferedInputStream(inputStream);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        } catch (IOException e) {
            logger.error("readInputStream error!", e);
            throw new RuntimeException("");
        } finally {
            if (Objects.nonNull(in)) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("文件输入流关闭失败！");
                }
            }
            if (Objects.nonNull(out)) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("文件输出流关闭失败！");
                }
            }

        }

    }
}
