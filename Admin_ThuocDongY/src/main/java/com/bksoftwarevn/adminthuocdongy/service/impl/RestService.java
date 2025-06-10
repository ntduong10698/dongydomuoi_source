package com.bksoftwarevn.adminthuocdongy.service.impl;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.entities.RestBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class RestService {

    @Autowired
    private RestTemplate template;

    @Value("${server-url}")
    private String SERVER_URL;

    public Object callGet(RestBuilder rest) {
        StringBuilder url = new StringBuilder(SERVER_URL + rest.getService() + rest.getUri() + "?");
        rest.getParams().forEach((key, value) -> url.append(key).append("=").append(value).append("&"));
        System.out.println(url.toString());
        try {
            JsonResult data = template.getForObject(url.toString(), JsonResult.class);
            if (data.getData() == null) return null;
            return data.getData();
        } catch (Exception ex) {
            log.error("rest call " + url + " err {0}", ex);
            throw ex;
        }
    }

    public List<Object> callGetList(RestBuilder rest) {
        StringBuilder url = new StringBuilder(SERVER_URL + rest.getService() + rest.getUri() + "?");
        rest.getParams().forEach((key, value) -> url.append(key).append("=").append(value).append("&"));
        System.out.println(url.toString());
        try {
            JsonResult data = template.getForObject(url.toString(), JsonResult.class);
            if (data.getData() == null) return null;
            return (List<Object>) data.getData();
        } catch (Exception ex) {
            log.error("rest call " + url + " err {0}", ex);
            throw ex;
        }
    }

    public void callPut(Object o, RestBuilder rest) {
        StringBuilder url = new StringBuilder(SERVER_URL + rest.getService() + rest.getUri() + "?");
        rest.getParams().forEach((key, value) -> url.append(key).append("=").append(value).append("&"));
        System.out.println(url.toString());
        try {
            template.put(url.toString(), o);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void callPost(Object o, RestBuilder rest) {
        StringBuilder url = new StringBuilder(SERVER_URL + rest.getService() + rest.getUri() + "?");
        rest.getParams().forEach((key, value) -> url.append(key).append("=").append(value).append("&"));
        System.out.println(url.toString());
        try {
            template.postForObject(url.toString(), o, JsonResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
