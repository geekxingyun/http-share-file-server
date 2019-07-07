package com.xingyun.httpsharefileserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class AppResponse {
    private Integer resultCode;
    private String resultMessage;
    private Object resultData;
}
