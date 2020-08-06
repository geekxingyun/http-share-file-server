package com.xingyun.httpsharefileserver.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class HomeController {

    @ApiIgnore
    @GetMapping(value = "/api.do")
    public String homePage(){
        return "Welcome to use Ftp Share File Server";
    }
}

