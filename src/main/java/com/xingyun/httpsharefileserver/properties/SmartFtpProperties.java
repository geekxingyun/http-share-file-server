package com.xingyun.httpsharefileserver.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ToString
@ConfigurationProperties(prefix="com.xingyun.ftp")
public class SmartFtpProperties {
    private String ftpShareFolder;//注意"这里的变量名称不可以有下划线 否则会出错
}
