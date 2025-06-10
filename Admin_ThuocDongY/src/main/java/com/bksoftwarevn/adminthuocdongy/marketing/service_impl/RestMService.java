package com.bksoftwarevn.adminthuocdongy.marketing.service_impl;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.RestBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestMService {

    @Autowired
    private RestTemplate template;

    @Value("${server-url}")
    private String SERVER_URL;

    public Object callGet(RestBuilder rest) {
        StringBuilder url = new StringBuilder(SERVER_URL + rest.getService() + rest.getUri());
        if (rest.getParams().size() > 0) {
            url.append("&");
            rest.getParams().forEach((key, value) -> url.append(key).append("=").append(value).append("&"));
        }
        log.info("call GET api: " + url.toString());
        try {
            JsonResult data = template.getForObject(url.toString(), JsonResult.class);
            if (data.getData() == null) return null;
            log.info("call GET api: " + url.toString() + " success");
            return data.getData();
        } catch (Exception ex) {
            log.error("rest call " + url + " err {0}", ex);
            throw ex;
        }
    }
}
