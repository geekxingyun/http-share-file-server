# Http-Share-File-Server
基于Spring Boot 文件上传下载实现类似FTP功能

该项目使用技术清单

```
Spring Boot 2.1.6 + Swagger2+ H2  + Web + actuator+thymeleaf + lombok+ logback
```

实现功能如下：

默认监控 D:\ftp_share_folder 的文件下的所有文件变化，一旦文件夹下不为空，会加入文件列表以供下载

文件上传下载支持 最大默认支持1GB

使用H2数据库实现类似剪切板功能


