package io.tiklab.teamwire.support.util;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.Result;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class HttpClientTeamWireUtil {
    public Result<Object> httpResposeJson(String url, Object params){
        Result result = new Result<>();

        // 设置请求头，指定 Content-Type 为 application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 设置请求体，这里使用一个包含 JSON 数据的字符串
        String jsonRequestBody = JSONObject.toJSONString(params);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        result =  JSONObject.parseObject(responseEntity.toString(),Result.class);

        return result;
    }
}
