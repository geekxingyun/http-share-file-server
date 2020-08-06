package com.xingyun.httpsharefileserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Autowired
    ServerProperties serverProperties;

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        String hostAddress=null;
        try {
           hostAddress= InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        StringBuilder sb=new StringBuilder();
        sb.append("http://");
        sb.append(hostAddress);
        sb.append(":");
        sb.append(serverProperties.getPort());
        String termsOfServiceUrl=sb.toString();
        return new ApiInfoBuilder()
                .title("Http Share File App Restful API")
                .description("Http Share File App Restful API")
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact("星云","https://xingyun.blog.csdn.net/column/info/33374","fairy_xingyun@hotmail.com"))
                .version("1.0")
                .build();
    }

    /**
     * API分组一
     * **/
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("SwaggerGroupOneAPI")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xingyun.httpsharefileserver.controller.api"))
                .paths(PathSelectors.any())
                .build();
    }
}
