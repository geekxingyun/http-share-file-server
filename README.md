# Http-Share-File-Server

基于Spring Boot 开发的一个类似FTP文件服务器系统，也可以叫前置机。

## 关于前置机


# 技术清单

```
Spring Boot 2.1.6 + Swagger2+ H2  + Web + actuator+thymeleaf + lombok+ logback
```

# 项目描述

基于Spring Boot 开发的一个类似FTP文件服务器,实现功能如下：

1. 支持文件上传下载

- 关于文件下载
 
 默认监控当前机器C:\ftp_share_folder 的文件下的所有文件变化,只要刷新下页面，就可以获取文件下载列表

- 关于文件上传

 默认上传到C:\ftp_share_folder 根目录下
 
- 关于文件上传下载限制
 
 最大默认支持1024GB,已解决tomcat内嵌容器大文件上传超时问题
 
 - 修改监控文件夹
 
 修改监控文件夹很简单只需要修改application-dev.properties 下的 com.xingyun.ftp.ftp-share-folder=C:/ftp_share_folder 即可
 
 
3. 长文本持久化支持以实现类似剪切板功能

 基于H2内嵌文本数据库实现,最大支持999999999个字符的保存和读取



