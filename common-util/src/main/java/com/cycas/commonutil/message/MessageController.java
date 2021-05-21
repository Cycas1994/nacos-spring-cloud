package com.cycas.commonutil.message;

import com.cycas.commonutil.message.dto.req.JunkMessageReq;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/message")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private JunkMessage junkMessage;

    @RequestMapping("/sendJunkMessage")
    public String sendJunkMessage(JunkMessageReq req) {

        int totalCnt = 0;
        try {
            if (StringUtils.isBlank(req.getPhone())) {
                return "手机号不能为空";
            }
            if (StringUtils.isBlank(req.getContent())) {
                return "内容不能为空";
            }
            if (Objects.isNull(req.getTime())) {
                req.setTime(1);
            }
            for (int i = 0; i < req.getTime(); i++) {
                int tempCnt = junkMessage.sendJunkMessage(req.getPhone(), req.getContent());
                if (tempCnt > 0) {
                    totalCnt += tempCnt;
                }
            }
            return String.format("成功条数：%s", totalCnt);
        } catch (Exception e) {
            logger.error("sendJunkMessage error!", e);
            return String.format("成功条数：%s", totalCnt);
        }
    }
}
