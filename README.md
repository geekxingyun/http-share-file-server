# Http-Share-File-Server

基于Spring Boot 开发的一个类似FTP文件服务器系统，也可以叫前置机。

## 关于前置机

>前置机这个概念一般在银行、券商、电信运营商那里用的比较多。这些地方都有很多后台核心处理系统，对外提供各种接口服务。如果我有某种业务接口需要跟他们的后台系统打交道，要从我们的外部网络访问他们的后台系统，这些单位是绝对不允许的。这个时候，他们要求你或者他们自己开发一个软件，运行在他们的内网，然后通过专线或硬件隔离技术将运行这个软件的计算机连接到你的外网系统上，那么运行这个软件的计算机，从功能上称呼为前置机。

### 前置机作用：

- 从网络和安全角度来看，它有隔离主机的作用（一种放在内网以外，分离内网外网的应用）保证外部的应用不能直接访问核心服务，比如银行的各类外部接口（电信代收费、银证通）。

- 从业务角度来看，前置机提供了业务渠道与核心服务的主机交流的一个桥梁。它一般起着管理和调度业务渠道发起的交易的作用，经过前置机的调用可以减轻核心后台服务器的负担，当然了它也有非核心业务的处理功能。

-  位于应用系统服务器端与客户端之间的独立处理机系统,担负数据格式转换、连接管理、业务流管理外围调度、外围处理，并把业务数据交后台应用服务系统处理等任务。 

- C/S概念中C和S是相对而言的,虽然多数是固定的，但是也是视指定而言.譬如银行的业务应用中，请求的发出就不可以是从后端应用服务器而来。以代理收费的例子来看，前置机就是一个应用网关。实际上在现在的应用中，由于有了前置机的存在，主机变得不可见。

- 政务内外网两端的业务系统需要数据交换，在各自业务系统前布置前置机，实现数据交换。

- 前置机是台物理机，部署前置交换系统。负责将需要交换过来或者交换的数据缓存到这台服务器中
      


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
 
 
2. 长文本持久化支持以实现类似剪切板功能

 基于H2内嵌文本数据库实现,最大支持999999999个字符的保存和读取



