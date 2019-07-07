package com.xingyun.httpsharefileserver.controller.api;

import com.xingyun.httpsharefileserver.model.AppResponse;
import com.xingyun.httpsharefileserver.model.TempMessage;
import com.xingyun.httpsharefileserver.repository.TempMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(value = "/api")
@Controller
public class MessageController {

    @Autowired
    AppResponse appResponse;
    @Autowired
    TempMessageRepository tempMessageRepository;
    @Autowired
    TempMessage tempMessage;

    @ResponseBody
    @PostMapping(value = "/saveMessage.do")
    public AppResponse saveMessage(@RequestParam(value = "message")String message){
        log.info(message);
        tempMessage.setMessageId(1L);
        tempMessage.setMessageContent(message);
        tempMessageRepository.save(tempMessage);
        appResponse.setResultCode(200);
        appResponse.setResultMessage("保存成功");
        appResponse.setResultData(tempMessage);
        return appResponse;
    }

    @GetMapping(value = "/showMessage.do")
    public String showMessage(Model model){
        TempMessage readTempMessage;
        try {
            readTempMessage=tempMessageRepository.findByMessageId(1L);
            if(null==readTempMessage){
                appResponse.setResultCode(200);
                appResponse.setResultMessage("未查到数据");
                appResponse.setResultData(null);
            }else{
                log.info(readTempMessage.toString());
                model.addAttribute("tempMessage",readTempMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return "message";
    }

}
