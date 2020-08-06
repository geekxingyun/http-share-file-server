package com.xingyun.httpsharefileserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Component
@Entity
public class TempMessage {
    @Id
    @Column(name = "MESSAGE_ID")
    private Long messageId;
    @Column(name = "MESSAGE_CONTENT",length = 999999999)
    private String messageContent;
}
