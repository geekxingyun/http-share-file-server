package com.xingyun.httpsharefileserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FtpFileInfo {
    private Integer ftpFileId;
    private String ftpFileName;
    private String ftpFilePath;
    private String operation;
}
