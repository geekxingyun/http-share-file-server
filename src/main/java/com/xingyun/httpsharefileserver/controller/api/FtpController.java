package com.xingyun.httpsharefileserver.controller.api;

import com.xingyun.httpsharefileserver.model.AppResponse;
import com.xingyun.httpsharefileserver.model.FtpFileInfo;
import com.xingyun.httpsharefileserver.properties.SmartFtpProperties;
import com.xingyun.httpsharefileserver.util.DownloadFileUtils;
import com.xingyun.httpsharefileserver.util.SmartFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class FtpController {

    @Autowired
    SmartFtpProperties smartFtpProperties;

    //遍历结果
    SmartFileUtils.FileTreeInfo shareFileList=null;

    //存储查询使用
    Map<String,String> shareFileMap=new HashMap<>();

    @Autowired
    AppResponse appResponse;

    @GetMapping(value = "/ftp.do")
    public AppResponse showFtpFileList(){

        //遍历结果
        shareFileList=SmartFileUtils.watchFolder(smartFtpProperties.getFtpShareFolder());

        List<FtpFileInfo> ftpFileInfoList=new ArrayList<>();
        FtpFileInfo ftpFileInfo;
        shareFileMap.clear();

        for (int i = 0; i <shareFileList.fileList.size() ; i++) {
            ftpFileInfo=new FtpFileInfo();
            ftpFileInfo.setFtpFileId(i+1);
            ftpFileInfo.setFtpFileName(shareFileList.fileList.get(i).getName());
            ftpFileInfo.setFtpFilePath(shareFileList.fileList.get(i).getAbsolutePath());
            ftpFileInfo.setOperation("Download");
            ftpFileInfoList.add(ftpFileInfo);
            shareFileMap.put(shareFileList.fileList.get(i).getName(),shareFileList.fileList.get(i).getAbsolutePath());
        }

        appResponse.setResultCode(200);
        appResponse.setResultMessage("Hello World");
        appResponse.setResultData(ftpFileInfoList);
        return appResponse;
    }

    @PostMapping(value = "/upload.do")
    public AppResponse uploadFile(@RequestParam(value = "ftpFileName")MultipartFile multipartFile){
        log.info(multipartFile.getOriginalFilename());
        StringBuffer sb=new StringBuffer();
        sb.append(smartFtpProperties.getFtpShareFolder());
        sb.append(File.separator);
        sb.append(multipartFile.getOriginalFilename());
        String uploadFilePath=sb.toString();
        File file=new File(uploadFilePath);
        try {
            multipartFile.transferTo(file);
            appResponse.setResultCode(200);
            appResponse.setResultMessage("上传成功");
            appResponse.setResultData(multipartFile.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("上传文件出错:{}",e.getMessage());
            appResponse.setResultCode(500);
            appResponse.setResultMessage("上传失败");
            appResponse.setResultData(e.getMessage());
        }
        return appResponse;
    }

    @GetMapping(value = "/download.do")
    public  void downloadFtpFileList(@RequestParam(value = "ftpFileName")String ftpFileName,HttpServletResponse response) throws Exception{

        String downloadFileName=ftpFileName;
        String downloadFilePath=shareFileMap.get(ftpFileName);

        log.info("下载文件名称:"+downloadFileName);
        log.info("下载文件路径:"+downloadFilePath);

        Boolean downloadResult=DownloadFileUtils.downloadFile(downloadFilePath,downloadFileName,response);
        if(downloadResult){
            appResponse.setResultCode(200);
            appResponse.setResultMessage("下载成功");
            appResponse.setResultData(downloadFileName);
        }else{
            appResponse.setResultCode(500);
            appResponse.setResultMessage("下载失败");
            appResponse.setResultData(downloadFileName);
        }
        log.info(appResponse.toString());
    }
}
