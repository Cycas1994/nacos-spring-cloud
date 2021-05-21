package com.cycas.commonutil.message;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;

@Service
public class JunkMessage {

    private static final Logger logger = LoggerFactory.getLogger(JunkMessage.class);

    public static void main(String[] args) throws IOException {

        String str = "18045622285";
        String content = "验证码，发送成功";
        JunkMessage junkMessage = new JunkMessage();
        junkMessage.sendJunkMessage(str, content);

    }

    public Integer sendJunkMessage(String phone, String content) {

        try {
            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
            post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");
            NameValuePair[] data = {
                    // 用户名
                    new NameValuePair("Uid", "cycas"),
                    // 密钥
                    new NameValuePair("Key", "d41d8cd98f00b204e980"),
                    // 手机号码
                    new NameValuePair("smsMob", phone),
                    // 消息内容
                    new NameValuePair("smsText", content)
            };
            post.setRequestBody(data);
            client.executeMethod(post);
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            logger.info("statusCode:{}", statusCode);
            for (Header h : headers) {
                logger.info(h.toString());
            }
            String result = new String(post.getResponseBodyAsString().getBytes(Charset.forName("gbk")));
            logger.info("result:{}", result);
            post.releaseConnection();
            return Integer.parseInt(result) > 0 ? Integer.parseInt(result) : -1;
        } catch (IOException e) {
            logger.error("sendJunkMessage error!", e);
            return -1;
        }
    }

}
