package io.tiklab.kanass.common;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.message.message.model.SendMessageNotice;
import io.tiklab.message.message.service.SendMessageNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendMessageUtil {

    @Autowired
    private SendMessageNoticeService sendMessageNoticeService;

    @Value("${external.url}")
    String baseUrl;
    public void sendMessage(Map<String, Object> content){
        String jsonString = JSONObject.toJSONString(content);
        SendMessageNotice messageNotice = new SendMessageNotice();
        messageNotice.setId(content.get("noticeId").toString());

        messageNotice.setBaseUrl(baseUrl);
        messageNotice.setSiteData(jsonString);
        messageNotice.setEmailData(jsonString);
        messageNotice.setQywechatData(jsonString);
        messageNotice.setDingdingData(jsonString);
        messageNotice.setAppData(jsonString);

        messageNotice.setSendId(LoginContext.getLoginId());
        messageNotice.setLink(content.get("link").toString());
        messageNotice.setAction(content.get("action").toString());

        sendMessageNoticeService.sendMessageNotice(messageNotice);
    }

    public void sendDomainMessage(Map<String, Object> content, String domainId){
        String jsonString = JSONObject.toJSONString(content);
        SendMessageNotice messageNotice = new SendMessageNotice();
        messageNotice.setId(content.get("noticeId").toString());
        messageNotice.setDomainId(domainId);

        messageNotice.setBaseUrl(baseUrl);
        messageNotice.setSiteData(jsonString);
        messageNotice.setEmailData(jsonString);
        messageNotice.setQywechatData(jsonString);
        messageNotice.setDingdingData(jsonString);
        messageNotice.setAppData(jsonString);

        messageNotice.setSendId(LoginContext.getLoginId());
        messageNotice.setLink(content.get("link").toString());
        messageNotice.setAction(content.get("action").toString());

        sendMessageNoticeService.sendMessageNotice(messageNotice);
    }
}
