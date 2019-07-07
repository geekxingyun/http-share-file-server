package com.xingyun.httpsharefileserver.controller.page;

import com.xingyun.httpsharefileserver.properties.SmartFtpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FtpPageController {
    @Autowired
    SmartFtpProperties smartFtpProperties;

    @GetMapping(value = "/toFtpPage")
    public String toFtpFileListPage(Model model){

        model.addAttribute("shareFileFolder",smartFtpProperties.getFtpShareFolder());
        return "ftp";
    }
}
