package com.xingyun.httpsharefileserver.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Slf4j
public final class DownloadFileUtils {

    public final static Boolean downloadFile(String downloadFilePath, String downloadFileName, HttpServletResponse response){
        //配置文件下载
        try {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(downloadFileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        // 实现文件下载
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(downloadFilePath);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer); while (i != -1) {
                os.write(buffer, 0, i); i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("Download the file failed!：{}",e.getMessage());
            return false;
        }finally {
            //关闭流资源
            if (bis != null) {
                try {
                    bis.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    log.error("关闭bis出错:{}",e.getMessage());
                    return false;
                }
            }
            if (fis != null) {
                try { fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("关闭fis出错:{}",e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }
}
