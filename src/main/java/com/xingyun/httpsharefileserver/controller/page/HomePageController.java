package com.xingyun.httpsharefileserver.controller.page;

import com.xingyun.httpsharefileserver.properties.SmartFtpProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Controller
public class HomePageController {

    @Autowired
    SmartFtpProperties smartFtpProperties;

    @Autowired
    ServerProperties serverProperties;

    @GetMapping(value = "/")
    public String indexPage(Model model){

        File ftpShareFolderFile=new File(smartFtpProperties.getFtpShareFolder());
        if(!ftpShareFolderFile.exists()){
            ftpShareFolderFile.mkdirs();
        }
        String hostAddress=null;
        try {
            hostAddress=InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            log.error("获取本地IP出错:{}",e.getMessage());
        }
        StringBuffer sb=new StringBuffer();
        sb.append("http://");
        sb.append(hostAddress);
        sb.append(":");
        sb.append(serverProperties.getPort());
        model.addAttribute("hostAddress", sb.toString());
        return "index";
    }
}
